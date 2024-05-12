package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
https://www.acmicpc.net/problem/15970

1. 색깔별로 분류 o(n)

2. 색깔을 위치별로 정렬 o(n log n)

 */

class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        /*
         * 예제입력
         * 5 // 2 <= N <= 5000. N개 만큼 x와 y가 주어진다.
         * 0 1 // 위치 0 <= x <= 10^5, 색깔 1 <= y <= N
         * 1 2
         * 3 1
         * 4 2
         * 5 1
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        class Point {
            final int cord;
            final int color;

            public Point(int cord, int color) {
                this.cord = cord;
                this.color = color;
            }
        }

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] pointsRaw = br.readLine().split(" ");
            points.add(new Point(Integer.parseInt(pointsRaw[0]), Integer.parseInt(pointsRaw[1])));
        }

        Map<Integer, List<Point>> pointsGroupByColor = points.stream()
                .collect(Collectors.groupingBy(point -> point.color));

        pointsGroupByColor.values()
                .stream()
                .forEach(it -> it.sort(Comparator.comparingInt(value -> value.cord)));

        int sumOfDistance = 0;

        for (List<Point> eachPoints : pointsGroupByColor.values()) {
            
            for (int i = 0; i <eachPoints.size(); i++) {
                int left = Integer.MAX_VALUE;
                if (i > 0) {
                    left = Math.abs(eachPoints.get(i - 1).cord - eachPoints.get(i).cord);
                }

                int right = Integer.MAX_VALUE;
                if (i < eachPoints.size() - 1) {
                    right = Math.abs(eachPoints.get(i).cord - eachPoints.get(i + 1).cord);
                }
                
                sumOfDistance += Math.min(left, right);
            }          
        }

        System.out.println(sumOfDistance);
    }

}

public class Boj15970_화살표그리기 {

}