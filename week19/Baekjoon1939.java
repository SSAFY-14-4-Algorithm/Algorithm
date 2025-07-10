import java.io.*;
import java.util.*;

/*
 *메모리: 66296
 *시간: 456
 *
 * 중량제한 , bfs, dfs
 *  
 */
public class Main {
	static int n,m;
	static boolean[] visited;
	static ArrayList<Edge>[] graph;
	static class Edge{
		int to;
		int weight;
		
		Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}
	static StringBuilder sb;
	
	
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	graph = new ArrayList[n+1];
    	for(int i=1;i<=n;i++) {
    		graph[i] = new ArrayList<>();
    	}
    	int max = 0;
    	for(int i=0;i<m;i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());
    		graph[a].add(new Edge(b,c));
    		graph[b].add(new Edge(a,c));
    		max = Math.max(max, c);
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	int x = Integer.parseInt(st.nextToken());
    	int y = Integer.parseInt(st.nextToken());
    	
    	int l = 1, r=max;
    	int ans = 0;
    	while(l<=r) {
    		int mid = (l+r)/2;
    		
    		if(pway(x,y,mid)) {
    			ans = mid;
    			l = mid+1;
    		} else {
    			r = mid-1;
    		}
    	}
    	
    	System.out.println(ans);
    }
    static boolean pway(int x, int y, int mid) {
    	Queue<Integer> queue = new LinkedList<>();
    	boolean[] visited = new boolean[n+1];
    	
    	queue.add(x);
    	visited[x] = true;
    	
    	while(!queue.isEmpty()) {
    		int cur = queue.poll();
    		
    		if(cur == y) return true;
    		
    		for(Edge edge : graph[cur]) {
    			if(!visited[edge.to] && edge.weight >= mid) {
    				visited[edge.to] = true;
    				queue.add(edge.to);
    			}
    		}
    	}
    	return false;
    }
}