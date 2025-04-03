import java.util.*;
import java.io.*;
/*
 * 14200kb, 104ms
 */
public class Baekjoon1138 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		ArrayList<Integer> answer = new ArrayList<>();
		for(int i = N; i >= 1; i--) {
			answer.add(arr[i], i);
		}
		StringBuilder sb = new StringBuilder();
		for(int num : answer) {
			sb.append(num).append(" ");
		}
		System.out.println(sb);
	}
}