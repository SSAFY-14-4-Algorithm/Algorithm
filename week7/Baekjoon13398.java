import java.util.*;
import java.io.*;

/*
 * 25228kb, 264ms
 */

//Bottom-up
public class Baekjoon13398 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int answer = Integer.parseInt(st.nextToken());
    		int[][] dp = new int[2][N];
        	dp[0][0] = answer;
        	dp[1][0] = answer;
        	answer = Math.max(answer, Math.max(dp[0][0], dp[1][0]));
    		for(int i = 1; i < N; i++) {
    			int num = Integer.parseInt(st.nextToken());
        		dp[0][i] = Math.max(dp[0][i-1] + num, num);
        		dp[1][i] = Math.max(dp[0][i-1], dp[1][i-1] + num);
        		answer = Math.max(answer, Math.max(dp[0][i], dp[1][i]));
        	}
    		System.out.println(answer);
    }
}


/*
 * 38524kb, 288ms
 */

//Top-down
//import java.util.*;
//import java.io.*;
//
//public class Main {
//	static int[][] dp;
//	static int N;
//	static int[] arr;
//	static int answer;
//	
//	public static int topDown(int idx, int cnt) {
//		if(idx < 0) return -100000001;
//		
//		if (dp[idx][cnt] != -100000001) return dp[idx][cnt];
//		
//		if(cnt == 0) {
//			dp[idx][cnt] = Math.max(topDown(idx-1, 0) + arr[idx], arr[idx]);
//		} else {
//			dp[idx][cnt] = Math.max(topDown(idx-1, 0), topDown(idx-1, 1) + arr[idx]);
//		}
//		answer = Math.max(dp[idx][cnt], answer);
//		return dp[idx][cnt];
//	}
//	
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		N = Integer.parseInt(br.readLine());
//		arr = new int[N];
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		dp = new int[N][2];
//		for(int i = 0; i < N; i++) {
//			arr[i] = Integer.parseInt(st.nextToken());
//			Arrays.fill(dp[i], -100000001);
//		}
//		answer = -100000001;
//		topDown(N-1, 0);
//		topDown(N-1, 1);
//		System.out.println(answer);
//	}
//}