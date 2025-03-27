/*
 * BOJ 16234번 : 인구 이동
 * 메모리 : 294,252kb
 * 시간 : 624ms
 *
 * 1. 국경선 열 수 있는지 판단
 * 2. 열린 나라들끼리 인구 이동 -> 평균값으로 인구 재분배
 * 3. 더 이상 이동할 수 없을 때까지 반복 -> 반복횟수 출력
 */

import java.io.*;
import java.util.*;

public class Baekjoon16234 {
	static int N, L, R;
	static int[][] person;
	static boolean[][] visited;
	static int answer;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int sum, cnt; // 연합 나라 계산

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		person = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				person[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;
		while(true){
			visited = new boolean[N][N];
			boolean move = false;

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						sum = 0;
						cnt = 0;
						List<int[]> list = new ArrayList<>(); // 이번 연합에서 방문한 나라 체크
						bfs(i, j, list);
						if(cnt > 1) { // 연합 있었으면
							move = true;
							for(int[] arr : list) {
								person[arr[0]][arr[1]] = sum/cnt;
							}
						}
					}
				}
			}

			if(!move) break;
			answer++;
		}

		sb.append(answer);
		System.out.println(sb);
		br.close();
	}

	public static void bfs(int x, int y, List<int[]> list) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{ x, y });
		visited[x][y] = true;
		list.add(new int[]{ x, y });
		sum += person[x][y];
		cnt++;

		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];

			for (int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];

				if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;

				int diff = Math.abs(person[nx][ny] - person[cx][cy]);
				if (diff >= L && diff <= R) {
					visited[nx][ny] = true;
					list.add(new int[]{ nx, ny });
					sum += person[nx][ny];
					cnt++;
					q.add(new int[]{ nx, ny });
				}
			}
		}
	}
}