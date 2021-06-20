package prgrms.메뉴리뉴얼;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();


        for (int each : course) {
            Map<String, Integer> courseCounts = new HashMap<>();

            for (String order : orders) {
                String[] splitOrder = order.split("");
                List<List<Integer>> combinations = new Combination().combinations(splitOrder.length, each);
                for (List<Integer> combination : combinations) {
                    // 조합해서 맵에 카운팅
                    String currentCourse = combination.stream()
                                                   .map(index -> splitOrder[index])
                                                   .sorted()
                                                   .collect(Collectors.joining());

                    courseCounts.put(currentCourse, courseCounts.getOrDefault(currentCourse, 0) + 1);
                }
            }

            // 카운트 수로 내림차순 정렬 후 맨 위에 있는 거(제일 많은 메뉴) 답에 추가
            List<Map.Entry<String, Integer>> entries = new ArrayList<>(courseCounts.entrySet());
            entries.sort((o1, o2) -> Comparator.<Integer>reverseOrder().compare(o1.getValue(), o2.getValue()));

            if (entries.isEmpty() || entries.get(0).getValue() < 2) {
                continue;
            }

            answer.add(entries.get(0).getKey());
            
            for (int i = 1; i < entries.size(); i++) {
                // 제일 많은게 여러개일 경우 추가
                int before = entries.get(i - 1).getValue();
                int current = entries.get(i).getValue();

                if (before != current) {
                    break;
                }

                answer.add(entries.get(i).getKey());
            }

        }

        answer.sort(Comparator.naturalOrder());

        return answer.toArray(new String[]{});
    }
}

class Combination {
    private List<List<Integer>> combinations = new ArrayList<>();

    public List<List<Integer>> combinations(int n, int r) {
        pick(new Stack<>(), n, r);

        return combinations;
    }

    private void pick(Stack<Integer> stack, int n, int r) {
        if (stack.size() == r) {
            combinations.add(stack.toList());
        }

        for (int i = stack.isEmpty() ? 0 : stack.peek() + 1; i < n; i++) {
            stack.push(i);
            pick(stack, n, r);
            stack.pop();
        }
    }
}

class Stack<T> {
    private Deque<T> stack = new ArrayDeque();

    public T pop() {
        return stack.pop();
    }

    public void push(T t) {
        stack.push(t);
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public T peek() {
        return stack.peek();
    }

    public List<T> toList() {
        return new ArrayList<>(stack);
    }
}

public class 메뉴리뉴얼 {
    public static void main(String[] args) {
        String[] result = new Solution().solution(
                new String[]{"XYZ", "XWY", "WXA"},
                new int[]{2, 3, 4}
        );

        System.out.println(Arrays.toString(result));

    }
}
