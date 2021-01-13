package leetcode.palindrome_number;

import java.util.*;

class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        Deque<Integer> deque = new ArrayDeque<>();

        while (x != 0) {
            deque.offerLast(x % 10);
            x /= 10;
        }

        while (1 < deque.size()) {
            if (deque.pollFirst() != (deque.pollLast())) {
                return false;
            }
        }

        return true;
    }
}

public class Main {
}
