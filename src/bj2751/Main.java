package bj2751;

import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    int endPoint = 5-1;
    int[] input = { 5, 4, 3, 2, 1 };
    int[] result = Arrays.copyOf(input,input.length);
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
     * R 0 2 4 
     * 
     * L 1 3 4 // endpoint-1
     * 
     * 2. ms=2
     * 
     * R 0 4
     * 
     * L 2 4 // endpoint-1
     * 
     * 3. ms=4
     * 
     * R 0123 8,9,1011
     *  
     * L 4567 12131415
     * 
     * //actual
     * 
     * R 0123
     *  
     * L 4 //endpoint 
     */

}