import java.io.*;
import java.util.*;

public class Swea1249 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int[][] dist;
	public static int[][] map;
	public static boolean[][] visited;
	public static int[] dx = {1,0,-1,0};
	public static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int i=1;i<=T;i++) {
			int n = Integer.parseInt(br.readLine());
			dist = new int[n+1][n+1];
			map = new int[n+1][n+1];
			visited = new boolean[n+1][n+1];
			for(int j=1;j<=n;j++) {
				char[] c = br.readLine().toCharArray();
				for(int k=1;k<=n;k++) {
					map[j][k] = c[k-1]-'0';
					dist[j][k] = 90001;
				}
			}
			Dijkstra();

			System.out.println("#"+i+" "+dist[n][n]);
		}
	}
	public static void Dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b)-> a[2]-b[2]);
		pq.offer(new int[]{1,1,0});
		dist[1][1] = 0;
		
		while (!pq.isEmpty()) {
			int[] current = pq.poll();
			int x = current[0];
			int y = current[1];
			
			if(visited[x][y]) continue;
			visited[x][y] = true;
			
			for(int i=0;i<4;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx>=1 && ny>=1 && nx<=map.length-1 && ny<=map.length-1 && !visited[nx][ny] && dist[nx][ny] > dist[x][y] + map[nx][ny] ) {
					dist[nx][ny] = dist[x][y] + map[nx][ny];
					pq.offer(new int[]{nx,ny,dist[nx][ny]});
				}
			}	
		}
	}
}
