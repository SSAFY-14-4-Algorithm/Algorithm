import java.util.*;
import java.io.*;
import java.math.*;

/*
 * 14360kb, 108ms
 */
public class Baekjoon11053{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[N+1];
		dp[0] = -1_000_000_000;
		int[] cnt = new int[N+1];
		int last = 0;
		for(int i = 1; i < N+1; i++) {
			int target = arr[i];
			if(target > dp[last]) {
				dp[++last] = target;
				cnt[i] = last;
			} else {
				int left = 0;
				int right = last;
				while(left <= right) {
					int mid = (left + right)/2;
					if(dp[mid] >= target) {
						right = mid - 1;
					} else {
						left = mid + 1;
					}
				}
				cnt[i] = left;
				dp[left] = target;
			}
		}
		System.out.println(last);
	}
}