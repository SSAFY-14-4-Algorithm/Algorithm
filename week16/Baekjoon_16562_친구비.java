import java.io.*;
import java.util.*;

/**
 * 시간: 88ms
 * 메모리: 12404KB
 */
public class Baekjoon_16562_친구비 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 10_000;
	// variables
	static int N, M, K;
	static int[] A = new int[SIZE + 1];
	static int[] parents = new int[SIZE + 1];
	static int[] ranks = new int[SIZE + 1];

	
	static int getId(int u) {
		if (u == parents[u]) return u;
		return parents[u] = getId(parents[u]);
	}

	static void merge(int u, int v) {
		u = getId(u);
		v = getId(v);
		if (u == v)
			return;

		if (ranks[u] < ranks[v]) {
			int tmp = u;
			u = v;
			v = tmp;
		} else if (ranks[u] == ranks[v]) {
			++ranks[u];
		}
		parents[v] = u;
		A[u] = Math.min(A[u], A[v]);
	}

	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		K = readInt();
		for (int i = 1; i <= N; ++i) A[i] = readInt();

		for (int i = 1; i <= N; ++i) parents[i] = i; // 서로소 집합 초기화
		for (int i = 0; i < M; ++i) {
			int u = readInt(), v = readInt();
			merge(u, v);
		}
		int sum = 0;
		for (int i = 1; i <= N; ++i) {
			if (i == parents[i]) {
				sum += A[i];
			}
		}
		if (sum <= K) System.out.println(sum);
		else System.out.println("Oh no");
	}

	static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}