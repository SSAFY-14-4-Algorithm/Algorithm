import java.io.*;
import java.util.*;

public class Baekjoon7576 {
	public static class Node{	
		int x;
		int y;
		int depth;
		
		public Node(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int[][] map;
	public static int[] dx = {0,0,-1,1};
	public static int[] dy = {-1,1,0,0};	
	public static Queue<Node> q = new ArrayDeque<>();
	public static int result = 0;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		int m = Integer.parseInt(s[0]);
		int n = Integer.parseInt(s[1]);
		map = new int[n][m];
		for(int i=0;i<n;i++) {
			String[] tomatoes = br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(tomatoes[j]);
				if(map[i][j]==1) {
					q.offer(new Node(i,j,0));
				}
			}
		}
		if(q.size()==m*n) {
			sb.append(0);
		}
		else {
			Bfs();
			boolean flag = false;
			A:for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map[i][j]==0) {
						flag = true;
						break A;
					}
				}
			}
			if(flag) sb.append(-1);
			else sb.append(result);
		}
		System.out.println(sb.toString());
	}
	public static void Bfs() {
		while(!q.isEmpty()) {
			Node tomato = q.poll();
			for(int i=0;i<4;i++) {
				int nx = tomato.x + dx[i];
				int ny = tomato.y + dy[i];
				if(nx>=0 && ny>=0 && nx<map.length && ny<map[0].length
						&& map[nx][ny]==0) {
					q.offer(new Node(nx,ny,tomato.depth+1));
					map[nx][ny] = 1;
					result = Math.max(result, tomato.depth+1);
				}
			}
		}
		
	}
}