package bj2503;

import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    boolean[][][] isNotPossible = new boolean[9][9][9];



  }

  private static List<Question> getInput() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int numberOfInputs = Integer.parseInt(br.readLine());

    List<Question> questions = new ArrayList<>();

    for (int i = 0; i < numberOfInputs; i++) {
      String[] readLines = br.readLine().split(" ");

      questions.add(new Question(Integer.parseInt(readLines[0]), Integer.parseInt(readLines[1]),
          Integer.parseInt(readLines[2])));
    }

    return questions;
  }
}


class Question {
  private int number;
  private int strike;
  private int ball;

  public Question(int number, int strike, int ball) {
    this.number = number;
    this.strike = strike;
    this.ball = ball;
  }

  public int getNumber() {
    return number;
  }

  public int getStrike() {
    return strike;
  }

  public int getBall() {
    return ball;
  }
}
