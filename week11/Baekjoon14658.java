
import java.io.*;
import java.util.*;

public class Baekjoon14658 {
	static int n, m, l, k;
	static ArrayList<int[]> posArr;
	static int ans;

	//메모리 16656KB 시간 172MS
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		posArr = new ArrayList<>();
		ans = Integer.MIN_VALUE;

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			posArr.add(new int[] {x, y});
		}

		// 모든 별똥별 좌표 조합을 트램펄린 좌측하단 (x, y)로 본다
		for (int[] s1 : posArr) {
			for (int[] s2 : posArr) {
				int leftX = s1[0];
				int bottomY = s2[1];

				int cnt = 0;

				for (int[] star : posArr) {
					int starX = star[0];
					int starY = star[1];

					if (leftX <= starX && starX <= leftX + l &&
							bottomY <= starY && starY <= bottomY + l) {
						cnt++;
					}
				}

				ans = Math.max(ans, cnt);
			}
		}

		System.out.println(k - ans);
		}

	}


