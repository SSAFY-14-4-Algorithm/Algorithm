package AlgorithmStudy.week8;

import java.io.*;
import java.util.*;

public class Baekjoon1956 {
	
	static final int INF = 1_000_000_000;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int[][] city;
	static int V, E, sum;

	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		city = new int[V+1][V+1];
		for(int i=0;i<=V;i++) {
			for(int j=0;j<=V;j++) {
				if(i==j) continue;
				
				city[i][j] = INF;
			}
		}
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			city[u][v] = Math.min(city[u][v], cost);
		}
		
		floyd();
		
		sum = (sum==INF)? -1:sum;
		
		System.out.println(sum);
	}
	
	private static void floyd() {
		
		for(int k=1;k<=V;k++) {
			for(int i=1;i<=V;i++) {
				for(int j=1;j<=V;j++) {
					city[i][j] = Math.min(city[i][j], city[i][k]+city[k][j]);
				}
			}
		}
		
		sum = INF;
		for(int i=1;i<=V;i++) {
			for(int j=1;j<=V;j++) {
				if(i==j) continue;
			
				if(city[i][j] != INF && city[j][i] !=INF) {
					sum = Math.min(sum, city[i][j] + city[j][i]);
				}
				
			}
		}
		
	}

}
