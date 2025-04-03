/*
 * BOJ 1138번 : 한 줄로 서기
 * 메모리 : 11,596kb
 * 시간 : 76ms
 *
 * 큰 사람부터 줄 세우기
 * input[n]은 자기보다 큰 사람이 몇 명? 이니까
 * input[n]번째 위치에 자기를 넣으면 됨
 */

import java.io.*;
import java.util.*;

public class Baekjoon1138 {
	static int N;
	static int[] input;
	static List<Integer> answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		input = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n = 1; n <= N; n++) {
			input[n] = Integer.parseInt(st.nextToken());
		}

		answer = new ArrayList<>();
		for(int n = N; n >= 1; n--) {
			answer.add(input[n], n);    // index, value
		}

		for(int a : answer) {
			sb.append(a).append(" ");
		}
		System.out.println(sb);
		br.close();
	}
}