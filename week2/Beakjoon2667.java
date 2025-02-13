import java.io.*;
import java.util.*;

public class Beakjoon2667 {
	public static class Node{
		int x;
		int y;
		
		public Node(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int[][] map;
	public static boolean[][] visited;
	public static List<Integer> estates;
	public static int[] dx = {0,0,-1,1};
	public static int[] dy = {1,-1,0,0};	
	public static int count = 0;
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n];
		estates = new ArrayList<>();
		for(int i=0;i<n;i++) {
			char[] c = br.readLine().toCharArray();
			for(int j=0;j<n;j++) {
				map[i][j] = c[j]-'0';
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]==1 && !visited[i][j]) {
//					Dfs(i,j);
					Bfs(i,j);
					estates.add(count);
					count=0;
				}
			}
		}
		Collections.sort(estates);
		System.out.println(estates.size());
		for(int estate : estates) {
			System.out.println(estate);
		}
	}
	
	public static void Dfs(int r,int c) {
		visited[r][c]=true;
		count++;
		for(int i=0;i<4;i++) {
			int nx = r + dx[i];
			int ny = c + dy[i];
			if(nx<0 || nx>=map.length || ny<0 || ny>=map.length) continue;
			if(map[nx][ny]==1 && !visited[nx][ny]) {
				Dfs(nx,ny);
			}
		}
	}
	public static void Bfs(int r,int c) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(r, c));
		visited[r][c]=true;
		count++;
		while(!q.isEmpty()) {
			Node temp = q.poll();
			for(int i=0;i<4;i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				if(nx<0 || nx>=map.length || ny<0 || ny>=map.length) continue;
				if(map[nx][ny]==1 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.offer(new Node(nx,ny));
					count++;
				}
			}
		}
	}
}