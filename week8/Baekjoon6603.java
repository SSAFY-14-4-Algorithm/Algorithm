package study;



import java.io.*;
import java.util.*;

/**
 * 
 * 문제 설명 개 어렵게 해놨네 짜증나게 ㄹㅇ 진짜
 * 
 * 
 * 메모리: 	11804 kb
 * 시간 : 	76 ms
 *
 */


public class Baekjoon6603 {
	
	static StringBuilder sb = new StringBuilder();;
	static boolean[] selected;
	static int[] arr;
	static int k;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			
			if(k == 0) break;
			
			selected = new boolean[k];
			
			arr = new int[k];
			
			for(int i = 0; i < k ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			
			subSet(0, 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void subSet(int idx ,int cnt) {
		
		if(cnt == 6) {
			
			for(int i = 0; i < k ; i++) {
				
				if(selected[i]) sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		if(idx == k) return;
		
		selected[idx] =true;
		subSet(idx + 1, cnt + 1);
		selected[idx] =false;
		subSet(idx + 1, cnt);
	}
}
