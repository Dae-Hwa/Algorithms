package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*
         * 1 <= N <= M <= 8
         * 1 부터 N 까지 자연수 중 중복없이 M개를 고른 수열 => 순열
         * 최대 약 8!
         */

        /*
         * 3 1
         */
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        // int N = 4;
        // int M = 2;

        List<List<Integer>> result = new Permutation(N, M).result();
        System.out.println(result.stream()
                .map(it -> it.stream().map(Object::toString).collect(Collectors.joining(" ")))
                .collect(Collectors.joining(System.lineSeparator())));

    }

    static class Permutation {
        private int n;
        private int r;
        private int cur;
        private Deque<Integer> deque;
        private List<List<Integer>> result;
        private boolean[] selected;

        public Permutation(int n, int r) {
            this.n = n;
            this.r = r;
            init();
        }

        private void init() {
            cur = 0;
            deque = new ArrayDeque<>();
            result = new ArrayList<>();
            selected = new boolean[n];
        }

        public List<List<Integer>> result() {
            init();
            premutation();

            return result;
        }

        private void premutation() {
            if (cur == r) {
                result.add(new ArrayList<>(deque));
            } else {
                for (int i = 0; i < n; i++) {
                    if (!selected[i]) {
                        selected[i] = true;
                        deque.add(i + 1);
                        cur++;
                        premutation();
                        cur--;
                        deque.removeLast();
                        selected[i] = false;
                    }
                }
            }
        }
    }
}

public class Boj15649_N과M1 {
}
