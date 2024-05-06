package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*

수와 수 사이에 연산자 넣은 최대값과 최소값

e.g.
3 4 5 와 +, *가 주어질 때

// 연산자 우선순위는 무시한다
3 + 4 * 5  -> 7 * 5 -> 35
3 * 4 + 5 -> 12 + 5 -> 17

 */
class Main {

    public static void main(String[] args) throws IOException {
        /*
        2           2 <= N <= 11
        5 6         1 <= A1 ... An <= 100
        0 0 1 0     p, s, m, d (+, -, *, /), p + s + m + d <= N - 1
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] operatorsCount = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

//        int N = 6;
//        int[] A = new int[]{1, 2, 3, 4, 5, 6};
//        int[] operatorsCount = new int[]{2, 1, 1, 1};
        List<String> operators = new ArrayList<>();

        for (int i = 0; i < operatorsCount.length; i++) {
            int cur = operatorsCount[i];
            for (int j = 0; j < cur; j++) {
                if (i == 0) {
                    operators.add("+");
                } else if (i == 1) {
                    operators.add("-");
                } else if (i == 2) {
                    operators.add("*");
                } else if (i == 3) {
                    operators.add("/");
                } else throw new RuntimeException();
            }
        }

//        System.out.println(operators);

        // 연산자 순열 구하기
        List<List<String>> operatorPermutations = new ArrayList<>();
        permutation(operators.size(), operators.size(), 0, 0, operators, new String[operators.size()], new boolean[operators.size()], operatorPermutations);
//        operatorPermutation.forEach(System.out::println);

        // 연산자 반복하면서 min max 구하기
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (List<String> operatorPermutation : operatorPermutations) {
            int result = A[0];
            for (int i = 0; i < operatorPermutation.size(); i++) {
                String operator = operatorPermutation.get(i);

                if (operator.equals("+")) {
                    result += A[i + 1];
                } else if (operator.equals("-")) {
                    result -= A[i + 1];
                } else if (operator.equals("*")) {
                    result *= A[i + 1];
                } else if (operator.equals("/")) {
                    result /= A[i + 1];
                }
            }
            max = Integer.max(max, result);
            min = Integer.min(min, result);
        }

        System.out.println(max);
        System.out.println(min);
    }

    static void permutation(int n, int r, int p, int cur, List<String> target, String[] temp, boolean[] visited, List<List<String>> result) {
        if (cur == r) {
            result.add(Arrays.stream(temp).collect(Collectors.toList()));
        } else {
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    temp[cur] = target.get(i);
                    cur++;
                    permutation(n, r, p, cur, target, temp, visited, result);
                    cur--;
                    visited[i] = false;
                }
            }
        }
    }
}

public class Boj14888_연산자끼워넣기 {
}
