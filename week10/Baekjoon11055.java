import java.io.*;
import java.util.*;

public class Baekjoon11055 {
	
	//메모리 15332kb 시간 128ms
	//dp 사용
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int a = Integer.parseInt(br.readLine());
		int[] arr = new int[a];
		int[] dp = new int[a];
		
		st  = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < a; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = arr[i];
		}
		int ans = arr[0];
		
		for (int i = 1; i < a; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
		
		System.out.println(ans);
		
	}
}
