package prgrms.행렬테두리회전하기;

import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        List<Integer> answer = new ArrayList<>();

        int[][] board = new int[rows][columns];

        int cnt = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = cnt;
                cnt++;
            }
        }

        for (int[] query : queries) {
            int startY = query[0] - 1;
            int startX = query[1] - 1;
            int endY = query[2] - 1;
            int endX = query[3] - 1;

            Deque<Integer> deque = new ArrayDeque<>();

            int min = Integer.MAX_VALUE;

            for (int i = startX; i < endX; i++) {
                int cur = board[startY][i];

                deque.offerLast(cur);

                if (cur < min) {
                    min = cur;
                }
            }

            for (int i = startY; i <= endY; i++) {
                int cur = board[i][endX];

                deque.offerLast(cur);

                if (cur < min) {
                    min = cur;
                }
            }

            for (int i = endX - 1; startX <= i; i--) {
                int cur = board[endY][i];

                deque.offerLast(cur);

                if (cur < min) {
                    min = cur;
                }
            }

            for (int i = endY - 1; startY < i; i--) {
                int cur = board[i][startX];

                deque.offerLast(cur);

                if (cur < min) {
                    min = cur;
                }
            }

            deque.offerFirst(deque.pollLast());

            for (int i = startX; i < endX; i++) {
                board[startY][i] = deque.pollFirst();
            }

            for (int i = startY; i <= endY; i++) {
                board[i][endX] = deque.pollFirst();
            }

            for (int i = endX - 1; startX <= i; i--) {
                board[endY][i] = deque.pollFirst();
            }

            for (int i = endY - 1; startY < i; i--) {
                board[i][startX] = deque.pollFirst();
            }

            answer.add(min);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}

public class 행렬테두리회전하기 {
    public static void main(String[] args) {
        int[] result = new Solution().solution(
                6,
                6,
                new int[][]{
                        {2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}
                }
        );

        System.out.println(Arrays.toString(result));
    }
}
