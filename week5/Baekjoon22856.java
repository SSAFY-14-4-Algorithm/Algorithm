import java.io.*;
import java.util.*;
/*
 * 	44684KB, 344ms
 */
//전체 간선수 *2 - (루트에서 마지막 노드까지의 간선 수)
public class Baekjoon22856 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] tree = new int[N+1][2];
		int answer = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			tree[p][0] = l;
			tree[p][1] = r;
			answer += l != -1 ? 1 : 0;
			answer += r != -1 ? 1 : 0;
		}
		answer *= 2;
		answer++;
		int node = 1;
		while(node != -1) {
			answer--;
			node = tree[node][1];
		}
		System.out.println(answer);
	}
}

//중위순회
//47300KB, 348ms
//import java.io.*;
//import java.util.*;
//
//public class Main {
//	static int[][] tree;
//	static int answer;
//	static int cnt;
//	public static boolean inorder(int node) {
//		if(node > 0) {
//			if(inorder(tree[node][0])) {
//				cnt += 1;
//			}
//			answer = cnt;
//			cnt += 1;
//			if(inorder(tree[node][1])) {
//				cnt += 1;
//			}
//			return true;
//		} else {
//			return false;
//		}
//	}
//	
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int N = Integer.parseInt(br.readLine());
//		StringTokenizer st;
//		tree = new int[N+1][2];
//		
//		for(int i = 0; i < N; i++) {
//			st = new StringTokenizer(br.readLine());
//			int p = Integer.parseInt(st.nextToken());
//			int l = Integer.parseInt(st.nextToken());
//			int r = Integer.parseInt(st.nextToken());
//			tree[p][0] = l;
//			tree[p][1] = r;
//		}
//		answer = 0;
//		cnt = 0;
//		inorder(1);
//		System.out.println(answer);
//	}
//}




//LCA 알고리즘 활용
//58600KB, 432ms
//public class Main {
//	static int[][] tree;
//	static int[] order;
//	static int orderN;
//	static int[][] parent;
//	static int N;
//	static int LOG;
//	
//	public static void inorder(int node, int level) {
//		if(node > 0) {
//			inorder(tree[node][0], level+1);
//			order[orderN++] = node;
//			tree[node][2] = level;
//			inorder(tree[node][1], level+1);
//		}
//	}
//	
//	public static void makeArr() {
//		for(int i = 1; i < LOG; i++) {
//			for(int j = 1; j < N+1; j++) {
//				if(parent[j][i-1] != 0) {
//					parent[j][i] = parent[parent[j][i-1]][i-1];
//				}
//			}
//		}
//	}
//	
//	public static int lca(int u, int v) {
//		if(tree[u][2] < tree[v][2]) { //높이 낮은 걸 u로
//			int temp = u;
//			u = v;
//			v = temp;
//		}
//		for(int i = LOG-1; i >= 0; i--) { //높이 맞춰주기
//			if((tree[u][2] - (1<<i)) >= tree[v][2]) {
//				u = parent[u][i];
//			}
//		}
//		if(u==v) return u;
//		
//		for(int i = LOG-1; i >= 0; i--) { //올라가면서 부모 찾기
//			if(parent[u][i] != parent[v][i]) {
//				u = parent[u][i];
//				v = parent[v][i];
//			}
//		}
//		return parent[u][0];
//	}
//	
//	
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		N = Integer.parseInt(br.readLine());
//		StringTokenizer st;
//		tree = new int[N+1][3];
//		order = new int[N+1];
//		LOG = (int)(Math.log(N)/Math.log(2))+1;
//		parent = new int[N+1][LOG];
//		for(int i = 0; i < N; i++) {
//			st = new StringTokenizer(br.readLine());
//			int p = Integer.parseInt(st.nextToken());
//			int l = Integer.parseInt(st.nextToken());
//			int r = Integer.parseInt(st.nextToken());
//			tree[p][0] = l;
//			tree[p][1] = r;
//			if(l != -1) parent[l][0] = p;
//			if(r != -1) parent[r][0] = p;
//		}
//		orderN = 1;
//		order[0] = 1;
//		inorder(1, 0);
//		parent[1][0] = 1;
//		makeArr();
//		int answer = 0;
//		for(int i = 1; i < N+1; i++) {
//			int p = lca(order[i-1], order[i]);
//			answer +=  tree[order[i-1]][2] + tree[order[i]][2] -tree[p][2]*2;
//		}
//		
//		System.out.println(answer);
//	}
//}