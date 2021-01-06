package boj.boj1052;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] input = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        int height = (int) (Math.log(input[0]) / Math.log(2)) + 2;

        long[] count = new long[height];
        int extra = 0;
        count[0] = input[0];

        while (true) {
            for (int i = 0; i < count.length - 1; i++) {
                if (2 <= count[i]) {
                    count[i + 1] = count[i] / 2;
                    count[i] %= 2;
                }
            }
            if (input[1] < Arrays.stream(count).sum()) {
                count[0] += 1;
                extra++;
            } else if (input[1] <= input[0] + extra) {
                break;
            }
        }

        System.out.println(extra);
    }
}
