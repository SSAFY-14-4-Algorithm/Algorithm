package AlgorithmStudy.week8;

import java.util.*;
import java.io.*;

/*
 * 메모리 : 13,528 kb
 * 실행 시간 : 124 ms
 */

public class Baekjoon14888 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int[] operator;
	static int[] nums;
	static int N, max, min;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		nums = new int [N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		operator = new int [4];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		DupPermutation(0, new int [N-1], new int[4]);
		
		System.out.println(max);
		System.out.println(min);
		
	}
	private static void DupPermutation(int depth, int[] result, int[] cntOper) {
		if(depth == N-1) {
			
			for(int i=0;i<4;i++) {
				if(cntOper[i] != operator[i]) return;
			}
			
			int preNum = nums[0];
			
			for(int i=0;i<N-1;i++) {
				if(result[i] == 0) { // 더하기
					preNum += nums[i+1];
				}else if(result[i] == 1) { // 빼기
					preNum -= nums[i+1];
				}else if(result[i] == 2) { // 곱셈
					preNum *= nums[i+1];
				}else if(result[i] == 3) { // 나눗셈
					if(preNum == 0) continue;
					preNum /= nums[i+1];
				}
			}
			
			max = Math.max(preNum, max);
			min = Math.min(preNum, min);
			
			return;
		}
		for(int i=0;i<4;i++) {
			result[depth] = i;
			cntOper[i]++;
			DupPermutation(depth+1, result, cntOper);
			cntOper[i]--;
		}
	}

}
