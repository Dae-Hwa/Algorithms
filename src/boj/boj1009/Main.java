package boj.boj1009;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] datas = new int[Integer.parseInt(br.readLine())][];

        for (int i = 0; i < datas.length; i++) {
            datas[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for (int[] data : datas) {
            System.out.println(getEndNumberOf(data[0], data[1]));
        }
    }

    public static int getEndNumberOf(int number, int power) {
        number = number % 10;
        int result = number;

        for (int i = 0; i < power - 1; i++) {
            result = (result * number) % 10;
        }

        if (result == 0) {
            result = 10;
        }

        return result;
    }
}

