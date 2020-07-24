package bj1003;

import java.io.*;

public class Main {

  private static int[][] results = new int[41][3];

  public static void main(String[] args) throws IOException {
    int[] input = getInput();
    results[0][0] = 1;
    results[0][2] = 1;

    for (int i = 0; i < input.length; i++) {
      int cur = input[i];
      setResult(cur);
      System.out.println(results[cur][0] + " " + results[cur][1]);
    }
  }

  private static void setResult(int n) {
    if (n == 0) {
      return;
    }

    if (results[n][2] != 1) {
      setResult(n - 1);
      results[n] = new int[] {results[n - 1][1], results[n - 1][0] + results[n - 1][1], 1};
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
