package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
문제 정의

용액의 특성값 산성은 1부터 1000000000(10억), 알칼리는 음수

두 용액을 혼합하여 0에 가장 가까운 용액 만들기

최대,최소값 |2000000000| 
용액의 수 N 2 이상 1000000 이하 -> 완전탐색 불가 

-99 -2 -1 4 98

하나씩 돌아가면서 이분탐색 한다면? 약 천만번 -> 가능

// 결과는 오름차순, 경우의 수 고려 X(가장 빨리 찾는 하나)
 */
class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> solutions = Stream.of(br.readLine().split(" ")).map(Integer::parseInt)
                .collect(Collectors.toList());
        solutions = solutions.stream().sorted().collect(Collectors.toList());

        int source = Integer.MAX_VALUE;
        int target = 0;
        for (int i = 0; i < solutions.size(); i++) {
            var sourceSolution = solutions.get(i);
            int left = 0;
            int right = solutions.size() - 1;

            while (left < right) {
                int cur = (left + right) / 2;
                int targetSolutionCandidate = solutions.get(cur);
                int currentSolution = sourceSolution + targetSolutionCandidate;

                if (Math.abs(currentSolution) < Math.abs(source + target)) {
                    if (i != cur) {
                        source = sourceSolution;
                        target = targetSolutionCandidate;
                    }
                }

                // 결과가 음수면 오른쪽으로 가야함. 양수면 왼쪽으로 가야함
                if (currentSolution < 0) {
                    left = cur + 1;
                } else if (currentSolution > 0) {
                    right = cur;
                } else {
                    break;
                }
            }
        }

        System.out.println(Math.min(source, target) + " " + Math.max(source, target));
    }
}

public class Boj2470_두용액 {

}
