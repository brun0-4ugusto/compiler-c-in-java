package org.budy.tac.nodes;

import java.util.List;

public class InstructionsList extends Tac {
    private final List<InstructionTac> instructionTacs;

    public InstructionsList(List<InstructionTac> instructionTacs) {
        this.instructionTacs = instructionTacs;
    }

    public List<InstructionTac> getInstructionTacs() {
        return instructionTacs;
    }
}
