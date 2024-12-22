package org.budy.assembler.pretty;

import org.budy.assembler.nodes.AssemblyFunction;
import org.budy.assembler.nodes.AssemblyProgram;
import org.budy.assembler.nodes.IdentifierAssembly;
import org.budy.assembler.nodes.instruction.InstructionsSet;
import org.budy.assembler.nodes.instruction.Mov;
import org.budy.assembler.nodes.instruction.Operand.Imm;
import org.budy.assembler.nodes.instruction.Operand.Register;
import org.budy.assembler.nodes.instruction.Ret;

public interface AssemblerVisitor<R> {
    R visit(AssemblyFunction assemblyFunction);

    R visit(Ret ret);

    R visit(IdentifierAssembly identifierAssembly);

    R visit(AssemblyProgram assemblyProgram);

    R visit(Mov mov);

    R visit(InstructionsSet instructionsSet);

    R visit(Imm imm);

    R visit(Register register);
}
