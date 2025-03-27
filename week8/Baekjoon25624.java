package study;



import java.io.*;
import java.util.*;

/**
 * 
 * 문제 설명 개 어렵게 해놨네 짜증나게 ㄹㅇ 진짜
 * 
 * 
 * 메모리: 	19900 kb
 * 시간 : 	260 ms
 *
 */


public class Baekjoon25624 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		TreeSet<Character>[] ts = new TreeSet[N];
		HashSet<String> set = new HashSet<>();
		
		for(int i = 0; i < N ; i++) {
			ts[i] = new TreeSet<>();
		}
		
		boolean check = false;
		for(int i = 0 ; i < M ; i++) {
			String word = br.readLine();
			
			if(set.contains(word)) check = true;
			set.add(word);
			for(int j = 0; j < N ; j++) {
				
				ts[j].add(word.charAt(j));
			}
		}
		
		int result = ts[N-1].size();
		for(int i = 0; i < N-1 ; i++) {
			result *= ts[i].size();
			
			for(int j = i+1; j < N ; j++) {
				for(char w : ts[j]) {
					
					if(ts[i].contains(w)) check = true;
				}
			}
		}
		
		
		
		
		if(result == M && !check) {
			sb.append("YES").append("\n");
			
			for(int i = 0; i < N ; i++) {
				for(char j : ts[i]) {
					sb.append(j);
				}
				sb.append("\n");
			}
		}
		else {
			sb.append("NO");
		}
		
		System.out.println(sb);
	}
}
