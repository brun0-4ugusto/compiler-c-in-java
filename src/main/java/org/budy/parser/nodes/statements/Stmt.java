package org.budy.parser.nodes.statements;

import org.budy.assembler.ast.VisitorAssembly;
import org.budy.assembler.nodes.instruction.InstructionsSet;
import org.budy.parser.nodes.ast.AstNode;
import org.budy.parser.nodes.visitor.ir.VisitorTac;
import org.budy.tac.nodes.InstructionsList;

public abstract class Stmt extends AstNode {
    @Override
    public InstructionsSet accept(VisitorAssembly visitor) {
        return acceptThis(visitor);
    }

    @Override
    public InstructionsList accept(VisitorTac visitorTac) {
        return acceptThis(visitorTac);
    }

    protected abstract InstructionsSet acceptThis(VisitorAssembly visitor);

    protected abstract InstructionsList acceptThis(VisitorTac visitor);
}
