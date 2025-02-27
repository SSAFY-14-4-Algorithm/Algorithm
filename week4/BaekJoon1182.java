package week4;
import java.util.*;
import java.io.*;

/*
 * 메모리 : 11,500 kb
 * 실행시간 : 76 ms
 */

/*
 * 1. 부분집합(백트래킹)을 통해 모든 경우 수 탐색
 * 2. 재귀가 끝이 났을 경우, 합(S) 조건이 성립하는 지 확인
 */

public class BaekJoon1182 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int[] arr;
	static boolean[] isSelected;
	static int N, S, totalCnt;
	
	// 부분 집합
	private static void subset(int depth, int sum) {
		
		if(depth == N) {
			if(sum == S) totalCnt++;
			return;
		}
	
		subset(depth+1, sum + arr[depth]);
		subset(depth+1, sum);
	}


	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		isSelected = new boolean[N];
		totalCnt = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// S가 0인경우, 아무것도 선택하지 않을 때의 케이스를 포함하지 않아야 하므로 -1
		if(S == 0) totalCnt--;
		subset(0, 0);
		
		System.out.println(totalCnt);
		
	}

}
