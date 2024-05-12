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
P[0] ... P[N-1] : 0 부터 N-1 까지의 수를 한 번씩 포함하고 있는 수열.
배열 A에 적용하면 배열 B가 된다.

적용 방법은 B[P[i]] = A[i]

예를 들어
i  P  A    B         정렬하면
0  1  2    B[1] = 2  B[0] = 1   
1  2  3    B[2] = 3  B[1] = 2   
2  0  1    B[0] = 1  B[2] = 3

결과가 비 내림차순이 되는 수열을 찾는다.
비 내림차순은 원소가 바로앞에 있는 원소보다 크거나 같은 경우
수열이 여러개면 사전순으로 앞서는 것을 선택

-> P를 찾아야 하는것

50 ^ 50이므로 완전 탐색은 안 됨.

A를 정렬시킨 결과에서 원래 인덱스를 가져온다?

e.g.
  
A 2, 3, 1 -> 1, 2, 3
i 0  1  2 -> 2  0  1
             0  1  2

i를 기준으로 다시 정렬하면 P를 구할 수 있다

2 3 1
0 1 2
1 2 0


예시입력/출력

3
2 3 1

1 2 0




4
2 1 3 1

2 0 3 1

8
4 1 6 1 3 6 1 4

4 0 6 1 3 7 2 5
 */
class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // /*
        // * N : 배열 A의 크기, 1 <= N <= 50
        // */
        int N = Integer.parseInt(br.readLine());
        // /*
        // * 배열 A의 원소가 순서대로 주어짐. 1 <= A[n] <= 1000
        // */
        int[] A = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        class IndexMapping {
            final int a;
            final int indexBeforeSort;

            public IndexMapping(int a, int indexBeforeSort) {
                this.a = a;
                this.indexBeforeSort = indexBeforeSort;
            }
        }

        List<IndexMapping> indexMappings = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            indexMappings.add(new IndexMapping(A[i], i));
        }
        indexMappings.sort(Comparator.comparingInt(it -> it.a));
        
        int[] result = new int[N];
        for (int i = 0; i < indexMappings.size(); i++) {
            result[indexMappings.get(i).indexBeforeSort] = i;
        }

        String resultMessage = Arrays.stream(result)
                .mapToObj(it -> String.valueOf(it))
                .collect(Collectors.joining(" "));
        System.out.println(resultMessage);
    }
}

public class Boj1015_수열정렬 {
}
