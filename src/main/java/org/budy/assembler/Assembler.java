package org.budy.assembler;

import org.budy.assembler.nodes.AssemblyProgram;
import org.budy.assembler.ast.AstVisitorAssembler;
import org.budy.parser.nodes.Program;

/**
 * A ideia aqui é similar ao parser. Mas vou gerar a partir de uma AST de C
 * uma AST de Assembly
 * <p>
 * O ASDL para isso pode ser representado assim:
 *
 * <code><p>
 * program = Program(function_definition)<p>
 * function_definition=Function(identifier name, instruction* instructions)<p>
 * instruction = Mov(operand src, operand dst) | Ret
 * operand = Imm(int) | Register
 * </code>
 * <p>
 * (* representa uma lista)
 */
public final class Assembler {
    private final AstVisitorAssembler visitorAssembler = new AstVisitorAssembler();

    public AssemblyProgram assemble(Program program) {
        return program.accept(visitorAssembler);
    }

}

