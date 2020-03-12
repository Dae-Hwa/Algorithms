package bj2751;

import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    int endPoint = 5;
    int[] input = { 5, 4, 3, 2, 1 };
    int[] result = new int[input.length];
    int left = 0;
    int leftEnd = 0;
    int rightEnd = 0;
    int temp = 0;

    for (int mergeSize = 1; mergeSize < endPoint; mergeSize <<= 1) {

      for (int right = mergeSize; right < endPoint; right += mergeSize << 1) {
        left = right - mergeSize;
        rightEnd = right + mergeSize;
        leftEnd = right - 1;
        do {
          if (input[right] < input[left]) {
            temp = input[right];
            input[right] = input[left];
            input[left] = temp;

            if (right < rightEnd - 1 && right < endPoint)
              right++;

          } else {
            if (left < leftEnd)
              left++;

          }
        } while (left < leftEnd && right < rightEnd && !(endPoint <= right));
      }

    }
    System.out.println(Arrays.toString(result));
  }
}