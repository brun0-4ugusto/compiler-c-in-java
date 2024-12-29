package org.budy.tac.nodes;

public class FunctionDefinitionTac extends Tac {
    private final String name;
    private final InstructionsList instructions;

    public FunctionDefinitionTac(String name, InstructionsList instructions) {
        this.name = name;
        this.instructions = instructions;
    }

    public InstructionsList getInstructions() {
        return instructions;
    }

    public String getName() {
        return name;
    }
}
