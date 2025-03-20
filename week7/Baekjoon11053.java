import java.io.*;
import java.util.*;
/*
 * 메모리 11924KB
 * 시간 88ms
 */
public class Baekjoon11053 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n];
		int[] nums = new int[n];
		//1개일때 1 출력해야하므로 1 시작
		int result=1;
		String[] s = br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			nums[i] = Integer.parseInt(s[i]);
		}
		
		dp[0] = 1;
		for(int i=1;i<n;i++) {
			dp[i]=1;
			//이전 값들과 비교해보면서 이전값보다 크고 
			//이전에 구해놓은 부분 수열 길이값보다 현재값이 작거나 같으면 저장 
			for(int j=0;j<i;j++) {
				if(nums[i]>nums[j] && dp[i] <= dp[j])
					dp[i] = dp[j]+1;
			}
			//다 구하면 최대값 저장
			result = Math.max(result,dp[i]);
		}
		System.out.println(result);
	}
}
