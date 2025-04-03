import java.io.*;
import java.util.*;
/*
 * 메모리:34820KB	
 * 시간 : 484ms
 */
public class Baekjoon14500 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	//도형 19개 다 구함 
	static int[][] tetroX = {
			{0, 0, 0, 0}, // I형 세로
			{0, 1, 2, 3}, // I형 가로
			{0, 0, 1, 1}, // O형
			{0, 1, 2, 2}, // L형
			{0, 0, 0, 1}, 
			{0, 0, 1, 2}, 
			{0, 1, 1, 1}, 
			{0, 1, 2, 2}, // J형
			{0, 1, 1, 1}, 
			{0, 0, 1, 2}, 
			{0, 0, 0, 1}, 
			{0, 0, 1, 1}, // S형
			{0, 1, 1, 2}, 
			{0, 0, 1, 1}, // Z형
			{0, 1, 1, 2}, 
			{0, 0, 0, 1}, // T형
			{0, 1, 1, 2}, 
			{0, 1, 1, 1}, 
			{0, 1, 1, 2}  
	};
	
	static int[][] tetroY = {
			{0, 1, 2, 3}, // I형 세로
			{0, 0, 0, 0}, // I형 가로
			{0, 1, 0, 1}, // O형
			{0, 0, 0, 1}, // L형
			{0, 1, 2, 0}, 
			{0, 1, 1, 1}, 
			{2, 0, 1, 2}, 
			{1, 1, 1, 0}, // J형
			{0, 0, 1, 2}, 
			{0, 1, 0, 0}, 
			{0, 1, 2, 2}, 
			{1, 2, 0, 1}, // S형
			{0, 0, 1, 1}, 
			{0, 1, 1, 2}, // Z형
			{1, 0, 1, 0}, 
			{0, 1, 2, 1}, // T형
			{1, 0, 1, 1}, 
			{1, 0, 1, 2}, 
			{0, 0, 1, 0}  
	};
	static int[][] paper;
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		paper = new int[n][m];
		for(int i=0;i<n;i++) {
			String[] s2 = br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				paper[i][j] = Integer.parseInt(s2[j]);
			}
		}
		int result = Integer.MIN_VALUE;
		//처음부터 전부 다 도형넣어봄
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				//19개 도형 전부 넣음
				for(int r=0;r<19;r++) {
					int count = 0;
					boolean allRange = true;
					for(int c=0;c<4;c++) {
						int nr = i+tetroX[r][c];
						int nc = j+tetroY[r][c];
						//도형에 있는 값들이 범위 안인지 확인후 값 추가
						if(isRange(nr,nc,n,m)) {
							count += paper[nr][nc];
						}
						else {
							allRange = false;
							break;
						}
					}
					//도형 안의 모든값이 범위내면 큰 값을 결과값에 저장
					if(allRange) {
						result = Math.max(result, count);
					}
				}
			}
		}
		System.out.println(result);
	}
	public static boolean isRange(int x, int y, int r, int c) {
		return x>=0 && y>=0 && x<r && y<c;
	}
}
