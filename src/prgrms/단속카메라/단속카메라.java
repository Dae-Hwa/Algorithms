package prgrms.단속카메라;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;

        Arrays.sort(routes, Comparator.comparingInt(o -> o[0]));

        int end = routes[0][1];

        for (int i = 0; i < routes.length; i++) {
            int enter = routes[i][0];
            int exit = routes[i][1];

            if (end < enter) {
                answer += 1;
                end = exit;
            } else if (exit <= end) {
                end = exit;
            }
        }

        return answer;
    }
}

public class 단속카메라 {
    public static void main(String[] args) {
        PrintStream ps = System.out;
        ps.println(new Solution().solution(new int[][]{{0, 1}, {0, 1}, {1, 2}}));
    }
}
