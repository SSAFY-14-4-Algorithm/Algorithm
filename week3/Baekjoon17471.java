import java.io.*;
import java.util.*;


public class Baekjoon17471{
	
	static int N;
	static int[] popInfo;
	static boolean[][] Matrix;
	static boolean[] isInA;
	static int minpop;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		popInfo = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1;i<=N;i++) {
			popInfo[i] = Integer.parseInt(st.nextToken());
		}
		
		Matrix = new boolean[N+1][N+1];
		for(int from =1;from<=N;from++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int i=0;i<n;i++) {
				int to = Integer.parseInt(st.nextToken());
				Matrix[from][to] = true;
				
			}
		}
		
		minpop = Integer.MAX_VALUE;
		isInA = new boolean[N+1];
		subset(1,0);
		
		System.out.println(minpop==Integer.MAX_VALUE?-1:minpop);
	}
	
	private static void subset(int idx,int aCnt) {
		if (idx==N+1) {
			if(aCnt ==0 || aCnt==N) return;
			
			if(!isConnected()) return;
			
			int curPopDiff = getPopDiff();
			minpop = Math.min(minpop, curPopDiff);
			
			return;
		}
		
		isInA[idx] = true;
		subset(idx+1,aCnt+1);
		
		isInA[idx] = false;
		subset(idx+1,aCnt);
		
	}
	
	private static int getPopDiff() {
		int SumA = 0;
		int SumB =0;
		for(int i=1;i<=N;i++) {
			if(isInA[i]) SumA+=popInfo[i];
			else SumB += popInfo[i];
			
		}
		return Math.abs(SumA-SumB);
	}
	
	private static boolean isConnected() {
		boolean[] visited = new boolean[N+1];
		
		bfs(1,isInA[1],visited);
		
		int second;
		for(second=2;second<=N;second++) {
			if(!visited[second]) break;
			
		}
		
		bfs(second,isInA[second],visited);
		
		for(int i=1;i<=N;i++) {
			if(!visited[i]) return false;
		}
		
		return true;
		
		
	}
	
	private static void bfs(int start,boolean isStartA, boolean[] visited) {
		Queue<Integer> q = new ArrayDeque<>();
		
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int from = q.poll();
			
			for(int to =1;to<=N;to++) {
				if(!Matrix[from][to]) continue;
				if(visited[to]) continue;
				if(isInA[to]!=isStartA) continue;
				
				q.offer(to);
				visited[to] = true;
			}
		}
	}
	
}
