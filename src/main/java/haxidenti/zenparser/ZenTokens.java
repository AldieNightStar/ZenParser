package haxidenti.zenparser;

public class ZenTokens {
    public enum TokenType {
        ENGLISH_ALPHABET, NUMBERS, SYMBOLS, SPACES
    }

    public static class StringToken implements ZenToken {
        public String content;
        public TokenType type;
    }
}
