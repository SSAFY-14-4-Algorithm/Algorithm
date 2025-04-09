import java.util.*;
import java.io.*;
/*
 * 14132kb, 100ms
 */
public class Baekjoon2607 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		int[] fword = new int[26];
		int len = s.length();
		for(int i = 0; i < len; i++) {
			fword[s.charAt(i)-'A'] += 1;
		}
		int cnt = 0;
		for(int i = 0; i < N-1; i++) {
			String w = br.readLine();
			int wlen = w.length();
			if(Math.abs(wlen-len) > 1) continue;
			
			int[] word = new int[26];
			int sCnt = 0;
			
			//같은 것의 개수 찾기,  sCnt = 같은 것의 개수
			for(int j = 0; j < wlen; j++) {
				int idx = w.charAt(j) - 'A';
				if(fword[idx] > word[idx]) sCnt++;
				word[idx]++;
			}
			
			//길이 긴 거 기준으로 같은 거 빼주기
			int diff = len > wlen ? len - sCnt : wlen - sCnt;
			if(diff < 2) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
