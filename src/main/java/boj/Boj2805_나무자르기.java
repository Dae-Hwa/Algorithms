package boj;
/*

절단기의 높이를 지정하면 절단기보다 높이가 높은 나무만 벨 수 있음

N[20, 15, 10, 17], H = 15 => N[15, 15, 10, 15] result = 5 + 2

M미터의 나무를 집에 가져가기 위한 높이 H의 최대값

입출력, 복잡도, 최소최대값

1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000

0 <= H(N) <= 1,000,000,000

M에서 H를 계속 빼주면 될 것. 하지만 H(N)이 1,000,000,000 이고 1,000,000번 나오는 경우?

그냥 다 더하면 범위 초과. M최대값보다 높으면 끝내도록 하자.

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var firstLine = br.readLine().split(" ");

        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);

        List<Integer> trees = Stream.of(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        // int N = 4;
        // int M = 7;
        // List<Integer> trees = List.of(20, 15, 10, 17);
        // int N = 5;
        // int M = 20;
        // List<Integer> trees = List.of(4, 42, 40, 26, 46);
        // int N = 4;
        // int M = 2_000_000_000;
        // List<Integer> trees = List.of(1_000_000_000, 1_000_000_000, 1_000_000_000, 1_000_000_000);

        // 높이 범위를 0~ max(H(N))으로 고정시켜놓자 => int[] heights
        final int MIN_HEIGHT = 0;
        final int MAX_HEIGHT = 1_000_000_000;

        // 각 height 를 선택했을때 필요한 나무길이와 비교해서 대소 비교 후 left, right 갱신
        // - 나무 길이는 전체에서 height보다 같거나 큰거 필터 후 자르고 sum
        int result = findOptHeight(MIN_HEIGHT, MAX_HEIGHT, M, trees);

        System.out.println(result);
    }

    static int findOptHeight(int left, int right, int M, List<Integer> trees) {
        int H = Integer.MIN_VALUE;
        while (left < right) {
            int mid = (left + right) / 2;

            // 적어도 M개 만큼은 가져가야 한다 -> 결과가 M개보다 작으면 H를 낮춰야 한다. -> right를 오른쪽으로
            if (sumOfCutTrees(mid, trees, M) < M) {
                right = mid;
            } else {
                H = mid;
                left = mid + 1;
            }
        }

        return H;
    }

    static long sumOfCutTrees(int cutterHeight, List<Integer> trees, int M) {
        long result = 0;
        for (Integer treeHeight : trees) {
            var cutTreeHeight = treeHeight - cutterHeight;
            if (cutTreeHeight > 0) {
                result += cutTreeHeight;
            }

            // M최대값보다 높으면 끝내도록 하자.
            if (result >= M) {
                return result;
            }
        }

        return result;
    }
}

public class Boj2805_나무자르기 {

}
