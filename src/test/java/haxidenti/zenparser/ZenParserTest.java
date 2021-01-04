package haxidenti.zenparser;

import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZenParserTest {

    ZenPtr ptr;

    @BeforeEach
    void init() {
        ptr = new ZenPtr("abc ы\t123 .!?#п");
    }

    @org.junit.jupiter.api.Test
    void parseWithTest() {
        List<ZenToken> tokens = ZenParser.parseWith(ptr, ZenParsers.ofNumbers(false), ZenParsers.ofEnglishAlphabet(), ZenParsers.ofSpaces(), ZenParsers.ofSymbols());

        assertEquals(8, tokens.size());

        ZenTokens.StringToken t0 = (ZenTokens.StringToken) tokens.get(0);
        ZenTokens.StringToken t1 = (ZenTokens.StringToken) tokens.get(1);
        ZenParser.UnknownToken t2 = (ZenParser.UnknownToken) tokens.get(2);
        ZenTokens.StringToken t3 = (ZenTokens.StringToken) tokens.get(3);
        ZenTokens.StringToken t4 = (ZenTokens.StringToken) tokens.get(4);
        ZenTokens.StringToken t5 = (ZenTokens.StringToken) tokens.get(5);
        ZenTokens.StringToken t6 = (ZenTokens.StringToken) tokens.get(6);
        ZenParser.UnknownToken t7 = (ZenParser.UnknownToken) tokens.get(7);

        assertEquals("abc", t0.content);
        assertEquals(ZenTokens.TokenType.ENGLISH_ALPHABET, t0.type);

        assertEquals(" ", t1.content);
        assertEquals(ZenTokens.TokenType.SPACES, t1.type);

        assertEquals("\t", t3.content);
        assertEquals(ZenTokens.TokenType.SPACES, t3.type);

        assertEquals("123", t4.content);
        assertEquals(ZenTokens.TokenType.NUMBERS, t4.type);

        assertEquals(" ", t5.content);
        assertEquals(ZenTokens.TokenType.SPACES, t5.type);

        assertEquals(".!?#", t6.content);
        assertEquals(ZenTokens.TokenType.SYMBOLS, t6.type);

    }


}