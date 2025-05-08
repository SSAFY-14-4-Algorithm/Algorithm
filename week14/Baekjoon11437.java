import java.util.*;
import java.io.*;

/* LCA (Lowest Common Ancestor)
 * 44304kb 412ms
 */

public class Baekjoon11437 {
	static int[][] parent;
	static int LOG;
	static int N;
	static int[] depth;
	
	//희소배열을 만드는 전처리 함수 (부모 배열 만들기)
	public static void makeArr() {
		for(int i = 1; i <= LOG; i++) {
			for(int j = 1; j <= N; j++) {
				if(parent[j][i-1] != 0) {
					parent[j][i] = parent[parent[j][i-1]][i-1];
				}
			}
		}
	}
	
	public static int lca(int u, int v) {
		//u의 깊이가 더 크게 설정
		if(depth[u] < depth[v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		
		//u를 v와 같은 깊이로 만들기
		for(int i = LOG; i>=0; i--) {
			if(depth[u] - (1<<i) >= depth[v]) {
				u = parent[u][i];
			}
		}
		
		//u와 v가 같은 깊이 인데, u와 v가 같은 노드라면 둘 중 하나 리턴, v가 둘의 공통부모라는 것
		if(u==v) return u;
		
		//같은 높이이므로 같은 높이를 올라 갔을 때 다른 노드라면 값 수정
		//결론적으로는 공통조상 노드 바로 아래의 노드로 가게 됨
		//그냥 LOG로 해도 됨
		for(int i = (int)(Math.log(depth[u])/Math.log(2)); i >= 0; i--) { 
			if(parent[u][i] != parent[v][i]) {
				u = parent[u][i];
				v = parent[v][i];
			}
		}
		
		//따라서 부모 노드를 반환
		return parent[u][0];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		//최대 깊이 보다 작으면서 가장 큰 지수값
		LOG = (int)(Math.log(N)/Math.log(2));
		parent = new int[N+1][LOG+1];
		depth = new int[N+1];
		
		//입력 받기
		ArrayList<Integer>[] arr = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a].add(b);
			arr[b].add(a);
		}
		
		//깊이 구하기
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		boolean[] visited = new boolean[N+1];
		visited[1] = true;
		int d = 0;
		while(!q.isEmpty()) {
			int len = q.size();
			for(int i = 0; i < len; i++) {
				int cur = q.poll();
				depth[cur] = d;
				for(int nxt : arr[cur]) {
					if(!visited[nxt]) {
						parent[nxt][0] = cur;
						q.add(nxt);
						visited[nxt] = true;
					}
				}
			}
			d++;
		}
		
		//부모 배열 전처리
		makeArr();
		
		//LCA 구하기
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(lca(a,b)).append("\n");
		}
		System.out.print(sb);
	}

}
