import java.io.*;
import java.util.*;
/*
 * 메모리 12056KB
 * 시간 76ms
 */
public class Baekjoon9084 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			int n = Integer.parseInt(br.readLine());
			int[] coin = new int[n+1];
			String[] s = br.readLine().split(" ");
			for(int i=1;i<=n;i++) {
				coin[i] = Integer.parseInt(s[i-1]);
			}
			int m = Integer.parseInt(br.readLine());
			int[] dp = new int[m+1];
			dp[0] = 1;
			//각 동전을 골라서
			for(int i=1;i<=n;i++) {
				//해당 동전으로 만들 수 있는 금액부터 m까지
				for(int j=coin[i];j<=m;j++) {
					//누적하여 더한다
					dp[j] += dp[j-coin[i]];
				}
			}
			System.out.println(dp[m]);
		}
	}
}
