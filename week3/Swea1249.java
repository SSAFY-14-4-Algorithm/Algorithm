import java.io.*;
import java.util.*;

public class Swea1249{
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static int N;
	static char [][] map;
	static int[][] dist;
	static StringBuilder sb = new StringBuilder();
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][];
			for(int i=0;i<N;i++) map[i]=br.readLine().toCharArray();
			
			dijkstra();
			
			sb.append('#').append(test).append(' ').append(dist[N-1][N-1]).append('\n');
		
		}
		System.out.println(sb);
	}
	
	static class Vertex{
		int r,c;
		int weight;
		
		public Vertex(int r,int c,int weight) {
			this.r= r;
			this.c =c;
			this.weight = weight;
		}
	}
	
	private static void dijkstra() {
		dist = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		
		for(int i=0;i<N;i++) Arrays.fill(dist[i] , Integer.MAX_VALUE);
		dist[0][0] =0;
		
		PriorityQueue<Vertex> q = new PriorityQueue<>((x,y)->x.weight-y.weight);
		q.offer(new Vertex(0,0,0));
		
		while(!q.isEmpty()) {
			Vertex v = q.poll();
			int r = v.r;
			int c = v.c;
			
			if(visited[r][c]) continue;
			
			visited[r][c] = true;
			if(r==N-1 && c==N-1) return;
			
			for(int d=0;d<4;d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc >= N) continue;
				if(visited[nr][nc]) continue;
				
				if(dist[nr][nc]<=dist[r][c]+(map[nr][nc]-'0')) continue;
				
				dist[nr][nc] = dist[r][c] +(map[nr][nc]-'0');
				q.offer(new Vertex(nr,nc,dist[nr][nc]));
			}
		}
	}
	
}
