package boj.boj5430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final int DIRECTION_LEFT = -1;
    private static final int DIRECTION_RIGHT = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            String[] p = br.readLine().split("");
            int n = Integer.parseInt(br.readLine());

            String x = br.readLine();

            Deque<Integer> xDeque = new ArrayDeque<>();

            if (n != 0) {
                xDeque.addAll(Arrays.stream(x.substring(1, x.length() - 1).split(",")).map(Integer::parseInt).collect(Collectors.toList()));
            }

            int direction = DIRECTION_RIGHT;
            boolean isEnded = false;

            for (int j = 0; j < p.length; j++) {
                if (p[j].equals("R")) {
                    direction *= -1;
                }

                if (p[j].equals("D")) {
                    if (xDeque.isEmpty()) {
                        sb.append("error").append(System.lineSeparator());
                        isEnded = true;
                        break;
                    }

                    if (direction == DIRECTION_RIGHT) {
                        xDeque.pollFirst();
                    } else {
                        xDeque.pollLast();
                    }
                }
            }

            if (isEnded) {
                continue;
            }

            List<Integer> result = new ArrayList<>(xDeque);

            if (direction == DIRECTION_LEFT) {
                Collections.reverse(result);
            }

            sb.append("[")
                    .append(result.stream().map(String::valueOf).collect(Collectors.joining(",")))
                    .append("]")
                    .append(System.lineSeparator());
        }

        System.out.println(sb);
    }
}
