import java.io.*;
import java.util.*;
/*
 * 메모리 108576KB
 * 시간 604ms
 */
public class Baekjoon2206 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] map;
	static boolean[][][] visited;
	static int n;
	static int m;
	static int result = Integer.MAX_VALUE;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		map = new int[n][m];
		for(int i=0;i<n;i++) {
			char[] c = br.readLine().toCharArray();
			for(int j=0;j<m;j++) {
				map[i][j] = c[j]-'0';
			}
		}
		//[n][m][0] 은 벽 안부순 상태 [1]은 부순상태
		visited = new boolean[n][m][2];
		result = bfs(0,0);

		System.out.println(result);
	}
	
	public static boolean isRange(int x , int y) {
		if(x>=0 && y>=0 && x<n && y<m) return true;
		else return false;
	}
	
	public static int bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {x,y,0,1});
		visited[x][y][0] = true;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			//도착하면 return
			if(temp[0]==n-1 && temp[1]==m-1) {
				return temp[3];
			}
			//4방 탐색
			for(int i=0;i<4;i++) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				int broken = temp[2];
				int dist = temp[3];
				//범위 내이고 방문하지 않은 이동가능한 곳이면
				if(isRange(nx,ny) && !visited[nx][ny][broken] && map[nx][ny]==0) {
					q.offer(new int[] {nx,ny,broken,dist+1});
					visited[nx][ny][broken] = true;
				}
				//벽을 안부순 상태에서 벽을 만났을때 부수고 이동
				if(isRange(nx,ny) && broken==0 && !visited[nx][ny][1] && map[nx][ny]==1) {
					q.offer(new int[] {nx,ny,1,dist+1});
					visited[nx][ny][1] = true;
				}
			}
		}
		//도착 못하면 -1 출력
		return -1;
	}
}
