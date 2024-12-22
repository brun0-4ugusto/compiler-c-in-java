package org.budy.assembler.nodes.instruction;

import org.budy.assembler.nodes.instruction.Operand.Operand;
import org.budy.assembler.pretty.AssemblerVisitor;

public class Mov extends Instruction {
    private final Operand source;
    private final Operand dest;

    public Mov(Operand source, Operand dest) {
        this.source = source;
        this.dest = dest;
    }

    public Operand getSource() {
        return source;
    }

    public Operand getDest() {
        return dest;
    }

    @Override
    public <R> R accept(AssemblerVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
