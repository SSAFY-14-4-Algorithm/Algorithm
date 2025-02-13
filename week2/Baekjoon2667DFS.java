import java.io.*;
import java.util.*;

public class Baekjoon2667DFS {
    static boolean[][] visited;
    static int cnt;
    static int N;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] array;
    public static void dfs(int x, int y) {
    	visited[x][y] = true;
    	cnt++;
    	for(int i = 0; i < 4; i++) {
    		int nx = x + dir[i][0];
    		int ny = y + dir[i][1];
    		if(0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny] && array[nx][ny] == 1) {
    			dfs(nx, ny);
    		}
    	}
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        array = new int[N][N];
        for(int i = 0; i < N; i++){
            String[] tmp = br.readLine().split("");
            for(int j = 0; j < N; j++){
                array[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        int answer = 0;
        visited = new boolean[N][N];
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		if(array[i][j] == 1 && !visited[i][j]) {
        			answer += 1;
        			cnt = 0;
        			dfs(i, j);
        			ans.add(cnt);
        		}
        	}
        }
        StringBuilder sb = new StringBuilder();
        sb.append(answer).append("\n");
        Collections.sort(ans);
        for(int i = 0; i < ans.size(); i++){
        	sb.append(ans.get(i)).append("\n");
        }
        System.out.print(sb);
    }
}