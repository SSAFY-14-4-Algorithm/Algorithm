import java.util.*;
import java.io.*;

/*
 * 메모리:18868kb, 160ms
 */
public class Baekjoon25624 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashSet<String> hs = new HashSet<>(); //중복 체크
		boolean flag = true;
		int[] alphabet = new int[N];
		int[] used = new int[26];
		A:for(int i = 0; i < M; i++) {
			String s = br.readLine();
			if(hs.contains(s)) {
				flag = false;
				break;
			}
			hs.add(s);
			for(int j = 0; j < N; j++) {
				char c = s.charAt(j);
				if(used[c-'A'] == 0) {
					used[c-'A'] = j+1;
				} else if(used[c-'A'] != j+1) {
					flag = false;
					break A;
				}
				alphabet[j] |= (1<<(c-'A'));
			}
		}
		int cnt = 1;
		if(flag) {
			for(int i = 0; i < N; i++) {
				cnt *= Integer.bitCount(alphabet[i]);
			}
			if(cnt != M) {
				flag = false;
			}
		}
		if(flag) {
			StringBuilder sb = new StringBuilder();
			sb.append("YES").append("\n");
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < 26; j++) {
					if((alphabet[i] & (1<<j)) != 0) {
						sb.append((char)(j + 'A'));
					}
				}
				sb.append("\n");
			}
			System.out.print(sb);
		} else {
			System.out.println("NO");
		}
	}
}