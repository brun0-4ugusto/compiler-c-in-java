package org.budy.parser.nodes.ast;

import org.budy.assembler.ast.VisitorAssembly;
import org.budy.assembler.nodes.Assembly;

public abstract class AstNode {
     public abstract <R> R accept(Visitor<R> visitor);

     public abstract Assembly accept(VisitorAssembly visitor);
}

