import java.io.*;
import java.util.*;


/**
 * 시간: 108ms
 * 메모리: 14,340KB
 */
public class Baekjoon_1516_게임개발 {
	// IO
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// variables
	static int N;
	static int[] costs;
	static List<Integer>[] adj;
	static boolean[] isVisited;
	static Deque<Integer> stk = new ArrayDeque<>();
	
	
	static void dfs(int u) {
		isVisited[u] = true;
		int delay = 0;
		for(int v : adj[u]) {
			if(isVisited[v]) continue;
			dfs(v);
		}
		for(int v : adj[u]) delay = Math.max(delay, costs[v]);
		
		stk.offerLast(u);
		costs[u] += delay;
	}
	
    public static void main(String[] args) throws IOException {
    	// input
    	N = readInt();
    	costs = new int[N + 1];
    	adj = new List[N + 1];
    	isVisited = new boolean[N + 1];
    	
    	for(int i = 1; i <= N; ++i) adj[i] = new ArrayList<>();
    	for(int u = 1; u <= N; ++u) {
    		costs[u] = readInt();
    		int v;
    		while((v = readInt()) > 0) adj[u].add(v);
    	}
    	// solution
    	for(int u = 1; u <= N; ++u) {
    		if(isVisited[u]) continue;
    		dfs(u);
    	}
    	// output
    	for(int i = 1; i <= N; ++i) {
    		sb.append(costs[i]).append('\n');
    	}
    	System.out.print(sb);
    }
    static int readInt() throws IOException {
    	int c, n = System.in.read() & 0x0F, s = 1;
    	if(n == ('-' & 0x0F)) {
    		n = 0;
    		s = -1;
    	}
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n*s;
    }
}