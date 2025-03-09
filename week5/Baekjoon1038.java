/*
 * 백준 1038번 : 감소하는 수  
 * 메모리 : 11,660kb
 * 시간 : 72ms
 */

package algorithm;

import java.io.*;
import java.util.*;

public class Baekjoon1038 {
	static int N;
	static List<Long> numList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine()); // N번째 감소하는 수
		numList = new ArrayList<Long>();
		
		if(N > 1022) {
			System.out.println(-1);
			return;
		}
		
		for(int i = 0; i <= 9; i++) {
			dfs(i);
		}
		
		Collections.sort(numList);
		sb.append(numList.get(N));
		System.out.println(sb);
		br.close();
	}
	
	private static void dfs(long num) {
		numList.add(num);
		
		long lastNum = num%10;
		
		for(long i = lastNum-1; i >= 0; i--) {
			dfs(num*10 + i);
		}
		
	}
}
