package org.budy.tac.nodes;

public abstract class InstructionTac extends Tac {
    public static class ReturnTac extends InstructionTac {
        private final ValTac val;

        public ReturnTac(ValTac val) {
            this.val = val;
        }

        public ValTac getVal() {
            return val;
        }
    }

    public static class UnaryTac extends InstructionTac {
        private final UnaryOp operator;
        private final ValTac src;
        private final ValTac.Variable dst;

        public UnaryTac(UnaryOp operator, ValTac src, ValTac.Variable dst) {
            this.operator = operator;
            this.src = src;
            this.dst = dst;
        }

        public ValTac getDst() {
            return dst;
        }

        public ValTac getSrc() {
            return src;
        }

        public UnaryOp getOperator() {
            return operator;
        }
    }
}
