package org.budy.parser.nodes.statements;

import org.budy.assembler.ast.VisitorAssembly;
import org.budy.assembler.nodes.instruction.InstructionsSet;
import org.budy.parser.nodes.ast.AstNode;

public abstract class Stmt extends AstNode {
    @Override
    public InstructionsSet accept(VisitorAssembly visitor) {
        return acceptThis(visitor);
    }

    protected abstract InstructionsSet acceptThis(VisitorAssembly visitor);
}
