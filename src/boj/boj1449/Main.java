package boj.boj1449;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int L = Integer.parseInt(input[1]);

        int[] spots = Arrays.stream(br.readLine().split(" "))
                              .mapToInt(Integer::parseInt)
                              .toArray();

        Arrays.sort(spots);

        int cur = 0;

        int count = N;

        for (int spot : spots) {
            if (spot <= cur) {
                count--;
                continue;
            }

            cur = spot + L - 1;
        }

        System.out.println(count);
    }
}
