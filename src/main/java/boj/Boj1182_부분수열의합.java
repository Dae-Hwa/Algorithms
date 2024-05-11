package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
4 0
3 -3 3 -3


 */

class Main {
    /*
     * 
     * N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는
     * 프로그램을 작성하시오.
     * 
     * 
     * 0 1
     * 0 2
     * 0 3
     * 0 4
     * 0 5
     * 
     * 1 0 -> 필요 없다
     * 1 1 -> 필요 없다
     * 
     * 1 2q
     * 1 3
     * 1 4
     * 1 5
     * 1 6
     ** 
     * 중복을 허용하지 않는 조합으로
     * 
     */

    public static void main(String[] args) throws IOException {
        /**
         * 
         * 
         * 입력 예시
         * 5 0 // 정수의 개수를 나타내는 1 <= N <= 20, 정수 | S | <= 1,000,000
         * -7 -3 -2 5 8 | n | < 100,000
         * 
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");

        int N = Integer.parseInt(firstLine[0]);
        int S = Integer.parseInt(firstLine[1]);
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 조합 N C 1 ... N C N 까지 돌려야 함
        int result = 0;
        for (int i = 1; i <= numbers.length; i++) {
            int[] resultHolder = new int[1];
            int r = i;
            combination(N, r, 0, new int[N], numbers, 0, S, resultHolder);
            result += resultHolder[0];
        }

        System.out.println(result);
    }

    static void combination(int n, int r, int cur, int[] temp, int[] numbers, int value, int target,
            int[] resultHolder) {
        if (r == 0) {
            // System.out.println(cur);
            // System.out.println(Arrays.toString(temp));
            // 조합 돌릴린 결과의 합이 S가 되면 결과에 +
            if (value == target) {
                resultHolder[0]++;
            }
        } else {
            for (int i = cur; i < n; i++) {
                value += numbers[i];
                temp[i] = numbers[i];
                combination(n, r - 1, i + 1, temp, numbers, value, target, resultHolder);
                temp[i] = 0;
                value -= numbers[i];
            }
        }
    }
}

public class Boj1182_부분수열의합 {

}
