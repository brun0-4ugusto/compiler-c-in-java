package org.budy.assembler.ast;

import org.budy.assembler.nodes.AssemblyFunction;
import org.budy.assembler.nodes.AssemblyProgram;
import org.budy.assembler.nodes.IdentifierAssembly;
import org.budy.assembler.nodes.instruction.InstructionsSet;
import org.budy.assembler.nodes.instruction.Operand.Imm;
import org.budy.parser.nodes.CFunctionDefinition;
import org.budy.parser.nodes.Program;
import org.budy.parser.nodes.builtin.types.Identifier;
import org.budy.parser.nodes.expressions.Constant;
import org.budy.parser.nodes.statements.Return;

public interface VisitorAssembly {
    Imm visit(Constant constant);

    AssemblyProgram visit(Program program);

    InstructionsSet visit(Return aReturn);

    AssemblyFunction visit(CFunctionDefinition cFunctionDefinition);

    IdentifierAssembly visit(Identifier identifier);
}
