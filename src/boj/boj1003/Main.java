package boj.boj1003;

import java.io.*;

public class Main {
  private static int[] zeroCnt = new int[41];
  private static int[] oneCnt = new int[41];

  public static void main(String[] args) throws IOException {
    int[] input = getInput();

    for (int i = 0; i < input.length; i++) {
      int cur = input[i];
      setResultBy(cur);
      System.out.println(zeroCnt[cur] + " " + oneCnt[cur]);
    }
  }

  private static void setResultBy(int n) {
    if (n == 0) {
      zeroCnt[0] = 1;

      return;
    }

    if (zeroCnt[n] == 0 && oneCnt[n] == 0) {
      setResultBy(n - 1);

      zeroCnt[n] = oneCnt[n - 1];
      oneCnt[n] = zeroCnt[n - 1] + oneCnt[n - 1];
    }
  }

  private static int[] getInput() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] result = new int[Integer.parseInt(br.readLine())];

    for (int i = 0; i < result.length; i++) {
      result[i] = Integer.parseInt(br.readLine());
    }

    return result;
  }
}
