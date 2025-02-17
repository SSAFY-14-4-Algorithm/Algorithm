package week3;

public class Swea1767{
    private static final int CORE = 1, WIRE = 2,BLANK = 0;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int [] dx = {-1,1,0,0};
	private static int [] dy = {0,0,-1,1};
	
	private static int N,maxCoreCnt,minLength;
	private static int [][] map;
	private static ArrayList<Core> cores;
	
	public static class Core{
		int x,y;
		
		public Core(int x,int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
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
		cores = new ArrayList<>();
		maxCoreCnt = Integer.MIN_VALUE;
		minLength = Integer.MAX_VALUE;
		
		for(int i=0;i<N;i++) {
			String [] tokens = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(tokens[j]);
				if(map[i][j] == CORE && i>0 && i<N-1 && j>0 && j<N-1) {
					cores.add(new Core(i,j));
				}
			}
		}
		
		backtracking(0,0,0);
		
		return minLength;
	}
	
	private static void backtracking(int depth, int coreCnt, int totalLength) {
		if(depth == cores.size()) {
			if(maxCoreCnt < coreCnt) {
				maxCoreCnt = coreCnt;
				minLength = totalLength;
			}else if(maxCoreCnt == coreCnt) {
				minLength = Math.min(minLength, totalLength);
			}
			
			return;
		}
		
		Core core = cores.get(depth);
		
		backtracking(depth+1,coreCnt,totalLength);	//현재 코어를 연결하지 않는 경우
		
		for(int d=0;d<4;d++) {
			int length = createLine(core,d,WIRE);
			if(length != -1) {
				backtracking(depth+1,coreCnt+1,totalLength+length);
				createLine(core,d,BLANK);
			}
		}
	}

	private static int createLine(Core core, int d, int value) {
		int length = 0;
		int x = core.x;
		int y = core.y;
		List<Core> path = new ArrayList<>();
		
		while(inRange(x + dx[d], y + dy[d])) {
			x += dx[d];
			y += dy[d];
			
			if(value == WIRE && map[x][y] != BLANK) {
				for(Core node : path) {
					map[node.x][node.y] = BLANK; 
				}
				
				return -1;
			}
			
			map[x][y] = value;
			length++;
			path.add(new Core(x,y));
		}
		return length;
	}
	
	private static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
