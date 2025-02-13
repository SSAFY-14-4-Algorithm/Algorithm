import java.util.*;
import java.io.*;

public class Baekjoon7576 {
	static final int[] dx = {0,1,0,-1};
	static final int[] dy = {1,0,-1,0};
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int[][] storage;
	static ArrayList<Node> arrListRipe;
	static Deque<Node> deque = new ArrayDeque<>();
	static boolean[][] visited;
	static int n, m, ripeTomato, unripeTomato = 0;
	
	private static class Node{
		int x;
		int y;
		int depth;
		
		Node(int x, int y, int depth){
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}
	
	private static int BFS() {
		
		for(int i=0;i<arrListRipe.size();i++) {
			deque.offerLast(arrListRipe.get(i));
		}
		
		while(!deque.isEmpty()) {
			Node currentNode = deque.pollFirst();
			
			if(unripeTomato == 0) {
				if(deque.isEmpty()) return currentNode.depth;
				
				Node finalNode = deque.pollLast();
				return finalNode.depth; 
			}
			
			int nDepth = currentNode.depth; 
			for(int k=0;k<4;k++) {
				int nx = currentNode.x + dx[k];
				int ny = currentNode.y + dy[k];
				
				if(!inRange(nx, ny)) continue;
				if(visited[nx][ny] || storage[nx][ny] == 1 || storage[nx][ny] == -1) continue;
				
				unripeTomato--;
				visited[nx][ny] = true;
				deque.offerLast(new Node(nx, ny, nDepth+1));
			}
		}
		
		return -1;
		
	}
	
	private static boolean inRange(int x,int y) {
		return x >= 0 && x < n && y>=0 && y < m;
	}
	
	private static void checkRipe(int i, int j, int num) {
		if (num == 0) unripeTomato++;
		else if(num == 1) {
			arrListRipe.add(new Node(i, j, 0));
			ripeTomato++;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		arrListRipe = new ArrayList<Node>();
		visited = new boolean[n][m];
		storage = new int[n][m];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				storage[i][j] = Integer.parseInt(st.nextToken());
				checkRipe(i, j, storage[i][j]);
			}
		}
		
		bw.write(BFS()+"");
		bw.flush();
		bw.close();
		br.close();
		
	}

}