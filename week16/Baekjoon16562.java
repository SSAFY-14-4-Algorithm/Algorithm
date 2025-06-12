import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//메모리 18780kb 시간 188ms
//유니온 파인드
public class Baekjoon16562 {
	static int parent[], cost[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int answer = 0;

		parent = new int[n+1];
		cost = new int[n+1];

		for (int i = 1 ; i <= n; i++) {
			parent[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1 ; i <= n; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1 ; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}

		Set<Integer> parents = new HashSet<>();
		for (int i = 1; i <= n; i++) {
			parents.add(find(i));
		}

		for (int parent : parents) {
			answer += cost[parent];
		}

		if (answer > k) {
			System.out.println("Oh no");
		} else {
			System.out.println(answer);
		}
		
	}

	static int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB) return;
		if (cost[rootA] < cost[rootB]) {
			parent[rootB] = rootA;
		} else {
			parent[rootA] = rootB;
		}
	}

}
