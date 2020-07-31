package bj10448;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    List<Integer> triangularNumbers = getTriangularNumbers(1000);
    int[] inputs = getInputs();

    for (int i = 0; i < inputs.length; i++) {
      if (canCalcByTriNums(inputs[i], triangularNumbers)) {
        System.out.println(1);
      } else {
        System.out.println(0);
      }
    }
  }

  static boolean canCalcByTriNums(int target, List<Integer> triangularNumbers) {
    for (int firstNumber : triangularNumbers) {
      for (int secondNumber : triangularNumbers) {
        for (int thirdNumber : triangularNumbers) {
          if (firstNumber + secondNumber + thirdNumber == target) {
            return true;
          }
        }
      }
    }

    return false;
  }

  static List<Integer> getTriangularNumbers(int maxNumber) {
    Deque<Integer> list = new ArrayDeque<>();

    list.add(1);

    for (int i = 2; list.peekLast() + i < maxNumber; i++) {
      list.add(list.peekLast() + i);
    }

    return new ArrayList<Integer>(list);
  }

  static int[] getInputs() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int[] inputs = new int[Integer.parseInt(br.readLine())];

    for (int i = 0; i < inputs.length; i++) {
      inputs[i] = Integer.parseInt(br.readLine());
    }

    return inputs;
  }
}
