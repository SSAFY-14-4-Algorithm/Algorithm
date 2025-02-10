import java.io.*;
import java.util.*;
import java.math.*;

public class Beakjoon1260 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int[][] map;
	public static boolean[] visited;
	public static void main(String[] args) throws IOException {
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		int V = Integer.parseInt(s[2]);
		map = new int[N][N];
		visited = new boolean[N];
		for(int i=0;i<M;i++) {
			String[] m = br.readLine().split(" ");
			map[Integer.parseInt(m[0])-1][Integer.parseInt(m[1])-1] = 1;
			map[Integer.parseInt(m[1])-1][Integer.parseInt(m[0])-1] = 1;
			
		}
		dfs(V);
		System.out.println();
		Arrays.fill(visited, false);
		bfs(V);
		
	}
	public static void dfs(int V) {
		visited[V-1] = true;
		System.out.print(V+" ");
		
		for(int i=1;i<visited.length+1;i++) {
			if(map[V-1][i-1]==1 && visited[i-1]==false) {
				dfs(i);
			}
		}
	}
	
	public static void bfs(int V) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(V);
		visited[V-1] = true;
		
		while(!q.isEmpty()) {
			int node = q.poll();
			System.out.print(node+" ");
			for(int i=1;i<visited.length+1;i++) {
				if(map[node-1][i-1]==1 && visited[i-1]==false) {
					q.offer(i);
					visited[i-1] = true;
				}
			}
		}
	}
}
