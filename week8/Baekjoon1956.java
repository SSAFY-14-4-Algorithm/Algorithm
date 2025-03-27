package ct2;
import java.util.*;
import java.io.*;
/*
 * 메모리:57952kb, 시간:504ms
 */
public class Baekjoon1956 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int INF = (int)1e9;
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int[][] dist = new int[V+1][V+1];
		for(int i = 0; i <= V; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			dist[a][b] = w;
		}
		
		for(int k = 1; k <= V; k++) {
			for(int i = 1; i <= V; i++) {
				for(int j = 1; j <= V; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		int answer = INF;
		for(int i = 1; i <= V; i++) {
			for(int j = i+1; j <= V; j++) {
				if(dist[i][j] != INF)
				answer = Math.min(dist[i][j] + dist[j][i], answer);
			}
		}
		
		System.out.println(answer==INF ? -1 : answer);
	}
}