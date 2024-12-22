package org.budy.assembler.nodes.instruction.Operand;

import org.budy.assembler.pretty.AssemblerVisitor;

public class Register extends Operand {
    private final String name;

    public Register(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public <R> R accept(AssemblerVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
