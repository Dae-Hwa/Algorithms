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
N개 중에 중복 없이 M 개를 고른 수열
고른 수열은 오름차순 => 조합


 */
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        List<List<Integer>> result = new Combintation(N, M).result();

        System.out.println(result.stream()
                .map(it -> it.stream().map(Object::toString).collect(Collectors.joining(" ")))
                .collect(Collectors.joining(System.lineSeparator())));
    }

    static class Combintation {
        private int n;
        private int r;
        private int cur;
        private Deque<Integer> deque;
        private List<List<Integer>> result;

        public Combintation(int n, int r) {
            this.n = n;
            this.r = r;
        }

        private void init() {
            cur = 0;
            deque = new ArrayDeque<>();
            result = new ArrayList<>();
        }

        public List<List<Integer>> result() {
            init();
            combination(0);
            return result;
        }

        private void combination(int next) {
            if (cur == r) {
                result.add(new ArrayList<>(deque));
            } else {
                for (int i = next; i < n; i++) {
                    deque.add(i + 1);
                    cur++;
                    // 다음 선택이 오름차순이어야 한다. => 넘길때 지금보다 커야함. 지금 숫자 = i
                    combination(i + 1);
                    deque.removeLast();
                    cur--;
                }
            }
        }

    }
}

public class Boj15650_n과m2 {

}
