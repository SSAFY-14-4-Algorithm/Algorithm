package ct2;
import java.util.*;
import java.io.*;
/*
 * 메모리:14316kb, 시간:112ms
 */
public class Baekjoon6603 {
	static StringBuilder sb;
	static int[] arr;
	static int[] selectArr;
	static int k;
	public static void select(int cnt, int idx) {
		if(cnt == 6) {
			for(int i = 0; i < 6; i++) {
				sb.append(selectArr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i = idx; i < k; i++) {
			selectArr[cnt] = arr[i];
			select(cnt+1, i+1);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if(k == 0) break;
			arr = new int[k];
			for(int i = 0; i < k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			selectArr = new int[6];
			select(0, 0);
			sb.append("\n");
		}
		System.out.print(sb);
	}

}