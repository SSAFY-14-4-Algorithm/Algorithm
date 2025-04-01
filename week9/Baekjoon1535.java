import java.io.*;
import java.util.StringTokenizer;

//메모리14236kb 시간104ms
public class Baekjoon1535 {
	static int n;
	static int hp[], happy[];
	static int dp[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		hp = new int[n];
		happy = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
		    hp[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
		    happy[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[101]; // 체력 소모가 최대 100
        for (int i = 0; i < n; i++) {
            for (int j = 100; j >= hp[i]; j--) {
                // 체력이 100을 넘기면 안되므로, 1 이상 남겨야 함
                if (j - hp[i] > 0) {
                    dp[j] = Math.max(dp[j], dp[j - hp[i]] + happy[i]);
                }
            }
        }

        // 체력 1 이상 남긴 상태에서의 최대 기쁨 찾기
        int answer = 0;
        for (int i = 1; i <= 100; i++) {
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
	}
}
