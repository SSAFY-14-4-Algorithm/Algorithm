/*
 * BOJ 10830번 : 행렬 제곱
 * 메모리 : 11,560kb
 * 시간 : 68ms
 */

package algorithm;

import java.io.*;
import java.util.*;

public class Baekjoon10830 {
	static int N;
	static long B;
	static int[][] A;
	static int[][] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken()) % 1000;
			}
		}

		result = new int[N][N];
		for (int i = 0; i < N; i++) { // 단위 행렬로 초기화
			result[i][i] = 1;
		}

		while (B > 0) {
			// B가 홀수면 결과 배열에 A를 한번만 더 곱함
			// 2^5 = (2^2) * (2^2) * 2
			if(B%2 == 1) {
				result = square(result, A);
			}
			
			// B가 짝수면 반으로 나눠서 행렬 곱셈 수행
			// 2^10 = (2^5) * (2^5)
			A = square(A, A);
			B /= 2;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
		br.close();
	}

	private static int[][] square(int[][] matrix1, int[][] matrix2) {
		int[][] temp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					temp[i][j] += matrix1[i][k] * matrix2[k][j];
					temp[i][j] %= 1000;
				}
			}
		}
		
		return temp;
	}
}