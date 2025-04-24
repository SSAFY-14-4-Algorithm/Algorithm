import java.io.*;
import java.util.*;

/*
 * 메모리: 29,732KB
 * 시간: 296ms
 */
public class Baekjoon15486 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static int MAX_N = 1_500_000;
	// variables
	static int N;
	static int[] T = new int[MAX_N + 10];  // 상담 소요 일수
	static int[] P = new int[MAX_N + 10];  // 수임료
	static int[] dp = new int[MAX_N + 10];  // i일부터 N일까지 얻을 수 있는 최대 수임료
	
	static int solution() {
		for(int d = N; d > 0; --d) {
			dp[d] = dp[d + 1];
			if(d + T[d] - 1 <= N) dp[d] = Math.max(dp[d], dp[d + T[d]] + P[d]);
		}
		return dp[1];
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 1; i <= N; ++i) {
			T[i] = readInt();
			P[i] = readInt();
		}
		System.out.print(solution());
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}