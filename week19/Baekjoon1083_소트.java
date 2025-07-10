import java.io.*;
import java.util.*;

/**
 * 시간: 68ms
 * 메모리: 11524KB
 */
public class Baekjoon1083_소트 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int N, S;
	static int[] A;
	
	static void solution() {
		for(int i = 0; i < N && S > 0; ++i) {
			
			int tgt = i;
			for(int j = i+1; j < N && j - i <= S; ++j) {
				if(A[j] > A[tgt]) {
					tgt = j;
				}
			}
			
			S -= tgt - i;
			
			while(tgt - i > 0) {
				int tmp = A[tgt]; A[tgt] = A[tgt-1]; A[tgt-1] = tmp;
				--tgt;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		A = new int[N];
		for(int i = 0; i < N; ++i) {
			A[i] = readInt();
		}
		S = readInt();
		
		solution();
		
		for(int i = 0; i < N; ++i) sb.append(A[i]).append(' ');
		System.out.print(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}