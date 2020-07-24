package bj1110;

import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    int input = getInput();

    System.out.println(getResult(input));

  }

  private static int getResult(int input) {
    int cnt = 0;
    int next = input;
    boolean isNotEnd = true;

    while (isNotEnd) {
      cnt++;

      int left = next / 10;
      int right = next % 10;
      int nextRight = (left + right) % 10;

      next = right * 10 + nextRight;

      if (next == input) {
        isNotEnd = false;
      }
    }

    return cnt;
  }

  private static int getInput() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    return Integer.parseInt(br.readLine());
  }
}
