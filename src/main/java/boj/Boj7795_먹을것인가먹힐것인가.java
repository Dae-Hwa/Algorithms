package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*

A는 자기보다 크기가 작으면 B를 먹을 수 있다. 같으면 불가

가능한 조합 구하기

1 <= N,M <= 20000
조합으로 불가(12!가 약 5억개)

20000 log 20000 = 180 => 정렬, 탐색 여러번해도 여유 있음

정렬해서 범위를 찾기?

A
8 1 7 3 1

B
3 6 1

A
1 1 3 7 8

B
1 6 3

처음부터 끝까지 찾으려면 20000^2 = 4억건 불가 => 이분 탐색으로

자기보다 같거나 큰 수와 작은 수의 경계에서 왼쪽 개수를 세면 찾을 수 있음. n번 반복

 */
class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var T = Integer.parseInt(br.readLine());
        var As = new ArrayList<List<Integer>>();
        var Bs = new ArrayList<List<Integer>>();
        for (int i = 0; i < T; i++) {
            String[] NM = br.readLine().split(" ");
            var A = Arrays.asList(br.readLine().split(" "))
                    .stream()
                    .map(Integer::parseInt).collect(Collectors.toList());
            As.add(A);

            var B = Arrays.asList(br.readLine().split(" "))
                    .stream()
                    .map(Integer::parseInt).collect(Collectors.toList());
            Bs.add(B);
        }

        var results = new ArrayList<Integer>();
        for (int i = 0; i < T; i++) {
            List<Integer> A = As.get(i);
            List<Integer> B = Bs.get(i);
            int result = bSearch(A, B);
            results.add(result);
        }

        System.out.println(results.stream().map(Object::toString).collect(Collectors.joining(System.lineSeparator())));

    }

    static int bSearch(List<Integer> A, List<Integer> B) {
        A.sort(Comparator.naturalOrder());
        B.sort(Comparator.naturalOrder());

        int count = 0;

        for (int i = 0; i < A.size(); i++) {

            int a = A.get(i);

            int left = 0;
            int right = B.size();
            int last = 0;
            while (left < right - 1) {
                int cur = (left + right) / 2;
                // System.out.println(cur);
                int b = B.get(cur);

                if (a <= b) {
                    // a가 작거나 같으면 오른쪽을 당겨준다(오른쪽에 있는 b는 모두 a보다 크거나 같다)
                    right = cur;
                } else {
                    left = cur;
                    last = cur;
                }
            }
            // System.out.println("A: " + a + ", B: " + B.get(last) + ", last: " + last);
            count += last + 1;
            if (a <= B.get(last)) count--;
        }

        return count;
    }
}

public class Boj7795_먹을것인가먹힐것인가 {

}
