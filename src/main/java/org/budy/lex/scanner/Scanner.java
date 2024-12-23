package org.budy.lex.scanner;

import org.budy.lex.token.Keywords;
import org.budy.lex.token.Token;
import org.budy.lex.token.TokenType;

import java.util.LinkedList;
import java.util.List;

public class Scanner {
    private final List<Token> tokens = new LinkedList<>();
    private int startLexeme;
    private int advancedChar;
    private char[] source;

    public List<Token> scan(char[] rawSource) {
        initSource(rawSource);

        while (!isAtEnd()) {
            startLexeme = advancedChar;
            consumeChar();

            switch (source[startLexeme]) {
                case '(' -> addToken(generateToken(TokenType.OPEN_PAREN));
                case ')' -> addToken(generateToken(TokenType.CLOSE_PAREN));
                case '{' -> addToken(generateToken(TokenType.OPEN_BRACE));
                case '}' -> addToken(generateToken(TokenType.CLOSE_BRACE));
                case ';' -> addToken(generateToken(TokenType.SEMICOLON));
                case '~' -> addToken(generateToken(TokenType.BITWISE_COMPLEMENT));
                case '-' -> {
                    if (isAdvancedCharEquals('-')) {
                        addToken(generateToken(TokenType.DECREMENT));
                        continue;
                    }
                    addToken(generateToken(TokenType.NEGATION));
                }
                default -> {
                    if (isWhitespace()) {
                        continue;
                    }
                    if (isDigit(startLexeme)) {
                        isConstant();
                        continue;
                    }
                    if (isIdentifier()) {
                        if (isKeyword()) {
                            addToken(generateToken(TokenType.KEYWORD));
                            continue;
                        }
                        addToken(generateToken(TokenType.IDENTIFIER));
                        continue;
                    }
                }
            }


        }

        return tokens;
    }

    private boolean isAdvancedCharEquals(char c) {
        if (isAtEnd()) return false;

        if (c == source[advancedChar]) {
            consumeChar();
            return true;
        } else {
            return false;
        }
    }


    private boolean isKeyword() {
        int length = advancedChar - startLexeme;
        String lexeme = String.valueOf(source, startLexeme, length);
        return Keywords.getKeyword(lexeme).isPresent();
    }

    private boolean isIdentifier() {
        if (isAlpha(startLexeme)) {
            readLexeme();
            return true;
        } else {
            return false;
        }
    }

    private void readLexeme() {
        while (!isAtEnd()) {
            if (!isAlpha(advancedChar) && !isDigit(advancedChar)) {
                break;
            }
            consumeChar();
        }
    }

    private boolean isAlpha(int c) {
        return (source[c] >= 'a' && source[c] <= 'z') ||
                (source[c] >= 'A' && source[c] <= 'Z') ||
                source[c] == '_';
    }

    private boolean isAtEnd() {
        return advancedChar >= source.length;
    }

    private void isConstant() {
        if (isAtEnd()) {
            addToken(generateToken(TokenType.CONSTANT));
            return;
        }

        while (isDigit(advancedChar)) {
            consumeChar();
            if (isAtEnd()) break;
        }

        addToken(generateToken(TokenType.CONSTANT));
    }

    private boolean isDigit(int c) {
        return source[c] >= '0' && source[c] <= '9';
    }

    private boolean isWhitespace() {
        return source[startLexeme] == '\r' || source[startLexeme] == '\n' || source[startLexeme] == '\t';
    }

    private void addToken(Token token) {
        tokens.add(token);
    }

    private void initSource(char[] rawSource) {
        source = rawSource;
    }

    private Token generateToken(TokenType tokenType) {
        return new Token(startLexeme, advancedChar - 1, tokenType);
    }

    private void consumeChar() {
        ++advancedChar;
    }
}
