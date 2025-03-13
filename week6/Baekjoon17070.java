import java.util.*;
import java.io.*;


import java.util.*;
import java.io.*;

/*
 * 14336kb, 104ms
 */
//DP 풀이
public class Baekjoon17070 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][][] dp = new int[N][N][3];
		if(map[N-1][N-1] == 1 || map[0][2] == 1) {
			System.out.println(0);
		} else {
			dp[0][1][0] = 1;
			for(int i = 0; i < N; i++) {	
				for(int j = 2; j < N; j++) {
					if(map[i][j] != 1) {
						dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
						if(i >= 1) {
							dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
							if(map[i-1][j] != 1 && map[i][j-1] != 1) {
								dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
							}
						}
					}
				}
			}
			System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
		}
	}
}


//파이프 옮기기, 구현 풀이
/*
 * 16168kb, 216ms
 */
//public class Baekjoon17070 {
//	static int N;
//	static int[][] map;
//	static int answer;
//	//dir 0 : 오른쪽, 1 : 대각선, 2 : 아래
//	public static void BT(int x, int y, int dir) {
//		switch(dir) {
//		case 0:
//			if(x == N-1 && y == N-2) {
//				answer++;
//				return;
//			}
//			if(y+2 < N && map[x][y+2] != 1) BT(x, y+1, dir);
//			if(y+2 < N && x + 1 < N && map[x+1][y+2] != 1 && map[x+1][y+1] != 1 && map[x][y+2] != 1) BT(x, y+1, 1);
//			break;
//		case 1:
//			if(x == N-2 && y == N-2 && dir == 1) {
//				answer++;
//				return;
//			}
//			if(y+2 < N && map[x+1][y+2] != 1) BT(x+1, y+1, 0);
//			if(x+2 < N && map[x+2][y+1] != 1) BT(x+1, y+1, 2);
//			if(x+2 < N && y+2 < N && map[x+2][y+2] != 1 && map[x+1][y+2] != 1 && map[x+2][y+1] != 1) BT(x+1, y+1, 1);
//			break;
//		case 2:
//			if(x == N-2 && y == N-1) {
//				answer++;
//				return;
//			}
//			if(x+2 < N && map[x+2][y] != 1) BT(x+1, y, dir);
//			if(x+2 < N && y+1 < N && map[x+2][y+1] != 1 && map[x+2][y] != 1 && map[x+1][y+1] != 1) BT(x+1, y, 1);
//			break;
//		}
//	}
//	
//	
//	
//	public static void main(String[] args) throws IOException{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		N = Integer.parseInt(br.readLine());
//		map = new int[N][N];
//		StringTokenizer st;
//		for(int i = 0; i < N; i++) {
//			st = new StringTokenizer(br.readLine());
//			for(int j = 0; j < N; j++) {
//				map[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}
//		answer = 0;
//		BT(0, 0, 0);
//		System.out.println(answer);
//	}
//}
