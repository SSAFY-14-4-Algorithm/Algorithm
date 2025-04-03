import java.util.*;
import java.io.*;
/*
 * 30384kb, 300ms
 */
public class Baekjoon14500 {
	static int[][] arr;
	static int max, result;
	static int N, M;
	static boolean[][] visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	//4개의 블록으로 만들 수 있는 모든 경우의 수
	public static void dfs(int x, int y, int cnt, int sum) {
		if(sum + max*(4-cnt) <= result) return;
		//각 칸의 최댓값 * 골라야하는 칸수 + 지금까지의 합이 현 최댓값보다 같거나 작으면 돌아가기(가지치기)
		if(cnt == 4) {
			result = Math.max(result, sum);
			return;
		}
		for(int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			if(0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
				if(cnt == 2) { 
					//ㅗ 모양을 위해 2개를 골랐을 때는 다음 블록을 움지이지 않고 카운트와 합만 늘린다.
					visited[nx][ny] = true;
					dfs(x, y, cnt+1, sum+arr[nx][ny]);
					visited[nx][ny] = false;
				}
				
				visited[nx][ny] = true;
				dfs(nx, ny, cnt+1, sum+arr[nx][ny]);
				visited[nx][ny] = false;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(arr[i][j], max);
			}
		}
		result = max;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, arr[i][j]);
				visited[i][j] = false;
			}
		}
		System.out.println(result);
	}

}
