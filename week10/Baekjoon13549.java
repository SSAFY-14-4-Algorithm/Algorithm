import java.io.*;
import java.util.*;

/*
 * 메모리: 22,676KB
 * 시간: 132ms
 */
public class Baekjoon13549 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	static final int SIZE = 200_100;
	// variables
	static int N, K;
	static int[] isVisited = new int[SIZE + 1];
	
	static boolean isValid(int x) {
		return 0 <= x && x < SIZE && isVisited[x] == -1;
	}
	
	static void solution() {
		Arrays.fill(isVisited, -1);
		Deque<Integer> q = new ArrayDeque<Integer>();
		q.offerLast(N);
		isVisited[N] = 0;

		while (!q.isEmpty()) {
			int x = q.pollFirst();
			if (x == K) return;

			for(int nx : new int[] {x-1, x+1, x<<1}) {
				if(isValid(nx)) {
					if(nx == x<<1) {
						q.offerFirst(nx);
						isVisited[nx] = isVisited[x];
					} else {
						q.offerLast(nx);
						isVisited[nx] = isVisited[x] + 1;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		N = readInt();
		K = readInt();
		solution();
		System.out.print(isVisited[K]);
	}

	static int readInt() throws IOException {
		int c, n = 0;
		while ((c = System.in.read()) >= 0x30)
			n = (n << 3) + (n << 1) + (c & 0x0F);
		if (c == '\r')
			System.in.read();
		return n;
	}
}
