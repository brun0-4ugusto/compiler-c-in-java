package org.budy.assembler.nodes.instruction;

import org.budy.assembler.pretty.AssemblerVisitor;

public class Ret extends Instruction{
    @Override
    public <R> R accept(AssemblerVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
