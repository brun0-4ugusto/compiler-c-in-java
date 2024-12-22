package org.budy.parser.nodes.ast;

import org.budy.parser.nodes.CFunction;
import org.budy.parser.nodes.Program;
import org.budy.parser.nodes.builtin.types.Identifier;
import org.budy.parser.nodes.expressions.Constant;
import org.budy.parser.nodes.statements.Return;

public interface Visitor<R> {
    R visit(Constant constant);

    R visit(Program program);

    R visit(Return aReturn);

    R visit(CFunction cFunction);

    R visit(Identifier identifier);
}
