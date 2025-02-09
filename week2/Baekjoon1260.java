import java.util.*;
import java.io.*;

class Baekjoon1260
{
	static ArrayList<ArrayList<Integer>> array;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		visited[start] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(" ");
			for(int nc : array.get(cur)) {
				if(!visited[nc]) {
					visited[nc] = true;
					q.add(nc);
				}
			}
		}
	}
	
	public static void dfs(int cur) {
		sb.append(cur).append(" ");
		visited[cur] = true;
		for(int nc: array.get(cur)) {
			if(!visited[nc]) {
				dfs(nc);
			}
		}
	}
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		array = new ArrayList<>();
		for(int i = 0; i < N+1; i++) {
			array.add(new ArrayList<>());
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			array.get(a).add(b);
			array.get(b).add(a);
		}
		for(int i = 1; i < N+1; i++) {
			Collections.sort(array.get(i));
		}
		visited = new boolean[N+1];
		dfs(start);
		sb.append("\n");
		visited = new boolean[N+1];
		bfs(start);
		System.out.println(sb);
	}
}