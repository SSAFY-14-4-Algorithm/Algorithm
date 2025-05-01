package algorithm;
/*
 * BOJ 2866번 : 문자열 잘라내기
 * 메모리 : 308,140kb
 * 시간 : 1088ms
 */

import java.io.*;
import java.util.*;

public class BOJ2866_문자열잘라내기 {
	static int R, C;
	static char[][] arr;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		cnt = 0;
		for(int r = 0; r < R; r++) {
			String line = br.readLine();
			for(int c = 0; c < C; c++) {
				arr[r][c] = line.charAt(c);
			}
		}
		
		Set<String> set  = new HashSet<>(); // 문자열 중복 검사
		List<String> list = new ArrayList<>(); // 최초로 만들어진 문자 리스트
		cnt = 0;
		
		for(int c = 0; c < C; c++) {
			sb = new StringBuilder();
			for(int r = 0; r < R; r++) {
				sb.append(arr[r][c]); // 세로로 문자열 만들기
			}
			set.add(sb.toString());
			list.add(sb.toString());
		}
		
		if(set.size() != list.size()) { // 처음부터 다르다면(=중복이 있다면) 0 출력
			System.out.println(cnt);
			return;
		}
		
		for(int r = 1; r < R; r++) {
			set = new HashSet<>();
			for(int c = 0; c < C; c++) {
				set.add(list.get(c).substring(r, R)); // 하나씩 빼고 문자열에 추가
				if(c+1 != set.size()) { // 크기 다르다면(=중복이 있다면) cnt 출력
					System.out.println(cnt);
					return;
				}
			}
			cnt++;
		}

		System.out.println(cnt);
		br.close();
	}
}