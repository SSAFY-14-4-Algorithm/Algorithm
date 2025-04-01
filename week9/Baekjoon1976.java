import java.io.*;
import java.util.*;

public class Baekjoon1976 {
	static int n, m;
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		parent = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
		
		// 연결된 도시 union 처리
		for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int connected = Integer.parseInt(st.nextToken());
                if (connected == 1) {
                    union(i, j);
                }
            }
        }
		
		st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        boolean ans = true;

        for (int i = 1; i < m; i++) {
            int next = Integer.parseInt(st.nextToken());
            if (find(start) != find(next)) {
                ans = false;
                break;
            }
        }
        
        System.out.println(ans ? "YES" : "NO");
	}
	
	static int find(int x) {
		if (x != parent[x]) parent[x] = find(parent[x]);
		return parent[x];
	}
	
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if (pa != pb) parent[pb] = pa;
	}
}
