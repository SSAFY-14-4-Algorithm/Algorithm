/*
 * BOJ 1956번 : 운동
 * 메모리 : 58,252kb
 * 시간 : 612ms
 *
 * 1~V개의 마을
 * 도로를 따라 운동경로 찾기, 운동 후에는 다시 시작점으로 돌아와야 함
 * -> 사이클을 이루는 도로의 합이 최소가 되도록 길이 합 출력
 * -> 못 맞으면 -1 출력
 *
 * 모든 마을에서 모든 경로 확인 -> 플로이드 워셜
 */

import java.io.*;
import java.util.*;

public class Baekjoon1956 {
	static int V, E;
	static int[][] dis;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		dis = new int[V+1][V+1];
		for(int v = 1; v <= V; v++) {
			Arrays.fill(dis[v], (int)1e9);
		}

		for(int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			dis[a][b] = Math.min(dis[a][b], d);
		}

		for(int v = 1; v <= V; v++) {
			for(int a = 1; a <= V; a++) {
				for(int b = 1; b <= V; b++) {
					if(dis[a][b] > dis[a][v]+dis[v][b]) {
						dis[a][b] = dis[a][v]+dis[v][b];
					}
				}
			}
		}

		answer = Integer.MAX_VALUE;
		for(int a = 1; a <= V; a++) {
			for(int b = 1; b <= V; b++) {
				if(dis[a][b] != (int)1e9 && dis[b][a] != (int)1e9)
					answer = Math.min(answer, dis[a][b]+dis[b][a]);
			}
		}

		if(answer == Integer.MAX_VALUE) {
			System.out.print(-1);
			br.close();
			return;
		}
		sb.append(answer);
		System.out.print(sb);
		br.close();

	}
}