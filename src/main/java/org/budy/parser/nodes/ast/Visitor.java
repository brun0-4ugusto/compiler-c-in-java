package org.budy.parser.nodes.ast;

import org.budy.parser.nodes.CFunctionDefinition;
import org.budy.parser.nodes.Program;
import org.budy.parser.nodes.builtin.types.Identifier;
import org.budy.parser.nodes.expressions.Constant;
import org.budy.parser.nodes.expressions.Unary;
import org.budy.parser.nodes.statements.Return;

public interface Visitor<R> {
    R visit(Constant constant);

    R visit(Program program);

    R visit(Return aReturn);

    R visit(CFunctionDefinition cFunctionDefinition);

    R visit(Identifier identifier);

    R visit(Unary unary);
}
