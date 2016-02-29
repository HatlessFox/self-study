package utils;

public class CliUtils {

    public static <T> String iterableToString(Iterable<T> iterable) {
        StringBuilder sb = new StringBuilder();
        iterable.forEach(e -> { sb.append(e); sb.append(" "); });
        return sb.toString().trim();
    }
}
