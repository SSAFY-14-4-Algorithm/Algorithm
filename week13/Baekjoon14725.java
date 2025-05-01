import java.io.*;
import java.util.*;

/**
 * 개미굴, 14725
 * 메모리: 14,348KB
 * 시간: 104ms
 */
public class Baekjoon14725 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	static final int SIZE = 10_000;	// 단어의 수
	static final int LENGTH = 10;	// 단어의 최대 길이
	static final int ROOT = 0;
	// types
	// variables
	static int size = 0;
	static Map<String, Integer>[] nexts = new Map[SIZE * LENGTH + 1];
	
	
	static void printHierarchy(int root, int indent) {
		for(Map.Entry<String, Integer> entry : nexts[root].entrySet()) {
			for(int i = 0; i < indent; ++i) sb.append("--");
			sb.append(entry.getKey()).append("\n");
			printHierarchy(entry.getValue(), indent + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		nexts[ROOT] = new TreeMap<>();
		
		int N = Integer.parseInt(br.readLine());
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			
			int cur = ROOT;
			while(K-- > 0) {
				String alphabet = st.nextToken();
				int nextKey = nexts[cur].getOrDefault(alphabet, size+1);
				if(nextKey == size+1) { // 노드 생성
					nexts[cur].put(alphabet, ++size);
					nexts[size] = new TreeMap<>();
				}
				cur = nextKey;
			}
		}
		printHierarchy(ROOT, 0);
		System.out.print(sb);
	}
}
