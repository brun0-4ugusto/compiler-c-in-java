package org.budy.parser.nodes.ast;

import org.budy.assembler.ast.VisitorAssembly;
import org.budy.assembler.nodes.Assembly;
import org.budy.parser.nodes.visitor.ir.VisitorTac;
import org.budy.tac.nodes.Tac;

public abstract class AstNode {
     public abstract <R> R accept(Visitor<R> visitor);

     public abstract Assembly accept(VisitorAssembly visitor);

     public abstract Tac accept(VisitorTac visitorTac);
}

