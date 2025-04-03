import java.util.*;
import java.io.*;
/* Class 이용
 * 55768kb, 540ms
 */
class Baekjoon4195
{	
	static class Node{
		Node parent;
		int cnt;
		public Node() {
			this.parent = this;
			this.cnt = 1;
		}
	}
	
	public static Node find(Node x) {
		if(x.parent != x) return x.parent = find(x.parent);
		return x;
	}
	
	public static void union(Node a, Node b) {
		if(a.cnt > b.cnt) {
			b.parent = a;
			a.cnt += b.cnt;
		} else {
			a.parent = b;
			b.cnt += a.cnt;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st;
			HashMap<String, Node> hm = new HashMap<>();
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				hm.putIfAbsent(a, new Node());
				hm.putIfAbsent(b, new Node());
				Node x = find(hm.get(a));
				Node y = find(hm.get(b));
				if(x != y) {
					union(x, y);
				}
				sb.append(find(x).cnt).append("\n");
			}
		}
		System.out.print(sb);
	}
}


/* 집합 사용
 * 65120kb, 636ms
 */
//import java.util.*;
//import java.io.*;
//
//class Main
//{	
//	static int[] parent;
//	static int[] size;
//	
//	public static int find(int x) {
//		if(x != parent[x]) return parent[x] = find(parent[x]);
//		return x;
//	}
//	
//	public static void union(int a, int b) {
//		if(size[a] > size[b]) {
//			parent[b] = a;
//			size[a] += size[b];
//		}
//		else {
//			parent[a] = b;
//			size[b] += size[a];
//		}
//	}
//	
//	public static void main(String args[]) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int T = Integer.parseInt(br.readLine());
//		StringBuilder sb = new StringBuilder();
//		for(int testCase = 1; testCase <= T; testCase++) {
//			int N = Integer.parseInt(br.readLine());
//			StringTokenizer st;
//			int index = 1;
//			HashMap<String, Integer> hm = new HashMap<>();
//			parent = new int[200001];
//			size = new int[200001];
//			for(int i = 1; i <= 200000; i++) {
//				parent[i] = i;
//				size[i] = 1;
//			}
//			for(int i = 0; i < N; i++) {
//				st = new StringTokenizer(br.readLine());
//				String a = st.nextToken();
//				String b = st.nextToken();
//				hm.putIfAbsent(a, index++);
//				hm.putIfAbsent(b, index++);
//				int x = find(hm.get(a));
//				int y = find(hm.get(b));
//				if(x != y) union(x, y);
//				sb.append(size[find(x)]).append("\n");
//			}
//		}
//		System.out.print(sb);
//	}
//}
