<<<<<<< HEAD
/*
 * BOJ 1520번 : 내리막길
 * 메모리 : 39,540번
 * 시간 : 276ms
 *
 * dfs로 모든 경로 재탐색하려고 했지만,
 * 불필요한 백트래킹이 발생해서 StackOverflowError 발생
 *
 * dp 배열에 경로값 저장해서 메모이제이션
 */

import java.io.*;
import java.util.*;

public class Baekjoon1520 {
	static int M, N;
	static int[][] map;
	static int[][] dp;
	static int H;
	static int[] dx = new int[] { -1, 1, 0, 0 };
	static int[] dy = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		dp = new int[M][N];
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			for(int n = 0; n < N; n++) {
				map[m][n] = Integer.parseInt(st.nextToken());
				dp[m][n] = -1;
			}
		}

		H = dfs(0, 0); // x, y

		sb.append(H);
		System.out.print(sb);
		br.close();
	}

	private static int dfs(int x, int y) {
		if(x==M-1 && y==N-1) {
			return 1; // 도착지에 도착했으면 경로 1
		}

		if(dp[x][y] != -1) { // 0이 아니라면 이전의 경로값 사용
			return dp[x][y];
		}

		dp[x][y] = 0; // 초기화

		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
			if(map[x][y] > map[nx][ny]) {
				dp[x][y] += dfs(nx, ny);
			}
		}

		return dp[x][y];
	}
}
=======

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 각 지점을 중심으로 dfs 아래, 왼쪽, 오른쪽 방향으로만 dfs 탐색 depth:4로
 * 	- 탐색 시 최대값을 발견한다면 갱신
 * 탐색한 지점은 방문 처리
 * 
 * 
 * 
 * 가운데 모양은 따로 탐색 필요
 * 
 * 
 * @author SSAFY
 *
 */
public class Main_B_14500_테트로미노_연민호 {
	
	//하좌우상
	final static int[] dr = {1, 0, 0, -1};
	final static int[] dc = {0, -1, 1, 0};
	
	
	
	static int N;	//세로 크기
	static int M;	//가로 크기
	
	static int[][] map;	//숫자판 정보
	
	static boolean[][] visited;	//방문체크
	
	static int max;	//최댓값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//탐색의 편이를 위해 공백을 넣어놓음
		map = new int[N+2][M+2];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		visited = new boolean[N+2][M+2];
		
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				visited[i][j] = true;
				dfs(i,j, map[i][j], 1);
				visited[i][j] = false;
				
				//가운데 블록 기준 3개 블록이 붙어있는 테트로미노 탐색
				etc(i, j, map[i][j]);
			}
		}
		
		System.out.println(max);
	}

	//가운데 블록 기준 3개 블록이 붙어있는 테트로미노 탐색
	private static void etc(int r, int c, int sum) {
		
		//(r,c) 기준 4방의 값을 모두 더함
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			sum+= map[nr][nc];
		}
		
		//4방의 값을 하나씩 빼보면서 해당 값이 최댓값이라면 갱신
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			max = Math.max(max, sum-map[nr][nc]);
		}
	}

	/**
	 * 블록하나를 선택하고 다음 블록 선택은 재귀호출로 넘김
	 * @param r
	 * @param c
	 * @param sum	//선택한 블록의 숫자 합
	 * @param cnt	//선택한 블록의 개수
	 */
	private static void dfs(int r, int c, int sum, int cnt) {

		//step01. 4개의 블록 선택 완료
		if(cnt==4) {
			//step 02. 선택한 블록 숫자의 합이 최댓값이라면 갱신
			max = Math.max(sum, max);
			return;
		}
		
		//윗 방향을 제외한 3방향으로 이동하며 블록 선택
		for(int d=0; d<3; d++) {
			
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			//경계를 벗어나거나 이미 방문했다면 다음 방향
			if(nr>N || nc<1 || nc>M || visited[nr][nc]) continue;
			
			visited[nr][nc] = true;
			dfs(nr, nc, sum+map[nr][nc], cnt+1);
			visited[nr][nc] = false;
		}
		
	}
	
	

}
>>>>>>> aaaa1fa11e6dbe8a113a7bba199a43dae92fad38
