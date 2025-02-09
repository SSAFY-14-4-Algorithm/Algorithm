import java.util.*;
import java.io.*;

public class Baekjoon7576 {
	static class Node {
		int x;
		int y;
		public Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] array = new int[M][N];
		int rest = 0;
		Queue<Node> q = new LinkedList<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++){
				array[i][j] = Integer.parseInt(st.nextToken());
				if(array[i][j] == 1) {
					q.add(new Node(i, j));
				}
				else if(array[i][j] == 0) {
					rest++;
				}
			}
		}
		int[][] dir = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
		
		int answer = 0;
		while(!q.isEmpty()) {
			if(rest == 0) {
				break;
			}
			int len = q.size();
			for(int i = 0; i < len; i++) {
				Node cur = q.poll();
				int x = cur.x;
				int y = cur.y;
				for(int j = 0; j < 4; j++) {
					int nx = x + dir[j][0];
					int ny = y + dir[j][1];
					if (0 <= nx && nx < M && 0 <= ny && ny < N && array[nx][ny] == 0) {
						array[nx][ny] = 1;
						rest--;
						q.add(new Node(nx, ny));
					}
				}
			}
			answer++;
		}
		
		if(rest == 0) {
			System.out.println(answer);
		} else {
			System.out.println(-1);
		}
		
	}
}
