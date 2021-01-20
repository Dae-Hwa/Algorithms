package prgrms.이상한_문자_만들기;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String solution(String s) {
        String[] words = s.split(" ", -1);

        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < words[i].length(); j++) {
                String cur = String.valueOf(words[i].charAt(j));
                sb.append(j % 2 == 0 ? cur.toUpperCase() : cur.toLowerCase());
            }
            words[i] = sb.toString();
        }

        return Arrays.stream(words)
                .collect(Collectors.joining(" "));
    }

    public String solution2(String s) {
        String answer = "";
        StringTokenizer st = new StringTokenizer(s, " ", true);
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            for (int i = 0; i < str.length(); i++) {
                if (i % 2 == 0) {
                    answer += String.valueOf(str.charAt(i)).toUpperCase();
                } else {
                    answer += String.valueOf(str.charAt(i)).toLowerCase();
                }
            }
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Object> inputs = new ArrayList<>(Arrays.asList(
                "try hello world",
                "abcd",
                "abcd efgh",
                "abc efgh",
                "abcd efg",
                "  a",
                "asdfsafasdfdasfasdf asfdsa dfqwer f zxcv asd fqwzxcvsadfqwer"
        ));

        for (int i = 0; i < inputs.size(); i++) {
            String input = (String) inputs.get(i);

            System.out.println(new Solution().solution(input));
            System.out.println(new Solution().solution2(input));
        }
    }
}
