package org.budy.emission;

import org.budy.assembler.nodes.AssemblyFunction;
import org.budy.assembler.nodes.AssemblyProgram;
import org.budy.assembler.nodes.IdentifierAssembly;
import org.budy.assembler.nodes.instruction.InstructionsSet;
import org.budy.assembler.nodes.instruction.Mov;
import org.budy.assembler.nodes.instruction.Operand.Imm;
import org.budy.assembler.nodes.instruction.Operand.Register;
import org.budy.assembler.nodes.instruction.Ret;
import org.budy.assembler.pretty.AssemblerVisitor;

public class EmissionCodeVisitor implements AssemblerVisitor<String> {

    @Override
    public String visit(AssemblyFunction assemblyFunction) {
        StringBuilder stringBuilder = new StringBuilder();
        String name = assemblyFunction.name().accept(this);
        stringBuilder.append(".globl ")
                .append(name)
                .append("\n")
                .append(name)
                .append(":")
                .append("\n")
                .append(assemblyFunction.instructionList().accept(this))
                .append("\n");
        return stringBuilder.toString();
    }

    @Override
    public String visit(Ret ret) {
        return "ret";
    }

    @Override
    public String visit(IdentifierAssembly identifierAssembly) {
        return identifierAssembly.getName();
    }

    @Override
    public String visit(AssemblyProgram assemblyProgram) {
        return assemblyProgram.function().accept(this) +
                "\n\t.section .note.GNU-stack,\"\",@progbits\n";
    }

    @Override
    public String visit(Mov mov) {
        return "movl " +
                mov.getSource().accept(this) +
                ", " +
                mov.getDest().accept(this);
    }

    @Override
    public String visit(InstructionsSet instructionsSet) {
        StringBuilder stringBuilder = new StringBuilder();
        instructionsSet.getInstructions().forEach(instruction -> {
            stringBuilder.append(instruction.accept(this)).append("\n");
        });
        return stringBuilder.toString();
    }

    @Override
    public String visit(Imm imm) {
        return "$" + imm.getValue();
    }

    @Override
    public String visit(Register register) {
        return "%eax";
    }
}
