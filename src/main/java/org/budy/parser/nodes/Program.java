package org.budy.parser.nodes;

import org.budy.assembler.ast.VisitorAssembly;
import org.budy.assembler.nodes.AssemblyProgram;
import org.budy.parser.nodes.ast.AstNode;
import org.budy.parser.nodes.ast.Visitor;

public final class Program extends AstNode {
    private final CFunction function;

    public Program(CFunction function) {
        this.function = function;
    }

    public CFunction function() {
        return function;
    }


    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);

    }

    @Override
    public AssemblyProgram accept(VisitorAssembly visitor) {
        return visitor.visit(this);
    }
}
