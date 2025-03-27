package ct2;
import java.util.*;
import java.io.*;
/*
 * 메모리:32684kb, 시간:248ms
 */
public class Baekjoon16234 {
	static Queue<Node> cur, next;
	static int[][] map;
	static List<Node> union;
	static boolean[][] visited;
	static int N, L, R;
	//공부용
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	static class Node{
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		cur = new ArrayDeque<>();
		next = new ArrayDeque<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				cur.add(new Node(i, j));
			}
		}
		
		int days = -1;
		
		while(!cur.isEmpty()) {
			bfs(); //인구이동 실시
			//cur은 체크해야할 좌표
			//next는 다음날 체크해야할 좌표
			Queue<Node> temp = cur;
			cur = next;
			next = temp;
			days++;
		}
		System.out.println(days);
	}
	
	static void bfs() {
		visited = new boolean[N][N];
		//체크해야할 cur을 돌면서 체크
		while(!cur.isEmpty()) {
			Node n = cur.poll();
			if(!visited[n.x][n.y]) {
				move(n);//방문한 적 없는 좌표면 국경선 체크
			}
		}
	}
	
	static void move(Node n) {
		union = new ArrayList<>(); 
		//연합 좌표를 저장할 배열
		
		int sum = dfs(n);
		//연합 확인 및 인구 총합 구하기
		
		if(sum != 0) { //연합이 존재한다면
			int val = sum / union.size();
			//인구이동 실시
			for(Node node : union) {
				map[node.x][node.y] = val;
				next.add(node);
				//다음에 체크할 좌표에 추가
				//-> 인구이동이 일어난 좌표만 확인 하면 됨
			}
		}
	}
	
	static int dfs(Node n) {
		//평범한 합을 구하는 DFS
		//하나 특이점이 있다면, 자기자신을 체크하고 출발하지 않음
		//따라서 시작점 주위에 조건을 만족하는 점이 있어야
		//자기자신을 다시 방문하면서 체크 가능
		//따라서 혼자 있는 정점을 체크하지 않는 다는 특징
		int sum = 0;
		int x = n.x;
		int y = n.y;
		for(int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			if(0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
				int dif = Math.abs(map[nx][ny] - map[x][y]);
				if(L <= dif && dif <= R) {
					visited[nx][ny] = true;
					Node node = new Node(nx, ny);
					
					//갈 수 있는 좌표가 있다면 연합에 추가
					union.add(node);
					sum += dfs(node) + map[nx][ny];
				}
			}
		}
		return sum;
	}
}
