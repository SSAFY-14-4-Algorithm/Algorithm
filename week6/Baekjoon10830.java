import java.util.*;
import java.io.*;

/*
 * 	14280kb, 108ms
 */

//분할정복을 이용한 거듭제곱
public class Baekjoon10830 {
	static int N;
	
	//분할정복
	public static int[][] DaC(int[][] A, long B) {
		if(B == 1) {
			return A;
		}
		int[][] ret = DaC(A, B/2);
		ret = multiple(ret, ret);
		if(B%2 == 1) {
			ret = multiple(ret, A);
		}
		return ret;
	}
	
	//행렬 곱셈
	public static int[][] multiple(int[][] A, int[][] B) {
		int[][] ret = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int sum = 0;
				for(int k = 0; k < N; k++) {
					sum += A[i][k] * B[k][j];
					sum %= 1000;
				}
				ret[i][j] = sum;
			}
		}
		return ret;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] answer = DaC(map, B);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sb.append(answer[i][j]%1000).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
		
	}

}
