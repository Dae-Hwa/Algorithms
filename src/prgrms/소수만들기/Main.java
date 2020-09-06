package prgrms.소수만들기;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{1, 2, 7, 6, 4}));
    }

}

class Solution {
    public int solution(int[] nums) {
        Combination<Integer> combinationObj = Combination.createBy(Arrays.stream(nums).boxed().collect(Collectors.toList()), 3);

        List<List<Integer>> combinations = combinationObj.getCombinationsOf();

        int primeNumberCount = 0;

        for (List<Integer> combination : combinations) {
            boolean isPrimeNumber = true;

            int sumOfCombination = combination.stream().mapToInt(i -> i).sum();

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

class Combination<T> {
    private final List<List<T>> combinationsOf = new ArrayList<>();


    public static <T> Combination<T> createBy(List<T> og, int r) {
        Combination<T> combination = new Combination();

        combination.init(og, og.size(), r);

        return combination;
    }

    private void init(List<T> nums, int n, int r) {
        Stack<Integer> combinationElements = new Stack<>();
        pick(nums, combinationElements, n, r);
    }

    private void pick(List<T> nums, Stack<Integer> combinationElements, int n, int r) {
        if (r == 0) {
            addCombination(nums, combinationElements);
            return;
        }

        Integer current = combinationElements.isEmpty() ? 0 : combinationElements.lastElement() + 1;

        for (int i = current; i < n; i++) {
            combinationElements.push(i);
            pick(nums, combinationElements, n, r - 1);
            combinationElements.pop();
        }
    }

    private void addCombination(List<T> nums, Stack<Integer> combinationElements) {
        List<T> combination = new ArrayList<>();

        for (int i : combinationElements) {
            combination.add(nums.get(i));
        }

        combinationsOf.add(combination);
    }

    public List<List<T>> getCombinationsOf() {
        return Collections.unmodifiableList(combinationsOf);
    }
}
