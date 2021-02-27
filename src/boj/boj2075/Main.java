package boj.boj2075;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            numbers.addAll(
                    Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .boxed()
                            .collect(Collectors.toList())

            );
        }

        numbers.sort(Comparator.reverseOrder());

        System.out.println(numbers.get(N - 1));
    }
}
