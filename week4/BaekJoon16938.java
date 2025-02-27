package week4;
import java.util.*;
import java.io.*;

/*
 * 1. 부분집합으로 문제  뽑기
 * 2. 조건 확인
 *   - 문제가 2개 미만인 경우는 제외
 *   - 모든 문제 난이도 합의 최저와 최고
 *   - 가장 쉬운 문제의 난이도와 가장 어려운 문제의 난이도 차이
 */

public class BaekJoon16938 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int[] question;
	static boolean[] visited;
	static int N, L, R, X, totalCnt;
	
	private static void subset(int depth, int Cnt, int sum, int max, int min) {
		
		if(depth == N) {
			if(Cnt < 2 || sum < L || R < sum || (max - min) < X) return;
			
			totalCnt++;
			
			return;
		}
		
		subset(depth + 1, Cnt + 1, sum + question[depth], Math.max(max, question[depth]), Math.min(min, question[depth]));
		subset(depth + 1, Cnt, sum, max, min);	
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		totalCnt = 0;
		question = new int[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			question[i] = Integer.parseInt(st.nextToken());
		}
		
		subset(0, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		System.out.println(totalCnt);
		
	}

}