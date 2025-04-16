import java.io.*;
import java.util.*;

public class Baekjoon1786 {
	static String t;
	static String p;


	//메모리 54860KB 시간 496ms
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = br.readLine();
		p = br.readLine();

		kmp(t,p);
	}

	static void kmp(String parent, String pattern) {
		int[] table = makeTable(pattern);

		int n1 = parent.length();
		int n2 = pattern.length();

		int begin = 0, matched = 0;
		int cnt = 0;
		StringBuilder sb = new StringBuilder();

		while(begin <= n1 - n2) {
			if(matched < n2 && parent.charAt(begin+matched) == pattern.charAt(matched)) {
				++matched;
				if(matched == n2) {
					sb.append((begin+1) + " ");
					cnt++;
				}
			} else {
				if(matched == 0) {
					++begin;
				} else {
					begin += matched - table[matched-1];
					matched = table[matched-1];
				}
			}
		}
		System.out.println(cnt);
		System.out.println(sb.toString());
	}

	static int[] makeTable(String pattern) {
		int n = pattern.length();
		int[] table = new int[n];
		int idx = 0;
		for (int i = 1; i < n; i++) {
			while(idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			if (pattern.charAt(i) == pattern.charAt(idx)) {
				table[i] = ++idx;
			}
		}

		return table;
	}
}
