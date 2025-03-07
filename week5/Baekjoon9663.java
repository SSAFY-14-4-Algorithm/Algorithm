import java.io.*;
import java.util.*;
/*
 * 메모리 12196KB
 * 시간 1364ms
 */
public class Baekjoon9663_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static boolean[][] visited;
	static int result=0;
	static boolean[] column;
	static boolean[] diag1; //우상단->좌하단 대각선은 x+y값이 같다
	static boolean[] diag2;	 // 좌상단->우하단 대각선은 x-y값이 같다
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		column = new boolean[n];
		diag1 = new boolean[2*n-1]; // x+y 최대값이 (n-1) + (n-1)
		diag2 = new boolean[2*n-1]; // x-y + (n-1) 최대값 2n-2
		NQueen(n,0);
		//결과값 계산
		result*=2;
		//n이 홀수이면 중간값 따로 계산해줘야한다
		if(n%2==1) {
			column[n/2] = true;
			diag1[0+n/2] = true;
			diag2[0-n/2+n-1] = true;
			NQueen(n, 1);
		}
		System.out.println(result);
	}
	public static void NQueen(int n,int row) {
		if(row == n) {
			result++;
			return;
		}
		//queen의 위치가 n/2일때만 계산
		int size= row==0?n/2: n;	
		
		for(int col=0;col<size;col++) {
			//같은 열이나 대각선이면 무시
			if(column[col] || diag1[row+col] || diag2[row-col+n-1])
				continue;
			
			//열 대각선 2개 true
			column[col] = true;
			diag1[row+col] = true;
			diag2[row-col+n-1] = true;
			
			//다음 행으로 이동
			NQueen(n,row+1);
			
			//백트래킹
			column[col] = false;
			diag1[row+col] = false;
			diag2[row-col+n-1] = false;
		}
		
	}
}
