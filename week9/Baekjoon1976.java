/*
 * BOJ 1976번 : 여행가자
 * 메모리 : 15,848kb
 * 시간 : 120ms
 *
 * 여행 일정이 주어졌을 때, 이 여행 경로가 가능한 것인지?
 * 도시들이 연결되어 있는지? 판단하기
 */

import java.io.*;
import java.util.*;

public class BOJ1976_여행가자 {
	static int N, M;
	static int[][] graph;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new int[N+1][N+1];
		parent = new int[N+1];
		for(int n = 1; n <= N; n++) {
			parent[n] = n;
		}

		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(graph[i][j] == 1) { // 연결
					union(i, j);
				}
			}
		}

		int[] arr = new int[M];
		boolean answer = true;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int m = 0; m < M; m++) {
			arr[m] = Integer.parseInt(st.nextToken());
		}
		// 부모가 같아야 함
		int temp = find(arr[0]);
		for(int i = 1; i < M; i++) {
			if(find(arr[i]) != temp) {
				answer = false;
				break;
			}
		}
		if(answer) {
			sb.append("YES");
		} else {
			sb.append("NO");
		}
		System.out.print(sb);
		br.close();
	}


	private static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if(x<=y) parent[y] = x;
		else parent[x] = y;
	}

	private static int find(int x) {
		if(parent[x]==x) return x;
		else return parent[x]=find(parent[x]);
	}
}
