package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);

        int[] reference = Arrays.stream((br.readLine().split(" ")))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(reference);

        List<List<Integer>> result = Permutation.result(N, M, reference);
        System.out.println(result.stream()
                .map(it -> it.stream().map(Object::toString).collect(Collectors.joining(" ")))
                .collect(Collectors.joining(System.lineSeparator())));
    }

    /**
     * 중복을 허용하지 않는 순열
     */
    static class Permutation {
        private int n;
        private int r;
        private int[] reference;
        private boolean[] visited;
        private Deque<Integer> deque = new ArrayDeque<>();
        private List<List<Integer>> result = new ArrayList<>();

        private Permutation(int n, int r, int[] reference) {
            this.n = n;
            this.r = r;
            this.reference = reference;
            visited = new boolean[n];
        }

        static List<List<Integer>> result(int n, int r, int[] reference) {
            Permutation permutation = new Permutation(n, r, reference);
            permutation.permutation(0);
            return permutation.result;
        }

        private void permutation(int cur) {
            if (cur == r) {
                result.add(new ArrayList<>(deque));
            } else {
                for (int i = 0; i < n; i++) {
                    if (!visited[i]) {
                        visited[i] = true;
                        deque.add(reference[i]);
                        permutation(cur + 1);
                        deque.removeLast();
                        visited[i] = false;
                    }
                }
            }
        }
    }
}

public class Boj15654_N과M5 {

}
