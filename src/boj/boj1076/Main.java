package boj.boj1076;

import java.io.*;

enum Color {
    black(0, 1),
    brown(1, 10),
    red(2, 100),
    orange(3, 1000),
    yellow(4, 10000),
    green(5, 100000),
    blue(6, 1000000),
    violet(7, 10000000),
    grey(8, 100000000),
    white(9, 1000000000);

    final long VALUE;
    final long MULTIPLE;

    Color(long VALUE, int MULTIPLE) {
        this.VALUE = VALUE;
        this.MULTIPLE = MULTIPLE;
    }

    public static Color getColor(String colorName) {
        for (Color color : values()) {
            if (color.name().equals(colorName)) {
                return color;
            }
        }

        throw new IllegalArgumentException("없는 색깔");
    }
}

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] colors = new String[3];

        colors[0] = br.readLine();
        colors[1] = br.readLine();
        colors[2] = br.readLine();

        long first = Color.getColor(colors[0]).VALUE;
        long second = Color.getColor(colors[1]).VALUE;

        long result = (first * 10 + second) * Color.getColor(colors[2]).MULTIPLE;

        System.out.println(result);
    }
}
