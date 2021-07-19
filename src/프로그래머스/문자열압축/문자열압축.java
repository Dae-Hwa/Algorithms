package 프로그래머스.문자열압축;

class Solution {
    public int solution(String s) {

        int max = (s.length() + 1) / 2;

        int cur = 0;
        int result = s.length();

        while (cur < max) {
            cur++;

            StringBuilder compressedString = new StringBuilder();
            String compressingMeasure = "";
            int count = 1;

            for (int i = 0; i < s.length(); i += cur) {
                int nextIndex = i + cur < s.length() ? i + cur : s.length();
                String curString = s.substring(i, nextIndex);

                if (compressingMeasure.equals(curString)) {
                    count++;
                } else {
                    String temp = (1 < count ? String.valueOf(count) : "") + compressingMeasure;
                    compressedString.append(temp);
                    count = 1;
                }

                compressingMeasure = curString;
            }

            String temp = (1 < count ? String.valueOf(count) : "") + compressingMeasure;
            compressedString.append(temp);

            if (compressedString.length() < result) {
                result = compressedString.length();
            }
        }

        return result;
    }
}

public class 문자열압축 {
    public static void main(String[] args) {
        String[] inputs = new String[]{
                "aabbaccc",
                "ababcdcdababcdcd",
                "abcabcdede",
                "abcabcabcabcdededededede",
                "xababcdcdababcdcd",
                "a",
                "aa",
                "aab",
                "aabb",
                "aaab",
                "baaa"
        };

        for (String input : inputs) {
            System.out.println(new Solution().solution(input));
        }
    }
}
