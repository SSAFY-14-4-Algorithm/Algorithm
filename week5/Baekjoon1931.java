package algorithm;
import java.io.*;
import java.util.*;

public class Baekjoon1931 {
	static int N;
	static int[][] time;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		time = new int[N][2];
		
		for(int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[n][0] = Integer.parseInt(st.nextToken());
			time[n][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(time, (o1, o2) -> {
			if(o1[1]==o2[1])
				return o1[0]-o2[0];
			else 
				return o1[1]-o2[1];
		});
//		System.out.print(Arrays.deepToString(time));
		
		int end = 0;
		for(int n = 0; n < N; n++) {
			if(time[n][0] >= end) {
				cnt++;
				end = time[n][1];
			}
		}
		
		System.out.print(cnt);
	}

}