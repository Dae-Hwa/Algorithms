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

        int id = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (1 <= map[i][j] && !visited[i][j]) {
                    queue.add(new int[]{i, j});
                    id++;
                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        visited[current[0]][current[1]] = true;

                        for (int k = 0; k < 4; k++) {
                            int x = current[0] + dx[k];
                            int y = current[1] + dy[k];

                            if (0 <= x && 0 <= y && x < map.length && y < map[i].length) {
                                if (1 == map[x][y] && !visited[x][y]) {
                                    queue.add(new int[]{x, y});
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

        for (int[] m : map) {
            System.out.println(Arrays.toString(m));
        }

        int max = Integer.MAX_VALUE;


        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (1 < map[i][j]) {
                    queue.add(new int[]{i, j, 0});
                    int level;
                    int curId = map[i][j];
                    visited = new boolean[map.length][map.length];

                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        visited[current[0]][current[1]] = true;
                        level = current[2];

                        for (int k = 0; k < 4; k++) {
                            int x = current[0] + dx[k];
                            int y = current[1] + dy[k];

                            if (0 <= x && 0 <= y && x < map.length && y < map[i].length) {
                                if (1 < map[x][y] && map[x][y] != curId) {
                                    if (level < max) {
                                        max = level;
                                    }

                                    queue.clear();

                                    break;
                                }

                                if (map[x][y] == 0 && !visited[x][y]) {
                                    visited[x][y] = true;
                                    queue.add(new int[]{x, y, level + 1});
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

        int[][] map = new int[sizeOfMap][];

        for (int i = 0; i < sizeOfMap; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
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