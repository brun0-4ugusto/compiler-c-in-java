package org.budy.assembler.nodes;

import org.budy.assembler.pretty.AssemblerVisitor;

public final class AssemblyProgram extends Assembly {
    private final AssemblyFunction function;

    public AssemblyProgram(AssemblyFunction function) {
        this.function = function;
    }

    public AssemblyFunction function() {
        return function;
    }

    @Override
    public <R> R accept(AssemblerVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
