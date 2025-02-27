package week4;

import java.io.IOException;
import java.util.Arrays;

public class Baekjoon16938 {
	private static int N; // N개 문제
	private static int L; // 난이도의 합은 L보다 크거나 같다
	private static int R; // 난이도의 합은 R보다 작거나 같다
	private static int X; // 난이도 차이는 X보다 크거나 같다
	private static int count = 0;
	private static int[] A; // N개 문제 난이도

	public static void main(String[] args) throws IOException {
		N = readInt();
		L = readInt();
		R = readInt();
		X = readInt();
		A = new int[N];
		for (int i = 0; i < N; i++)
			A[i] = readInt();
		Arrays.sort(A);
		for (int subset = 0; subset < (1 << N); subset++) {
			if (2 <= Integer.bitCount(subset)) {
				int tempSum = 0;
				int tempMin = Integer.MAX_VALUE;
				int tempMax = Integer.MIN_VALUE;
				for (int i = 0; i < N; i++) {
					if ((subset & (1 << i)) != 0) {
						tempSum += A[i];
						tempMin = Math.min(tempMin, A[i]);
						tempMax = Math.max(tempMax, A[i]);
					}
				}
				if (checkSum(tempSum) && checkDiff(tempMax - tempMin)) {
					count++;
				}
			}
		}
		System.out.print(new StringBuilder().append(count));
	}

	private static boolean checkSum(int sum) {
		return L <= sum && sum <= R;
	}

	private static boolean checkDiff(int diff) {
		return X <= diff;
	}

	private static int readInt() throws IOException {
		int c;
		int n = System.in.read() & 15;
		while ((c = System.in.read()) >= 48)
			n = (n * 10) + (c & 15);
		if (c == 13)
			System.in.read();
		return n;
	}
}
