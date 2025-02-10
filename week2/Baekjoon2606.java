import java.io.*;
import java.util.*;

public class Baekjoon2606 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int[][] map;
	public static boolean[] visited;
	public static int count = 0;
	public static void main(String[] args) throws IOException {
		int coms = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		map = new int[coms][coms];
		visited = new boolean[coms];
		for(int i=0;i<n;i++) {
			String[] s = br.readLine().split(" ");
			map[Integer.parseInt(s[0])-1][Integer.parseInt(s[1])-1] = 1;
			map[Integer.parseInt(s[1])-1][Integer.parseInt(s[0])-1] = 1;
			
		}
		dfs(1);
//		bfs(1);
		System.out.println(count);
	}
	public static void dfs(int v) {
		visited[v-1] = true;
		for(int i=1;i<map.length+1;i++) {
			if(map[v-1][i-1]==1 && !visited[i-1]) {
				count++;
				visited[i-1]= true;
				dfs(i);
			}
		}
	}
//	public static void bfs(int v) {
//		Queue<Integer> q = new LinkedList<>();
//		q.offer(v);
//		visited[v-1] = true;
//		while(!q.isEmpty()) {
//			int temp = q.poll();
//			if (temp != 1) {
//				count++;
//			}
//			for(int i=1;i<map.length+1;i++) {
//				if(map[temp-1][i-1]==1 && !visited[i-1]) {
//					q.offer(i);
//					visited[i-1] = true;
//				}
//			}
//		}	
//	}
}
