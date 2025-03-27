import java.io.*;
import java.util.*;

public class Baekjoon16234 {
    static int n, l, r;
    static int[][] arr;
    static ArrayList<int[]> list;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    //메모리297456kb 시간644ms
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        
        arr = new int[n][n];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < n; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        System.out.println(move());
    }
    
    static int move() {
    	int result = 0;
    	while (true) {
    		visited = new boolean[n][n];
    		boolean move = false;
    		
    		for (int i = 0 ; i < n; i++) {
    			for (int j = 0; j < n; j++) {
    				if (!visited[i][j]) {
    					int sum = bfs(i, j);
    					if (list.size() > 1) {
    						int avg = sum / list.size();
    						for (int k = 0; k < list.size(); k++) {
    							int x = list.get(k)[0];
    							int y = list.get(k)[1];
    							arr[x][y] = avg;
    							move = true;
    						}
    					}
    				}
    			}

    		}
    		if (!move) return result; 
            result++;
    	}
    }
    
    
    static int bfs(int x, int y) {
    	Queue<int[]> q = new ArrayDeque<>();
    	list = new ArrayList<>();
    	
    	q.add(new int[] { x, y });
    	list.add(new int[] { x, y });
    	visited[x][y] = true;
    	int sum = arr[x][y];
    	
    	while(!q.isEmpty()) {
    		int[] poll = q.poll();
    		for (int i = 0; i < 4; i++) {
    			int nx = poll[0] + dx[i];
    			int ny = poll[1] + dy[i];
    			
    			if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
    				if (!visited[nx][ny]) {
    					int tmp = Math.abs(arr[poll[0]][poll[1]] - arr[nx][ny]);
    					if (tmp >= l && tmp <= r) {
    						visited[nx][ny] = true;
    						q.add(new int[] {nx, ny});
    						sum += arr[nx][ny];
    						
    						list.add(new int[] {nx, ny});
    					}
    				}
    			}
    			
    		}
    	}
    	
    	return sum;
    }

    
}
