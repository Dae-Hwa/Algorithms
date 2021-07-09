package 백준.단어수학1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        System.out.println(solution(words));
    }

    public static int solution(String[] words) {
        Map<Character, Integer> wordPoints = new HashMap<>();

        // 수는 최대 8자리
        for (int i = 1; i <= 8; i++) {
            Map<Character, Integer> numberOfWords = new HashMap<>();

            // words 순회하며 해당 자릿수의 word 개수 파악
            for (String word : words) {
                if (i <= word.length()) {
                    char curWord = word.charAt(word.length() - i);

                    int numberOfWord = numberOfWords.getOrDefault(curWord, 0);
                    numberOfWords.put(curWord, numberOfWord + 1);
                }
            }

            // 각 자릿수만큼 곱하여 누적 point계산
            for (Map.Entry<Character, Integer> entry : numberOfWords.entrySet()) {
                int wordPoint = wordPoints.getOrDefault(entry.getKey(), 0);

                wordPoint += Math.pow(10, i - 1) * entry.getValue();

                wordPoints.put(entry.getKey(), wordPoint);
            }
        }

        Deque<Character> sortedWords = wordPoints.entrySet().stream()
                                               .sorted((o1, o2) -> Comparator.<Integer>reverseOrder().compare(o1.getValue(), o2.getValue()))
                                               .map(characterIntegerEntry -> characterIntegerEntry.getKey())
                                               .collect(Collectors.toCollection(ArrayDeque::new));

        String[] mappedWord = Arrays.copyOf(words, words.length);

        for (int i = 9; 0 <= i; i--) {
            String curCharacter = String.valueOf(sortedWords.pollFirst());
            for (int j = 0; j < mappedWord.length; j++) {
                mappedWord[j] = mappedWord[j].replaceAll(curCharacter, String.valueOf(i));
            }
        }

        int result = 0;

        for (String word : mappedWord) {
            result += Integer.parseInt(word);
        }

        return result;
    }
}

public class 단어수학 {
    public static void main(String[] args) {
//        int result = Main.solution(new String[]{"AAA", "AAA"});
//        int result = Main.solution(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"});
        int result = Main.solution(new String[]{"ABB", "BB", "BB", "BB"});

        System.out.println(result);
    }
}
