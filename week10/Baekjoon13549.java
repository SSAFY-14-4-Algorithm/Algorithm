/*
 * BOJ 13549번 : 숨바꼭질3
 * 메모리 : 24,900kb
 * 시간 : 124ms
 * 
 * 최소 시간 기록 배열 수정 -> 시간초과 해결
 */

import java.io.*;
import java.util.*;

public class Baekjoon13549 {
	static int N, K;
	static int time = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		bfs();

		sb.append(time);
		System.out.println(sb);
		br.close();
	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		int[] visited = new int[100001];
		Arrays.fill(visited, Integer.MAX_VALUE);
		
		q.offer(new int[] { N, 0 }); // 현재 위치, 시간
		visited[N] = 0; // 최소 시간 기록
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int currPosition = curr[0];
			int currTime = curr[1];
			
			// 동생이 있는 K 위치에 도착했을 때
			if(currPosition == K) {
				time = Math.min(time, currTime);
				continue;
			}
			
			// 1. 걷는 경우 : 1초 후 X-1, X+1 위치로 이동
			// 2. 순간이동을 하는 경우 : 0초 후 2*X 위치로 이동
			int[] nextArr = new int[] { currPosition-1, currPosition+1, currPosition*2 };
			for(int i = 0; i < 3; i++) {
				int next = nextArr[i];
				if(next < 0 || next > 100000)
					continue;
				
				if(i < 2) {
					if(visited[next] > currTime+1) {
						visited[next] = currTime+1;
						q.offer(new int[] { next, currTime+1 });
					}
				} else {
					if(visited[next] > currTime) {
						visited[next] = currTime;
						q.offer(new int[] { next, currTime });
					}
				}
			}			
		}
	}
}