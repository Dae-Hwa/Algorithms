package prgrms.큰수만들기;

import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < number.length(); i++) {
            int cur = charToInt(number.charAt(i));
            for (; 0 < result.length() && charToInt(result.charAt(result.length() - 1)) < cur && 0 < k; k--) {
                result.deleteCharAt(result.length() - 1);
            }
            if (result.length() < number.length() - k) {
                result.append(cur);
            }
        }

        return result.toString();
    }

    public static int charToInt(char c) {
        return Character.digit(c, 10);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Object> inputs;
        inputs = new ArrayList<>(Arrays.asList(
                new ArrayList(Arrays.asList(
                        "7777777",
                        2
                ))
        ));

        for (Object inputObj : inputs) {
            List<Object> input = (List<Object>) inputObj;
            System.out.println(new Solution().solution((String) input.get(0), (int) input.get(1)));
        }
    }
}
