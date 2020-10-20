package prgrms.숫자게임;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int result = new Solution().solution(
                new int[]{
                        5, 1, 3, 7
                },
                new int[]{
                        2, 2, 6, 8
                }
        );

        System.out.println(result);
    }
}

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        int index = 0;

        for (int i = 0; i < A.length; i++) {
            if (B.length <= index) {
                break;
            }

            while (index < B.length && B[index] <= A[i]) {
                index++;
            }
            index++;
            answer++;
        }

        return answer;
    }
}
