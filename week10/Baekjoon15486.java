import java.io.*;
import java.util.*;

public class Baekjoon15486 {
	
	static int n;
	static int[] t, p;
	static int[] dp; //i일부터 퇴사일까지 얻을 수 있는 최대 수익
	static int ans;

	//메모리 315184kb 시간 668ms
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		t = new int[n+1]; //걸리는 시간
		p = new int[n+1]; //수익
		dp = new int[n+2];
		ans = Integer.MIN_VALUE;
		
		for (int i = 1 ; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[n+1] = 0;
		
		//퇴사일부터 거꾸로(역방향) 진행
		for (int i = n; i >= 1; i --) {
			if (i + t[i] <= n + 1) {
				//상담을 하는 경우(현재 수익 + 미래 최대 수익), 상담을 하지 않는 경우 고려
				dp[i] = Math.max(p[i]+dp[i + t[i]], dp[i+1]);
			}
			else {
				dp[i] = dp[i + 1];
			}
		}
		
		System.out.println(dp[1]);
	}
}
