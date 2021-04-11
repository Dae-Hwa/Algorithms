package prgrms.단속카메라;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;

        Queue<int[]> cars = Arrays.stream(routes)
                .sorted(Comparator.comparing(ints -> ints[0]))
                .collect(Collectors.toCollection(ArrayDeque::new));

        int nextExit = Integer.MAX_VALUE;

        while (!cars.isEmpty()) {
            int[] enteredCar = cars.poll();

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
        ps.println(new Solution().solution(new int[][]{{-20, 15}, {-14, -5}, {-18, -13}, {-5, -3}}));
        ps.println(new Solution().solution(new int[][]{{-2, -1}, {1, 2}, {-3, 0}}));
    }
}
