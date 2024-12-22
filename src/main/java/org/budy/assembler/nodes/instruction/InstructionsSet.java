package org.budy.assembler.nodes.instruction;

import org.budy.assembler.nodes.Assembly;
import org.budy.assembler.pretty.AssemblerVisitor;

import java.util.List;

public class InstructionsSet extends Assembly {
    private final List<Instruction> instructions;

    public InstructionsSet(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    @Override
    public <R> R accept(AssemblerVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
