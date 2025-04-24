/*
 * BOJ 19539번 : 사과나무
 * 메모리 : 22,760kb
 * 시간 : 192ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon19539 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		int one = 0, two = 0, sum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++) {
			int num = Integer.parseInt(st.nextToken());
			sum += num;
			one += num%2;
			two += num/2;
		}
		
		if(sum%3 > 0 || one > two) { // 3으로 나누어 떨어져야 함, 1은 2보다 크면 안됨
			sb.append("NO");
		} else { // 1==2인 경우, 2가 1보다 큰 경우 만들 수 있음
			sb.append("YES");
		}
		
		System.out.print(sb);
		br.close();
	}
}