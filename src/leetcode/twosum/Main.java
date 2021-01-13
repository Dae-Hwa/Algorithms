package leetcode.twosum;

import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 보수를 찾는다. 예를 들어, 9에 대한 2의 보수는 7이며, 7의 보수는 2다. 현재 숫자가 2나 7일때 테이블에서 2 혹은 7을 찾아오면 되는 것이다.
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                // 정답이 2와 7이라고 할 때 둘 중 먼저 계산된 것이 아래에서 put 되어있을 것이다. 예를 들어, 7이 먼저 나왔으면 테이블에는 7이 들어있을 것이고 2의 보수를 찾는 과정에서 7을 찾을 수 있다.
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