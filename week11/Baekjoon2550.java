package week11;

import java.io.IOException;
import java.util.Arrays;

public class Baekjoon2550 {
	public static void main(String[] args) throws IOException {
		int N = readInt();
		int[] switchOrder = new int[N + 1];
		int[] bulbOrder = new int[N + 1];
		// 스위치 번호 위에서 부터
		for (int i = 1; i <= N; i++) {
			switchOrder[i] = readInt();
		}

		// 전구 번호 위에서 부터
		for (int i = 1; i <= N; i++) {
			bulbOrder[i] = readInt();
		}

		// 전구 번호 → 오른쪽 위치 역매핑
		int[] rightPos = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			rightPos[bulbOrder[i]] = i;
		}

		// i번째 스위치가 연결된 전구 위치
		int[] seq = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			seq[i] = rightPos[switchOrder[i]];
		}

		// LIS 구하기
		int[] LIS = new int[N + 1];
		int[] dp = new int[N + 1];
		LIS[0] = Integer.MIN_VALUE;
		int len = 0;
		for (int i = 1; i <= N; i++) {
			int key = seq[i];
			// 지금까지 LIS의 마지막 값보다 크면 LIS 마지막에 추가
			if (LIS[len] < key) {
				LIS[++len] = key;
				dp[i] = len;
			} else {
				// LIS[?-1] < key ≤ LIS[?]인 위치 찾기
				int pos = Arrays.binarySearch(LIS, 1, len + 1, key);
				// LIS에서 같은 값이 없는경우 음수로 리턴됨 -> 변환
				if (pos < 0) {
					pos = -(pos + 1);
				}
				LIS[pos] = key;
				dp[i] = pos;
			}
		}
		System.out.println("seq" + Arrays.toString(seq));
		System.out.println("dp" + Arrays.toString(dp));
		System.out.println("LIS" + Arrays.toString(LIS));

		// 역추적: dp[i]==curLen인 위치에서 switchOrder[i] 찾기
		int curLen = len;
		int[] answer = new int[len];
		for (int i = N; i >= 1; i--) {
			if (dp[i] == curLen) {
				System.out.println("i:" + i + " curLen:" + curLen + " switchOrder[i]:" + switchOrder[i]);
				answer[--curLen] = switchOrder[i];
			}
		}

		// 스위치 번호 오름차순으로 정렬
		Arrays.sort(answer);
		System.out.println("sorted answer" + Arrays.toString(answer));

		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(len).append("\n");
		for (int v : answer)
			sb.append(v).append(" ");
		System.out.print(sb);
	}

	// 기존 readInt() 그대로
	private static int readInt() throws IOException {
		int c = System.in.read(), n = 0;
		while (c <= ' ')
			c = System.in.read();
		while ('0' <= c && c <= '9') {
			n = (n * 10) + (c & 15);
			c = System.in.read();
		}
		return n;
	}
}
