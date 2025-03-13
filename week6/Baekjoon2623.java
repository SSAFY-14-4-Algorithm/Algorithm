/*
 * BOJ 2623번 : 음악 프로그램
 * 메모리 : 12,272kb
 * 시간 : 80ms
 * 
 * 위상정렬, 줄 세우기 유사 문제
 * 진입차수 배열 만들어서, 0이 되면 큐에 삽입
 */

package algorithm;

import java.io.*;
import java.util.*;

public class Baekjoon2623 {
	static int N, M;
	static List<Integer>[] graph;
	static int[] indegree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new List[N+1];
		indegree = new int[N+1];
		for(int n = 1; n <= N; n++) {
			graph[n] = new ArrayList<Integer>();
		}
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int singerNum = Integer.parseInt(st.nextToken());
			int singerCurr = Integer.parseInt(st.nextToken());
			for(int s = 0; s < singerNum-1; s++) {
				int singerNext = Integer.parseInt(st.nextToken());
				graph[singerCurr].add(singerNext);
				indegree[singerNext]++;
				singerCurr = singerNext;
			}
		}
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		for(int n = 1; n <= N; n++) {
			if(indegree[n] == 0) {
				q.offer(n);
			}
		}
		
		int cnt = 0;
		while(!q.isEmpty()) {
			int curr = q.poll();
			sb.append(curr).append("\n");
			cnt++;
			
			for(int next : graph[curr]) {
				indegree[next]--;
				if(indegree[next] == 0) {
					q.offer(next);
				}
			}
		}
		
		if(cnt == N) {
			System.out.print(sb);
		} else {
			System.out.print(0);
		}
		
		br.close();
	}	
}
