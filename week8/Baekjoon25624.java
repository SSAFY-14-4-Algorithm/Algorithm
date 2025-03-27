package AlgorithmStudy.week8;

import java.io.*;
import java.util.*;

public class Baekjoon25624 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int[] alpha;
	static boolean[][] visited; // 각 척도별로 등장한 알파벳 기록 배열
	static int[] checkVisted; // 각 자리의 알파벳 중복 확인 배열
	static Set<String> set = new HashSet<String>();
	static int N, M;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		alpha = new int [N];
		visited = new boolean [N][26];
		checkVisted = new int [26];
		
		// 각 척도별로 등장한 알파벳 기록
		for(int i=0;i<M;i++) {
			String input = br.readLine();
			
			set.add(input); // 입력 값 중 중복 값 제거
			for(int j=0;j<N;j++) {
				int c = input.charAt(j) - 'A';
				
				visited[j][c] = true;
			}
			
		}
		
		// 각 척도의 가능한 알파벳 갯수를 구하고, 그 곱을 계산하며 출력 문자열(sb) 구성
		long sum = 1; 
		for(int i=0;i<N;i++) {
			
			int cnt= 0;
			
			for(int j=0;j<26;j++) {
				if(visited[i][j]) {
					
					cnt++;
					checkVisted[j]++; // 각 척도의 알파벳 중복 체크
					
					char c = (char) (j + 'A'); // 척도 가능한 결과일 경우, 출력을 위한 로직
					sb.append(c);
				}
			}
			
			alpha[i] = cnt;
			sum *= cnt;
			
			sb.append("\n");
		}
		
		if(sum != set.size() || set.size() != M) { // 유일한 결과의 개수가 M과 같고, 가능한 경우의 수(sum)와 일치해야 함
			System.out.println("NO");
			System.exit(0);
		}
		
		// 각 척도의 알파벳 집합이 서로 겹치면 안 됨
		boolean flag = true;
		for(int j=0;j<26;j++) {
			if(checkVisted[j] > 1) {
				flag = false;
				break;
			}
		}
		
		if(!flag) { // 알파벳이 중복된 경우
			System.out.println("NO");
			System.exit(0);
		}
		
		System.out.println("YES");
		System.out.println(sb);

	}

}

