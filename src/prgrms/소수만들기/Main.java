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
        int r = 3;

        Combinations combinations = Combinations.createBy(nums.length, r);

        List<Integer> targetOfCombination = Arrays.stream(nums).boxed().collect(Collectors.toList());

        int primeNumberCount = 0;

        for (Combination<Integer> combination : combinations.getCombinationsOf(targetOfCombination)) {
            boolean isPrimeNumber = true;

            int sumOfCombination = combination.getCombination().stream().mapToInt(i -> i).sum();

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

        System.out.println(combinations.getCombinations().toString());

        return primeNumberCount;
    }
}

class Combination<T> {
    private List<T> combination;

    public Combination(List<T> combination) {
        this.combination = combination;
    }

    public List<T> getCombination() {
        return Collections.unmodifiableList(combination);
    }

    @Override
    public String toString() {
        return "Combination{" +
                "combination=" + combination +
                '}';
    }
}

class Combinations {
    private final List<Combination<Integer>> combinations = new ArrayList<>();

    private final int n;
    private final int r;

    public Combinations(int n, int r) {
        this.n = n;
        this.r = r;
    }

    public static Combinations createBy(int n, int r) {
        Combinations combinations = new Combinations(n, r);
        combinations.init();

        return combinations;
    }

    private void init() {
        Stack<Integer> combinationElements = new Stack<>();
        pick(combinationElements, r);
    }

    private void pick(Stack<Integer> combinationElements, int r) {
        if (r == 0) {
            addCombination(combinationElements);
            return;
        }

        Integer current = combinationElements.isEmpty() ? 0 : combinationElements.lastElement() + 1;

        for (int i = current; i < n; i++) {
            combinationElements.push(i);
            pick(combinationElements, r - 1);
            combinationElements.pop();
        }
    }

    private void addCombination(Stack<Integer> combinationElements) {
        List<Integer> combination = new ArrayList<>();

        for (int i : combinationElements) {
            combination.add(i);
        }

        combinations.add(new Combination(combination));
    }

    public List<Combination<Integer>> getCombinations() {
        return Collections.unmodifiableList(combinations);
    }

    public <T> List<Combination<T>> getCombinationsOf(List<T> target) {
        compareCombinationSizeWithTarget(target.size());

        List<Combination<T>> mappedCombinations = new ArrayList<>();

        for (Combination<Integer> combination : this.combinations) {
            List<T> result = new ArrayList<>();

            for (int i : combination.getCombination()) {
                result.add(target.get(i));
            }

            mappedCombinations.add(new Combination<>(result));
        }


        return mappedCombinations;
    }

    /**
     * 생성된 조합 nCr 의 n과 매개변수 sizeOfTarget 비교
     *
     * @param sizeOfTarget 조합 변환 대상의 길이
     */
    private void compareCombinationSizeWithTarget(int sizeOfTarget) {
        if (sizeOfTarget != n) {
            StringBuilder sb = new StringBuilder()
                    .append("조합 변환 대상의 길이가 생성된 조합의 길이와 일치하지 않습니다.")
                    .append("[조합의 길이 : ")
                    .append(n)
                    .append("], ")
                    .append("[원본 배열의 길이 : ")
                    .append(sizeOfTarget)
                    .append("]");

            throw new IllegalArgumentException(sb.toString());
        }
    }

    @Override
    public String toString() {
        return "Combinations{" +
                "combinations=" + combinations +
                ", n=" + n +
                ", r=" + r +
                '}';
    }
}
