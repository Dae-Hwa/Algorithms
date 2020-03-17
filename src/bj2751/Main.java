package bj2751;

import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    int endPoint = 5 - 1;
    int[] input = { 3, 5, 2, 4, 1 };// { 5, 4, 3, 2, 1 };
    int[] result;
    int left = 0;
    int leftEnd = 0;
    int right = 0;
    int rightEnd = 0;
    int mergeSize = 0;
    int temp = 0;
    int pointer = 0;

    // 안쪽 로직
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

    // System.out.println(Arrays.toString(result));

    // 바깥쪽 for문
    /**
     * the result what I want is
     * 
     * 1. ms=1
     * 
     * L 0 2 4
     * 
     * R 1 3 4 // endpoint-1
     * 
     * 2. ms=2
     * 
     * L 0 4
     * 
     * R 2 4 // endpoint-1
     * 
     * 3. ms=4
     * 
     * L 0123 8,9,1011
     * 
     * R 4567 12131415
     * 
     * //actual
     * 
     * L 0123
     * 
     * R 4 //endpoint
     * 
     * r = r+me re = r+me-1 l = right-ms le = right-1
     */

    for (mergeSize = 1; mergeSize <= endPoint; mergeSize <<= 1) {
      right = 0;
      rightEnd = 0;
      left = 0;
      leftEnd = 0;
      System.out.println("ms" + mergeSize);
      for (; rightEnd < endPoint;) {
        if (right != 0)
          left = right + mergeSize;
        right = left + mergeSize;
        leftEnd = right - 1;
        rightEnd = right + mergeSize - 1;
        // System.out.println("left" + left);
        // System.out.println("leftEnd" + leftEnd);
        // System.out.println("right" + right);
        // System.out.println("rightEnd" + rightEnd);
        result = Arrays.copyOf(input, input.length);

        for (pointer = left; pointer <= endPoint; pointer++) {

          if (right <= rightEnd && right <= endPoint && input[right] <= input[left]) {
            result[pointer] = input[right];
            right++;
          } else if (left <= leftEnd && right <= endPoint && input[left] <= input[right]) {
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
        left--;
        right--;
        input = result;
      }
    }
    System.out.println(Arrays.toString(result));
  }
}