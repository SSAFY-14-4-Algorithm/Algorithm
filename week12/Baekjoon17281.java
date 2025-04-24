import java.io.*;
import java.util.*;

/*
 * 메모리: 14,616KB
 * 시간: 884ms
 */
public class Baekjoon17281 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	static final int NUM_PLAYER = 9;
	static final int MAX_N = 50;
	static final int NUM_STONE = 3;
	// variables
	static int N;
	static int[][] hits = new int[MAX_N][NUM_PLAYER];
	
	static int[] perm; // 순열 배열
	static boolean[] isVisited;
	
	static boolean[] stones = new boolean[NUM_STONE];
	static int result = 0; // 최대 점수
	
	static void solution(int[] perm) {
		// 야구 
		int score = 0;
		
		int playerCur = 0;
		int stoneCur = 0;
		for(int e = 0; e < N; ++e) { // e번째 이닝
			// 초기화
			int out = 0;
			Arrays.fill(stones, false);
			
			while(out < 3) {
				int playerId = perm[playerCur];
				int hit = hits[e][playerId];
				
				if(hit == 0) { // 아웃
					++out;
				} else { // 진루
					for(int i = 0; i < hit; ++i) {
						if(stones[stoneCur]) 
							++score;
						stones[stoneCur] = i == 0;
						stoneCur = (stoneCur + 1) % NUM_STONE;
					}
				}
				playerCur = (playerCur + 1) % NUM_PLAYER;
			}
		}
		
		result = Math.max(result, score);
	}
	
	static void nextPermutation(int idx, int size) {
		if(idx == size) { // 기저
			solution(perm);
			return;
		}
		
		if(perm[idx] >= 0) { // 순열의 4번째 원소는 1번 선수로 고정
			nextPermutation(idx + 1, size); 
		} else {
			for(int i = 0; i < size; ++i) {
				if(isVisited[i]) continue;
				
				isVisited[i] = true;
				perm[idx] = i;
				nextPermutation(idx + 1, size);
				perm[idx] = -1;
				isVisited[i] = false;
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < NUM_PLAYER; ++j) {
				hits[i][j] = readInt();
			}
		}
		
		perm = new int[NUM_PLAYER]; // 순열 배열 생성 
		Arrays.fill(perm, -1); 
		perm[3] = 0; // 첫번째 선수가 반드시, 4번 타자
		
		isVisited = new boolean[NUM_PLAYER]; 
		isVisited[0] = true;
		
		nextPermutation(0, NUM_PLAYER);
		System.out.print(result);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}
	