package bj2503;

import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    boolean[][][] isNotPossible = new boolean[9][9][9];

    Queue<Integer> answers = new ArrayDeque<>();

    Queue<Question> questions = getInput();

    Question firstQuestion = questions.poll();

    for (int i = 123; i < 987; i++) {
      addAnswers(firstQuestion, i, answers);
    }

    while (!questions.isEmpty()) {
      Question currentQuestion = questions.poll();
      Queue<Integer> results = new ArrayDeque<>();

      while (!answers.isEmpty()) {
        addAnswers(currentQuestion, answers.poll(), results);
      }

      answers = results;
    }

    System.out.println(answers.size());
  }

  private static void addAnswers(Question question, int targetNumber, Queue<Integer> answers) {
    int ballCnt = 0;
    int strikeCnt = 0;
    int[] targetNumbers = getPlaceValuesBy(targetNumber);
    int[] numbers = getPlaceValuesBy(question.getNumber());


    for (int i = 0; i < targetNumbers.length; i++) {
      int searchResult = Arrays.binarySearch(targetNumbers, numbers[i]);
      if (searchResult != -1) {
        ballCnt++;
      }
      if (searchResult == i) {
        strikeCnt++;
        ballCnt--;
      }
    }

    if (ballCnt < 0) {
      ballCnt = 0;
    }

    if (question.getBall() == ballCnt && question.getStrike() == strikeCnt) {
      answers.add(targetNumber);
    }
    // TODO : 초기화 확인
  }

  private static int[] getPlaceValuesBy(int number) {
    int h = number / 100;
    int t = number % 100 / 10;
    int u = number % 100 % 10;

    if (h == t || h == u || t == u) {
      return new int[] {};
    }

    return new int[] {h, t, u};
  }

  private static Queue<Question> getInput() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int numberOfInputs = Integer.parseInt(br.readLine());

    Queue<Question> questions = new ArrayDeque<>();

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
