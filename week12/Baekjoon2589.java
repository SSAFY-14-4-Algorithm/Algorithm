import java.io.*;
import java.util.*;

public class Baekjoon2589 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int r,c;
	static int max = 0;
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		r = Integer.parseInt(s[0]);
		c = Integer.parseInt(s[1]);
		map = new char[r][c];
		for(int i=0;i<r;i++) {
			map[i] = br.readLine().toCharArray();
		}
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(map[i][j]=='L') {
					visited=new boolean[r][c];
					max = Math.max(max, bfs(i,j,0));
				}
			}
		}
		System.out.println(max);
	}
	public static int bfs(int x,int y,int dist) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		int count = 0;
		q.offer(new int[] {x,y,dist});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			count=Math.max(count, tmp[2]);
			for(int i=0;i<4;i++) {
				int nx = tmp[0]+dx[i];
				int ny = tmp[1]+dy[i];
				if(!isRange(nx,ny)) continue;
				if(!visited[nx][ny] && map[nx][ny]=='L') {
					q.offer(new int[] {nx,ny,tmp[2]+1});
					visited[nx][ny] = true;
				}
			}
		}
		return count;
	}
	public static boolean isRange(int x, int y) { 
		return x>=0 && y>=0 && x<r && y<c;
	}
}
