import java.util.*;
import java.io.*;
import java.math.*;

public class Baekjoon9663 {
	static int N;
	//N이 14가 최대라 32비트 int로 가능
	static int col;
	static int slash;
	static int bslash;
	static int answer = 0;
	public static void nq(int row) {
		if(row == N) {
			answer++;
		}
		for(int i = 0; i < N; i++) {
			if(check(row, i)) {
				col |= 1<<i;
				slash |= 1<<(row+i);
				bslash |= 1<<(row-i+N);
				nq(row+1);
				col ^= 1<<i;
				slash ^= 1<<(row+i);
				bslash ^= 1<<(row-i+N);
			}
		}
	}
	public static boolean check(int x, int y) {
		return ((col & 1<<y) == 0) && ((slash & 1<<(x+y)) == 0) && ((bslash & 1<<(x-y+N)) == 0);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = slash = bslash = 0;
		for(int i = 0; i < N/2; i++) {
			col |= 1<<i;
			slash |= 1<<i;
			bslash |= 1<<(N-i);
			nq(1);
			col ^= 1<<i;
			slash ^= 1<<i;
			bslash ^= 1<<(N-i);
		}
		answer *= 2;
		if(N%2 == 1) {
			col |= 1<<N/2;
			slash |= 1<<N/2;
			bslash |= 1<<(N-N/2);
			nq(1);
		}
		
		System.out.println(answer);
	}
}
