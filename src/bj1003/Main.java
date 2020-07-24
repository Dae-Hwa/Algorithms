package bj1003;

import java.io.*;

public class Main {
  private static int[][] results = new int[41][2];

  public static void main(String[] args) throws IOException {
    int[] input = getInput();

    for (int i = 0; i < input.length; i++) {
      int cur = input[i];
      setResultBy(cur);
      System.out.println(results[cur][0] + " " + results[cur][1]);
    }
  }

  private static void setResultBy(int n) {
    if (n == 0) {
      results[0][0] = 1;

      return;
    }

    if (results[n][0] == 0 && results[n][1] == 0) {
      setResultBy(n - 1);

      int zeroCnt = results[n - 1][1];
      int oneCnt = results[n - 1][0] + results[n - 1][1];
      results[n] = new int[] {zeroCnt, oneCnt};
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
