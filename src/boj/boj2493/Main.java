package boj.boj2493;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] towers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] results = new int[N];

        Stack<Integer> stack = new Stack<>();

        for (int cur = 1, max = 0; cur < N; cur++) {
            if (towers[cur] < towers[max]) {
                results[cur] = max + 1;

                while (!stack.isEmpty() && towers[stack.peek()] < towers[cur]) {
                    stack.pop();
                }

                if (!stack.isEmpty()) {
                    results[cur] = stack.peek() + 1;
                }

                stack.push(cur);
            } else {
                max = cur;
                stack.clear();
            }
        }

        String result = Arrays.stream(results)
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}
