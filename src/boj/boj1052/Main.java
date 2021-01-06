package boj.boj1052;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] input = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        Map<Long, Long> map = new HashMap<>();

        map.put(1L, input[0]);

        long cur = input[0];
        long target = input[1];
        long extra = 0;

        while (true) {
            Set<Long> keySet = map.keySet();

            for (long key : keySet) {
                if (target == cur) {
                    System.out.println(extra);
                    return;
                }

                if (2 <= map.get(key)) {
                    cur--;
                    map.put(key, map.get(key) - 2);
                    map.put(key * 2, map.getOrDefault(key * 2, 0l) + 1);
                }
            }

            if (target == cur) {
                System.out.println(extra);
                return;
            }

            boolean cannotAdd = true;

            for (Long key : keySet) {
                if (2 <= map.get(key)) {
                    cannotAdd = false;
                    break;
                }
            }

            if (cannotAdd) {
                map.put(1l, map.getOrDefault(1l, 0l) + 1);
                cur++;
                extra++;
            }
        }
    }
}
