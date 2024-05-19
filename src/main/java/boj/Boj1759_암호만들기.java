package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/* 

암호
1 서로 다른 L개의 알파벳 소문자
2 최소 한 개의 모음(a e i o u)과 최소 두 개의 자음으로 구성
3 알파벳이 증가하는 순서로 배열 되었을 것 => 오름차순 => 조합(중복 x)
  e.g. abc (o) bac (x)

  사용했을법한 문자의 종류는 C가지

3 ≤ L ≤ C ≤ 15

문자들은 알파벳 소문자, 중복되는 것은 없다.

- 처음에 알파벳 정렬
- 후보로 넣을때 조건 2번 만족하는지 판단

*/

class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int L = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);
        List<String> alplhabets = List.of(br.readLine().split(" "));

        List<String> candidates = PassWordCandidateCreator.candidates(L, C, alplhabets);
        System.out.println(
                candidates.stream()
                        .collect(Collectors.joining(System.lineSeparator())));
    }

    static class PassWordCandidateCreator {
        private static List<String> CONSONANTS = Collections.unmodifiableList(Arrays.asList("a", "e", "i", "o", "u"));
        private static int VALID_CONSONANTS_COUNT = 1;
        private static int VALID_VOWELS_COUNT = 2;

        private int n;
        private int r;
        private List<String> alphabets;
        private Deque<String> candidate = new ArrayDeque<>();
        private List<String> candidates = new ArrayList<>();

        private PassWordCandidateCreator(int n, int r, List<String> alphabets) {
            this.n = n;
            this.r = r;
            this.alphabets = alphabets;
        }

        static List<String> candidates(int L, int C, List<String> alphabets) {
            var alphabetsSorted = new ArrayList<>(alphabets);
            Collections.sort(alphabetsSorted);

            var passWordCandidateCreator = new PassWordCandidateCreator(C, L, alphabetsSorted);
            passWordCandidateCreator.combination(0, 0);
            return passWordCandidateCreator.candidates;
        }

        private void combination(int cur, int next) {
            if (cur == r) {
                if (isCurrentCandidateValid()) {
                    candidates.add(candidate.stream().collect(Collectors.joining()));
                }
            } else {
                for (int i = next; i < n; i++) {
                    candidate.add(alphabets.get(i));
                    combination(cur + 1, i + 1);
                    candidate.removeLast();
                }
            }
        }

        private boolean isCurrentCandidateValid() {
            long consonantsCount = candidate.stream().filter(it -> CONSONANTS.contains(it)).count();
            long vowelsCount = candidate.size() - consonantsCount;
            return consonantsCount >= VALID_CONSONANTS_COUNT && vowelsCount >= VALID_VOWELS_COUNT;
        }
    }
}

public class Boj1759_암호만들기 {

}
