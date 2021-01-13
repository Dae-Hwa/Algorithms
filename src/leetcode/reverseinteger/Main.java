package leetcode.reverseinteger;


import java.util.*;

class Solution {
    public int reverse(int x) {


        Deque<String> deque = new ArrayDeque<>(Arrays.asList(String.valueOf(x).split("")));

        StringBuilder sb = new StringBuilder();

        int sign = 1;
        int result = 0;

        if (x < 0) {
            sign = -1;
            deque.pollFirst();
        }

        while (!deque.isEmpty()) {
            int modular = Integer.parseInt(deque.pollLast()) % 10;

            if (Integer.MAX_VALUE / 10 < result) {
                return 0;
            }
            if (result < Integer.MIN_VALUE / 10) {
                return 0;
            }
            if (result == Integer.MAX_VALUE / 10 && Integer.MAX_VALUE % 10 < modular) {
                return 0;
            }
            if (result == Integer.MIN_VALUE / 10 && modular < Integer.MIN_VALUE % 10) {
                return 0;
            }

            if (modular < 0) {
                modular *= -1;
            }

            result *= 10;
            result += modular;
        }

        return result * sign;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Object> inputs = new ArrayList<>(Arrays.asList(
                new ArrayList(Arrays.asList(
                        123, 321
                )),
                new ArrayList(Arrays.asList(
                        -123, -321
                )),
                new ArrayList(Arrays.asList(
                        1534236469, 0
                ))

        ));

        for (int i = 0; i < inputs.size(); i++) {
            List<Object> input = (List<Object>) inputs.get(i);

            System.out.println("results");
            System.out.println(new Solution().reverse((int) input.get(0)));
            System.out.println("expected");
            System.out.println(input.get(1));
        }
    }
}
