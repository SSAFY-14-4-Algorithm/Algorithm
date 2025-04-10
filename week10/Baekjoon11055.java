import java.io.*;
import java.util.*;
/*
 * 메모리 11924KB
 * 시간 84ms
 */
public class Baekjoon11055 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n+1];
		int[] dp = new int[n+1];
		String[] s = br.readLine().split(" ");
		for(int i=1;i<=n;i++) {
			A[i] = Integer.parseInt(s[i-1]);
		}
		int result = 0;
		for(int i=1;i<=n;i++) {
			int max = 0;
			for(int j=1;j<i;j++) {
				//이전값들 중에 값이 최대인것 저장
				if(A[i]>A[j])
					max = Math.max(max, dp[j]);
			}
			dp[i] = max + A[i];
			result = Math.max(result, dp[i]);
		}
		System.out.println(result);
	}
}
