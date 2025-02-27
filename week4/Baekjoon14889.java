import java.util.*;
import java.io.*;

public class Baekjoon14889 {
	static boolean[] peoples;
	static int N;
	static int[][] stat;
	static int answer;
	public static void select(int cnt, int index) {
		if(cnt == N/2) {
			int tf = 0;
			int ff = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(peoples[i] == peoples[j]) {
						if(peoples[i]) tf += stat[i][j];
						else ff += stat[i][j];
					}
				}
			}
			answer = Math.min(answer, Math.abs(tf-ff));
			return;
		}
		if(index == N) return;
		peoples[index] = true;
		select(cnt+1, index+1);
		peoples[index] = false;
		select(cnt, index+1);
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		stat = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				stat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		peoples = new boolean[N];
		peoples[0] = true;
		answer = Integer.MAX_VALUE;
		select(1, 1);
		System.out.println(answer);
	}

}
