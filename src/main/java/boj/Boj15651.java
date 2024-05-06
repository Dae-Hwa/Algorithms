package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
1부터 N까지 자연수 중 M개를 고른 수열
중복 가능

(1 ≤ M ≤ N ≤ 7)
 */
class Main {

    static class Input {
        final int n;
        final int m;

        public Input() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            this.n = n;
            this.m = m;
        }
    }

    public static void main(String[] args) throws IOException {

        Input input = new Input();

        List<List<Integer>> result = permutation(input.n, input.m);


        String resultString =
                result.stream()
                        .map(it -> it.stream().map(Object::toString).collect(Collectors.joining(" ")))
                        .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(resultString);
    }

    static List<List<Integer>> permutation(int n, int r) {

        int[] checked = new int[r];
        List<List<Integer>> result = new ArrayList<>();

        permutationInner(n, r, 0, checked, result);

        return result;
    }

    static void permutationInner(int n, int r, int cur, int[] checked, List<List<Integer>> result) {
        if (cur == r) {
            result.add(Arrays.stream(checked).boxed().collect(Collectors.toList()));
        } else {
            for (int i = 0; i < n; i++) {
                checked[cur] = i + 1;
                cur++;
                permutationInner(n, r, cur, checked, result);
                cur--;
            }
        }
    }
}


public class Boj15651 {
}
