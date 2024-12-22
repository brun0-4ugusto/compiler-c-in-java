package org.budy.assembler.pretty;

import org.budy.assembler.nodes.AssemblyFunction;
import org.budy.assembler.nodes.AssemblyProgram;
import org.budy.assembler.nodes.IdentifierAssembly;
import org.budy.assembler.nodes.instruction.InstructionsSet;
import org.budy.assembler.nodes.instruction.Mov;
import org.budy.assembler.nodes.instruction.Operand.Imm;
import org.budy.assembler.nodes.instruction.Operand.Register;
import org.budy.assembler.nodes.instruction.Ret;

public class VisitorPrettier implements AssemblerVisitor<String> {
    @Override
    public String visit(AssemblyFunction assemblyFunction) {
        return "AssemblyFunction(\n" +
                "name=" +
                assemblyFunction.name().accept(this) +
                "Instructions=" +
                assemblyFunction.instructionList().accept(this) +
                "\n)";
    }


    @Override
    public String visit(Ret ret) {
        return "Ret";
    }

    @Override
    public String visit(IdentifierAssembly identifierAssembly) {
        return identifierAssembly.getName();
    }

    @Override
    public String visit(AssemblyProgram assemblyProgram) {
        return "AssemblyProgram(\n" +
                assemblyProgram.function().accept(this) +
                "\n)";
    }

    @Override
    public String visit(Mov mov) {
        return "Mov(\n" +
                "Operand1=" +
                mov.getSource().accept(this) +
                "\n" +
                "Operand2=" +
                mov.getDest().accept(this) +
                "\n)";
    }

    @Override
    public String visit(InstructionsSet instructionsSet) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        instructionsSet.getInstructions()
                .forEach(instruction -> {
                    stringBuilder
                            .append(instruction.accept(this))
                            .append(",");
                });
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public String visit(Imm imm) {
        return "Imm=" + imm.getValue();
    }

    @Override
    public String visit(Register register) {
        return "Register=" + register.getName();
    }
}
