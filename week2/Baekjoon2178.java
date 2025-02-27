package week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon2178 {

	static final int[] dx = {0,1,0,-1};
	static final int[] dy = {1,0,-1,0};
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Deque<Node> deque = new ArrayDeque<Node>();
	static List<Integer> counts = new ArrayList<>();
	static StringTokenizer st;
	
	static boolean[][] visited;
	static int[][] arr;
	static int n, m= -1;
	static int min = Integer.MAX_VALUE;
	
	private static class Node {
		int x;
		int y;
		int depth;
		
		Node(int x, int y, int depth){
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}
	
	public static int DFS(int x, int y) {
		
		if(x == n && y == m)  return 1;
		
		visited[x][y] = true;
		
		int minDepth = Integer.MAX_VALUE;
		
		for(int k=0;k<4;k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			
			if(!inRange(nx, ny)) continue;
			if(visited[nx][ny] || arr[nx][ny] == 0) continue;
			
			int result = DFS(nx, ny);
		
			if(result != Integer.MAX_VALUE) {
				minDepth = Math.min(minDepth, result + 1);
			}
		}
		
		visited[x][y] = false;
	
		return minDepth;
	}
	
	public static int BFS(int x, int y, int depth) {
		
		insertQueue(x,y, depth);
		
		while(!deque.isEmpty()) {
			Node currentNode = deque.pollFirst();
			
			if(currentNode.x == n & currentNode.y == m) return currentNode.depth;
			
			int nDepth = currentNode.depth;
			for(int k=0;k<4;k++) {
				int nx = currentNode.x + dx[k];
				int ny = currentNode.y + dy[k];
				
				if(!inRange(nx, ny)) continue;
				if(visited[nx][ny] || arr[nx][ny] == 0) continue;
				
				insertQueue(nx, ny, nDepth+1);
			}
			
		}
		
		return -1;
	}
	
	public static void insertQueue(int x, int y, int depth) {
		Node node = new Node(x, y, depth);
		deque.offerLast(node);
		
		visited[x][y] = true;
	}
	
	private static boolean inRange(int x,int y) {
		return x >= 0 && x <= n && y>=0 && y <= m;
	}
	
	public static void main(String[] args) throws IOException {
		int count = 1;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken())-1;
		m = Integer.parseInt(st.nextToken())-1;
		arr = new int[n+1][m+1];
		visited = new boolean[n+1][m+1];
		
		for (int i = 0; i <= n; i++) {
	        st = new StringTokenizer(br.readLine());
	        String s = st.nextToken();
	        for (int j = 0; j <= m; j++) {
	            arr[i][j] = s.charAt(j) - '0';
	        }
	    }
		
//		bw.write(DFS(0,0)+"");
		bw.write(BFS(0,0,count)+"");
		
		bw.flush();
		bw.close();
		br.close();
	}
}