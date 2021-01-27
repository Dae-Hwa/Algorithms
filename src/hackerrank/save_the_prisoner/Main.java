package hackerrank.save_the_prisoner;

public class Main {

    static int saveThePrisoner(int n, int m, int s) {
        int result = (m % n + s - 1) % n;
        return result == 0 ? n : result;

    }

    public static void main(String[] args) {
        int n = 999999999;
        int m = 999999999;
        int s = 1;
        System.out.println(saveThePrisoner(n, m, s));
    }
}
