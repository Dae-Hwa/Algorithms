package prgrms.다리를_지나는_트럭;

import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        int[] times = new int[truck_weights.length];
        Arrays.fill(times, bridge_length);
        Queue<Integer> truckQueue = new ArrayDeque<>();

        for (int i = 0; i < truck_weights.length; ) {
            moveTruck(times, truckQueue);

            answer++;

            int currentTotalWeight = truckQueue.stream()
                    .mapToInt(index -> truck_weights[index])
                    .sum() + truck_weights[i];

            if (currentTotalWeight <= weight) {
                truckQueue.add(i);
                i++;
            }
        }

        while (!truckQueue.isEmpty()) {
            moveTruck(times, truckQueue);

            answer++;
        }

        return answer;
    }

    private void moveTruck(int[] times, Queue<Integer> truckQueue) {
        for (int truck : truckQueue) {
            times[truck]--;
        }

        while (!truckQueue.isEmpty() && times[truckQueue.peek()] == 0) {
            truckQueue.poll();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        List<Integer> answers = List.of(
                new Solution().solution(2, 10, new int[]{7, 4, 5, 6}),
                new Solution().solution(100, 100, new int[]{10}),
                new Solution().solution(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}),
                new Solution().solution(1, 1, new int[]{1}),
                new Solution().solution(2, 1, new int[]{1})
        );

        List<Integer> expecteds = List.of(
                8,
                101,
                110,
                2,
                3
        );

        for (int i = 0; i < answers.size(); i++) {
            System.out.println(answers.get(i) + ", " + expecteds.get(i));
        }
    }
}
