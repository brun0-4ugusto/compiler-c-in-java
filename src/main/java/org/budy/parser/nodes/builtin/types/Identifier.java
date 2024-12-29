package org.budy.parser.nodes.builtin.types;

import org.budy.assembler.ast.VisitorAssembly;
import org.budy.assembler.nodes.IdentifierAssembly;
import org.budy.parser.nodes.ast.AstNode;
import org.budy.parser.nodes.ast.Visitor;
import org.budy.parser.nodes.visitor.ir.VisitorTac;
import org.budy.tac.nodes.ValTac;

public final class Identifier extends AstNode {
    private final String name;

    public Identifier(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);

    }

    @Override
    public IdentifierAssembly accept(VisitorAssembly visitor) {
        return visitor.visit(this);
    }

    @Override
    public ValTac.Variable accept(VisitorTac visitorTac) {
        return visitorTac.visit(this);
    }
}
