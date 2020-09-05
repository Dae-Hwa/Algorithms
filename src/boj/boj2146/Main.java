package boj.boj2146;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int[][] map = getInput();
        boolean[][] visited = new boolean[map.length][map.length];
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<int[]> queue = new ArrayDeque<>();
        Stack<int[]> stack = new Stack<>();

        int id = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (1 <= map[i][j] && !visited[i][j]) {
                    stack.push(new int[]{i, j});
                    id++;
                    while (!stack.isEmpty()) {
                        int[] current = stack.pop();
                        visited[current[0]][current[1]] = true;

                        for (int k = 0; k < 4; k++) {
                            int x = current[0] + dx[k];
                            int y = current[1] + dy[k];

                            if (0 <= x && 0 <= y && x < map.length && y < map[i].length) {
                                if (1 == map[x][y] && !visited[x][y]) {
                                    stack.push(new int[]{x, y});
                                }
                                if (map[x][y] == 0) {
                                    map[current[0]][current[1]] = id + 1;
                                }
                            }
                        }
                    }
                }
            }
        }


        queue.clear();
        int max = Integer.MAX_VALUE;
        stack.clear();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (1 < map[i][j]) {
                    stack.push(new int[]{i, j, 0});
                    visited = new boolean[map.length][map.length];
                    int currentId = map[i][j];
                    while (!stack.isEmpty()) {
                        int[] current = stack.pop();

                        if(max <= current[2]) {
                            continue;
                        }

//                        System.out.println("==");
//                        for(boolean[] m : visited) {
//                            System.out.println(Arrays.toString(m));
//                        }
//                        System.out.println("==");

                        for (int k = 0; k < 4; k++) {
                            int x = current[0] + dx[k];
                            int y = current[1] + dy[k];

                            if (0 <= x && 0 <= y && x < map.length && y < map[i].length) {
                                if (map[x][y] != currentId && 1 < map[x][y]) {
                                    if (current[2] < max) {
                                        max = current[2];
                                    }
//                                } else if (map[x][y] == 0 && !visited[x][y]) {
                                } else if (map[x][y] == 0 && !visited[x][y]) {
                                    stack.push(new int[]{x, y, current[2] + 1});
                                    visited[x][y] = true;
                                }
                            }
                        }
                    }
                }
            }
        }


        System.out.println(max);
    }

    public static int[][] getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sizeOfMap = Integer.parseInt(br.readLine());

        int[][] map = new int[sizeOfMap][sizeOfMap];

        for (int i = 0; i < sizeOfMap; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < sizeOfMap; j++) {
                try {
                    map[i][j] = Integer.parseInt(input[j]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return map;
    }
}

enum SquareType {
    LAND(1), SEA(0);

    SquareType(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public static SquareType getTypeOf(int code) {
        for (SquareType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("일치하는 코드가 없습니다.");
    }
}

class Lands {
    private final List<Land> lands;
    private final int id;

    public Lands(List<Land> lands, int id) {
        this.lands = lands;
        this.id = id;
    }
}

class Land {
    private final SquareType type = SquareType.LAND;
}

class Sea {
    private final SquareType type = SquareType.SEA;
}