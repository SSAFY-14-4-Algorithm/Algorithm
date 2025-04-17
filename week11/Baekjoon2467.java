/*
 * BOJ 2467번 : 용액
 * 메모리 : 31,564kb
 * 시간 : 248ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon2467 {
	static int N;
	static int[] value;

	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		value = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++) {
			value[n] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(value);

		int left = 0;
		int right = N-1;
		int minDiff = Integer.MAX_VALUE;

		int answerLeft = 0;
		int answerRight = 0;

		while(left < right) {
			int sum = value[left] + value[right];
			int diff = Math.abs(sum);


			if(minDiff > diff) {
				minDiff = diff;
				answerLeft = value[left];
				answerRight = value[right];
			}

			if(sum < 0) {
				left++;
			} else {
				right--;
			}
		}

		sb.append(answerLeft).append("\n").append(answerRight);
		System.out.print(sb);
		br.close();
	}
}