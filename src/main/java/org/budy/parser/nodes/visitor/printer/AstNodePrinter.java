package org.budy.parser.nodes.visitor.printer;

import org.budy.parser.nodes.CFunctionDefinition;
import org.budy.parser.nodes.Program;
import org.budy.parser.nodes.ast.Visitor;
import org.budy.parser.nodes.builtin.types.Identifier;
import org.budy.parser.nodes.expressions.Constant;
import org.budy.parser.nodes.expressions.Expr;
import org.budy.parser.nodes.expressions.Unary;
import org.budy.parser.nodes.statements.Return;
import org.budy.parser.nodes.statements.Stmt;

public class AstNodePrinter implements Visitor<String> {


    @Override
    public String visit(Constant constant) {
        int anInt = constant.getAnInt();
        return String.format("Constant(anInt=%d)",anInt);
    }

    @Override
    public String visit(Program program) {
        CFunctionDefinition function = program.function();
        return String.format("Program(\n\t%s\n)", function.accept(this));
    }

    @Override
    public String visit(Return aReturn) {
        Expr expression = aReturn.getExpression();
        return String.format("Return(\n\t\t\tExpr=%s\n\t\t\t)", expression.accept(this));
    }

    @Override
    public String visit(CFunctionDefinition cFunctionDefinition) {
        Identifier name = cFunctionDefinition.name();
        Stmt body = cFunctionDefinition.body();


        return String.format("Function(\n\t\tname=%s,\n\t\tbody=%s\n\t\t)", name.accept(this), body.accept(this));
    }

    @Override
    public String visit(Identifier identifier) {
        return identifier.name();
    }

    @Override
    public String visit(Unary unary) {
        String operator = unary.getOperator();
        String expr = unary.getRight().accept(this);
        return "Unary(" + "operator=" + operator + " " + "Expr=(" + expr + ")";
    }
}
