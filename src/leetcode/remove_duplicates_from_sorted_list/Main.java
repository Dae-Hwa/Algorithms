package leetcode.remove_duplicates_from_sorted_list;


import java.util.*;

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        Set<Integer> valOfNodes = new HashSet<>();

        ListNode cur = head;
        ListNode prev = head;

        while (cur != null) {
            if (valOfNodes.contains(cur.val)) {
                prev.next = cur.next;
            }

            valOfNodes.add(cur.val);
            cur = cur.next;
            prev = cur;
        }

        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().deleteDuplicates(new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))))));
    }
}
