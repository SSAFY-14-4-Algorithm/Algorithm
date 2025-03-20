import java.util.*;
import java.io.*;

/*
 * 14312kb, 108ms
 */

//dp 풀이?
public class Baekjoon1446 {
	static class Node{
		int nxt, w;
		public Node(int nxt, int w) {
			this.nxt = nxt;
			this.w = w;
		}
	}
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	ArrayList<Node>[] arr = new ArrayList[M+1];
    	int[] visited = new int[M+1];
    	for(int i = 0; i <=M; i++) {
    		visited[i] = M+1;
    		arr[i] = new ArrayList<>();
    	}
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int s = Integer.parseInt(st.nextToken());
    		int e = Integer.parseInt(st.nextToken());
    		int w = Integer.parseInt(st.nextToken());
    		if(e <= M && e-s > w) {
    			arr[s].add(new Node(e, w));
    		}
    	}
    	visited[0] = 0;
    	for(int i = 0; i < M; i++) {
    		if(visited[i+1] > visited[i] + 1) {
    			visited[i+1] = visited[i] + 1;
    		}
    		for(Node nxt : arr[i]) {
        		if(visited[nxt.nxt] > visited[i] + nxt.w) {
        			visited[nxt.nxt] = visited[i] + nxt.w;
        		}
        	}
    	}
    	System.out.println(visited[M]);
    }
}



/*
 * 14380kb, 108ms
 */

//다익스트라
//import java.util.*;
//import java.io.*;
//
//public class Main {
//	static class Node {
//		int nxt;
//		int w;
//		public Node(int nxt, int w) {
//			this.nxt = nxt;
//			this.w = w;
//		}
//	}
//    public static void main(String[] args) throws IOException {
//    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	StringTokenizer st = new StringTokenizer(br.readLine());
//    	int N = Integer.parseInt(st.nextToken());
//    	int D = Integer.parseInt(st.nextToken());
//    	ArrayList<Node>[] arr = new ArrayList[D+1];
//    	int[] dist = new int[D+2];
//    	for(int i = 0; i <= D; i++) {
//    		arr[i] = new ArrayList<>();
//    		arr[i].add(new Node(i+1, 1));
//    		dist[i] = Integer.MAX_VALUE;
//    	}
//    	
//    	for(int i = 0; i < N; i++) {
//    		st = new StringTokenizer(br.readLine());
//    		int s = Integer.parseInt(st.nextToken());
//    		int e = Integer.parseInt(st.nextToken());
//    		int w = Integer.parseInt(st.nextToken());
//    		if(e <= D && e-s > w) {
//    			arr[s].add(new Node(e, w));
//    		}
//    	}
//    	PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.w-o1.w);
//    	dist[0] = 0;
//    	pq.add(new Node(0, 0));
//    	while(!pq.isEmpty()) {
//    		Node cur = pq.poll();
//    		if(dist[cur.nxt] < cur.w) continue;
//    		int nxt = cur.nxt;
//    		int w = cur.w;
//    		for(Node nxtE : arr[nxt]) {
//    			int cost = w + nxtE.w;
//    			if(dist[nxtE.nxt] > cost) {
//    				dist[nxtE.nxt] = cost;
//    				pq.add(new Node(nxtE.nxt, cost));
//    			}
//    		}
//    	}
//    	System.out.println(dist[D]);
//    }
//}


/*
 * 14304kb, 104ms
 */
// BFS (사실상 DP랑 같음)
//public class Main {
//	static class Node{
//		int nxt, w;
//		public Node(int nxt, int w) {
//			this.nxt = nxt;
//			this.w = w;
//		}
//	}
//    public static void main(String[] args) throws IOException {
//    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	StringTokenizer st = new StringTokenizer(br.readLine());
//    	int N = Integer.parseInt(st.nextToken());
//    	int M = Integer.parseInt(st.nextToken());
//    	ArrayList<Node>[] arr = new ArrayList[M+1];
//    	int[] visited = new int[M+1];
//    	for(int i = 0; i <=M; i++) {
//    		visited[i] = M+1;
//    		arr[i] = new ArrayList<>();
//    	}
//    	for(int i = 0; i < N; i++) {
//    		st = new StringTokenizer(br.readLine());
//    		int s = Integer.parseInt(st.nextToken());
//    		int e = Integer.parseInt(st.nextToken());
//    		int w = Integer.parseInt(st.nextToken());
//    		if(e <= M && e-s > w) {
//    			arr[s].add(new Node(e, w));
//    		}
//    	}
//    	
//    	Queue<Integer> q = new ArrayDeque<>();
//    	q.add(0);
//    	visited[0] = 0;
//    	while(!q.isEmpty()) {
//    		int cur = q.poll();
//    		int w = visited[cur];
//    		for(Node nxt : arr[cur]) {
//    			if(visited[nxt.nxt] > w + nxt.w ) {
//    				visited[nxt.nxt] = w + nxt.w;
//    				q.add(nxt.nxt);
//    			}
//    		}
//    		if(cur + 1 <= M && visited[cur+1] > w + 1) {
//    			q.add(cur+1);
//    			visited[cur+1] = w+1;
//    		}
//    	}
//    	System.out.println(visited[M]);
//    }
//}
