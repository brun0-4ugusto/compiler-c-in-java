package org.budy.lex.token;

public record Token(int startLexeme, int endLexeme, TokenType tokenType) {
    public String readLexemeFromToken(char[] source) {
        int length = this.endLexeme() - this.startLexeme();
        return String.valueOf(source, this.startLexeme(), length +1);

    }
}
