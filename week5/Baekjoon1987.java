
import java.io.*;
import java.util.*;

/**
 * 
 * 메모리 12468 kb
 * 시간 956 ms
 *
 *
 */


public class Baekjoon1987 {
	
	static int R, C;
	static char[][] map;
	static boolean[] visited = new boolean[26];
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	static int count = 0;
	static int maxResult = 0;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        
        for(int i = 0; i < R ; i++) {
        	map[i] = br.readLine().toCharArray();
        }
        
        dfs(0, 0);
        System.out.println(maxResult);
        
    }
    
    public static void dfs(int x, int y) {
    	visited[map[x][y] - 'A'] = true;
    	count++;
    	maxResult = Math.max(maxResult, count);
    	
    	for (int dir = 0; dir < 4; dir++) {
    		int nx = x + dx[dir];
    		int ny = y + dy[dir];
    		
    		if(nx >= 0 && ny >= 0 && nx < R && ny < C && !visited[map[nx][ny] - 'A']) dfs(nx, ny);
    	}
    	
    	visited[map[x][y] - 'A'] = false;
    	count--;
    }
}
