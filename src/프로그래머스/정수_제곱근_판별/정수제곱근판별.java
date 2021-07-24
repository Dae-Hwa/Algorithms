package 프로그래머스.정수_제곱근_판별;

class Solution {
    public long solution(long n) {
        double sqrt = Math.sqrt(n);

        if (sqrt % 1 != 0) {
            return -1;
        }

        return (long) Math.pow(sqrt + 1, 2.0);
    }
}

public class 정수제곱근판별 {
    public static void main(String[] args) {
        long[] inputs = new long[]{
                121,
                3,
                1
        };

        for (long input : inputs) {
            System.out.println(new Solution().solution(input));
        }
    }
}
