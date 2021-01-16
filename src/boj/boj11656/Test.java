package boj.boj11656;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Object> inputs = new ArrayList<>(Arrays.asList(
                "baekjoon"
                ,
                "banana"
                ,
//                "aaa"
//                ,
                "abcabcabcabac"
        ));

        for (int i = 0; i < inputs.size(); i++) {
//            List<Object> input = (List<Object>) inputs.get(i);

            Main.solution((String) inputs.get(i));

        }
    }
}
