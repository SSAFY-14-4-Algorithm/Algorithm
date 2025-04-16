import java.io.*;
import java.util.*;

public class Baekjoon2550 {
	static int n;
	static int[] swh, bulb, dp;
	static int max;

	//메모리 19524KB 시간 476ms
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력
		n = Integer.parseInt(br.readLine());
		swh = new int[n];
		bulb = new int[n];
		dp = new int[n];  // dp 배열 크기를 n으로 (0부터 n-1까지 사용)
		max = 0;

		// HashMap 대신 pos 배열 사용 (전구 번호 1~n의 위치 기록)
		int[] pos = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			swh[i] = Integer.parseInt(st.nextToken());
			pos[swh[i]] = i;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			bulb[i] = Integer.parseInt(st.nextToken());
		}

		// dp 배열을 이용해 Bulb 배열에 따른 스위치 인덱스 최장길이 구하기
		for (int i = 0; i < n; i++) {
			dp[i] = 1;  // 자기 자신만 포함하는 수열 길이
			for (int j = 0; j < i; j++) {
				if (pos[bulb[j]] < pos[bulb[i]]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			max = Math.max(max, dp[i]);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(max).append("\n");

		// 역추적: dp 배열을 뒤에서부터 보며 최장 증가 수열 횟수에 해당하는 bulb 번호 수집
		ArrayList<Integer> result = new ArrayList<>();
		int len = max;
		for (int i = n - 1; i >= 0 && len > 0; i--) {
			if (dp[i] == len) {
				result.add(bulb[i]);
				len--;
			}
		}
		Collections.sort(result);  // 문제 요구대로 오름차순 정렬

		for (int x : result) {
			sb.append(x).append(" ");
		}

		System.out.println(sb.toString().trim());
	}

}
