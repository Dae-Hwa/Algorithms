package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*
        1 <= N <= M <= 8
        1 부터 N 까지 자연수 중 중복없이 M개를 고른 수열
         */

        /*
        3 1
         */
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        List<List<Integer>> result = new ArrayList<>();
        permutation(N, 0, M, new int[M], new boolean[N], result);

        System.out.println(
                result.stream()
                        .map(it -> it.stream().map(Object::toString).collect(Collectors.joining(" ")))
                        .collect(Collectors.joining(System.lineSeparator()))
        );
    }

    static void permutation(int n, int p, int r, int[] temp, boolean[] visited, List<List<Integer>> result) {
        if (p == r) {
            result.add(Arrays.stream(temp).boxed().collect(Collectors.toList()));
        } else {
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    temp[p] = i + 1;
                    p++;
                    permutation(n, p, r, temp, visited, result);
                    p--;
                    visited[i] = false;
                }
            }
        }
    }
}

public class Boj15649_N과M1 {
}
