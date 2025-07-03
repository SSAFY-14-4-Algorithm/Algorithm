import java.util.*;
import java.io.*;
/* 암호코드
 * 14240kb, 104ms
 */
public class Baekjoon2011 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int len = s.length();
		int[] dp = new int[len+1];
		dp[0] = 1;
		final int MOD = 1000000;
		for(int i = 1; i <= len; i++) {
			int cur = s.charAt(i-1) - '0';
			if(cur >= 1 && cur <= 9) {
				dp[i] += dp[i-1];
				dp[i] %= MOD;
			}
			
			if(i == 1) continue;
			
			int prev = s.charAt(i-2) - '0';
			
			if(prev == 0) continue;
			
			int ten = prev*10 + cur;
			
			if(ten >= 10 && ten <= 26) {
				dp[i] += dp[i-2];
				dp[i] %= MOD;
			}
		}
		System.out.println(dp[len]);
	}
}
