package utils;

public class LocalMathUtils {
    private LocalMathUtils() {
    }

    public static Integer getByUnit(int a, int b, int c, Unit unit) {
        if (Unit.MAX.equals(unit)) {
            return Math.max(a, Math.max(b, c));
        }
        if (Unit.MIN.equals(unit)) {
            return Math.min(a, Math.min(b, c));
        }
        if (Unit.MIDDLE.equals(unit)) {
            return Math.max(Math.min(a, b), Math.min(Math.max(a, b), c));
        }
        throw new RuntimeException("Something went wrong!");
    }

    public enum Unit {
        MIN, MAX, MIDDLE
    }
}
