import java.io.*;
import java.util.*;


public class Main {
	public static class Node{
		long x;
		int depth;
		public Node(long x, int depth) {
			this.x = x;
			this.depth = depth;
		}
		
	}
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		
		
		System.out.println(bfs(A,B));
		
	}
	
	public static int bfs(long A, long B) {
		ArrayDeque<Node> queue = new ArrayDeque<>();
		queue.add(new Node(A,1));
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			long x = cur.x;
			int depth = cur.depth;
			
			if(x==B) return depth;
			
			if(x*2<=B) queue.add(new Node(x*2,depth+1));
			if(x*10+1<=B) queue.add(new Node(x*10+1,depth+1));
			
		}
		
		return -1;
		
	}
}
