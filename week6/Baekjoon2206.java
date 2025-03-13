import java.io.*;
import java.util.*;

/*
 * 메모리 107072KB
 * 시간 624ms
 * 
 */

public class Baekjoon2206{
	static class Node{
		int x;
		int y;
		int dist;
		int broken;
		public Node(int x,int y, int dist, int broken) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.broken = broken;
		}
		
	}
	
	static int N,M;
	static int[][]map;
	static boolean[][][] visited; //벽을 부순 경우를 구분하기 위해서 삼중배열 사
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	
	N = Integer.parseInt(st.nextToken());
	M = Integer.parseInt(st.nextToken());
	map = new int[N][M];
	visited = new boolean[N][M][2];
	
	for(int i=0;i<N;i++) {
		String line = br.readLine();
		for(int j=0;j<M;j++) {
			map[i][j] = line.charAt(j)-'0';
		}
	}
	System.out.println(bfs());
	
	
	}	

	static int bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(0,0,1,0)); // 0,0에서 시작, 거리는 1, 벽 부수지 않음(0)
		visited[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if(cur.x == N-1 && cur.y == M-1) {
				return cur.dist;
			}
			
			for(int i=0;i<4;i++) { 
				int nx = cur.x+dx[i];
				int ny = cur.y +dy[i];
				
				if(nx<0 || ny <0 || nx>=N || ny>=M) continue;
				
				
				if(map[nx][ny]==0) {
					if(!visited[nx][ny][cur.broken]) {
						visited[nx][ny][cur.broken] = true;
						queue.add(new Node(nx,ny,cur.dist+1,cur.broken));
						
					}
				}
				
				else if(map[nx][ny] ==1 && cur.broken ==0) { //벽을 부수고 이동하는 경우(1회까지는 허용) 
					if(!visited[nx][ny][1]) {
						visited[nx][ny][1]=true;
						queue.add(new Node(nx,ny,cur.dist+1,1));
					}
				}
			}
		}
		return -1;
		
	}
}
	
