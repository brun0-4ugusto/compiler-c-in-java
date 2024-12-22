package org.budy.assembler.nodes;

import org.budy.assembler.pretty.AssemblerVisitor;

public abstract class Assembly {
    public abstract <R> R accept(AssemblerVisitor<R> visitor);
}
