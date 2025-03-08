import java.util.*;
import java.io.*;
/*
 * 41788kb,	428ms
 */
public class Baekjoon1931 {
	
	public static class Node{
		int start, end;
		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b)-> a.end!=b.end ? a.end-b.end : a.start-b.start);
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add( new Node( Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) ) );
		}
		int answer = 0;
		int prev = -1;
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			if(current.start >= prev){
				answer++;
				prev = current.end;
			}
		}
		
		System.out.println(answer);
	}
}