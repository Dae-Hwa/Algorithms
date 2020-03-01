import java.util.*;
import java.io.*;

class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {

    int num = Integer.parseInt(br.readLine());
    StringTokenizer input = new StringTokenizer(br.readLine());
    int[] result = new int[num];
    ArrayDeque<Tower> stack = new ArrayDeque<>();

    setResult(input, stack, result);
    printResult(result);

  }

  private static void setResult(StringTokenizer input, ArrayDeque<Tower> stack, int[] result) {
    int cur = 0;

    for (int i = 0; input.hasMoreTokens(); i++) {
      cur = Integer.parseInt(input.nextToken());
      if (!stack.isEmpty() && stack.peek().height < cur) {
        stack.pop();
      }
      stack.push(new Tower(cur, i + 1));

      for (Tower tower : stack) {
        if (cur < tower.height) {
          result[i] = tower.index;
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

class Tower {
  int height;
  int index;

  public Tower(int height, int index) {
    this.height = height;
    this.index = index;
  }
}