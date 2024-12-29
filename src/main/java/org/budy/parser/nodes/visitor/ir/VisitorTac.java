package org.budy.parser.nodes.visitor.ir;

import org.budy.parser.nodes.CFunctionDefinition;
import org.budy.parser.nodes.Program;
import org.budy.parser.nodes.builtin.types.Identifier;
import org.budy.parser.nodes.expressions.Constant;
import org.budy.parser.nodes.expressions.Unary;
import org.budy.parser.nodes.statements.Return;
import org.budy.tac.nodes.*;

public interface VisitorTac {
    FunctionDefinitionTac visit(CFunctionDefinition cFunctionDefinition);

    ProgramTac visit(Program program);

    ValTac.Variable visit(Identifier identifier);

    InstructionsList visit(Return aReturn);

    ValTac.Constant visit(Constant constant);

    InstructionTac.UnaryTac visit(Unary unary);

}
