package week4;
import java.util.*;

/*
 * 파스칼 삼각형 사용
 *   - nCk = n-1Ck-1 + n-1Ck
 * 모듈러 연산 사용
 */

public class BaekJoon11051 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[][] dp = new int [1001][1001];
		
		for(int i=1;i<=N;i++) {
			for(int j=0;j<=Math.min(i, K);j++) {
				if(j == 0 || i == j) {
					dp[i][j] = 1;
					
					continue;
				}
				
				dp[i][j] = (dp[i-1][j-1] + dp[i-1][j]) % 10007;
			}
			
		}
		
		System.out.println(dp[N][K]);
		
		sc.close();
	}

}
