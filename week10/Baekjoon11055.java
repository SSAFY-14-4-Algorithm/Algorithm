import java.io.*;
import java.util.*;

/*
 * 메모리: 11,844KB
 * 시간: 84ms
 */
public class Baekjoon11055 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	// variables	
	static int N;
	static int[] A = new int[1_000];
	static int len;
	static int[] buffer = new int[1_000];
	static int[] dp = new int[1_000]; // A[i]를 마지막 원소로 하는 LIS의 원소 합 
	
	
	static int lowerBound(int lo, int hi, int key) {
		while(lo < hi) {
			int mid = (lo + hi) >> 1;
			if(A[buffer[mid]] < key) lo = mid + 1;
			else hi = mid;
		}
		return lo;
	}
	
	static int solution() {
		int max = 0;
		for(int i = 0; i < N; ++i) {
			int at = lowerBound(0, len, A[i]);
			if(at == len) ++len;
			buffer[at] = i;
			if(at == 0) dp[i] = A[i];
			else {
				for(int j = 0; j < i; ++j) {
					if(A[j] < A[i]) dp[i] = Math.max(dp[i], dp[j] + A[i]);
				}
			}
			max = Math.max(max, dp[i]);
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) A[i] = readInt();
		System.out.print(solution());
	}
	
	static int readInt() throws IOException {
		int c, n = System.in.read() & 0x0F, s = 1;
		if(n == ('-' & 0x0F)) {
			s = -1;
			n = 0;
		}
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n*s;
	}
}