package org.budy.parser.nodes.expressions;

import org.budy.assembler.ast.VisitorAssembly;
import org.budy.assembler.nodes.Assembly;
import org.budy.parser.nodes.ast.Visitor;
import org.budy.parser.nodes.visitor.ir.VisitorTac;
import org.budy.tac.nodes.InstructionTac;

public class Unary extends Expr {
    private final String operator;
    private final Expr right;

    public Unary(String operator, Expr right) {
        this.operator = operator;
        this.right = right;
    }

    public String getOperator() {
        return operator;
    }

    public Expr getRight() {
        return right;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Assembly accept(VisitorAssembly visitor) {
        //return visitor.visit(this);
        return null;
    }

    @Override
    public InstructionTac.UnaryTac accept(VisitorTac visitorTac) {
        return visitorTac.visit(this);
    }
}
