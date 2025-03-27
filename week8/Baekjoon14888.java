package study;



import java.io.*;
import java.util.*;

/**
 * 
 * 메모리: 	13944 kb
 * 시간 : 	76 ms
 *
 */


public class Baekjoon14888 {
	
	static int N;
	static long maxResult, minResult;
	
	static int[] arr;
	static int[] calcul;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		maxResult = Long.MIN_VALUE;
		minResult = Long.MAX_VALUE;
		
		
		arr = new int[N];
		calcul = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4 ; i++) {
			calcul[i] = Integer.parseInt(st.nextToken());
		}
		
		combi(1, arr[0]);
		sb.append(maxResult).append("\n").append(minResult).append("\n");

		System.out.println(sb);
	}
	
	public static void combi(int idx, long sum) {
		
		if (idx == N) {
			
			maxResult = Math.max(maxResult, sum);
			minResult = Math.min(minResult, sum);
			return;
		}
		
		
		for(int i = 0; i < 4 ; i++) {
			
			if(calcul[i] == 0) continue;
			
			if(i == 0) {
				calcul[i]--;
				combi(idx+1, sum + arr[idx]);
				calcul[i]++;
			}
			
			if(i == 1) {
				calcul[i]--;
				combi(idx+1, sum - arr[idx]);
				calcul[i]++;
			}
			
			if(i == 2) {
				calcul[i]--;
				combi(idx+1, sum * arr[idx]);
				calcul[i]++;
			}
			
			if(i == 3) {
				calcul[i]--;
				combi(idx+1, sum / arr[idx]);
				calcul[i]++;
			}
			
		}
	}
}
