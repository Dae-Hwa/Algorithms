package bj1427;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
  public static void main(String[] args) throws IOException {
    int[] input = getInput();
    StringBuilder sb = new StringBuilder();

    Arrays.sort(input);

    for (int i = input.length - 1; 0 <= i; i--) {
      sb.append(input[i]);
    }

    System.out.println(sb);
  }

  public static int[] getInput() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] input = br.readLine().split("");

    return Stream.of(input).mapToInt(Integer::parseInt).toArray();
  }
}
