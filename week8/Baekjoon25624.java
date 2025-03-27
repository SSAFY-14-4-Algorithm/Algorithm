/*
 * BOJ 25624번 : SNUPTI
 * 메모리 : 19,076kb
 * 시간 : 168ms
 *
 * 각 자리(열) 알파벳 집합 찾기
 * 그 집합을 겹치지 않게 배정할 수 있으면 YES, 알파벳 집합 출력
 * 불가능하면 NO
 */

import java.io.*;
import java.util.*;

public class Baekjoon25624 {
	static int N, M;
	static Set<String> set; // 입력 문자열 중복 방지
	static int[] alpha; // 알파벳이 어느 자리(열)에 있는지
	static boolean result = true;
	static PriorityQueue<Character>[] answer; // 알파벳 순으로 문자열 집합 출력

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		set = new HashSet<>();
		alpha = new int[26];
		Arrays.fill(alpha, -1);
		answer = new PriorityQueue[N];
		for(int n = 0; n < N; n++) {
			answer[n] = new PriorityQueue<>();
		}

		for(int m = 0; m < M; m++) {
			String str = br.readLine();
			if (set.contains(str)) {
				result = false;
				continue;
			} else {
				set.add(str);
			}

			for(int n = 0; n < N; n++) {
				char ch = str.charAt(n);
				if(alpha[ch-'A'] == -1) { // 처음 등장한 알파벳
					alpha[ch-'A'] = n; // 자리(열) 위치 저장
					answer[n].add(ch);
				} else if(alpha[ch-'A'] != n) { // 다른 자리(열)
					result = false;
				}
			}
		}

		// 불가능하면 NO
		if(!result) {
			System.out.println("NO");
			return;
		}

		// 문자열 집합으로 만들 수 있는 경우의 수 계산
		int total = 1;
		for(int n = 0; n < N; n++) {
			total *= answer[n].size();
		}

		if(total != M){
			System.out.println("NO");
		} else {
			System.out.println("YES");
			for(int n = 0; n < N; n++) {
				while(!answer[n].isEmpty()) {
					System.out.print(answer[n].poll());
				}
				System.out.println();
			}
		}
	}
}