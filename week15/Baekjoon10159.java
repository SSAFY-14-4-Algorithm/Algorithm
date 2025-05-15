import java.util.*;
import java.io.*;
/* 저울
 * 16588kb, 144ms
 */
public class Baekjoon10159 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][N+1];
		final int INF = (int)1e9;
		for(int i = 0; i <= N; i++) {
			Arrays.fill(arr[i], INF);
			arr[i][i] = 0;
		}
		StringTokenizer st;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1;
		}
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(arr[i][k] == INF || arr[k][j] == INF) continue;
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			int cnt = N;
			for(int j = 1; j <= N; j++) {
				if(arr[i][j] != INF || arr[j][i] != INF) {
					cnt--;
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.print(sb);
	}
}
