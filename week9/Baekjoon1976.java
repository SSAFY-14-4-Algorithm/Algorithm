import java.io.*;
import java.util.*;
/*
 * 17396kb, 152ms
 */
public class Baekjoon1976 {
	static int[] parent;
	public static int find(int x) {
		if(x != parent[x]) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}
	
	public static void union(int x, int y) {
		if(x > y) {
			parent[x] = y;
		} else {
			parent[y] = x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		parent = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int k = Integer.parseInt(st.nextToken());
				if(k == 1) {
					int a = find(i);
					int b = find(j);
					if(i != j) {
						union(a, b);
					}
				}
			}
		}
		int[] ans = new int[M];
		st = new StringTokenizer(br.readLine());
		ans[0] = find(Integer.parseInt(st.nextToken()));
		boolean flag = true;
		for(int i = 1; i < M; i++) {
			ans[i] = find(Integer.parseInt(st.nextToken()));
			if(ans[i-1] != ans[i]) {
				flag = false;
				break;
			}
		}
		if(flag) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
}