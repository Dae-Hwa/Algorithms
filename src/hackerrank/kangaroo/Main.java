package hackerrank.kangaroo;

public class Main {
    static String kangaroo(int x1, int v1, int x2, int v2) {
        if (v1 - v2 < 1) {
            return "NO";
        }

        double result = ((double) (x1 - x2)) / ((double) (v2 - v1));

        if (result < 1) {
            return "NO";
        }

        if (0 < result % 1) {
            return "NO";
        }

        return "YES";
    }

    public static void main(String[] args) {
        System.out.println(kangaroo(21, 6, 47, 3));
    }
}
