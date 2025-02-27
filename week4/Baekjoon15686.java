import java.util.*;
import java.io.*;

public class Baekjoon15686 {
	static int N, M, lenH, lenC;
	static int[][] house, chicken;
	static boolean[] check;
	static int answer;
	public static void select(int cnt, int index) {
		if(cnt == M) {
			int sum = 0;
			for(int i = 0; i < lenH; i++) {
				int x = house[i][0];
				int y = house[i][1];
				int min = Integer.MAX_VALUE;
				for(int j = 0; j < lenC; j++) {
					if(!check[j]) continue;
					min = Math.min(Math.abs(x-chicken[j][0]) + Math.abs(y-chicken[j][1]), min);
				}
				sum += min;
			}
			answer = Math.min(answer, sum);
			return;
		}
		for(int i = index; i < lenC; i++) {
			check[i] = true;
			select(cnt+1, i+1);
			check[i] = false;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		house = new int[2*N][2];
		lenH = 0;
		chicken = new int[13][2];
		lenC = 0;
		check = new boolean[13];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num != 0) {
					if(num == 1) { //1
						house[lenH][0] = i;
						house[lenH++][1] = j;
					} else { //2
						chicken[lenC][0] = i;
						chicken[lenC++][1] = j;
					}
				}
			}
		}
		answer = Integer.MAX_VALUE;
		select(0, 0);
		System.out.println(answer);
	}
}
