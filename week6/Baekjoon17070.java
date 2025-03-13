package AlgorithmStudy.week6;

import java.util.*;
import java.io.*;

public class Baekjoon17070 {
	
	static final int[] dx = {0,1,1};
	static final int[] dy = {1,0,1};
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int[][] home;
	static int N, cnt;
	static int startX, startY, endX, endY;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		home = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cnt = 0;
		startX = 0; startY = 0; endX = 0; endY = 1;
		
		movePipe(startX,startY,endX,endY);
		
		System.out.println(cnt);

	}
	private static void movePipe(int sx, int sy, int ex, int ey) {
		int newSx, newSy, newEx, nexEy;
		
		if(ex == N-1 && ey == N-1) { 
			cnt++;
			return;
		}
		
		if(sy != ey) { // 가로로 놓인 경우
			if(inRange(ex, ey+1) && home[ex][ey+1] == 0) {
				newSx = ex; 
				newSy = ey; 
				newEx = ex; 
				nexEy = ey+1;
				
				movePipe(newSx, newSy, newEx, nexEy);
			}
			
		}
		if(sx != ex) { // 세로로 놓인 경우
			if(inRange(ex+1, ey) && home[ex+1][ey] == 0) {
				newSx = ex; 
				newSy = ey; 
				newEx = ex+1; 
				nexEy = ey;
				
				movePipe(newSx, newSy, newEx, nexEy);
			}
			
		}
		
		// 대각선은 공통
		if(inRange(ex+1, ey+1) && home[ex+1][ey+1] == 0 && home[ex][ey+1] == 0 && home[ex+1][ey] == 0) {
			newSx = ex; 
			newSy = ey; 
			newEx = ex+1; 
			nexEy = ey+1;
			
			movePipe(newSx, newSy, newEx, nexEy);
		}
		
	}
	private static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

}
