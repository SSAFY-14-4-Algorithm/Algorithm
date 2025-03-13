package test;

import java.io.*;
import java.util.*;

/**
 * 
 * 메모리: 12288
 * 시간: 88	
 *
 */

public class Baekjoon2623 {

	static int N, M;
	static List<Integer>[] graph;
	static int[] indegree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
		
		indegree = new int[N + 1];
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int prev = Integer.parseInt(st.nextToken());
			
			for(int j = 1; j < cnt; j++) {
				
				int curr = Integer.parseInt(st.nextToken());
				graph[prev].add(curr);
				indegree[curr]++;
				prev = curr;
			}
		}
		
		 List<Integer> result = topologicalSort(N, graph, indegree);
		 
		 if (result.isEmpty()) {
			 sb.append(0);  // 순서를 정하는 것이 불가능한 경우
	        } 
		 else {
	            for (int singer : result) {
	            	sb.append(singer).append("\n");
	            }
		 }
		 
		 System.out.println(sb);
	}
	
	public static List<Integer> topologicalSort(int N, List<Integer>[] graph, int[] indegree) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> order = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int singer = queue.poll();
            order.add(singer);

            for (int next : graph[singer]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        if (order.size() < N) {
            return new ArrayList<>();
        }

        return order;
    }
}
