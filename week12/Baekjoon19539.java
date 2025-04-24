import java.io.*;
import java.util.*;

/*
 * 메모리: 12,336KB
 * 시간: 96ms
 */
public class Baekjoon19539 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	// variables
	static int N;
	static int[] H = new int[100_000];
	
	static boolean solution() {
		int one = 0, two = 0;
		for(int i = 0; i < N; ++i) {
			one += H[i] & 1;
			two += H[i] >> 1;
		}
		return (two - one) >= 0 && (two - one) % 3 == 0;
	}
	
	public static void main(String[] args) throws IOException {
		// Input
		N = readInt();
		for(int i = 0; i < N; ++i) H[i] = readInt();
		// Output
		System.out.println(solution() ? "YES" : "NO");
	}
	
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}