package leetcode.twosum;

import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

public class Main {
    public static void main(String[] args) {
        List<Object> inputs = new ArrayList<>(Arrays.asList(
                new ArrayList(Arrays.asList(
                        new int[]{2, 7, 11, 15},
                        9,
                        new int[]{0, 1}
                )),
                new ArrayList(Arrays.asList(
                        new int[]{3, 2, 4},
                        6,
                        new int[]{1, 2}
                )),
                new ArrayList(Arrays.asList(
                        new int[]{3, 3},
                        6,
                        new int[]{0, 1}
                ))
        ));

        for (Object inputObj : inputs) {
            List<Object> input = (List<Object>) inputObj;

            System.out.println("results");
            System.out.println(Arrays.toString(new Solution().twoSum((int[]) input.get(0), (int) input.get(1))));
            System.out.println("expected");
            System.out.println(Arrays.toString((int[]) input.get(2)));
            System.out.println("---");
        }

    }
}