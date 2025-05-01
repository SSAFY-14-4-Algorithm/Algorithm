import java.io.*;
import java.util.*;

/**
 * 전화번호 목록, 5052
 * 메모리: 37,936KB
 * 시간: 240ms
 */
public class Beakjoon5052 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	static final int SIZE = 10_000;	// 단어의 수
	static final int LENGTH = 10;	// 단어의 최대 길이
	static final int RANGE = 10;	// 문자(알파벳)의 수
	static final int ROOT = 0;
	static final int[] DEFAULT_NEXTS = new int[RANGE];
	// types
	// variables
	static int size = 0;
	static int[][] nexts = new int[SIZE * LENGTH + 1][RANGE];
	static boolean[] isTerminals = new boolean[SIZE * LENGTH + 1];
	// (트라이의 최대 노드 수) = (단어의 수 * 단어 당 최대 문자의 수)
	
	static boolean insert(char[] word) {
		int cur = ROOT;
		for(char ch : word){
			int digit = ch & 0x0F;
			if(nexts[cur][digit] == 0) {
				nexts[cur][digit] = ++size;
			}
			// 이미 트라이에 존재하어 단어가, 현재 삽입하는 단어의 접두사인지 확인
			if(isTerminals[nexts[cur][digit]]) return false;
			
			cur = nexts[cur][digit];
		}
		
		isTerminals[cur] = true;
		// 현재 삽입하는 단어가 이미 트라이에 존재하는 단어의 접두사인지 확인
		return size == cur; 
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			// 초기화: 이전에 사용했던 자원 초기화
			for(int i = 0; i <= size; ++i) {
				// Arrays.fill(nexts[i], 0); 보다 빠르다.
				System.arraycopy(DEFAULT_NEXTS, 0, nexts[i], 0, RANGE);
			}
			Arrays.fill(isTerminals, 0, size+1, false);
			size = 0;
			
			// Solution
			int N = Integer.parseInt(br.readLine());
			boolean pass = true;
			while(N-- > 0) {
				char[] word = br.readLine().toCharArray();
				pass = pass && insert(word); // 조기 종료
			}
			sb.append(pass ? "YES\n" : "NO\n");
		}
		System.out.print(sb);
	}
}
