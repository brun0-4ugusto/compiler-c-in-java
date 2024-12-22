package org.budy.parser.nodes.expressions;

import org.budy.assembler.ast.VisitorAssembly;
import org.budy.assembler.nodes.instruction.Operand.Imm;
import org.budy.parser.nodes.ast.Visitor;

public class Constant extends Expr {
    private final int anInt;

    public Constant(int anInt) {
        this.anInt = anInt;
    }

    public int getAnInt() {
        return anInt;
    }


    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Imm accept(VisitorAssembly visitor) {
        return visitor.visit(this);
    }
}
