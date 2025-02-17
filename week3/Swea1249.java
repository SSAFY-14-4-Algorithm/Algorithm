package week3;

import java.util.*;
import java.io.*;

public class Swea1249{
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int [] dx = {0,0,-1,1};
	private static int [] dy = {-1,1,0,0};
	
	private static class Node implements Comparable<Node>{
		int x,y,cost;
		
		public Node(int x,int y,int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
		
	}
	private static int [][] map;
	private static int [][] dist;
	private static int N;
	public static void main(String [] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			int answer = solution();
			bw.write("#"+t+" "+answer+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int solution() throws IOException{
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dist = new int[N][N];
		
		for(int [] d : dist) {
			Arrays.fill(d, Integer.MAX_VALUE);
		}
		
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = input.charAt(j)-'0';
			}
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[0][0] = 0;
		pq.add(new Node(0,0,0));

		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			
			if(curNode.cost > dist[curNode.x][curNode.y]) continue;
			
			for(int d=0;d<4;d++) {
				int nx = curNode.x + dx[d];
				int ny = curNode.y + dy[d];
				
				if(!inRange(nx,ny)) continue;
				
				if(dist[nx][ny] > curNode.cost + map[curNode.x][curNode.y]) {
					dist[nx][ny] = curNode.cost + map[curNode.x][curNode.y];
					pq.add(new Node(nx,ny,dist[nx][ny]));
				}
			}
		}
		return dist[N-1][N-1];
	}

	private static boolean inRange(int nx, int ny) {
		return nx >= 0 && nx < N && ny >= 0 && ny < N;
	}
}
