import java.util.*;
import java.io.*;

class Baekjoon2606BFS
{	
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
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
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visited[1] = true;
		int answer = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next : graph.get(cur)) {
				if(!visited[next]) {
					answer += 1;
					visited[next] = true;
					q.add(next);
				}
			}
		}
		System.out.println(answer);
	}
}