import java.io.*;
import java.util.*;

/*
 * 메모리: 80,936KB
 * 시간: 452ms
 */
public class Baekjoon1786 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	// variables
	static String text, pattern;
	static List<Integer> ans = new ArrayList<>();
	
	static int[] getZ(String T) {
		char[] cT = T.toCharArray();
		int N = cT.length;
		
		int[] z = new int[cT.length];
		int l = 0, r = 0;
		for(int i = 1; i < N; ++i) {
			if(i < r) z[i] = Math.min(r - i, z[i - l]);
			while(i + z[i] < N && cT[z[i]] == cT[i + z[i]]) ++z[i];
			if(i + z[i] > r) {
				l = i;
				r = i + z[i];
			}
		}
		return z;
	}
	
	static void solution() {
		int txtLength = text.length(), patLength = pattern.length();
		int[] z = getZ(pattern+"$"+text);
		
		for(int i = 0; i < txtLength; ++i) {
 			if(z[i + patLength + 1] == patLength) ans.add(i + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		// Input
		text = br.readLine();
		pattern = br.readLine();
		// solution
		solution();
		// Output
		sb.append(ans.size()).append("\n");
		for(int idx : ans) sb.append(idx).append(" ");
		System.out.print(sb);
		
	}
}