package boj.boj1009;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Object> inputs = new ArrayList<>(
                Arrays.asList(
                        new int[]{1, 6},
                        new int[]{3, 7},
                        new int[]{6, 2},
                        new int[]{7, 100},
                        new int[]{9, 635},
                        new int[]{93, 4000001},
                        new int[]{19, 2},
                        new int[]{2, 16},
                        new int[]{10, 1},
                        new int[]{99 , 2}
                )
        );

        for (Object inputObj : inputs) {
            int[] input = (int[]) inputObj;

            System.out.println(Main.getEndNumberOf(input[0], input[1]));
        }

    }
}
