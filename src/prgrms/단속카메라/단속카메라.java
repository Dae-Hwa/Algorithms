package prgrms.단속카메라;

import java.io.PrintStream;
import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;

        Arrays.sort(routes, Comparator.comparingInt(o -> o[0]));

        int nextExit = Integer.MAX_VALUE;

        for (int[] enteredCar : routes) {
            if (nextExit < enteredCar[0]) {
                answer++;
                nextExit = enteredCar[1];
            } else if (nextExit == enteredCar[0]) {
                answer++;
                nextExit = Integer.MAX_VALUE;
            } else if (enteredCar[1] <= nextExit) {
                nextExit = enteredCar[1];
            }
        }

        if (nextExit != Integer.MAX_VALUE) {
            answer++;
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
