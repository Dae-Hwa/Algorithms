package prgrms.위장;

import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> clothesMap = new HashMap<>();

        for (String[] cloth : clothes) {
            clothesMap.put(cloth[1], clothesMap.getOrDefault(cloth[1], 0) + 1);
        }

        int answer = 1;

        for (Map.Entry<String, Integer> entry : clothesMap.entrySet()) {
            answer *= (entry.getValue() + 1);
        }


        return answer - 1;
    }
}

public class Main {
    public static void main(String[] args) {
        int result = new Solution().solution(new String[][]{
                {"yellow_hat", "headgear"}, {"blue_sunglasses", "headgear"}, {"green_turban", "headgear"}
        });

        System.out.println(result);
    }
}
