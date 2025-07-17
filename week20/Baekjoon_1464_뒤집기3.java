import java.io.*;
import java.util.*;


/**
 * 시간: 68ms
 * 메모리: 11492KB
 */
public class Baekjoon_1464_뒤집기3 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static char[] word;
	static int[] min;
	static char[] result;
	
	public static void main(String[] args) throws IOException {
		word = br.readLine().toCharArray();
		min = new int[word.length];
		result = new char[word.length];
		
		min[0] = word[0];
		for(int i = 1; i < word.length; ++i) {
			min[i] = Math.min(min[i-1], word[i]);
		}
		
		int lcur = 0, rcur = word.length - 1;
		for(int i = word.length - 1; i >= 0; --i) {
			if(min[i] == word[i]) result[lcur++] = word[i];
			else result[rcur--] = word[i];
		}
		
		System.out.print(result);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}