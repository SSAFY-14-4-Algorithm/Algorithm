import java.util.*;
import java.io.*;
/* 텀프로젝트
 * 307600kb, 1064ms
 */
public class Baekjoon9466 {
	static int[] students;
	static int cnt;
	static boolean[] visited;
	static boolean[] checked;
	static void dfs(int x) {
		visited[x] = true;
		int next = students[x];
		if(!visited[next]) {
			dfs(next);
		} else {
			if(!checked[next]) {
				cnt--;
				while(next != x) {
					next = students[next];
					cnt--;
				}
			}
		}
		checked[x] = true;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			students = new int[N+1];
			for(int i = 1; i <= N; i++) {
				students[i] = Integer.parseInt(st.nextToken());
			}
			visited = new boolean[N+1];
			checked = new boolean[N+1];
			cnt = N;
			for(int i = 1; i <= N; i++) {
				if(!visited[i]) {
					dfs(i);
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
