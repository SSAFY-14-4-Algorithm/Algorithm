import java.io.*;
import java.util.*;

/*
 * 시간: 776ms
 * 메모리: 51100KB
 */
public class Baekjoon_9466_텀프로젝트 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int N;
	static int[] S;
	static int[] isVisited; // unvisited: 0, visited-in: 1, visited-out: 2
	static int ans;
	
	static int dfs(int u) {
		if(isVisited[u] == 1) { // 순환 발견
			return u;
		}
		
		if(isVisited[S[u]] == 2) {
			isVisited[u] = 2;
			return -1;
		}
		
		isVisited[u] = 1;
		int ret = dfs(S[u]);
		if(ret >= 0) --ans;
		isVisited[u] = 2;
		return u != ret ? ret : -1;
	}
	
	static int solution() {
		ans = N;
		for(int u = 0; u < N; ++u) {
			if(isVisited[u] == 0) dfs(u);
		} 
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		int T = readInt();
		while(T-- > 0) {
			N = readInt();
			S = new int[N];
			isVisited = new int[N];
			
			for(int i = 0; i < N; ++i) S[i] = readInt() - 1;
			sb.append(solution()).append('\n');
		}
		System.out.print(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}