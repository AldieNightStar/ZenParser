package haxidenti.zenparser;

public class ZenParsers {
    public static ZenParser ofChars(String charList, ZenTokens.TokenType type) {
        return ptr -> {
            StringBuilder sb = new StringBuilder();
            while (true) {
                char c = ptr.read();
                if (charList.indexOf(c) > -1) {
                    sb.append(c);
                    ptr.ptr++;
                } else {
                    if (!sb.isEmpty()) {
                        ZenTokens.StringToken token = new ZenTokens.StringToken();
                        token.content = sb.toString();
                        token.type = type;
                        return token;
                    } else {
                        return null;
                    }
                }
            }
        };
    }

    public static ZenParser ofNumbers(boolean withDot) {
        String str = (withDot) ? "0123456789." : "0123456789";
        return ofChars(str, ZenTokens.TokenType.NUMBERS);
    }

    public static ZenParser ofSymbols() {
        return ofChars("!?@#$%^&*().,/\\;'][`\"+_~", ZenTokens.TokenType.SYMBOLS);
    }

    public static ZenParser ofEnglishAlphabet() {
        return ofChars("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", ZenTokens.TokenType.ENGLISH_ALPHABET);
    }

    public static ZenParser ofSpaces() {
        return ofChars(" \n\t", ZenTokens.TokenType.SPACES);
    }
}
