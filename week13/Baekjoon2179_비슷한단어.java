/*
 * BOJ 2179번 : 비슷한 단어
 * 메모리 : 15,988kb
 * 시간 : 1,204ms 
 */

package algorithm;

import java.io.*;
import java.util.*;

public class BOJ2179_비슷한단어 {
	static int N;
	static String[] word;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		word = new String[N];
		for(int n = 0; n < N; n++) {
			word[n] = br.readLine();
		}
		
		int word1Idx = 0;
		int word2Idx = 0;
		int max = Integer.MIN_VALUE;
		
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				int cnt = prefix(word[i], word[j]);
				
				if(cnt > max) {
					word1Idx = i;
					word2Idx = j;
					max = cnt;
				}
			}
		}
		
		sb.append(word[word1Idx]).append("\n");
		sb.append(word[word2Idx]).append("\n");
		System.out.print(sb);
		br.close();
	}
	
	private static int prefix(String word1, String word2) {
		int cnt = 0;
		int length = Math.min(word1.length(), word2.length());
		
		for(int k = 0; k < length; k++) {
			if(word1.charAt(k) == word2.charAt(k)) {
				cnt++;
			} else {
				break;
			}
		}

		return cnt;
	}
}
