import java.io.*;
import java.util.*;

/*
 *메모리: 372660
 *시간:  1588
 *
 * 벽 부수고 이동하기 
 *  
 */
public class Main {
	static int n,m,k;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Node {
		int x, y, dist, broken;
		Node(int x, int y, int dist, int broken){
			this.x = x;
			this.y = y;
			this.dist =dist;
			this.broken = broken;
		}
	}
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());

    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	map = new int[n][m];
    	visited = new boolean[n][m][k+1];
    	
    	for(int i=0;i<n;i++) {
    		String line = br.readLine();
    		for(int j=0;j<m;j++) {
    			map[i][j] = line.charAt(j)-'0';
    			}
    		}
    	System.out.print(bfs());
    	}
    	
    	static int bfs() {
    		Queue<Node> q = new LinkedList<>();
    		q.add(new Node(0,0,1,0));
    		visited[0][0][0] = true;
    		
    		while(!q.isEmpty()) {
    			Node cur = q.poll();
    			if(cur.x == n-1 && cur.y == m-1) {
    				return cur.dist;
    			}
    			for(int d=0;d<4;d++) {
    				int nx = cur.x+dx[d];
    				int ny = cur.y+dy[d];
    				if(nx<0 || ny <0 || nx >=n || ny >=m) {
    					continue;
    				}
    				if(map[nx][ny] ==0 && !visited[nx][ny][cur.broken]) { //벽이 아닌 경우
    					visited[nx][ny][cur.broken] = true;
    					q.add(new Node(nx,ny,cur.dist+1, cur.broken));
    					
    				}
    				if (map[nx][ny] == 1 && cur.broken <k && !visited[nx][ny][cur.broken+1]) {//벽인데 부술 수 있는 경우
    					visited[nx][ny][cur.broken+1] = true;
    					q.add(new Node(nx,ny,cur.dist+1,cur.broken+1));
    				}
    			}
    		}
    		return -1;
    	}
    
}
    