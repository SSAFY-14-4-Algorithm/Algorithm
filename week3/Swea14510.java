import java.util.*;
import java.io.*;
import java.math.*;

public class Swea14510{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] trees = new int[N];
			int maxNum = 0;
			for(int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				maxNum= Math.max(maxNum, trees[i]);
			}
			int one_cnt = 0;
			int two_cnt = 0;
			int answer = 0;
			for(int i = 0; i < N; i++) {
				int target = maxNum - trees[i];
				one_cnt += target%2;
				two_cnt += target/2;
			}
			
			if(two_cnt > one_cnt) {
				while(Math.abs(two_cnt-one_cnt) > 1) {
					two_cnt--;
					one_cnt += 2;
				}
			}
			if(two_cnt > one_cnt) {
				answer = two_cnt*2;
			} else if(one_cnt > two_cnt) {
				answer = one_cnt*2-1;
			} else {
				answer = one_cnt*2;
			}
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
}