import java.io.*;
import java.util.*;
/*
 * 메모리 12700KB
 * 시간 948ms
 */
public class Baekjoon1987 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int r;
	static int c;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[] visited = new boolean[122];
	static char[][] map;
	static int result;
	
	public static boolean isRange(int x, int y,int r,int c) {
		if(x>=0 && y>=0 && x<r && y<c) return true;
		else return false;
	}
	
	public static void dfs(int x,int y,int depth) {
		visited[map[x][y]] = true;
		
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			result = Math.max(result, depth);
			if(isRange(nx,ny,r,c) && !visited[map[nx][ny]]) {
				visited[map[nx][ny]] = true;
				dfs(nx,ny,depth+1);
				visited[map[nx][ny]] = false;
			}
		}
		
	}
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		r = Integer.parseInt(s[0]);
		c = Integer.parseInt(s[1]);
		map = new char[r][c];
		for(int i=0;i<r;i++) {
			map[i] = br.readLine().toCharArray();
		}
		dfs(0,0,1);
		System.out.println(result);
	}
}
