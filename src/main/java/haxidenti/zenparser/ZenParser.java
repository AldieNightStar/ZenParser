package haxidenti.zenparser;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface ZenParser {
    ZenToken parse(ZenPtr ptr);

    static List<ZenToken> parseWith(String str, ZenParser... parsers) {
        return parseWith(new ZenPtr(str), parsers);
    }

    static List<ZenToken> parseWith(ZenPtr zenPtr, ZenParser... parsers) {
        StringBuilder sb = new StringBuilder();

        List<ZenToken> tokens = new ArrayList<>();

        global:
        while (true) {
            for (ZenParser parser : parsers) {
                ZenPtr ptr = zenPtr.copy();
                ZenToken token = parser.parse(ptr);
                if (token != null) {
                    if (!sb.isEmpty()) {
                        UnknownToken t = new UnknownToken();
                        t.content = sb.toString();
                        sb = new StringBuilder();
                        tokens.add(t);
                    }
                    zenPtr.apply(ptr);
                    tokens.add(token);
                    continue global;
                }
            }
            char c = zenPtr.read();
            if (c == 0) {
                if (!sb.isEmpty()) {
                    String s = sb.toString();
                    UnknownToken token = new UnknownToken();
                    token.content = s;
                    tokens.add(token);
                    break;
                } else {
                    return tokens;
                }
            } else {
                sb.append(c);
                zenPtr.ptr++;
            }
        }

        return tokens;
    }

    class UnknownToken implements ZenToken {
        public String content;
    }
}
