import java.io.*;
import java.util.*;
/*
 * 메모리: 78048KB	
 * 시간: 684ms
 */
public class Baekjoon1956 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] dist;
	static final int INF = 10000001;
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		int v = Integer.parseInt(s[0]);
		int e = Integer.parseInt(s[1]);
		dist = new int[v+1][v+1];
		for(int i=1;i<=v;i++) {
			Arrays.fill(dist[i],INF);
		}
		for(int i=0;i<e;i++) {
			String[] s2 = br.readLine().split(" ");
			int start = Integer.parseInt(s2[0]);
			int end = Integer.parseInt(s2[1]);
			int cost = Integer.parseInt(s2[2]);
			dist[start][end] = cost;
		}
		//플루이드-워셜 알고리즘
		for(int k=1;k<=v;k++) {
			for(int i=1;i<=v;i++) {
				for(int j=1;j<=v;j++) {
					if(dist[i][k] < INF && dist[k][j] < INF)
						dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
				}
			}
		}
		int min = INF;
		//i -> j -> i 로 돌아오는 것중에 최소값
		for(int i=1;i<=v;i++) {
			for(int j=1;j<=v;j++) {
				if (i!=j && dist[i][j]<INF && dist[j][i]<INF) {
					min = Math.min(min, dist[i][j]+dist[j][i]);
				}
			}
		}
		System.out.println(min==INF?-1:min);
	}
}
