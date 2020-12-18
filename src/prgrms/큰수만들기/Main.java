package prgrms.큰수만들기;

import java.util.*;

class Solution {
    public String solution(String number, int k) {
        int currentLength = number.length() - k;
        int resultLength = currentLength;

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < resultLength; i++) {
            int max = 0;
            int maxIndex = 0;

            for (int j = 0; j < number.length() - currentLength + 1; j++) {
                int cur = Character.digit(number.charAt(j), 10);

                if (max < cur) {
                    max = cur;
                    maxIndex = j;
                }

                if (cur == 9) {
                    break;
                }
            }

            result.append(max);
            currentLength--;
            number = number.substring(maxIndex + 1);
        }

        return result.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        List<Object> inputs;
        inputs = new ArrayList<>(Arrays.asList(
                new ArrayList(Arrays.asList(
                        "4177252841",
                        4
                ))
        ));

        for (Object inputObj : inputs) {
            List<Object> input = (List<Object>) inputObj;
            System.out.println(new Solution().solution((String) input.get(0), (int) input.get(1)));
        }
    }
}
