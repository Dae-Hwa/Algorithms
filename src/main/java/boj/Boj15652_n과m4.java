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
고른 수열은 비내림차순이어야 한다

=> 중복을 허용하는 조합
*/
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        List<List<Integer>> result = Combination.result(N, M);

        System.out.println(
                result.stream()
                        .map(it -> it.stream().map(Object::toString).collect(Collectors.joining(" ")))
                        .collect(Collectors.joining(System.lineSeparator())));
    }

    static class Combination {
        private int n;
        private int r;
        private Deque<Integer> deque = new ArrayDeque<>();
        private List<List<Integer>> result = new ArrayList<>();

        private Combination(int n, int r) {
            this.n = n;
            this.r = r;
        }

        static List<List<Integer>> result(int n, int r) {
            Combination combination = new Combination(n, r);
            combination.combination(0, 0);
            return combination.result;
        }

        private void combination(int cur, int next) {
            if (cur == r) {
                result.add(new ArrayList<>(deque));
            } else {
                for (int i = next; i < n; i++) {
                    deque.add(i + 1);
                    // 비내림차순 => 다음이 지금보다 같거나 크면 된다. => 다음 수는 지금(i)과 같다
                    combination(cur + 1, i);
                    deque.removeLast();
                }
            }
        }
    }
}

public class Boj15652_n과m4 {

}
