package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/*
1부터 N까지 자연수 중에서 M개를 고른 수열
같은 수를 여러 번 골라도 된다.

=> 중복 순열 n^m

입력 1 <= M <= N <= 7
최대치 7^7 = 823543

 */
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        List<List<Integer>> result = new Permutation(N, M).result();
        System.out.println(result.stream()
                .map(it -> it.stream().map(Object::toString).collect(Collectors.joining(" ")))
                .collect(Collectors.joining(System.lineSeparator())));
    }

    static class Permutation {
        private final int n;
        private final int r;
        private Deque<Integer> deque;
        private List<List<Integer>> result;

        public Permutation(int n, int r) {
            this.n = n;
            this.r = r;
        }

        public List<List<Integer>> result() {
            deque = new ArrayDeque<>();
            result = new ArrayList<>();
            recursive(0);
            return result;
        }

        private void recursive(int cur) {
            if (cur == r) {
                result.add(deque.stream().collect(Collectors.toList()));
            } else {
                for (int i = 0; i < n; i++) {
                    deque.add(i + 1);
                    recursive(cur + 1);
                    deque.removeLast();
                }
            }
        }
    }
}

public class Boj15651_n과m3 {
}
