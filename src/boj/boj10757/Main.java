package boj.boj10757;

import java.io.*;

public class Main {
    private static final int BUFFER_SIZE = 8;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");


        int[] A = new int[input[0].length() / BUFFER_SIZE + (input[0].length() % BUFFER_SIZE != 0 ? 1 : 0)];
        int[] B = new int[input[1].length() / BUFFER_SIZE + (input[1].length() % BUFFER_SIZE != 0 ? 1 : 0)];

        for (int i = 0; i < A.length; i++) {
            int left = input[0].length() - (i * BUFFER_SIZE) - BUFFER_SIZE;
            int right = input[0].length() - (i * BUFFER_SIZE);

            A[i] = Integer.parseInt(input[0].substring(left < 0 ? 0 : left, right));
        }

        for (int i = 0; i < B.length; i++) {
            int left = input[1].length() - (i * BUFFER_SIZE) - BUFFER_SIZE;
            int right = input[1].length() - (i * BUFFER_SIZE);

            B[i] = Integer.parseInt(input[1].substring(left < 0 ? 0 : left, right));
        }

        int[] result = new int[Math.max(A.length, B.length) + 1];

        for (int i = 0; i < result.length - 1; i++) {
            result[i] += (A.length <= i ? 0 : A[i]) + (B.length <= i ? 0 : B[i]);

            int overflow = (int) Math.pow(10, BUFFER_SIZE);

            if (overflow - 1 < result[i]) {
                result[i + 1] = 1;
                result[i] %= overflow;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (result[result.length - 1] != 0) {
            sb.append(result[result.length - 1]);
        }


        for (int i = result.length - 2; 0 <= i; i--) {
            String cur = String.valueOf(result[i]);
            if (sb.length() != 0) {
                while (cur.length() < BUFFER_SIZE) {
                    cur = "0" + cur;
                }
            }

            sb.append(cur);
        }

        System.out.println(sb);
    }
}
