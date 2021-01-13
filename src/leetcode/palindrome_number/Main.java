package leetcode.palindrome_number;

import java.util.*;

class Solution {
    public boolean isPalindrome(int x) {
        Deque<String> deque = new ArrayDeque<>(Arrays.asList(String.valueOf(x).split("")));

        while (1 < deque.size()) {
            if (!deque.pollFirst().equals(deque.pollLast())) {
                return false;
            }
        }

        return true;
    }
}

public class Main {
}
