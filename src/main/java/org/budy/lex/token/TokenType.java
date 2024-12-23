package org.budy.lex.token;

public enum TokenType {
    IDENTIFIER,
    CONSTANT,
    OPEN_PAREN,
    CLOSE_PAREN,
    OPEN_BRACE,
    CLOSE_BRACE,
    SEMICOLON,
    KEYWORD,

    BITWISE_COMPLEMENT, //~ complemento a dois: invertendo todos os bits e add 1
    NEGATION,
    DECREMENT
}
