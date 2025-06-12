
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2096 {

	//메모리 42112kb 시간 368ms
	//dp
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] maxDp = new int[3];
		int[] minDp = new int[3];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x0 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());

			if (i == 0) {
				//첫 줄 요소들 저장
				maxDp[0] = minDp[0] = x0;
				maxDp[1] = minDp[1] = x1;
				maxDp[2] = minDp[2] = x2;
			} else {
				//이전 줄에서의 누적 결과값을 백업
				int prevMax0 = maxDp[0], prevMax1 = maxDp[1], prevMax2 = maxDp[2];
				int prevMin0 = minDp[0], prevMin1 = minDp[1], prevMin2 = minDp[2];

				//현재 위치에 오기까지 가능한 경로 들 중 가장 큰 누적값 더해서 더하기
				maxDp[0] = x0 + Math.max(prevMax0, prevMax1);
				maxDp[1] = x1 + Math.max(prevMax0, Math.max(prevMax1, prevMax2));
				maxDp[2] = x2 + Math.max(prevMax1, prevMax2);

				minDp[0] = x0 + Math.min(prevMin0, prevMin1);
				minDp[1] = x1 + Math.min(prevMin0, Math.min(prevMin1, prevMin2));
				minDp[2] = x2 + Math.min(prevMin1, prevMin2);
			}
		}

		int finalMax = Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]));
		int finalMin = Math.min(minDp[0], Math.min(minDp[1], minDp[2]));

		System.out.println(finalMax + " " + finalMin);
	}

}
