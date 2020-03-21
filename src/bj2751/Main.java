
import java.io.*;
// import java.util.*;

class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {
    StringBuffer sb = new StringBuffer();
    int endPoint = Integer.parseInt(br.readLine()) - 1;
    int[] input = new int[endPoint + 1];

    for (int i = 0; i <= endPoint; i++) {
      input[i] = Integer.parseInt(br.readLine());
    }

    mergeSort(input, endPoint);

    for (int i = 0; i < input.length - 1; i++) {
      sb.append(input[i] + "\r\n");
    }
    sb.append(input[input.length - 1]);

    System.out.print(sb.toString());
  }

  public static void mergeSort(int[] input, int endPoint) {
    int[] result = new int[input.length];
    int left = 0;
    int leftEnd = 0;
    int right = 0;
    int rightEnd = 0;
    int mergeSize = 0;
    int pointer = 0;
    int startPoint = 0;

    for (mergeSize = 1; mergeSize <= endPoint; mergeSize <<= 1) {
      // 병합 크기가 변할때마다 0에서부터 시작
      right = 0;
      rightEnd = 0;
      left = 0;
      leftEnd = 0;

      for (; rightEnd < endPoint;) {
        if (right != 0)
          // 시작점에서 left가 0이어야함
          left = right + mergeSize;
        right = left + mergeSize;
        leftEnd = right - 1;
        rightEnd = right + mergeSize - 1;
        startPoint = left;

        for (pointer = left; pointer <= endPoint; pointer++) {
          if (right <= rightEnd && right <= endPoint && input[right] <= input[left]) {
            // 오른쪽 부분이 결과에 채워지는 경우
            result[pointer] = input[right];
            right++;
          } else if (left <= leftEnd && right <= endPoint && input[left] <= input[right]) {
            // 왼쪽 부분이 결과에 채워지는 경우
            result[pointer] = input[left];
            left++;
          }
          /**
           * 왼쪽 혹은 오른쪽의 포인터가 범위를 벗어날 경우 반대쪽의 남은 요소들을 붙여주면 된다.
           * 
           * 남아있는 반대 쪽 요소들은 반드시 정렬된 상태이고,
           * 
           * 병합되지 않은 값들은 병합이 이루어진 값보다 항상 크다.
           */
          else if (rightEnd < right || endPoint < right) {
            // right가 끝 지점에 다다랐을경우
            for (; left <= leftEnd; left++) {
              result[pointer] = input[left];
              pointer++;
            }
            break;
          } else if (leftEnd < left) {
            // left가 right(경계)에 다다랐을경우
            for (; right <= rightEnd; right++) {
              result[pointer] = input[right];
              pointer++;
            }
            break;
          }
        }
        left--;
        right--;

        // 병합결과 반영
        for (int i = startPoint; i <= rightEnd && i <= endPoint; i++) {
          input[i] = result[i];
        }
      }
    }
  }
}