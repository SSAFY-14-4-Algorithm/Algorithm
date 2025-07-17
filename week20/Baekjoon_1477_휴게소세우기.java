import java.io.*;
import java.util.*;

/**
 * 시간: 72ms
 * 메모리: 11552KB
 */
public class Baekjoon_1477_휴게소세우기 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int N, M, L;
	static int[] locations;
	
	static boolean isVaild(int limit) {
		int cnt = 0;
		for(int i = 1; i < locations.length; ++i) {
			cnt += ((locations[i] - locations[i - 1]) - 1) / limit;
		}
		return cnt <= M;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		L = readInt();
		
		locations = new int[N+2];
		locations[0] = 0;
		for(int i = 1; i <= N; ++i) locations[i] = readInt();
		locations[N + 1] = L;
		Arrays.sort(locations);
		
		int lo = 1, hi = L;
		while(lo < hi) {
			int mid = (lo + hi) >> 1;
			if(isVaild(mid)) hi = mid;
			else lo = mid + 1;
		}
		System.out.print(lo);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}