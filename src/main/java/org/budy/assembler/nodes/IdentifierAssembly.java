package org.budy.assembler.nodes;

import org.budy.assembler.pretty.AssemblerVisitor;

public class IdentifierAssembly extends Assembly {
    private final String name;

    public IdentifierAssembly(String name) {
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
