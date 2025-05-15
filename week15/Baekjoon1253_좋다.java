import java.io.*;
import java.util.*;

/**
 * 메모리: 12664kb
 * 시간: 100ms
 */
public class Baekjoon1253_좋다 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int N;
	static int[] A;
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		A = new int[N]; 
		for(int i = 0; i < N; ++i) A[i] = readInt();
		Arrays.sort(A);
		
		int cnt = 0;
		for(int i = 0; i < N; ++i) {
			
			int lcur = 0, rcur = N - 1;
			while(lcur < rcur) {
				int sum = A[lcur] + A[rcur]; 
				if(sum < A[i] || lcur == i) {
					++lcur;
				} else if(A[i] < sum || rcur == i) {
					--rcur;
				} else {
					++cnt;
					break;
				}
			}
		}
		System.out.print(cnt);
	}
	
	static int readInt() throws IOException {
		int c, n = System.in.read() & 0x0F, s = 1;
		if(n == 13) {
			s = -1;
			n = 0;
		}
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n * s;
	}
}