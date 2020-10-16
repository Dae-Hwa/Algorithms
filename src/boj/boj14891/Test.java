package boj.boj14891;

public class Test {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static void test1() {
        String[] gearsInput = new String[]{
                "10101111",
                "01111101",
                "11001110",
                "00000010"
        };

        Gears gears = Gears.create(gearsInput);

        int K = 2;

        int[][] rotateStatus = new int[][]{
                new int[]{3, -1},
                new int[]{1, 1}
        };

        for (int i = 0; i < rotateStatus.length; i++) {
            gears.rotate(rotateStatus[i][0], rotateStatus[i][1]);
        }

        System.out.println(gears.getScore());
    }

    public static void test2() {
        String[] gearsInput = new String[]{
                "11111111",
                "11111111",
                "11111111",
                "11111111"
        };

        Gears gears = Gears.create(gearsInput);

        int[][] rotateStatus = new int[][]{
                new int[]{1, 1},
                new int[]{2, 1},
                new int[]{3, 1}
        };

        for (int i = 0; i < rotateStatus.length; i++) {
            gears.rotate(rotateStatus[i][0], rotateStatus[i][1]);
        }

        System.out.println(gears.getScore());
    }

    public static void test3() {
        String[] gearsInput = new String[]{
                "10001011",
                "10000011",
                "01011011",
                "00111101"
        };

        Gears gears = Gears.create(gearsInput);

        System.out.println(gears);

        int[][] rotateStatus = new int[][]{
                new int[]{1, 1},
                new int[]{2, 1},
                new int[]{3, 1},
                new int[]{4, 1},
                new int[]{1, -1}

        };

        for (int i = 0; i < rotateStatus.length; i++) {
            gears.rotate(rotateStatus[i][0], rotateStatus[i][1]);
            System.out.println(gears);
        }

        System.out.println(gears.getScore());
    }
}
