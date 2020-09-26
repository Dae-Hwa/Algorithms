package boj.boj16955;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        solution(getInput());
    }

    public static void solution(String[][] board) {


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (check(board, i, j)) {
                    System.out.println(1);

                    return;
                }
            }
        }

        System.out.println(0);
    }

    public static String[][] getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[][] board = new String[10][];

        for (int i = 0; i < board.length; i++) {
            board[i] = br.readLine().split("");
        }

        return board;
    }

    public static boolean check(String[][] board, int x, int y) {
//        if (board[x][y].equals("X")) {
        return checkBottom(board, x, y) || checkBottomRight(board, x, y) || checkRight(board, x, y) || checkBottomLeft(board, x, y);
//        }

//        return false;
    }

    private static boolean checkBottomLeft(String[][] board, int x, int y) {
        if (5 < x || y < 4) {
            return false;
        }

        int cnt = 0;

        for (int i = 0; i < 5; i++) {
            String cur = board[x + i][y - i];

            if (cur.equals("X")) {
                cnt++;
            }
            if (i < 4 && cur.equals("O")) {
                return false;
            } else if (i == 4 && cur.equals("O")) {
                if(0 < x && y < 9 && board[x - 1][y + 1].equals("O")) {
                    return false;
                }

                if(x == 0 || y == 9) {
                    return false;
                }
            }
        }

        return cnt == 4;
    }

    private static boolean checkRight(String[][] board, int x, int y) {
        if (5 < y) {
            return false;
        }

        int cnt = 0;

        for (int i = 0; i < 5; i++) {
            String cur = board[x][y + i];

            if (cur.equals("X")) {
                cnt++;
            }
            if (i < 4 && cur.equals("O")) {
                return false;
            } else if (i == 4 && cur.equals("O") ) {
                if(0 < y && board[x][y - 1].equals("O")) {
                    return false;
                }

                if(y == 0) {
                    return false;
                }
            }
        }

        return cnt == 4;
    }

    private static boolean checkBottomRight(String[][] board, int x, int y) {
        if (5 < x || 5 < y) {
            return false;
        }

        int cnt = 0;

        for (int i = 0; i < 5; i++) {
            String cur = board[x + i][y + i];

            if (cur.equals("X")) {
                cnt++;
            }
            if (i < 4 && cur.equals("O")) {
                return false;
            } else if (i == 4 && cur.equals("O")) {
                if(0 < x && 0 < y && board[x -1][y -1].equals("O")) {
                    return false;
                }

                if(x == 0 || y == 0) {
                    return false;
                }
            }
        }

        return cnt == 4;
    }

    private static boolean checkBottom(String[][] board, int x, int y) {
        if (5 < x) {
            return false;
        }

        int cnt = 0;

        for (int i = 0; i < 5; i++) {
            String cur = board[x + i][y];

            if (cur.equals("X")) {
                cnt++;
            }
            if (i < 4 && cur.equals("O")) {
                return false;
            } else if (i == 4 && cur.equals("O")) {
                if(0 < x && board[x - 1][y].equals("O")) {
                    return false;
                }

                if(x == 0) {
                    return false;
                }
            }
        }

        return cnt == 4;
    }
}
