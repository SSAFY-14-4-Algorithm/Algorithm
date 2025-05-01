package week13;

import java.io.*;
import java.util.*;

/**
 * 그냥 set넣어서 중복 체크하면 오래걸림
 * 이분 ㄱ
 * 
 *  메모리 : 47004
 *  시간 : 324
 */

public class Baekjoon11055 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int R = Integer.parseInt(split[0]);
		int C = Integer.parseInt(split[1]);
		String str;
		int result= 0;
		char[][] map = new char[R][C];
		for (int i = 0; i < R; i++) {
			str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		String[] ing = new String[C];
		StringBuilder sb;
		for(int i=0;i<C;i++) {	
			sb = new StringBuilder();
			
			for(int j=1;j<R;j++) {
				sb.append(map[j][i]);
			}
			
			ing[i] = sb.toString();
		}
		
		
		HashSet<String> set;
		int end = 0;
		int start = R-1;
		while(end <= start) {
			
			int mid = (start+end)/2;
			set = new HashSet<>();
			
			for(int j=0;j<C;j++) { 
				set.add(ing[j].substring(mid));
			}
			
			if(set.size()==C) {
				end = mid + 1;
			}
			else {
				start = mid - 1;
			}
		}
		
		System.out.println(end);
	}
}