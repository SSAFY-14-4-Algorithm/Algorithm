import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon2667 {
	static final int[] dx = {0,1,0,-1};
	static final int[] dy = {1,0,-1,0};
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Deque<Node> deque = new ArrayDeque<Node>();
	static List<Integer> counts = new ArrayList<>();
	static StringTokenizer st;
	
	static boolean[][] visited;
	static int[][] arr;
	static int n = 0;
	
	private static class Node {
		int x;
		int y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static int DFS(int x, int y, int count) {
		
		visited[x][y] = true;
		count++;
		
		for(int k=0;k<4;k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			
			if(nx < 0 | arr.length <= nx | ny < 0 | arr.length <=ny ) continue;
			if(visited[nx][ny] | arr[nx][ny] == 0) continue;
			
			count = DFS(nx, ny, count);
		}
		
		return count;
		
	}
	
	public static int BFS(int x, int y, int count) {
		
		count = insertQueue(x, y, count);
		
		while(!deque.isEmpty()) {
			Node currentNode = deque.pollFirst();
			
			for(int k=0;k<4;k++) {
				int nx = currentNode.x + dx[k];
				int ny = currentNode.y + dy[k];
				
				if(nx < 0 | arr.length <= nx | ny < 0 | arr.length <=ny ) continue;
				if(visited[nx][ny] | arr[nx][ny] == 0) continue;
				
				count = insertQueue(nx, ny, count);
			}
		}
		
		return count;
	}
	
	public static int insertQueue(int x, int y, int count) {
		Node node = new Node(x, y);
		deque.offerLast(node);
		
		visited[x][y] = true;
		count++;
		
		return count;
	}

	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		visited = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
	        st = new StringTokenizer(br.readLine());
	        String s = st.nextToken();
	        for (int j = 0; j < n; j++) {
	            arr[i][j] = s.charAt(j) - '0';
	        }
	     }
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				int count = 0;
				if(!visited[i][j] & arr[i][j] == 1) {
//					count = DFS(i, j, count);
					count = BFS(i, j, count);
					counts.add(count);
				}
			}
		}
		
		
		bw.write(counts.size()+"\n");
		
		Collections.sort(counts);
		for(int count : counts) {
			bw.write(count+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
		
		
	}

}
