package study;



import java.io.*;
import java.util.*;

/**
 * 
 * 
 * 
 * 메모리: 	58792 kb
 * 시간 : 	620 ms
 *
 */


public class Baekjoon1956 {
	
	public static void main(String[] args) throws IOException {
		int INF = 100000000;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		int[][] dist = new int[v + 1][v + 1];
		
		for(int i = 1 ; i <= v; i++) {
			Arrays.fill(dist[i], INF);
		}
		
		for(int i = 0; i < e ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			dist[a][b] = Math.min(dist[a][b], w);
		}
		
		for(int a = 1 ; a <= v ; a++) {
			for(int i = 1; i <= v; i++) for(int j = 1; j <= v ; j++) {
				if(dist[a][j] != INF && dist[i][a] != INF) {
					dist[i][j] = Math.min(dist[i][j], dist[a][j] + dist[i][a]);
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		for(int i = 1 ; i <= v ; i++) {
			
			if(dist[i][i] != INF) {
				result = Math.min(result, dist[i][i]);
			}
		}
		
		if(result == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
	}
}
