package boj.boj2178;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input1 = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] map = new int[input1[0]][];
        boolean[][] visited = new boolean[input1[0]][input1[1]];

        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[] startPoint = new int[]{0, 0, 1};

        Queue<int[]> bfs = new ArrayDeque<>();

        bfs.offer(startPoint);

        int[] dX = new int[]{1, -1, 0, 0};
        int[] dY = new int[]{0, 0, 1, -1};

        while (!bfs.isEmpty()) {
            int[] current = bfs.poll();
            int curX = current[0];
            int curY = current[1];
            int curLevel = current[2] + 1;

            for (int i = 0; i < 4; i++) {
                int x = curX + dX[i];
                int y = curY + dY[i];

                if (x < 0 || y < 0 || input1[0] <= x || input1[1] <= y) {
                    continue;
                }

                if (!visited[x][y] && map[x][y] == 1) {
                    if (x == input1[0] - 1 && y == input1[1] - 1) {
                        System.out.println(curLevel);
                        return;
                    }

                    bfs.offer(new int[]{x, y, curLevel});
                    visited[x][y] = true;
                }
            }
        }

    }
}
