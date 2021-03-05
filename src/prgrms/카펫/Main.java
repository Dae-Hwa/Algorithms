package prgrms.카펫;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Main().solution(24, 24)));
    }

    public int[] solution(int brown, int yellow) {
        int y = 3;
        int sumXY = (brown + 4) / 2;

        while ((sumXY - y) * y != brown + yellow) {
            y++;
        }

        return new int[]{sumXY - y, y};
    }
}
