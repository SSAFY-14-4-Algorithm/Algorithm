import java.io.*;
import java.util.*;
import java.util.function.BiFunction;

/**
 * https://www.acmicpc.net/problem/20159
 * 시간: 100ms
 * 메모리: 13296KB
 */
public class Baekjoon_20159_동작그만밑장빼기냐 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int N;
	static int[] X;
	static int[] leftSum, rightSum;
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		X = new int[N];
		for(int i = 0; i < N; ++i) X[i] = readInt();
		
		leftSum = new int[N+1];  // [i]: Sum(X[j % 2 == 0 && j < i]) 
		rightSum = new int[N+1]; // [i]: Sum(X[j % 2 == 1 && j >= N - i])
		
		leftSum[0] = 0;
		for(int i = 2; i <= N; i += 2) {
			leftSum[i - 1] = leftSum[i] = leftSum[i - 2] + X[i - 2];   
		}
		rightSum[0] = 0;
		for(int i = 2; i <= N; i += 2) {
			rightSum[i - 1] = rightSum[i] = rightSum[i - 2] + X[N - i + 1]; 
		}
		
		int max = Integer.MIN_VALUE;
		for(int i = 0; i <= N; ++i) {
			int sum = leftSum[i] + rightSum[N - i];
			if(i % 2 == 1) sum -= X[N - 1];
			max = Math.max(max, sum);
		}
		System.out.print(max);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}