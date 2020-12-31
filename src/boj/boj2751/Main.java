package boj.boj2751;

import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    int endPoint = 16 - 1;
    int[] input = { 5, 4, 3, 2, 1 };
    int[] result = Arrays.copyOf(input, input.length);
    int left = 0;
    int leftEnd = 0;
    int right = 0;
    int rightEnd = 0;
    int mergeSize = 0;
    int temp = 0;

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

    for (int i = 1; i <= endPoint; i <<= 1) {
      right = 0;
      rightEnd = 0;
      left = 0;
      leftEnd = 0;
      System.out.println("ms" + i);
      for (; rightEnd < endPoint;) {
        if (right != 0)
          left = right + i;
        right = left + i;
        leftEnd = right - 1;
        rightEnd = right + i - 1;
        // System.out.println("left" + left);
        // System.out.println("leftEnd" + leftEnd);
        // System.out.println("right" + right);
        // System.out.println("rightEnd" + rightEnd);
      }

    }
  }

}