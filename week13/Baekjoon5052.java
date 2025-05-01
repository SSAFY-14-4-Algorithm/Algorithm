/*
 * BOJ 5052번 : 전화번호 목록 
 * 메모리 : 137,884kb
 * 시간 : 736ms 
 */

package algorithm;

import java.io.*;
import java.util.*;

public class BOJ5052_전화번호목록 {	
	static int N;
	static int[][] trie;
	static boolean[] isEnd;
	static int nodeCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			trie = new int[N*10][10]; // 최대 노드 수(최악의 경우 번호 1개당 10개의 노드), 숫자(0~9)
			isEnd = new boolean[N*10]; 
			nodeCount = 1; // 루트를 제외하고 1번부터 카운팅 
			
			String[] num = new String[N];
			for(int n = 0; n < N; n++) {
				num[n] = br.readLine();
			}
			
			Arrays.sort(num);
			
			boolean isConsist = true;
			for(String n : num) {
				if(!insert(n)) {
					isConsist = false;
					break;
				}
			}
			
			if(isConsist) {
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			}
		}
		System.out.print(sb);
		br.close();
	}
	
	private static boolean insert(String num) {
		int node = 0;
		for(int i = 0; i < num.length(); i++) {
			int digit = num.charAt(i)-'0';
			
			if(trie[node][digit] == 0) {
				trie[node][digit] = nodeCount++;
			}
			node = trie[node][digit];
			
			// 현재 지나온 경로 중 누군가가 끝나는 단어라면 -> 이 번호는 누군가의 접두사 
			if(isEnd[node]) {
				return false;
			}
		}
		
		isEnd[node] = true; // 현재 노드는 이 번호의 끝! 표시 
		return true;
	}
}
