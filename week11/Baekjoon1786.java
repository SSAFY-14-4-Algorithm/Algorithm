/*
 * BOJ 1786번 : 찾기
 * 메모리 : 67,340kb
 * 시간 : 388ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon1786 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		int tLength = text.length;
		int pLength = pattern.length;

		// 1. 부분 일치 테이블 만들기
		int[] pi = new int[pLength];

		for(int i=1, j=0; i < pLength; i++) {
			// 두 포인터의 위치에서 불일치가 발생하면 맞은 직전위치의 정보를 활용해서 불필요한 비교 줄임
			while(j>0 && pattern[i] != pattern[j]) {
				j = pi[j-1];
			}

			// 현재 i 위치의 부분문자열의 접미사와 접두사가 일치하는 패턴의 최장길이 저장
			if(pattern[i] == pattern[j]) { // j 위치까지 맞은 상황
				pi[i] = ++j; // j 위치까지 맞으면 j+1 길이만큼 맞은 것, 후에 j 이동
			}
		}
//		System.out.println(Arrays.toString(pi));

		// 2. 패턴 찾기
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<>();

		for(int i=0, j=0; i < tLength; i++) {
			while(j > 0 && text[i] != pattern[j]) {
				j = pi[j-1];
			}

			if(text[i] == pattern[j]) {
				if(j == pLength-1) { // 일치가 발생한 위치가 패턴의 끝이면
					cnt++; // 패턴 찾았으므로 카운트 증가
					list.add(i-j+1); // 시작 위치, 문제에서는 1부터 위치 카운트
					j = pi[j];
				} else {
					j++;
				}
			}
		}

		sb.append(cnt).append("\n");
		if(cnt > 0) {
			for(int result : list) {
				sb.append(result).append(" ");
			}
		}

		System.out.print(sb);
		br.close();
	}
}