import java.io.*;
import java.util.*;
/* 내려가기
 * DP풀이
 * 52444kb, 336ms
 */
public class Baekjoon2096 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] maxArr = new int[N][3];
		int[][] minArr = new int[N][3];
		int[][] arr = new int[N][3];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < 3; i++) {
			maxArr[0][i] = arr[0][i];
			minArr[0][i] = arr[0][i];
		}
		for(int i = 1; i < N; i++) {
			maxArr[i][0] = Math.max(maxArr[i-1][0], maxArr[i-1][1]) + arr[i][0];
			minArr[i][0] = Math.min(minArr[i-1][0], minArr[i-1][1]) + arr[i][0];
			maxArr[i][1] = Math.max(maxArr[i-1][0], Math.max(maxArr[i-1][1], maxArr[i-1][2])) + arr[i][1];
			minArr[i][1] = Math.min(minArr[i-1][0], Math.min(minArr[i-1][1], minArr[i-1][2])) + arr[i][1];
			maxArr[i][2] = Math.max(maxArr[i-1][1], maxArr[i-1][2]) + arr[i][2];
			minArr[i][2] = Math.min(minArr[i-1][1], minArr[i-1][2]) + arr[i][2];
		}
		StringBuilder sb = new StringBuilder();
		sb.append(Math.max(maxArr[N-1][0], Math.max(maxArr[N-1][1], maxArr[N-1][2])));
		sb.append(" ").append(Math.min(minArr[N-1][0], Math.min(minArr[N-1][1], minArr[N-1][2])));
		System.out.println(sb);
	}
}