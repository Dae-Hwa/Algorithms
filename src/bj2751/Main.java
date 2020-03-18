package bj2751;

import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    int endPoint = 5 - 1;
    int[] input = { 4, 5, 3, 2, 1 };// { 5, 4, 3, 2, 1 };
    int[] result = null;
    int left = 0;
    int leftEnd = 0;
    int right = 0;
    int rightEnd = 0;
    int mergeSize = 0;
    int pointer = 0;

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
        // result에 deep copy 후 result에 결과병합(병합을 했을 때, 병합(정렬)되지 않은 부분은 그대로 있어야 함)
        result = Arrays.copyOf(input, input.length);

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
        input = result;
      }
    }
    System.out.println(Arrays.toString(result));
  }
}