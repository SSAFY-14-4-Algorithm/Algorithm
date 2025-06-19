import java.io.*;
import java.util.*;

public class Baekjoon17182 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final int MAX = 10000000;
	private static int N,K;
	private static int [][] graph;
	private static int minCost = MAX;
	private static boolean [] visited;
	public static void main(String [] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		K = Integer.parseInt(tokens[1]);
		visited = new boolean[N];
		
		createGraph();
		fluid();
		backtracking(K,0,0);
		
		bw.write(minCost+"\n");
		bw.flush();
		bw.close();
	}
	
	private static void backtracking(int currentNode, int cost, int count) {
		if(count == N) {
			minCost = Math.min(minCost, cost);
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(visited[i]) continue;
			if(graph[currentNode][i] == MAX) continue;
			
			visited[i] = true;
			backtracking(i,cost+graph[currentNode][i],count+1);
			visited[i] = false;
		}
	}
	
	private static void fluid() {
		for(int k=0;k<N;k++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(i == j) continue;
					
					if(graph[i][k]+graph[k][j] < graph[i][j]) {
						graph[i][j] = graph[i][k]+graph[k][j]; 
					}
				}
			}
		}
	}
	private static void createGraph() throws IOException{
		graph = new int[N][N];
		for(int [] a : graph) {
			Arrays.fill(a, MAX);
		}
		
		for(int i=0;i<N;i++) {
			String [] tokens = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				graph[i][j] = Integer.parseInt(tokens[j]);
			}
		}
	}
}
