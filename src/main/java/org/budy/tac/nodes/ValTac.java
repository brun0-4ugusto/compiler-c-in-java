package org.budy.tac.nodes;

public abstract class ValTac extends Tac{
    public static class Constant extends ValTac {
        private final Integer integer;

        public Constant(Integer integer) {
            this.integer = integer;
        }

        public Integer getInteger() {
            return integer;
        }
    }

    public static class Variable extends ValTac {
        private final String name;

        public Variable(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
