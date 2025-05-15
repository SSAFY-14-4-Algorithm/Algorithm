/*
 * BOJ 10159번 : 저울
 * 메모리 : 13,524kb
 * 시간 : 96ms
 * 
 * 1~N개의 물건, i번째 줄에는 물건 i와 비교 결과를 알 수 없는 물건의 개수 출력
 */

package algorithm;

import java.io.*;
import java.util.*;

public class BOJ10159_저울 {
	static int N, M;
	static List<List<Integer>> graph;
	static List<List<Integer>> reverseGraph;
	static boolean[] visited;
	static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new ArrayList<List<Integer>>();
		reverseGraph = new ArrayList<List<Integer>>();
		for(int n = 0; n < N+1; n++) {
			graph.add(new ArrayList<Integer>());
			reverseGraph.add(new ArrayList<Integer>());
		}
		result = new int[N+1];
		
		for(int m = 0; m < M; m++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			reverseGraph.get(b).add(a);
		}
		
        for (int n = 1; n < N+1; n++) {
            visited = new boolean[N+1];
            int cnt1 = dfs(n, graph); 
            int cnt2 = dfs(n, reverseGraph); 
            result[n] = cnt1+cnt2-1; // 자기자신 제외
        }
		
		for(int n = 1; n < N+1; n++) {
			sb.append(N-result[n]).append("\n");
		}
		System.out.print(sb);
		br.close();
	}
	
	private static int dfs(int idx, List<List<Integer>> graph) {
        visited[idx] = true;
        
        int cnt = 1;
        for (int next : graph.get(idx)) {
            if (!visited[next]) {
                cnt += dfs(next, graph);
            }
        }
        return cnt;
    }
}
