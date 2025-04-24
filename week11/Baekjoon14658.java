import java.io.*;
import java.util.*;

/*
 * 메모리: 11,740KB
 * 시간: 84ms
 */
public class Baekjoon14658 {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StreamTokenizer st = null;
	// types
	// constants
	static final int MAX_K = 100;
	// variables
	static int N, M, L, K;
	static int[] X = new int[MAX_K];
	static int[] Y = new int[MAX_K];
	
	
	static boolean overlap(int ay, int ax, int y, int x) {
		return 0 <= ay - y && ay - y <= L && 0 <= ax - x && ax - x <= L;
	}
	
	static int solution() {
		int max = 0;
		for(int ix = 0; ix < K; ++ix) {
			int x = X[ix];
			for(int iy = 0; iy < K; ++iy) {
				int y = Y[iy];
				
				int cnt = 0;
				for(int i = 0; i < K; ++i) {
					if(overlap(y, x, Y[i], X[i])) ++cnt;
				}
				max = Math.max(max, cnt);
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt(); M = readInt(); L = readInt(); K = readInt();
		for(int i = 0; i < K; ++i) {
			X[i] = readInt(); Y[i] = readInt();
		}
		System.out.print(K-solution());
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		//if(c == '\r') System.in.read();
		return n;
	}
}
