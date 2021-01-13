package leetcode.reverseinteger;


import java.util.*;

class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
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
