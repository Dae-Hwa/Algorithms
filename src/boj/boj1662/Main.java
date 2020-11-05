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
        Decompressor decompressor = new Decompressor();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c != '(') {
                decompressor.add(c, i + 1 < input.length() && input.charAt(i + 1) == '(');
            }

        }

        decompressor.printTop();
    }
}

class Decompressor {
    private Stack<Integer> stack = new Stack<>();

    public Decompressor() {
        stack.push(0);
    }

    public void add(char c, boolean needNext) {
        if (needNext) {
            addNext(c);

            return;
        }

        if (c == ')') {
            decompress();

            return;
        }

        stack.push(stack.pop() + 1);
    }

    private void decompress() {
        int target = stack.pop();
        int multiplyNumber = stack.pop();
        int cur = target * multiplyNumber + stack.pop();

        stack.push(cur);
    }

    private void addNext(char c) {
        stack.push(Integer.parseInt(String.valueOf(c)));
        stack.push(0);
    }

    public void printTop() {
        System.out.println(stack.peek());
    }
}
