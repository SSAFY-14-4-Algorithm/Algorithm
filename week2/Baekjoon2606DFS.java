import java.util.*;
import java.io.*;

class Baekjoon2606DFS
{	
	static ArrayList<ArrayList<Integer>> graph;
	static int answer;
	static boolean[] visited;
	public static void dfs(int cur) {
		visited[cur] = true;
		answer += 1;
		for(int nxt : graph.get(cur)) {
			if(!visited[nxt]) {
				dfs(nxt);
			}
		}
	}
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		for(int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<>());
		}
		StringTokenizer st;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph.get(start).add(end);
			graph.get(end).add(start);
		}
		visited = new boolean[N+1];
		answer = -1;
		dfs(1);
		System.out.println(answer);
	}
}