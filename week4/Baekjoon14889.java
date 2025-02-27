import java.io.*;
import java.util.*;

/**
 * 
 * 스타트팀에 대한 boolean 배열 생성하여 조합 설정
 * 조합 짜면 능력치 차이 구함
 *
 */

public class Baekjoon14889 {
	
	static int N;
	static int[][] map;
	static boolean[] startCheck;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		startCheck = new boolean[N];
		map = new int[N][N];
		for(int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combi(0, 0);
		
		System.out.println(result);
	}
	
	// 조합
	public static void combi(int cnt, int idx) {
		
		if(idx == N) return;
		
		if (cnt == N / 2) {
			
			int sSum = 0;
			int lSum = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = i + 1; j < N ; j++) {
					
					if(startCheck[i] && startCheck[j])
						sSum += (map[i][j] + map[j][i]);
					else if(!startCheck[i] && !startCheck[j])
						lSum += (map[i][j] + map[j][i]);
				}
			}
			
			result = Math.min(result, Math.abs(sSum - lSum));
			return;
		}
		
		startCheck[idx] = true;
		combi(cnt + 1, idx + 1);
		startCheck[idx] = false;
		combi(cnt, idx + 1);
	}
}