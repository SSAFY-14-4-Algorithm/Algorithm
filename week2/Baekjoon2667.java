public class Main{

	static int N;
	static int[][] map;
	static boolean[][] visit;
	static final int[] dx= {-1,1,0,0};
	static final int[] dy = {0,0,1,-1};
	
	private static class Node{
		int x,y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static int bfs(int x, int y) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x,y));
		visit[x][y] = true;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			Node cur = q.remove();
			
			for(int i=0;i<4;i++) {
				int nx= cur.x+dx[i];
				int ny = cur.y+dy[i];
				
				if(nx>=0 && ny>=0 && nx<N && ny<N && !visit[nx][ny] && map[nx][ny]==1) {
					q.add(new Node(nx,ny));
					visit[nx][ny] = true;
					cnt++;
				}
			}
			
		}
		return cnt;
	}
	
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		
		
		
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j]= input.charAt(j)-'0';
			}
			
		}
		
		List<Integer> ans = new ArrayList<>();
		int cnt=0;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==1 &&!visit[i][j]) {
					ans.add(bfs(i,j));
					cnt++;
				}
			}
		}
		
		Collections.sort(ans);
		System.out.println(cnt);
		for(int x: ans) {
			System.out.println(x);
		}
		
	
		
		
		
	}
	
	

}
