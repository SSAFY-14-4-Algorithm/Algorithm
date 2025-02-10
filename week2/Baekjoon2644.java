import java.io.*;
import java.util.*;

public class Baekjoon2644 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int[][] map;
	public static boolean[] visited;
	public static List<Integer> result = new ArrayList<>();
	public static int count = 0;
	public static boolean flag = false;
	
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n];
		String[] s = br.readLine().split(" ");
		int p1 = Integer.parseInt(s[0]);
		int p2 = Integer.parseInt(s[1]);
		int m = Integer.parseInt(br.readLine());
		for(int i=0;i<m;i++) {
			String[] related = br.readLine().split(" ");
			map[Integer.parseInt(related[0])-1][Integer.parseInt(related[1])-1] = 
					map[Integer.parseInt(related[1])-1][Integer.parseInt(related[0])-1] = 1;
		}
		Dfs(p1,p2);
		if(!flag) System.out.println(-1);
	}
	public static void Dfs(int p1,int p2) {
		visited[p1-1] = true;
		if(p1==p2) {
			System.out.println(count);
			flag = true;
			return;
		}
		for(int i=1;i<map.length+1;i++) {
			if(map[p1-1][i-1]==1 && !visited[i-1]) {
				result.add(i);
				count++;
				Dfs(i,p2);
				count--;
			}
		}
	}
	public static void Bfs(int p1,int p2) {
		Queue<Integer> q  = new LinkedList<>();
		q.offer(p1);
		visited[p1-1]=true;
		while(!q.isEmpty()) {
			int temp = q.poll();			
			for(int i=1;i<map.length+1;i++) {
				if(map[temp-1][i-1]==1 && !visited[i-1]) {
					q.offer(i);
					result.add(i);				
					visited[i-1]=true;
				}	
			}
			count++;
			if(q.contains(p2)) break;
		}
	}
}
