import java.util.*;
import java.io.*;
/* 찾기
 * 43228kb, 472ms
 */
public class Baekjoon1786 {
	static int[] failarr;
	static int[] answer;
	static int alen;
	public static void failure_function(char[] P) {
		int n = P.length;
		//i는 비교되는 문자열, j는 비교하는 패턴 문자열의 인덱스
		int j = 0;
		for(int i = 1; i < n; i++) {
			while(j > 0 && P[i] != P[j]) {
				j = failarr[j-1]; 
				//내 앞 일치 갯수에 따른 접두사 접미사 일치 갯수를 이용해 인덱스 이동
			}
			if(P[i] == P[j]) {
				failarr[i] = ++j;
			}
		}
	}
	
	public static void KMP(char[] T, char[] P) {
		int n = T.length;
		int m = P.length;
		//i는 비교되는 문자열, j는 비교하는 패턴 문자열의 인덱스
		int j = 0;
		for(int i = 0; i < n; i++) {
			while(j > 0 && T[i] != P[j]) {
				j = failarr[j-1];
			}
			if(T[i] == P[j]) {
				if (j == m - 1) {
					answer[alen++] = i-m+2;
					j = failarr[j];
				} else {
					j++;
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] T = br.readLine().toCharArray();
		char[] P = br.readLine().toCharArray();
		failarr = new int[P.length+1];
		answer = new int[T.length+1];
		failure_function(P);
		KMP(T, P);
		StringBuilder sb = new StringBuilder();
		
		sb.append(alen).append("\n");
		for(int i = 0; i < alen; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
	}
}
