import java.io.*;
import java.util.*;
/*
 * 메모리 : 11564KB	
 * 시간: 	68ms
 */
public class Baekjoon1535 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		String[] s1 = br.readLine().split(" ");
		String[] s2 = br.readLine().split(" ");
		int[] health = new int[n+1];
		int[] happy = new int[n+1];
		for(int i=1;i<=n;i++) {
			health[i] = Integer.parseInt(s1[i-1]);//체력
			happy[i] = Integer.parseInt(s2[i-1]);//기쁨
		}
		//KnapSack Problem
		int[][] dp = new int[n+1][101];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=100;j++) {
				//W의 무게보다 K의 무게가 더 크면 못넣으므로 이전 값 그대로 사용
				if(health[i]>=j) {
					dp[i][j] = dp[i-1][j];
				}
				else {
					//W의 무게보다 K의 무게가 더 작을때
					//넣을수도 있고 안넣을 수도 있으니 두개 비교후 큰값 저장
					dp[i][j] = Math.max(dp[i-1][j], happy[i]+dp[i-1][j-health[i]]);
				}				
			}
		}
		System.out.println(dp[n][100]);
	}
}
