import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//메모리 14516 KB 실행시간 5912ms
public class Baekjoon9663 {

	static int N, answer = 0;
	static int[] queens;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		queens = new int[N];
		backTracking(0);
		System.out.println(answer);
	}

	public static void backTracking(int depth) {
		if (depth == N) {
			answer++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (isSafe(depth, i)) {
				queens[depth] = i;
				backTracking(depth + 1);
			}
		}
	}

	public static boolean isSafe(int row, int col) {
		for (int i = 0; i < row; i++) {
			if (queens[i] == col || Math.abs(queens[i] - col) == Math.abs(i - row)) {
				return false;
			}
		}
		return true;
	}
}
