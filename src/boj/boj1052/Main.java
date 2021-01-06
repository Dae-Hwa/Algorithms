package boj.boj1052;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int N = input[0];
        int K = input[1];
        int count = 0;

        while (K < Long.bitCount(N)) {
            N++;
            count++;
        }

        System.out.println(count);
    }
}

