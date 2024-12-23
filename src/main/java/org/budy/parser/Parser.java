package org.budy.parser;

import org.budy.lex.token.Token;
import org.budy.lex.token.TokenType;
import org.budy.parser.nodes.CFunctionDefinition;
import org.budy.parser.nodes.Program;
import org.budy.parser.nodes.builtin.types.Identifier;
import org.budy.parser.nodes.expressions.Constant;
import org.budy.parser.nodes.expressions.Expr;
import org.budy.parser.nodes.statements.Return;
import org.budy.parser.nodes.statements.Stmt;

import java.util.List;

/**
 * <h1>ASDL</h1>
 * Seguindo o Abstract Syntax Description Language (ASDL)
 * Nos mostra como construir um tipo de Node
 * <p>
 * <h2>ASDL by example</h2>
 * <a href="https://www.cs.princeton.edu/~appel/papers/asdl97.pdf">exemplo</a>
 * <p>
 * An ASDL description consists of three fundamental
 * constructs: types, constructors, and productions.
 * A type is defined by productions that enumerate the constructors for that type
 * stmt = Compound(stmt, stmt) | Assign(identifier, expr)
 * expr = Id(identifier)
 * <p>
 * Objetivo: Descrever estruturas de dados usadas
 * em compiladores e interpretadores, como árvores sintáticas abstratas (ASTs).
 *
 * <h2>Formal Grammar</h2>
 * É preciso também uma forma de definir as regras da sintaxe (formal grammar)
 * para isso podemos usar a notação extended Backus-Naur form (EBNF).
 * Cada linha da gramatica é chamado de production rule.
 * Production rule define como cada a linguagem pode ser construida a partir de outros
 * production rules e tokens.
 * <p>
 * Exemplo
 * <code>&lt;statement&gt; ::= "return" &lt;exp&gt;</code> ";"
 */
public final class Parser {
    private int currentToken;
    private int lookAheadToken;
    private List<Token> tokens;
    private char[] rawSource;

    public Program parse(List<Token> tokens, char[] rawSource) {
        this.tokens = tokens;
        this.rawSource = rawSource;

        return program();

    }

    private Program program() {
        CFunctionDefinition function = generateFunction();
        return new Program(function);
    }

    private CFunctionDefinition generateFunction() {
        consumeExpected("int");
        Identifier identifier = identifier();
        consumeExpected("(");
        consumeExpected("void");
        consumeExpected(")");
        consumeExpected("{");
        Stmt body = statement();
        consumeExpected("}");
        return new CFunctionDefinition(identifier, body);
    }

    private Stmt statement() {
        consumeExpected("return");
        Expr expr = expression();
        consumeExpected(";");
        return new Return(expr);
    }

    private Expr expression() {
        return switch (tokens.get(currentToken).tokenType()) {
            case TokenType.CONSTANT -> Cint();
            default -> throw new IllegalStateException("Unexpected value: " + tokens.get(currentToken).tokenType());
        };
    }

    private Constant Cint() {
        Constant constant = new Constant(Integer.parseInt(readLexemeCurrentToken()));
        advanceCurrentToken();
        return constant;
    }

    private Identifier identifier() {
        if (!matchCurrentToken(TokenType.IDENTIFIER)) {
            throw new RuntimeException("Error bbbb");
        }
        Identifier identifier = new Identifier(readLexemeCurrentToken());
        advanceCurrentToken();
        return identifier;
    }

    private boolean matchCurrentToken(TokenType tokenType) {
        return match(tokenType, currentToken);
    }

    private boolean match(TokenType tokenType, int currentToken) {
        return tokens.get(currentToken).tokenType().equals(tokenType);
    }

    private void consumeExpected(String expected) {
        String lexeme = readLexemeCurrentToken();
        if (!lexeme.equals(expected)) {
            throw new RuntimeException("Error aaaa");
        }
        advanceCurrentToken();
    }

    private String readLexemeCurrentToken() {
        return readLexeme(currentToken);
    }

    private String readLexemeLookAheadToken() {
        return readLexeme(lookAheadToken);
    }

    private String readLexeme(int index) {
        return tokens.get(index).readLexemeFromToken(rawSource);
    }

    private void advanceCurrentToken() {
        currentToken++;
    }

    private boolean isAtEnd() {
        return currentToken >= tokens.size();
    }

}
