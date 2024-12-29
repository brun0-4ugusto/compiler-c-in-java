package org.budy.tac.nodes;

public class ProgramTac extends Tac {
    private final FunctionDefinitionTac functionDefinitionTac;

    public ProgramTac(FunctionDefinitionTac functionDefinitionTac) {
        this.functionDefinitionTac = functionDefinitionTac;
    }

    public FunctionDefinitionTac getFunctionDefinitionTac() {
        return functionDefinitionTac;
    }
}
