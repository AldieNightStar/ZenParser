package haxidenti.zenparser;

public class ZenPtr {
    public int ptr;
    String str;

    public ZenPtr(String s) {
        ptr = 0;
        str = s;
    }

    public char read(int i) {
        if (i < 0 || i >= str.length()) {
            return 0;
        }
        return str.charAt(i);
    }

    public char read() {
        return read(ptr);
    }

    public char readNext() {
        return read(ptr++);
    }

    public void apply(ZenPtr z) {
        ptr = z.ptr;
    }

    public ZenPtr copy() {
        ZenPtr z = new ZenPtr(str);
        z.ptr = ptr;
        return z;
    }
}
