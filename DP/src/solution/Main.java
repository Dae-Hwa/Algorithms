package solution;

public class Main {
	public static void main(String[] args) {
		int length = 6;
		int[] stairs = { 10, 20, 5, 10, 25, 20 };
		Stair stair = new Stair(length);

		System.out.println(stair.solution(stairs));
	}
}

class Stair {
	boolean canGo = true;
	int result = 0;
	int pointer = 0;

	public Stair(int length) {
		pointer = length;
	}

	public int solution(int[] stair) {
		while (pointer > 0) {
			// 첫번째를 밟는 경우, 밟지않는 경우로 나눠서 테스트
			// 점프해야하는 상황에서 더 작은 수를 만날 경우
			if (pointer > 1 && stair[pointer - 1] < stair[pointer - 2]) {
				result += stair[pointer - 2];
				pointer -= 2;
				canGo = true;
			} else {
				if (canGo && pointer > 0) {
					result += stair[pointer - 1];
					if (pointer != stair.length) {
						canGo = false;
					}
					pointer -= 1;
				} else if (!canGo && pointer == 1) {
					pointer -= 1;
				}
			}
		}
		return result;
	}
}