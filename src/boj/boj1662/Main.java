package boj.boj1662;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        solution(input);
    }

    public static void solution(String input) {

        Stack<Integer> stack = new Stack<>();

        stack.push(0);

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == ')') {

                int target = Integer.parseInt(stack.pop().toString());

                int multiplyNumber = Integer.parseInt(stack.pop().toString());

                int cur = target * multiplyNumber + Integer.parseInt(stack.pop().toString());

                stack.push(cur);

                continue;
            }

            if (i + 1 < input.length() && input.charAt(i + 1) == '(') {
                stack.push(Integer.parseInt(String.valueOf(c)));
                stack.push(0);
                continue;
            }

            if (c != '(') {
                stack.push(stack.pop() + 1);
            }
        }

        System.out.println(stack.pop());
    }
}