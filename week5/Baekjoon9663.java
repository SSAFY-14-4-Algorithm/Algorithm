package algorithm;
import java.io.*;
import java.util.*;

public class Baekjoon9663 {
	static int N;
	static int[][] board;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		backTracking(0);

		sb.append(cnt);
		System.out.print(sb);
		br.close();
	}

	private static void backTracking(int idx) {
		if (idx == N) {
			cnt++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if(board[idx][i] != 0) {
				continue;
			}
			
			setQueen(idx, i, 1); // 선택, 배치 한 칸 +1
			backTracking(idx+1);
			setQueen(idx, i, -1); // 선택안함, 배치 안 한 칸 -1
		}
	}
	
	private static void setQueen(int row, int col, int command) {
        // 같은 열 체크
		for(int i = 0; i < N; i++) {
			board[i][col] += command; 
		}
		
		// 대각선 방향 체크
		for(int i = 0; i < N; i++) {
			if(row+i < N && col+i < N) {
				board[row+i][col+i] += command;
			}
			if(row-i >= 0 && col+i < N) {
				board[row-i][col+i] += command;
			}
			if(row+i < N && col-i >= 0) {
				board[row+i][col-i] += command;
			}
			if(row-i >= 0 && col-i >= 0){
				board[row-i][col-i] += command;
			}
		}
		
	}
	

}