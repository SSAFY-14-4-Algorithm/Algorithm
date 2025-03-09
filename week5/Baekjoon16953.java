/*
 * 백준 16953번 : A->B
 * 메모리 : 11,532kb
 * 시간 : 68ms
 */

package algorithm;

import java.io.*;
import java.util.*;

public class Baekjoon16953 {
	static int A, B;
	static int minCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		minCnt = 1;
		while(B>A){
			if(B%2 == 0) {
				B /= 2;
			} else if(B%10 == 1) {
				B /= 10;
			} else { // 끝자리가 1이 아닌 홀수일 때 탈출 
				minCnt = -1;
				break;
			}
			minCnt++;
		}
		
		if(B==A) {
			sb.append(minCnt);
		} else { 
			sb.append(-1);
		}
		
		System.out.println(sb);
		br.close();
	}
}
