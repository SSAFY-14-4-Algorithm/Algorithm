import java.util.*;
import java.io.*;

public class Baekjoon1182 {
	static int answer;
	static int N, S;
	static int[] nums;
	public static void select(int cnt, int index, int sum) {
		if(cnt > 0) {
			if(sum == S) {
				answer++;
			}
		}
		for(int i = index; i < N; i++) {
			select(cnt+1, i+1, sum+nums[i]);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		select(0, 0, 0);
		System.out.println(answer);
	}
}
