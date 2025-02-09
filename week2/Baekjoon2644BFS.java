import java.util.*;
import java.math.*;
import java.io.*;

class Baekjoon2644BFS
{	
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
		for(int i = 0; i <= n; i++) {
			arr.add(new ArrayList<>());
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr.get(x).add(y);
			arr.get(y).add(x);
		}
		boolean[] visited = new boolean[n+1];
		int[] cnt = new int[n+1];
		Queue<Integer> q= new ArrayDeque<>();
		q.add(s);
		visited[s] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int nxt : arr.get(cur)) {
				if(!visited[nxt]){
					q.add(nxt);
					visited[nxt] = true;
					cnt[nxt] = cnt[cur]+1;
					if(nxt == e) {
						break;
					}
				}
			}
		}
		if(cnt[e] == 0) {
			System.out.println(-1);
		} else {
			System.out.println(cnt[e]);
		}
	}
}