import java.util.*;
import java.io.*;
/* 선긋기
 * 275264kb, 1440ms 
 */
public class Baekjoon2170 {
	static class Node{
		int x1, x2;
		public Node(int x1, int x2) {
			this.x1 = x1;
			this.x2 = x2;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.x1==o2.x1 ? o1.x2-o2.x2 : o1.x1-o2.x1);
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			pq.add(new Node(x1, x2));
		}
		int ans = 0;
		Node first = pq.poll();
		int s = first.x1;
		int e = first.x2;
		for(int i = 1; i < N; i++) {
			Node cur = pq.poll();
			if(cur.x1 <= e) {
				if(cur.x2 > e) {
					e = cur.x2;
				}
			} else {
				ans += e-s;
				s = cur.x1;
				e = cur.x2;
			}
		}
		ans += e-s;
		System.out.println(ans);
	}
}
