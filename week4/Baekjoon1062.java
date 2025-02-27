
import java.io.*;
import java.util.*;

/**
 * 26개 불린배열 사용해
 * 
 * 메모리 : 13064 KB
 * 시간 : 252 ms
 * 
 */

public class Baekjoon1062 {
	
    static int N, K;
    static String[] arr;
    static boolean[] visited;
    static int result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new String[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}
		
		visited = new boolean[26];
        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;
        
        K -= 5;
        
        combi(0, 0);
        
        System.out.println(result);
	}
	
	public static void combi(int idx, int start) {
		
		if (idx == K) {
			int count = 0;
			for(String word : arr) {
				boolean check = false;
				for (int i = 4 ; i < word.length() -4 ; i++) {
					if(!visited[word.charAt(i) - 'a']) {
						check = true;
						break;
					}
				}
				if(!check) count++;
			}
			
			result = Math.max(result, count);
			return;
		}
		
		
		for (int i = start ; i < 26 ; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combi(idx + 1, i + 1);
				visited[i] = false;
			}
		}
	}
}