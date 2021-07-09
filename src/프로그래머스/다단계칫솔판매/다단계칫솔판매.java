package 프로그래머스.다단계칫솔판매;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        Map<String, Enroll> enrollMap = new HashMap<>();

        for (String each : enroll) {
            enrollMap.put(each, new Enroll(each));
        }

        for (int i = 0; i < referral.length; i++) {
            if (referral[i] == "-") {
                continue;
            }

            enrollMap.get(enroll[i]).referral = enrollMap.get(referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            enrollMap.get(seller[i]).earnProfit(amount[i] * 100);
        }

        for (int i = 0; i < enroll.length; i++) {
            answer[i] = enrollMap.get(enroll[i]).profit;
        }

        return answer;
    }
}

class Enroll {
    String name;
    Enroll referral;
    int profit;

    public Enroll(String name) {
        this.name = name;
    }

    public void earnProfit(int newProfit) {
        int fee = newProfit * 10 / 100;

        profit += newProfit - fee;

        if (referral != null) {
            referral.earnProfit(fee);
        }
    }
}

public class 다단계칫솔판매 {
    public static void main(String[] args) {
        int[] result = new Solution().solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"young", "john", "tod", "emily", "mary"},
                new int[]{12, 4, 2, 5, 10}
        );

        System.out.println(Arrays.toString(result));
    }
}
