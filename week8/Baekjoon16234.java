package AlgorithmStudy.week8;

import java.io.*;
import java.util.*;

/*
 * 메모리 : 296,124 kb
 * 실행 시간 : 584 ms
 */

public class Baekjoon16234 {

	static final int[] dx= {0,1,0,-1};
	static final int[] dy= {1,0,-1,0};
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static List<Node> nodeList;
	static int[][] country;
	static boolean[][] visited;
	static int N, L, R, result;
	
	private static class Node{
		int x;
		int y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		country = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				country[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		result = 0;
		while(true) {
			boolean isMove = false;
			visited = new boolean[N][N];
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(visited[i][j]) continue;
				
					bfs(i, j);
					
					if(nodeList.size() > 1) isMove = true;
				}
			}
			
			if(!isMove) break;
			result++;
		}
		
		System.out.println(result);

	}
	private static void bfs(int i, int j) {
		
		Queue<Node> q = new ArrayDeque<Node>();
		nodeList = new ArrayList<Node>();
		
		q.offer(new Node(i, j));
		visited[i][j] = true;
		nodeList.add(new Node(i, j));
		
		int sum = 0;
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			int x = node.x;
			int y = node.y;
			
			sum += country[x][y];
			
			for(int k=0;k<4;k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				
				if(!inRange(nx, ny) || visited[nx][ny]) continue;
				
				int diff = Math.abs(country[x][y] - country[nx][ny]);
				if(L > diff || diff > R) continue;
				
				q.offer(new Node(nx, ny));
				visited[nx][ny] = true;
				nodeList.add(new Node(nx, ny));
			}
			
		}
		
		changeCountry(sum, nodeList.size());
		
	}
	private static void changeCountry(int sum, int cnt) {
		int result = sum / cnt;
		
		for(Node node : nodeList) {
			int x = node.x;
			int y = node.y;
			
			country[x][y] = result;
		}
		
	}
	private static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

}
