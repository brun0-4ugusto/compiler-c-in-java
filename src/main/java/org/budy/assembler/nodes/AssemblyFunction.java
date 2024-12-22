package org.budy.assembler.nodes;

import org.budy.assembler.nodes.instruction.InstructionsSet;
import org.budy.assembler.pretty.AssemblerVisitor;

public final class AssemblyFunction extends Assembly {
    private final IdentifierAssembly name;
    private final InstructionsSet instructionList;

    public AssemblyFunction(IdentifierAssembly name, InstructionsSet instructionList) {
        this.name = name;
        this.instructionList = instructionList;
    }

    public IdentifierAssembly name() {
        return name;
    }

    public InstructionsSet instructionList() {
        return instructionList;
    }

    @Override
    public <R> R accept(AssemblerVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
