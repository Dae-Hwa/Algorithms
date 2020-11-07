package prgrms.주식가격;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<int[]> inputs = Arrays.asList(
                new int[]{1, 2, 3, 2, 3},
                new int[]{1, 2, 3, 2, 3},
                new int[]{100000, 1}
        );

        for (int i = 0; i < inputs.size(); i++) {
            printResult(i, inputs.get(i));
        }
    }

    private static void printResult(int index, int[] input) {
        System.out.println(index + " : " + Arrays.toString(new Solution().solution(input)));
    }
}

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        Stack<Stock> stockStack = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            answer[i] = prices.length - 1 - i;
            Stock currentStock = new Stock(prices[i], i);

            while (!stockStack.isEmpty() && stockStack.peek().compareTo(currentStock) == 1) {
                // droppedStock.price > currentStock.price
                Stock droppedStock = stockStack.pop();

                answer[droppedStock.getIndex()] = i - droppedStock.getIndex();

                continue;
            }

            stockStack.push(currentStock);
        }

        return answer;
    }
}

class Stock implements Comparable<Stock> {
    private int price;
    private int index;

    public Stock(int price, int index) {
        this.price = price;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(Stock o) {
        return Integer.compare(price, o.price);
    }
}