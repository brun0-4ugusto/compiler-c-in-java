package org.budy.lex.token;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Keywords {
    private static final Map<String, TokenType> keywords = new HashMap<>();

    static {
        keywords.put("int", TokenType.KEYWORD);
        keywords.put("return", TokenType.KEYWORD);
        keywords.put("void", TokenType.KEYWORD);
    }

    public static Optional<TokenType> getKeyword(String lexeme) {
        if (!keywords.containsKey(lexeme)){
            return Optional.empty();
        }

        return Optional.of(keywords.get(lexeme));
    }

}
