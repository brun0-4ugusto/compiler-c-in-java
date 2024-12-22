package org.budy.assembler.ast;

import org.budy.assembler.nodes.Assembly;
import org.budy.assembler.nodes.AssemblyFunction;
import org.budy.assembler.nodes.AssemblyProgram;
import org.budy.assembler.nodes.IdentifierAssembly;
import org.budy.assembler.nodes.instruction.Instruction;
import org.budy.assembler.nodes.instruction.InstructionsSet;
import org.budy.assembler.nodes.instruction.Mov;
import org.budy.assembler.nodes.instruction.Operand.Imm;
import org.budy.assembler.nodes.instruction.Operand.Register;
import org.budy.assembler.nodes.instruction.Ret;
import org.budy.parser.nodes.CFunction;
import org.budy.parser.nodes.Program;
import org.budy.parser.nodes.builtin.types.Identifier;
import org.budy.parser.nodes.expressions.Constant;
import org.budy.parser.nodes.statements.Return;

import java.util.LinkedList;
import java.util.List;

public class AstVisitorAssembler implements VisitorAssembly {
    @Override
    public Imm visit(Constant constant) {
        return new Imm(constant.getAnInt());
    }

    @Override
    public AssemblyProgram visit(Program program) {
        AssemblyFunction programAssembly = program.function().accept(this);
        return new AssemblyProgram(programAssembly);
    }

    @Override
    public InstructionsSet visit(Return aReturn) {
        Assembly imm = aReturn.getExpression().accept(this);
        List<Instruction> instructions = new LinkedList<>();
        InstructionsSet instructionsSet = new InstructionsSet(instructions);
        if (imm instanceof Imm i) {
            instructions.add(new Mov(i, new Register("EAX")));
            instructions.add(new Ret());
        }
        return instructionsSet;
    }

    @Override
    public AssemblyFunction visit(CFunction cFunction) {
        IdentifierAssembly name = cFunction.name().accept(this);
        InstructionsSet instructions = cFunction.body().accept(this);
        return new AssemblyFunction(name, instructions);
    }

    @Override
    public IdentifierAssembly visit(Identifier identifier) {
        return new IdentifierAssembly(identifier.name());
    }
}
