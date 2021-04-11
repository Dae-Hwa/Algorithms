package prgrms.단속카메라;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;

        List<int[]> cars = Arrays.stream(routes)
                .sorted(Comparator.comparing(ints -> ints[1]))
                .collect(Collectors.toList());

        for (int i = 0; i < cars.size(); i++) {
            answer++;
            int exit = cars.get(i)[1];

            for (int j = i + 1; j < cars.size(); j++) {
                if (cars.get(j)[0] <= exit) {
                    cars.remove(j);
                    j--;
                }
            }
        }

        return answer;
    }
}

public class 단속카메라 {
    public static void main(String[] args) {
        PrintStream ps = System.out;
        ps.println(new Solution().solution(new int[][]{{0, 1}, {0,1}, {1, 2}}));
    }
}
