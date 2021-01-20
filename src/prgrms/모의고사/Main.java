package prgrms.모의고사;

import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] student1 = new int[]{1, 2, 3, 4, 5};
        int[] student2 = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        int[] student3 = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        // score, index
        int[][] scores = new int[3][2];
        scores[0][1] = 1;
        scores[1][1] = 2;
        scores[2][1] = 3;

        for(int i = 0; i < answers.length; i++) {
            int answer = answers[i];
            if(student1[i % student1.length] == answer) {
                scores[0][0]++;
            }
            if(student2[i % student2.length] == answer) {
                scores[1][0]++;
            }
            if(student3[i % student3.length] == answer) {
                scores[2][0]++;
            }
        }

        Arrays.sort(scores, (arr1, arr2) -> {
            if(arr1[0] == arr2[0]) {
                return Integer.compare(arr1[1], arr2[1]);
            }

            return Integer.compare(arr1[0], arr2[0]) * -1;
        });

        List<Integer> winners = new ArrayList<>();

        for(int i = 0; i < scores.length; i++) {
            if(scores[0][0] == scores[i][0]) {
                winners.add(scores[i][1]);
            }
        }

        return winners.stream().mapToInt(i->i).toArray();
    }
}

public class Main {
}
