package bj2579;

import java.io.*;

public class Main {

  private final static int START_POINT = 3;

  public static void main(String[] args) throws IOException {
    int[] input = getInput();

    System.out.println(getResult(input));
  }

  private static int getResult(int[] input) {
    int size = input.length;
    int result[] = new int[size];

    for (int i = START_POINT; i < size; i++) {
      result[i] = Math.max(result[i - 2], result[i - 3] + input[i - 1]) + input[i];
    }

    return result[size - 1];
  }

  private static int[] getInput() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 시작지점+3으로 초기화
    int[] result = new int[Integer.parseInt(br.readLine()) + START_POINT];

    for (int i = START_POINT; i < result.length; i++) {
      result[i] = Integer.parseInt(br.readLine());
    }

    return result;
  }
}
