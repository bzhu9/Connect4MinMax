public class Mark {
    private String mark;

    public Mark (String specifiedString) {
        mark = new String(specifiedString.substring(0, 1));
    }

    public String toString () {
        return mark;
    }
}
