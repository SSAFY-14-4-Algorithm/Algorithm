
import java.io.*;
import java.util.*;

/**
 * 1. N개중 2개 이상 선택
 * 2.  L <= sum <= R
 * 3. MAX - MIN >= X
 * 
 * 메모리 : 11804 KB
 * 시간 : 68 ms
 * 
 */


public class Baekjoon16938 {
	
	static int N, L, R, X;
	static int[] arr;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		combi(0, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
		System.out.println(result);
	}
	public static void combi(int idx, int cnt, int sum, int max, int min) {
		
		if(sum > R) return;
		
		if(idx == N && cnt >= 2) {
			
			if(max - min >= X && sum >= L) result++;
			return;
		}
		
		if(idx == N) return;
		
		
		combi(idx + 1, cnt + 1, sum + arr[idx], Math.max(max, arr[idx]), Math.min(min, arr[idx]));
		combi(idx + 1, cnt, sum, max, min);
	}
}
