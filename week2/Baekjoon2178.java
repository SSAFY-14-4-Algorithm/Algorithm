import java.io.*;
import java.util.*;

public class Baekjoon2178 {
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
	public static boolean[][] visited;
	public static int[] dx = {1,0,-1,0};
	public static int[] dy = {0,1,0,-1};
	public static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		String[] s = br.readLine().split(" ");
		int n=Integer.parseInt(s[0]), m=Integer.parseInt(s[1]);
		map = new int[n][m];
		visited = new boolean[n][m];
		for(int i=0;i<n;i++) {
			char[] c = br.readLine().toCharArray();
			for(int j=0;j<m;j++) {
				map[i][j]= c[j]-'0';
			}
		}
//		Dfs(0,0,0);
//		System.out.println(min + 1);
		Bfs(0,0);
	}
//	public static void Dfs(int r,int c, int count) {
//		if(r==map.length-1 && c==map[0].length-1) {
//			min = Math.min(min, count);
//			return;
//		}
//		
//		for(int i=0;i<4;i++) {
//			int nx = r+dx[i];
//			int ny = c+dy[i];
//			if(nx<0 || nx>=map.length || ny<0 || ny>=map[0].length) continue;
//			if(!visited[nx][ny] && map[nx][ny]==1) {
//				visited[nx][ny] = true;
//				Dfs(nx,ny, count + 1);
//				visited[nx][ny] = false;
//			}
//		}
	public static void Bfs(int r, int c) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(r,c,0));
		
		A:while(!q.isEmpty()) {
			Node temp = q.poll();
			visited[temp.x][temp.y] = true;
			if(temp.x==map.length-1 && temp.y==map[0].length-1) {
				System.out.println(temp.depth +1);
				break A;
			}
			
			for(int i=0;i<4;i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				if(nx<0 || ny<0 || nx>=map.length || ny>=map[0].length) continue;
				if(!visited[nx][ny] && map[nx][ny]==1) {
					q.offer(new Node(nx,ny,temp.depth+1));
					visited[nx][ny] = true;
				}
			}
		}
	}
//	}
	
}