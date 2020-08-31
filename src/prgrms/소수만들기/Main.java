package prgrms.소수만들기;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{1, 2, 7, 6, 4}));
    }

}

class Solution {
    public int solution(int[] nums) {
        Combination combination = Combination.createBy(nums, 3);

        List<Integer> sumsOfCombinations = combination.getSumsOfCombination();

        int primeNumberCount = 0;

        for (int sumOfCombination : sumsOfCombinations) {
            boolean isPrimeNumber = true;

            for (int j = 2; j <= Math.sqrt(sumOfCombination); j++) {
                if (sumOfCombination % j == 0) {
                    isPrimeNumber = false;
                    break;
                }
            }

            if (isPrimeNumber) {
                primeNumberCount++;
            }
        }

        return primeNumberCount;
    }
}

class Combination {
    private final List<Integer> sumsOfCombination = new ArrayList<>();
    private final int[] nums;

    private Combination(int[] nums) {
        this.nums = nums;
    }

    public static Combination createBy(int[] nums, int r) {
        Combination combination = new Combination(nums);

        combination.init(nums.length, r);

        return combination;
    }

    private void init(int n, int r) {
        Stack<Integer> combinationElements = new Stack<>();
        pick(combinationElements, n, r);
    }

    private void pick(Stack<Integer> combinationElements, int n, int r) {
        if (r == 0) {
            addCombination(combinationElements);
            return;
        }

        int current = combinationElements.isEmpty() ? 0 : combinationElements.lastElement() + 1;

        for (int i = current; i < n; i++) {
            combinationElements.push(i);
            pick(combinationElements, n, r - 1);
            combinationElements.pop();
        }
    }

    private void addCombination(Stack<Integer> combinationElements) {
        int sum = 0;
        for (int i : combinationElements) {
            sum += nums[i];
        }

        sumsOfCombination.add(sum);
    }

    public List<Integer> getSumsOfCombination() {
        return Collections.unmodifiableList(sumsOfCombination);
    }
}
