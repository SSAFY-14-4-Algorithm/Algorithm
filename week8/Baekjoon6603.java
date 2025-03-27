/*
 * BOJ 6603번 : 로또
 * 메모리 : 11,740kb
 * 시간 : 68ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon6603 {
	static int K;
	static int[] S;
	static boolean[] visited;
	static int[] answer;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			if(K == 0) break;
			S = new int[K];
			visited = new boolean[K];
			answer = new int[6];
			for(int k = 0; k < K; k++) {
				S[k] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(S);
			comb(0, 0);
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	// K개에서 6개 뽑는 조합
	public static void comb(int cnt, int start) {
		if(cnt == 6){
			for(int i = 0; i < 6; i++){
				sb.append(answer[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for(int i = start; i < K; i++) {
			if(!visited[i]){
				visited[i] = true;
				answer[cnt] = S[i];
				comb(cnt+1, i+1);
				visited[i] = false;
			}
		}
	}
}