import java.io.*;
import java.util.*;

public class Baekjoon1062 {
	static int alpha;
	static int N, K;
	static int n;
	static int answer;
	static int[] words;
	
	public static void select(int cnt, int index) {
		if(cnt == K) {
			int ret = 0;
			for(int i = 0; i < n; i++) {
				if((words[i] & alpha) == words[i]) {
					ret++;
				}
			}
			answer = Math.max(answer, ret);
			return;
		}
		for(int i = index; i < 26; i++) {
			if((alpha & (1<<i)) == 0) {
				alpha |= 1<<i;
				select(cnt + 1, i + 1);
				alpha ^= 1<<i;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if(K < 5) {
			System.out.println(0);
		} else if(K == 26) {
			System.out.println(N);
		} else {
			char[] defaultAlpha = {'a', 'n', 't', 'i', 'c'};
			alpha = 0;
			for(char c : defaultAlpha) {
				alpha |= (1 << c-'a');
			}
			words = new int[N];
			n = 0;
			int count = 0;
			for(int i = 0; i < N; i++) {
				String s = br.readLine();
				int len = s.length();
				if(len == 8) { //antatica인 경우
					count++;
					continue;
				}
				int word = 0;
				for(int j = 0; j < len; j++) { //직접확인해봐야하는 경우
					word |= 1<<(s.charAt(j) - 'a');
				}
				if(word == 532741) { //antic만 있는 경우
					count++;
					continue;
				}
				words[n++] = word;
			}
			answer = 0;
			select(5, 0);
			System.out.println(answer+count);
		}
	}
}