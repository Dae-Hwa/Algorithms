package boj.boj11656;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solution(br.readLine());
    }

    public static void solution(String str) {
        char[] suffix = str.toCharArray();
        int length = suffix.length;
        // {rank, index of suffix}
        int[][] ranks = new int[str.length()][2];

        for (int i = 0; i < length; i++) {
            ranks[i][0] = suffix[i];
            ranks[i][1] = i;
        }

        Arrays.sort(ranks, Comparator.comparingInt(rank -> rank[0]));

        for (int i = 1; i <= str.length(); i <<= 1) {
            for (int j = 0; j < length - 1; j++) {
                int[] cur = ranks[j];
                int[] next = ranks[j + 1];
                int curRank = cur[0];
                int nextRank = next[0];
                int curIndexForCompare = cur[1] + i;
                int nextIndexForCompare = next[1] + i;

                if (curRank == nextRank) {
                    if (length <= curIndexForCompare && length <= nextIndexForCompare) {
                        // 둘 다 null
                        continue;
                    }

                    if (length <= curIndexForCompare) {
                        next[0] += 1;
                        suffix[next[1]] = (char) next[0];
                        continue;
                    }

                    if (length <= nextIndexForCompare) {
                        cur[0] += 1;
                        suffix[cur[1]] = (char) cur[0];
                        ranks[j] = cur;
                        ranks[j + 1] = next;
                        continue;
                    }

                    int curRankForCompare = suffix[curIndexForCompare];
                    int nextRankForCompare = suffix[nextIndexForCompare];

                    if (nextRankForCompare < curRankForCompare) {
                        cur[0] += 1;
                        suffix[cur[1]] = (char) cur[0];
                        ranks[j] = cur;
                        ranks[j + 1] = next;
                    } else if (curRankForCompare < nextRankForCompare) {
                        next[0] += 1;
                        suffix[next[1]] = (char) next[0];
                    }
                }
            }

            Arrays.sort(ranks, Comparator.comparingInt(a -> a[0]));
        }

        for (int i = 0; i < ranks.length; i++) {
            int startIndex = ranks[i][1];

            System.out.println(str.substring(startIndex));
        }
    }
}