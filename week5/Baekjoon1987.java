import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 메모리 14904KB 실행시간 872ms
public class Baekjoon1987 {

	static int r, c, answer = 0;
	static char[][] list;
	static boolean[] visited = new boolean[26];
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		r = Integer.parseInt(input[0]);
		c = Integer.parseInt(input[1]);

		list = new char[r][c];

		for (int i = 0; i < r; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < c; j++) {
				list[i][j] = tmp.charAt(j);
			}
		}

		visited[list[0][0] - 'A'] = true;
		dfs(0, 0, 1);

		System.out.println(answer);
	}

	public static void dfs(int x, int y, int depth) {
		answer = Math.max(answer, depth);

		for (int i = 0; i < 4; i++) {
			int di = x + dx[i];
			int dj = y + dy[i];

			if (di >= 0 && di < r && dj >= 0 && dj < c) {
				int charIdx = list[di][dj] - 'A';
				if (!visited[charIdx]) {
					visited[charIdx] = true;
					dfs(di, dj, depth + 1);
					visited[charIdx] = false;
				}
			}
		}
	}
}
