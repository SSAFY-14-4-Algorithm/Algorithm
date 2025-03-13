import java.io.*;
import java.util.*;
/*
 * 메모리: 17388KB
 * 시간: 184ms
 */
public class Baekjoon17070{
	static int N;
	static int[][] map;
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		dfs(0,1,0); //방향 가로:0, 세로:1, 대각선:2
		System.out.println(cnt);
		
	}
	
	static void dfs(int x, int y, int dir) {
		if(x==N-1 && y==N-1) {
			cnt++;
			return;
		}
		
		if(dir==0 ||dir==2) { //가로로 이
			if(y+1<N && map[x][y+1]==0) {
				dfs(x,y+1,0);
			}
		}
		
		if(dir==1 || dir ==2) { //세로로 이
			if(x+1<N && map[x+1][y]==0) {
				dfs(x+1,y,1);
			}
		}
		
		if(x+1 <N && y+1<N && map[x+1][y]==0 && map[x][y+1]==0 && map[x+1][y+1]==0) { //대각선으로 이
			dfs(x+1, y+1,2);
		}
	}
}
