import java.io.*;
import java.util.*;

/**
 * Java8
 * 메모리: 28,928KB
 * 시간: 200ms
 * 
 * Java11
 * 메모리: 30,648KB
 * 시간: 240ms
 * 
 * @author 배준수
 */
public class Baekjoon11437_LCA {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static final int MAX_N = 50_000;
	// variables
	static int N, M;
	static List<Integer>[] adj = new List[MAX_N + 1];
	static boolean[] isVisited = new boolean[MAX_N + 1];

	static int idx = 0;
	static int[] inv = new int[MAX_N + 1];
	static int[] depth = new int[MAX_N * 2 - 1];
	static int[] nodes = new int[MAX_N * 2 - 1];

	static void init(int u, int d) {
		isVisited[u] = true;
		nodes[idx] = u;
		depth[idx] = d;
		inv[u] = idx;
		++idx;

		for (int v : adj[u]) {
			if (isVisited[v]) continue;
			init(v, d + 1);
			
			nodes[idx] = u;
			depth[idx] = d;
			inv[u] = idx;
			++idx;
		}
	}

	static int lca(int u, int v) {
		int min = inv[u];
		for (int i = Math.min(inv[u], inv[v]), limit = Math.max(inv[u], inv[v]); i <= limit; ++i) {
			if (depth[min] > depth[i]) {
				min = i;
			}
		}
		return nodes[min];
	}

	public static void main(String[] args) throws IOException {
		N = readInt();
		for (int i = 0; i <= N; ++i)
			adj[i] = new ArrayList<>();
		for (int i = 0; i < N - 1; ++i) {
			int u = readInt(), v = readInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		init(1, 0);

		M = readInt();
		for (int i = 0; i < M; ++i) {
			int u = readInt(), v = readInt();
			sb.append(lca(u, v)).append("\n");
		}
		System.out.print(sb);
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