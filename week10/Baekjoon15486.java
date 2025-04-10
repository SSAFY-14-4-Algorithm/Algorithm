import java.io.*;
import java.util.*;
/*
 * 메모리:311532KB
 * 시간:832ms
 */
public class Baekjoon15486 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		int[] T = new int[n+1];
		int[] P = new int[n+1];
		for(int i=1;i<=n;i++) {
			String[] s = br.readLine().split(" ");
			T[i] = Integer.parseInt(s[0]);
			P[i] = Integer.parseInt(s[1]);
		}
		int[] dp = new int[n+2];
		int result = 0;
		//뒤에서부터 dp계산
		for(int i=n;i>=1;i--) {
			// N+1일째에는 회사에 없기 때문에 넘으면 상담못함
			if(i+T[i]>n+1) {
				dp[i] = dp[i+1];
				continue;
			}
			//상담을 안하고 넘어가는 것과 해당 일자 상담을 하는 것중 최대값 저장 
			dp[i] = Math.max(dp[i+1], dp[i+T[i]] + P[i]);
			result = Math.max(result, dp[i]);
		}
		System.out.println(result);
	}
}
