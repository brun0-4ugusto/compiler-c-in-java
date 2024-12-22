package org.budy.parser.nodes.statements;

import org.budy.assembler.nodes.ast.VisitorAssembly;
import org.budy.assembler.nodes.instruction.InstructionsSet;
import org.budy.parser.nodes.ast.Visitor;
import org.budy.parser.nodes.expressions.Expr;

public class Return extends Stmt {
    private final Expr expression;

    public Return(Expr expression) {
        this.expression = expression;
    }

    public Expr getExpression() {
        return expression;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    protected InstructionsSet acceptThis(VisitorAssembly visitor) {
        return visitor.visit(this);
    }
}
