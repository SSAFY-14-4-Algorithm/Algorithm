import java.io.*;
import java.util.*;
/*
 * 메모리 11596KB
 * 시간 	68ms
 */
public class Baekjoon2579 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		int[] stairs = new int[n+1];
		for(int i=1;i<=n;i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		//dp[0]은 1번뛸때 계산 dp[1]은 2번뛸때 계산
		int[][] dp = new int[2][n+1];
		dp[0][0] = 0;
		dp[1][0] = 0;
		dp[0][1] = stairs[1];
		dp[1][1] = 0;
		if(n>=2) {
            //시작점 제외하고 한번뛰기를 연속으로 하면 안되기 때문에 따로 처리
			dp[0][2] = dp[0][1]+stairs[2];
			dp[1][2] = dp[1][0]+stairs[2];
			for(int i=3;i<=n;i++) {
                //1번뛰기를 연속으로 할 수 없으므로 이전에 2번뛰었을때 다음 한번뛰기
				dp[0][i] = dp[1][i-1] + stairs[i];
                //2번뛸때는 그 전값이 1번뛰든 2번뛰든 상관없으므로 둘중 최대값 선택 
				dp[1][i] = Math.max(dp[0][i-2], dp[1][i-2]) + stairs[i];
			}			
		}
		System.out.println(Math.max(dp[0][n], dp[1][n]));
	}
}
