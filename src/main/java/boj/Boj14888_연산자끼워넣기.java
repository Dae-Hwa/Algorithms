package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        MinMaxCalculator calculator = MinMaxCalculator.of(A, operators);

        System.out.println(calculator.max);
        System.out.println(calculator.min);
    }

    static class MinMaxCalculator {
        private final int n;
        private final int r;
        private final int[] numbers;
        private final List<String> operators;
        private final boolean[] visited;

        private int cur = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        private MinMaxCalculator(int n, int r, int[] numbers, List<String> operators, boolean[] visited) {
            this.n = n;
            this.r = r;
            this.numbers = numbers;
            this.operators = operators;
            this.visited = visited;
        }

        public static MinMaxCalculator of(int[] numbers, List<String> operators) {
            int operatorsSize = operators.size();
            MinMaxCalculator minMaxCalculator = new MinMaxCalculator(
                    operatorsSize,
                    operatorsSize,
                    numbers,
                    operators,
                    new boolean[operatorsSize]
            );

            minMaxCalculator.calculateUsingPermutation(numbers[0]);

            return minMaxCalculator;
        }

        private void calculateUsingPermutation(int currentValue) {
            if (cur == r) {
                max = Integer.max(max, currentValue);
                min = Integer.min(min, currentValue);
            } else {
                for (int i = 0; i < n; i++) {
                    if (!visited[i]) {
                        visited[i] = true;
                        int currentResult = calculate(currentValue, operators.get(i), numbers[cur + 1]);
                        cur++;
                        calculateUsingPermutation(currentResult);
                        cur--;
                        visited[i] = false;
                    }
                }
            }
        }

        private int calculate(int currentValue, String operator, int operand) {
            int result = currentValue;
            if (operator.equals("+")) {
                result += operand;
            } else if (operator.equals("-")) {
                result -= operand;
            } else if (operator.equals("*")) {
                result *= operand;
            } else if (operator.equals("/")) {
                result /= operand;
            }

            return result;
        }
    }
}

public class Boj14888_연산자끼워넣기 {
}
