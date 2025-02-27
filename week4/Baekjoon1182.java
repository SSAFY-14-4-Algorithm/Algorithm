package week4;

import java.io.IOException;

public class Baekjoon1182 {
	private static int N;
	private static int S;
	private static int[] nums;
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		N = readInt3();
		S = readInt3();
		nums = new int[N];
		for (int i = 0; i < N; i++)
			nums[i] = readInt3();
		dfs(0, 0, 0);
		System.out.print(new StringBuilder().append(result));
	}

	private static void dfs(int depth, int count, int sum) {
		if (depth == N) {
			if (S == sum && count != 0)
				result++;
			return;
		}
		dfs(depth + 1, count, sum);
		dfs(depth + 1, count + 1, sum + nums[depth]);
	}

	private static int readInt3() throws IOException {
		int n = 0;
		int c = System.in.read();
		boolean isNegative = false;
		while (c <= 32) {
			c = System.in.read();
		}
		if (c == '-') {
			isNegative = true;
			c = System.in.read();
		}
		while ('0' <= c && c <= '9') {
			n = (n * 10) + (c - '0');
			c = System.in.read();
		}
		return isNegative ? -n : n;
	}
}
