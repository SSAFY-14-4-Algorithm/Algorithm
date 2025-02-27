package week4;
import java.util.*;
import java.io.*;

/*
 * 1. 고정 단어 제외 후 배열 삽입
 *   - "anta" "tica"
 * 2. 고정 단어에 존재하는 글자 방문처리
 *   - a n t c i
 * 3. 알파벳 조합을 이용하여 읽을 수 있는 단어 카운트
 * 4. 최댓값 갱신
 */ 

public class BaekJoon1062 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static String[] words;
	static boolean[] visited;
	static int N, K, totalCnt;
	
	private static void combination(int depth, int start) {
		
		if(depth == K-5) {
			int cnt = 0;

			for(int i=0;i<N;i++) { // 단어
				boolean readable = true; // 단어를 읽을 수 있는 지 체크
				
				for(int j=0;j<words[i].length();j++) { // 단어 글자 
					char c = words[i].charAt(j);
					
					if(!visited[c - 'a']) { // 배운 글자가 없어 읽을 수 없음
						readable = false;
						break;
					}
				}
				
				if(readable) cnt++;
			}
			
			totalCnt = Math.max(totalCnt, cnt);
			
			return;
		}
		
		
		for(int i=start;i<26;i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			combination(depth+1, i+1);
			visited[i] = false;
		}

	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		totalCnt = Integer.MIN_VALUE;
		
		// 글자 수(K)가 5보다 작거나 26인 경우, 예외 처리
		if(K < 5) {
			System.out.println("0");
			return;
		}else if(K == 26) {
			System.out.println(N);
			return;
		}
		
		words = new String [N];
		visited = new boolean [26];

		for(int i=0;i<N;i++) {
			String str = br.readLine();
			words[i] = str.substring(4, str.length()-4);
		}
		
		// "anta" "tica" 알파벳 방문 처리
		visited['a' - 'a'] = true;
		visited['n' - 'a'] = true;
		visited['t' - 'a'] = true;
		visited['i' - 'a'] = true;
		visited['c' - 'a'] = true;
	
		combination(0,0);
	
		System.out.println(totalCnt);
	}

}
