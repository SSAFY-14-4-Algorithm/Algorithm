/*
 * BOJ 2607번 : 비슷한 단어
 * 메모리 : 11,472kb
 * 시간 : 64ms
 * 
 * 한 문자를 더하거나, 빼거나, 하나의 문자를 다른 문자로 바꾸어 
 * 한 단어와 같은 구성을 가지면 비슷한 단어
 */

import java.io.*;
import java.util.*;

public class Baekjoon2607 {
	static int N;
	static int[] alpha;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		alpha = new int[26];
		
		String first = br.readLine();
		
		for(int n = 0; n < N-1; n++) {
			for(int i = 0; i < first.length(); i++) {
				alpha[first.charAt(i)-'A']++;
			}
			
			int cnt = 0; // 비슷한 글자의 개수
			String line = br.readLine();
			for(int i = 0; i < line.length(); i++) {
				if(alpha[line.charAt(i)-'A']>0) {
					cnt++;
					alpha[line.charAt(i)-'A']--;
				}
			}
			
			if(first.length()==line.length()+1 && line.length()==cnt) {
				// 1. 문자 더해주는 경우
				answer++;
			} else if(first.length()==line.length()-1 && line.length()-1==cnt) {
				// 2. 문자 빼주는 경우
				answer++;
			} else if(first.length()==line.length() && first.length()-1==cnt) {
				// 3. 문자 하나만 바꾸는 경우
				answer++;
			} else if(first.length()==line.length() && first.length() == cnt) {
				// 4. 문자 동일한 경우
				answer++;
			}
		}
		sb.append(answer);
		System.out.print(sb);
		br.close();
	}
}