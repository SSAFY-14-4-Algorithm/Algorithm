import java.io.*;
import java.util.*;

/*
 * 메모리: 11,436KB
 * 시간: 64ms
 */
public class Baekjoon9084 {
	// IO Handler
	static StringBuilder sb = new StringBuilder();
	// types
	// constants
	// variables
	static int N, M;
	static int[] coins = new int[20];
	static int[] dp = new int[10_001];
	
	
	static int solution() {
		Arrays.fill(dp, 1, M + 1, 0);
		for(int i = 0; i < N; ++i) {
			for(int j = coins[i]; j <= M; ++j) {
				dp[j] += dp[j - coins[i]];
			}
		}
		return dp[M]; 
	}
	
	public static void main(String[] args) throws IOException {
		dp[0] = 1;
    	int T = readInt();
    	while(T-- > 0) {
    		N = readInt();
    		for(int i = 0; i < N; ++i) coins[i] = readInt();
    		M = readInt();
    		sb.append(solution()).append("\n");
    	}
    	System.out.println(sb);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}