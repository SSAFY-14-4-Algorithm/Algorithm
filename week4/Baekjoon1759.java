import java.util.*;
import java.io.*;

public class Baekjoon1759 {
	static int L, C;
	static int[] aeiou;
	static int[] arr;
	static boolean[] alpha;
	static int[] defaultAEIOU;
	static StringBuilder sb;
	public static void select(int cnt, int index) {
		if(cnt == L) {
			int count = 0;
			for(int i : defaultAEIOU) {
				if(alpha[i]) {
					count++;
				}
			}
			if(count >= 1 && L-count >= 2) {
				for(int i = 0; i < 26; i++) {
					if(alpha[i]) {
						sb.append((char)('a' + i));
					}
				}
				sb.append("\n");
			}
		}
		for(int i = index; i < C; i++) {
			alpha[arr[i]] = true;
			select(cnt+1, i+1);
			alpha[arr[i]] = false;
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		//0 4 8 14 20
		aeiou = new int[5];
		int n = 0;
		arr = new int[C];
		st = new StringTokenizer(br.readLine());
		defaultAEIOU = new int[]{0, 4, 8, 14, 20};
		for(int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0) - 'a';
			for(int j : defaultAEIOU) {
				if(arr[i] == j) {
					aeiou[n++] = arr[i];
					break;
				}
			}
		}
		alpha = new boolean[26];
		Arrays.sort(arr);
		select(0, 0);
		System.out.print(sb);
	}
}
