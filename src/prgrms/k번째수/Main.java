package prgrms.k번째수;

import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> answer = new ArrayList<>();

        for (int[] command : commands) {
            int[] cur = Arrays.copyOfRange(array, command[0] - 1, command[1]);
            Arrays.sort(cur);

            answer.add(cur[command[2] - 1]);
        }

        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
