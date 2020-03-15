package bj2751;

import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    int endPoint = 5;
    int[] input = { 3, 5, 2, 4, 1 };// { 5, 4, 3, 2, 1 };
    int[] result;
    int left = 0;
    int leftEnd = 0;
    int right = 0;
    int rightEnd = 0;
    int temp = 0;
    int pointer = 0;

    // l과 r 비교해서 병합
    left = 0;
    leftEnd = 1;
    right = 2;
    rightEnd = 3;
    result = Arrays.copyOf(input, input.length);

    for (pointer = left; pointer <= rightEnd; pointer++) {

      if (right <= rightEnd && input[right] <= input[left]) {
        result[pointer] = input[right];
        right++;
      } else if (left <= leftEnd && input[left] <= input[right]) {
        result[pointer] = input[left];
        left++;
      } else if (rightEnd < right) {
        for (; left <= leftEnd; left++) {
          result[pointer] = input[left];
          pointer++;
        }
        break;
      } else if (leftEnd < left) {
        for (; right <= rightEnd; right++) {
          result[pointer] = input[right];
          pointer++;
        }
        break;
      }
    }

    input = result;

    System.out.println(Arrays.toString(result));

  }
}