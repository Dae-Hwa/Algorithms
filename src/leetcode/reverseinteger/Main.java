package leetcode.reverseinteger;


import java.util.*;

class Solution {
    public int reverse(int x) {
        StringBuilder sb = new StringBuilder();

        int sign = 1;

        if (x < 0) {
            sign = -1;
        }

        while (x != 0) {
            int modular = x % 10;
            sb.append(modular < 0 ? modular * -1 : modular);
            x /= 10;
        }
        try {
            return Integer.parseInt(sb.toString()) * sign;
        } catch (NumberFormatException e) {
            return 0;
        }
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
