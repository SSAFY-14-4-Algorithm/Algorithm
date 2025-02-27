import java.io.*;
import java.util.*;

public class Baekjoon16938 {
	static int answer;
	static int N, L, R, X;
	static int[] nums;
	
	public static void select(int cnt, int index, int sum, int max, int min) {
		if(sum > R) return;
		if(cnt + N-index < 2) return;
		if(index == N) {
			if(sum >= L && max-min >= X) {
				answer++;
			}
			return;
		}
		select(cnt+1, index+1, sum+nums[index], Math.max(max, nums[index]), Math.min(min, nums[index]));
		select(cnt, index+1, sum, max, min);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		nums = new int[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		answer = 0;
		select(0, 0, 0, 0, Integer.MAX_VALUE);
		System.out.println(answer);
	}
}