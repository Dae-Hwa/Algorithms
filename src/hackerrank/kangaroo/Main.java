package hackerrank.kangaroo;

public class Main {
    static String kangaroo(int x1, int v1, int x2, int v2) {
        return v2 < v1 && (x1 - x2) % (v2 - v1) == 0 ? "YES" : "NO";
    }

    public static void main(String[] args) {
        System.out.println(kangaroo(21, 6, 47, 3));
    }
}
