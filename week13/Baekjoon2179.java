import java.io.*;
import java.util.*;

/**
 * 비슷한 단어, 2179
 * 메모리: 25,424KB
 * 시간: 124ms
 */
public class Baekjoon2179 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	static final int SIZE = 20_000;
	static final int RANGE = 26;
	static final int LENGTH = 100;
	// types
	static class Node{
		Node[] nexts = new Node[RANGE];
		int conquared;
		
		Node(int no) {this.conquared=no;}
	}
	// variables
	static int N;
	static String[] words = new String[SIZE];
	static Node root = new Node(-1);
	static int maxLength = 0, maxS = 0, maxT = 1;
	 
	
	static void insert(int no, String word) {
		char[] cWord = word.toCharArray();
		
		int lcp = 0;
		Node cur = root;
		for(int i = 0; i < cWord.length; ++i) {
			int chi = cWord[i] - 'a';
			
			if(cur.nexts[chi] == null) {
				cur.nexts[chi] = new Node(no);
			} else {
				int s = cur.nexts[chi].conquared;
				int t = no;
				
				++lcp;
				if(lcp > maxLength
					|| lcp == maxLength && s < maxS
					|| lcp == maxLength && s == maxS && t < maxT) {
					
					maxLength = lcp;
					maxS = s;
					maxT = t;
				}
			}
			cur = cur.nexts[chi];
		}
		maxLength = Math.max(maxLength, lcp);
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) {
			String word = br.readLine();
			words[i] = word;
			insert(i, word);
		}
		System.out.print(sb.append(words[maxS]).append("\n").append(words[maxT]));
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}