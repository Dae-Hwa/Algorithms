import java.util.*;
import java.io.*;

class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {

    int num = Integer.parseInt(br.readLine());
    String[] input = br.readLine().split(" ");
    int[] result = new int[num];
    int[] stack = new int[num];
    int[] index = new int[num];

    setResult(input, index, stack, result);
    printResult(result);

  }

  private static void setResult(String[] input, int[] stack, int[] index, int[] result) {
    int cur = 0;
    int pointer = 0;

    for (int i = 0; i < input.length; i++) {
      cur = Integer.parseInt(input[i]);
      if (stack[pointer] < cur) {
        pointer--;
      }
      pointer++;

      stack[pointer] = cur;
      index[pointer] = i;

      for (int j = pointer; 0 <= j; j--) {
        if (cur < stack[j]) {
          result[i] = index[j] + 1;
          break;
        }
      }
    }
  }

  private static void printResult(int[] result) {
    StringBuffer sb = new StringBuffer();

    for (int i = 0; i < result.length; i++) {
      if (i == result.length - 1) {
        sb.append(result[i]);
      } else {
        sb.append(result[i] + " ");
      }
    }

    System.out.println(sb.toString());
  }
}