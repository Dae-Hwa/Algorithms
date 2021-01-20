package prgrms.크레인_인형뽑기_게임;

import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        List<Queue<Integer>> dollQueues = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            dollQueues.add(new ArrayDeque<>());
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    dollQueues.get(j).add(board[i][j]);
                }
            }
        }

        Stack<Integer> dollStack = new Stack<>();

        for (int i = 0; i < moves.length; i++) {
            Queue<Integer> cur = dollQueues.get(moves[i] - 1);

            if (!cur.isEmpty()) {
                if (!dollStack.isEmpty() && dollStack.peek() == cur.peek()) {
                    dollStack.pop();
                    cur.poll();
                    answer += 2;
                } else {
                    dollStack.push(cur.poll());
                }
            }
        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 3},
                {0, 2, 5, 0, 1},
                {4, 2, 4, 4, 2},
                {3, 5, 1, 3, 1}
        };

        int[] moves = new int[]{1, 5, 3, 5, 1, 2, 1, 4};

        System.out.println(new Solution().solution(board, moves));
    }
}
