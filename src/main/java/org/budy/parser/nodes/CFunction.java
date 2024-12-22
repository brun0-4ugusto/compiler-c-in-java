package org.budy.parser.nodes;

import org.budy.assembler.nodes.ast.VisitorAssembly;
import org.budy.assembler.nodes.AssemblyFunction;
import org.budy.parser.nodes.ast.AstNode;
import org.budy.parser.nodes.ast.Visitor;
import org.budy.parser.nodes.builtin.types.Identifier;
import org.budy.parser.nodes.statements.Stmt;

public final class CFunction extends AstNode {
    private final Identifier name;
    private final Stmt body;

    public CFunction(Identifier name, Stmt body) {
        this.name = name;
        this.body = body;
    }

    public Identifier name() {
        return name;
    }

    public Stmt body() {
        return body;
    }


    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public AssemblyFunction accept(VisitorAssembly visitor) {
        return visitor.visit(this);
    }
}
