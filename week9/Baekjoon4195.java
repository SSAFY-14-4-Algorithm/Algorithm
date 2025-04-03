import java.io.*;
import java.util.*;

public class Baekjoon4195 {
	static int F;
	static Map<String, Integer> map;
	static int[] parent;
	static int[] size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			F = Integer.parseInt(br.readLine());

			parent = new int[F*2];
			size = new int[F*2];
			for(int f = 0; f < F*2; f++) {
				parent[f] = f;
				size[f] = 1;
			}

			map = new HashMap<String, Integer>();
			int idx = 0;
			for (int f = 0; f < F; f++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				String user1 = st.nextToken();
				String user2 = st.nextToken();
				if (!map.containsKey(user1)) {
					map.put(user1, idx++);
				}
				if (!map.containsKey(user2)) {
					map.put(user2, idx++);
				}

				union(map.get(user1), map.get(user2));

				int cnt = size[find(map.get(user1))];
				sb.append(cnt).append("\n");
			}
		}
		System.out.print(sb);
		br.close();
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if(x==y) return;
		if(x<y) {
			parent[y]=x;
			size[x]+=size[y];
		} else {
			parent[x]=y;
			size[y]+=size[x];
		}
	}

	private static int find (int x) {
		if(parent[x]==x) return x;
		return parent[x]=find(parent[x]);
	}
}
