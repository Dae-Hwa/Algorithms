package boj.boj2800;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<String> inputs = Arrays.asList(
//                "(0/(0))",
//                "((((((((((+))))))))))",
                "(((1)))(2)"
        );
        for (String input : inputs) {
            System.out.println("============");
            printResult(input);
        }
    }

    public static void printResult(String input) {
        Main.solution(input);
    }

    public static void createNodesTest(String input) {
        System.out.println(Nodes.create(input));
    }

    public static void combinationTest() {
        System.out.println(Combinations.createBy(4, 3));
    }
}
