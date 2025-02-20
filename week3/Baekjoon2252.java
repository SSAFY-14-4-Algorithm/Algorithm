
import java.util.*;
import java.io.*;

public class Baekjoon2252{
	
	static List<Integer> [] Graph;
	static int[] degrees;
	static int N,M;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Graph = new List[N+1];
		for(int i=1;i<=N;i++)	Graph[i] = new ArrayList<>();
		
		degrees = new int[N+1];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			Graph[from].add(to);
			degrees[to]++;
		}
		
		BFS();
		
		System.out.println(sb);
		
		
	}
	private static void BFS() {
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int i=1;i<=N;i++)
			if(degrees[i]==0) q.offer(i);
		
		while(!q.isEmpty()) {
			int from = q.poll();
			
			sb.append(from).append(' ');
			
			for(int to: Graph[from]) {
				if(--degrees[to]==0) q.offer(to);
			}
		}
	}
}
