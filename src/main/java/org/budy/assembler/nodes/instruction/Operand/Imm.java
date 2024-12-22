package org.budy.assembler.nodes.instruction.Operand;

import org.budy.assembler.pretty.AssemblerVisitor;

public class Imm extends Operand {
    private final int value;

    public Imm(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public <R> R accept(AssemblerVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
