package prgrms.구명보트;

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int result = 0;
        int pointer = 0;
        int pointerFromEnd = people.length - 1;

        while (pointer <= pointerFromEnd) {
            if (people[pointer] + people[pointerFromEnd] <= limit) {
                pointer++;
            }
            pointerFromEnd--;
            result++;
        }

        return result;
    }
}


public class Main {
    public static void main(String[] args) {
        List<List<Object>> lists =
                new ArrayList<>(Arrays.asList(Arrays.asList(new int[] {70, 50, 80, 50}, 100),
                        Arrays.asList(new int[] {70, 80, 50}, 100),
                        Arrays.asList(new int[] {10, 40, 40, 50, 60, 60}, 100),
                        Arrays.asList(new int[] {21, 20, 1, 19, 19}, 40),
                        Arrays.asList(new int[] {40, 50, 60, 90}, 100),
                        Arrays.asList(new int[] {40, 40, 80}, 160),
                        Arrays.asList(new int[] {10, 20, 30, 40, 50, 60, 70, 80, 90}, 100)));

        for (List<Object> list : lists) {
            System.out.println(new Solution().solution((int[]) list.get(0), (int) list.get(1)));
        }
    }
}
