package org.budy.parser.nodes;

import org.budy.assembler.ast.VisitorAssembly;
import org.budy.assembler.nodes.AssemblyProgram;
import org.budy.parser.nodes.ast.AstNode;
import org.budy.parser.nodes.ast.Visitor;
import org.budy.parser.nodes.visitor.ir.VisitorTac;
import org.budy.tac.nodes.ProgramTac;

public final class Program extends AstNode {
    private final CFunctionDefinition function;

    public Program(CFunctionDefinition function) {
        this.function = function;
    }

    public CFunctionDefinition function() {
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

    @Override
    public ProgramTac accept(VisitorTac visitorTac) {
        return visitorTac.visit(this);
    }
}
