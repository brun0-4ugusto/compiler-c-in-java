package org.budy.parser.nodes.visitor.ir;

import org.budy.parser.nodes.CFunctionDefinition;
import org.budy.parser.nodes.Program;
import org.budy.parser.nodes.builtin.types.Identifier;
import org.budy.parser.nodes.expressions.Constant;
import org.budy.parser.nodes.expressions.Expr;
import org.budy.parser.nodes.expressions.Unary;
import org.budy.parser.nodes.statements.Return;
import org.budy.tac.nodes.*;

import java.util.LinkedList;
import java.util.List;

public class TacVisitor implements VisitorTac {
    private int counter = 0;

    @Override
    public FunctionDefinitionTac visit(CFunctionDefinition cFunctionDefinition) {
        ValTac.Variable variable = cFunctionDefinition.name().accept(this);
        InstructionsList instruction = cFunctionDefinition.body().accept(this);

        return new FunctionDefinitionTac(variable.getName(), instruction);
    }

    @Override
    public ProgramTac visit(Program program) {
        return new ProgramTac(program.function().accept(this));
    }

    @Override
    public ValTac.Variable visit(Identifier identifier) {
        return new ValTac.Variable(identifier.name());
    }

    @Override
    public InstructionsList visit(Return aReturn) {
        LinkedList<InstructionTac> instructions = new LinkedList<>();
        Tuple evaluated = evaluate(aReturn.getExpression(), instructions);
        instructions.add(new InstructionTac.ReturnTac(evaluated.valTac));

        return new InstructionsList(instructions);
    }

    @Override
    public ValTac.Constant visit(Constant constant) {
        return new ValTac.Constant(constant.getAnInt());
    }

    @Override
    public InstructionTac.UnaryTac visit(Unary unary) {
        LinkedList<InstructionTac> instructions = new LinkedList<>();
        UnaryOp op = resolveOp(unary);
        Tuple evaluatedExpr = evaluate(unary.getRight(), instructions);
        String dstName = generateName();
        ValTac.Variable tempDest = new ValTac.Variable(dstName);

        return new InstructionTac.UnaryTac(
                op,
                evaluatedExpr.valTac,
                tempDest
        );
    }

    private UnaryOp resolveOp(Unary u) {
        switch (u.getOperator()) {
            case "-" -> {
                return UnaryOp.NEGATE;
            }
            case "~" -> {
                return UnaryOp.COMPLEMENT;
            }
            default -> throw new RuntimeException();
        }
    }

    private String generateName() {
        return "temp." + ++counter;
    }

    private Tuple evaluate(Expr expression, List<InstructionTac> instructions) {

        switch (expression) {
            case Constant c -> {
                return new Tuple(instructions, c.accept(this));
            }
            case Unary u -> {
                InstructionTac.UnaryTac unaryTac = u.accept(this);
                instructions.add(unaryTac);

                return new Tuple(instructions, unaryTac.getDst());
            }
            default -> throw new IllegalStateException("Unexpected value: " + expression);
        }
    }

    private record Tuple(List<InstructionTac> instructionTacs, ValTac valTac) {
    }

}
