package org.budy.tac;

import org.budy.parser.nodes.Program;
import org.budy.parser.nodes.visitor.ir.TacVisitor;
import org.budy.tac.nodes.ProgramTac;

public class TacGenerator {
    private final TacVisitor tacVisitor = new TacVisitor();

    public ProgramTac genTac(Program program) {
        return tacVisitor.visit(program);
    }
}
