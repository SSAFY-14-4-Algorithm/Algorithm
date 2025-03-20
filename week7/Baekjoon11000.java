import java.util.*;
import java.io.*;

/*
 * 69824kb, 600ms
 */

public class Baekjoon11000 {
	static class Node{
		int start, end;
		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)-> (o1.start==o2.start ? o1.end-o2.end : o1.start-o2.start));
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pq.add(new Node(start, end));
		}
		PriorityQueue<Integer> rooms = new PriorityQueue<>();
		rooms.add(pq.poll().end);
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int start = cur.start;
			int end = cur.end;
			if(start >= rooms.peek()) {
				rooms.poll();
			}
			rooms.add(end);
		}
		System.out.println(rooms.size());
	}
}