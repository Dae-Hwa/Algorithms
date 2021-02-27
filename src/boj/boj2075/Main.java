package boj.boj2075;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());


        int[][] numbers = new int[N][N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int result = Arrays.stream(numbers)
                .flatMapToInt(Arrays::stream)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .limit(N)
                .collect(Collectors.toList())
                .get(N - 1);


        System.out.println(result);
    }
}
