package test;

import java.io.*;
import java.util.*;

/**
 * 
 * 메모리: 11532
 * 시간 : 68
 * 
 * 제곱의 제곱의 제곱으 제곱으 제곱으 제곱으
 *
 */

public class Baekjoon10830 {
	
	static int N;
	static long B;
	static int[][] map, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		map = new int[N][N];
		result = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) % 1000;
			}
		}
		
		result = power(map, B);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

	public static int[][] power(int[][] matrix, long exp) {
		int[][] identity = new int[N][N];
		for (int i = 0; i < N; i++) {
			identity[i][i] = 1;
		}
		
		// 제곱으 제곱으 제고으 제고제곱으
		while (exp > 0) {
			if (exp % 2 == 1) {
				identity = mul(identity, matrix);
			}
			matrix = mul(matrix, matrix);
			exp /= 2;
		}

		return identity;
	}

	
	public static int[][] mul(int[][] mat1, int[][] mat2) {
		int[][] result = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int sum = 0;
				for (int k = 0; k < N; k++) {
					sum = (sum + (mat1[i][k] * mat2[k][j]) % 1000) % 1000;
				}
				result[i][j] = sum;
			}
		}

		return result;
	}
}
