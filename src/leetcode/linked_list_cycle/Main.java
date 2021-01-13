package leetcode.linked_list_cycle;

import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> valSets = new HashSet<>();

        ListNode cur = head;

        while (cur != null) {
            if (valSets.contains(cur)) {
                return true;
            }

            valSets.add(cur);
            cur = cur.next;
        }

        return false;
    }
}

public class Main {
}
