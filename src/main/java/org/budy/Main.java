package org.budy;

import org.budy.assembler.Assembler;
import org.budy.assembler.nodes.AssemblyProgram;
import org.budy.assembler.pretty.VisitorPrettier;
import org.budy.emission.EmissionCode;
import org.budy.lex.scanner.Scanner;
import org.budy.lex.token.Token;
import org.budy.parser.Parser;
import org.budy.parser.nodes.Program;
import org.budy.parser.printer.AstNodePrinter;
import org.budy.reader.ReaderCFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    private static final Scanner scanner = new Scanner();
    private static final Parser parser = new Parser();
    private static final Assembler assembler = new Assembler();
    private static final EmissionCode emissionCode = new EmissionCode();

    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                throw new RuntimeException("É necessário a operação e o path do código");
            }

            Path path = Path.of(args[1]);
            char[] rawSource = ReaderCFile.read(path);

            switch (args[0]) {
                case "--lex" -> {
                    lex(rawSource);
                }
                case "--parse" -> {
                    List<Token> tokens = lex(rawSource);
                    parse(tokens, rawSource);
                }
                case "--codegen" -> {
                    List<Token> tokens = lex(rawSource);
                    Program abstractSyntaxTree = parse(tokens, rawSource);
                    assemblyGenAst(abstractSyntaxTree);
                }

                case "--complete" -> {
                    List<Token> tokens = lex(rawSource);
                    Program abstractSyntaxTree = parse(tokens, rawSource);
                    AssemblyProgram assemblyProgram = assemblyGenAst(abstractSyntaxTree);
                    emissionCode.createAssemblyFile(assemblyProgram, path);
                }

                default -> {
                    throw new RuntimeException("Operação nao suportada: " + args[0]);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }

    private static AssemblyProgram assemblyGenAst(Program abstractSyntaxTree) {
        System.out.println("\n/////////////////////////");
        System.out.println("Assembly Gen");
        System.out.println("/////////////////////////");
        AssemblyProgram assemblyProgram = assembler.assemble(abstractSyntaxTree);
        readAssembly(assemblyProgram);
        System.out.println("/////////////////////////");
        return assemblyProgram;
    }

    private static Program parse(List<Token> tokens, char[] rawSource) {
        System.out.println("\n/////////////////////////");
        System.out.println("Parser");
        System.out.println("/////////////////////////");
        Program abstractSyntaxTree = parser.parse(tokens, rawSource);
        readAST(abstractSyntaxTree);
        return abstractSyntaxTree;
    }

    private static List<Token> lex(char[] rawSource) {
        List<Token> tokens = scanner.scan(rawSource);
        readTokens(tokens, rawSource);
        return tokens;
    }

    private static void readAssembly(AssemblyProgram assemblyProgram) {
        VisitorPrettier visitorPrettier = new VisitorPrettier();
        String accept = assemblyProgram.accept(visitorPrettier);
        System.out.println(accept);
    }

    private static void readAST(Program abstractSyntaxTree) {
        AstNodePrinter astNodePrinter = new AstNodePrinter();
        System.out.println(astNodePrinter.visit(abstractSyntaxTree));
    }

    private static void readTokens(List<Token> tokens, char[] rawSource) {
        tokens.forEach(
                token -> {
                    System.out.println("=================================");
                    System.out.println(token);
                    String lexeme = token.readLexemeFromToken(rawSource);
                    System.out.println(lexeme);
                    System.out.println("=================================");
                }
        );

    }
}