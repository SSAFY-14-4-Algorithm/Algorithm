<<<<<<< HEAD
/*
 * BOJ 1786번 : 찾기
 * 메모리 : 67,340kb
 * 시간 : 388ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon1786 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		int tLength = text.length;
		int pLength = pattern.length;

		// 1. 부분 일치 테이블 만들기
		int[] pi = new int[pLength];

		for(int i=1, j=0; i < pLength; i++) {
			// 두 포인터의 위치에서 불일치가 발생하면 맞은 직전위치의 정보를 활용해서 불필요한 비교 줄임
			while(j>0 && pattern[i] != pattern[j]) {
				j = pi[j-1];
			}

			// 현재 i 위치의 부분문자열의 접미사와 접두사가 일치하는 패턴의 최장길이 저장
			if(pattern[i] == pattern[j]) { // j 위치까지 맞은 상황
				pi[i] = ++j; // j 위치까지 맞으면 j+1 길이만큼 맞은 것, 후에 j 이동
			}
		}
//		System.out.println(Arrays.toString(pi));

		// 2. 패턴 찾기
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<>();

		for(int i=0, j=0; i < tLength; i++) {
			while(j > 0 && text[i] != pattern[j]) {
				j = pi[j-1];
			}

			if(text[i] == pattern[j]) {
				if(j == pLength-1) { // 일치가 발생한 위치가 패턴의 끝이면
					cnt++; // 패턴 찾았으므로 카운트 증가
					list.add(i-j+1); // 시작 위치, 문제에서는 1부터 위치 카운트
					j = pi[j];
				} else {
					j++;
				}
			}
		}

		sb.append(cnt).append("\n");
		if(cnt > 0) {
			for(int result : list) {
				sb.append(result).append(" ");
			}
		}

		System.out.print(sb);
		br.close();
	}
}
=======

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 각 지점을 중심으로 dfs 아래, 왼쪽, 오른쪽 방향으로만 dfs 탐색 depth:4로
 * 	- 탐색 시 최대값을 발견한다면 갱신
 * 탐색한 지점은 방문 처리
 * 
 * 
 * 
 * 가운데 모양은 따로 탐색 필요
 * 
 * 
 * @author SSAFY
 *
 */
public class Main_B_14500_테트로미노_연민호 {
	
	//하좌우상
	final static int[] dr = {1, 0, 0, -1};
	final static int[] dc = {0, -1, 1, 0};
	
	
	
	static int N;	//세로 크기
	static int M;	//가로 크기
	
	static int[][] map;	//숫자판 정보
	
	static boolean[][] visited;	//방문체크
	
	static int max;	//최댓값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//탐색의 편이를 위해 공백을 넣어놓음
		map = new int[N+2][M+2];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		visited = new boolean[N+2][M+2];
		
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				visited[i][j] = true;
				dfs(i,j, map[i][j], 1);
				visited[i][j] = false;
				
				//가운데 블록 기준 3개 블록이 붙어있는 테트로미노 탐색
				etc(i, j, map[i][j]);
			}
		}
		
		System.out.println(max);
	}

	//가운데 블록 기준 3개 블록이 붙어있는 테트로미노 탐색
	private static void etc(int r, int c, int sum) {
		
		//(r,c) 기준 4방의 값을 모두 더함
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			sum+= map[nr][nc];
		}
		
		//4방의 값을 하나씩 빼보면서 해당 값이 최댓값이라면 갱신
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			max = Math.max(max, sum-map[nr][nc]);
		}
	}

	/**
	 * 블록하나를 선택하고 다음 블록 선택은 재귀호출로 넘김
	 * @param r
	 * @param c
	 * @param sum	//선택한 블록의 숫자 합
	 * @param cnt	//선택한 블록의 개수
	 */
	private static void dfs(int r, int c, int sum, int cnt) {

		//step01. 4개의 블록 선택 완료
		if(cnt==4) {
			//step 02. 선택한 블록 숫자의 합이 최댓값이라면 갱신
			max = Math.max(sum, max);
			return;
		}
		
		//윗 방향을 제외한 3방향으로 이동하며 블록 선택
		for(int d=0; d<3; d++) {
			
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			//경계를 벗어나거나 이미 방문했다면 다음 방향
			if(nr>N || nc<1 || nc>M || visited[nr][nc]) continue;
			
			visited[nr][nc] = true;
			dfs(nr, nc, sum+map[nr][nc], cnt+1);
			visited[nr][nc] = false;
		}
		
	}
	
	

}
>>>>>>> aaaa1fa11e6dbe8a113a7bba199a43dae92fad38
