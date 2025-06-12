import java.io.*;
import java.util.*;

/**
 * 시간: 184ms
 * 메모리: 20,660KB
 */
public class Baekjoon_1916_최소비용구하기 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static class Node implements Comparable<Node> {
		int vertex, weight;

		Node() { }
		Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node other) {
			return this.weight - other.weight;
		}
	}

	static final int MAX_N = 1_000;
	static final int MAX_W = 100_000;
	static final int INF = MAX_N * MAX_W;

	static int N, M, S, T;
	static List<Node>[] adj = new List[MAX_N];
	static int[] minDist = new int[MAX_N];

	static void solution() {
		Arrays.fill(minDist, 0, N, INF);

		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(S, 0));
		minDist[S] = 0;

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int u = node.vertex, w = node.weight;
			if (u == T)
				break;

			for (Node next : adj[u]) {
				int v = next.vertex, edge = next.weight;
				if (w + edge < minDist[v]) {
					minDist[v] = w + edge;
					pq.offer(new Node(v, minDist[v]));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// Input
		N = readInt();
		for (int i = 0; i < N; ++i)
			adj[i] = new ArrayList<Node>();

		M = readInt();
		for (int i = 0; i < M; ++i) {
			int u = readInt() - 1;
			int v = readInt() - 1;
			int w = readInt();
			adj[u].add(new Node(v, w));
		}

		S = readInt() - 1;
		T = readInt() - 1;

		solution();

		// Output
		System.out.print(minDist[T]);
	}

	static int readInt() throws IOException {
		int c, n = 0;
		while ((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if (c == '\r') System.in.read();
		return n;
	}
}
