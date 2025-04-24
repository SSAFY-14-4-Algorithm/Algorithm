/*
* BOJ 16637번 : 괄호 추가하기
* 메모리 : 11,552kb
* 시간 : 68ms
* 
* 숫자, 연산자 분리
* 괄호 없이 연산 수행, 괄호 추가 후 연산 수행
* N=19 -> 연산자 9개
* 2^9 = 512 * 숫자 10개 = 5000번 완탐 가능!!!
 */

import java.io.*;
import java.util.*;

public class Baekjoon16637 {
    static int N;
    static int[] num;
    static boolean[] visited;
    static char[] oper;
    static int maxAnswer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine()); // 수식의 길이

        num = new int[N/2+1];
        visited = new boolean[N/2+1];
        oper = new char[N/2];

        String input = br.readLine();
        num[0] = input.charAt(0) - '0';
        int numIdx = 1;
        int operIdx = 0;

        for (int n = 1; n < input.length(); n++) {
            if(n%2 == 1) {
                oper[operIdx] = input.charAt(n);
                operIdx++;
            } else if(n%2 == 0){
                num[numIdx] = input.charAt(n) - '0';
                numIdx++;
            }
        }
//		System.out.println(Arrays.toString(num));
//		System.out.println(Arrays.toString(oper));

        maxAnswer = Integer.MIN_VALUE;
        dfs(0, num[0]);

        sb.append(maxAnswer);
        System.out.println(sb);
        br.close();
    }

    private static void dfs(int idx, int sum) {
        // 모든 연산자를 다 처리했을 때
        if(idx >= oper.length) {
            maxAnswer = Math.max(maxAnswer, sum);
            return;
        }

        // 괄호 없이 연산 수행
        int res1 = calc(sum, num[idx+1], oper[idx]);
        dfs(idx+1, res1);

        // 괄호를 추가할 수 있는 경우
        if(idx+1 < oper.length) {
            int inSum = calc(num[idx+1], num[idx+2], oper[idx+1]); // 괄호 안의 값 계산
            int res2 = calc(sum, inSum, oper[idx]); // 괄호 계산 결과 포함해서 전체 계산
            dfs(idx+2, res2);
        }
    }

    private static int calc(int a, int b, int op) {
        if(op == '+'){
            return a+b;
        } else if(op == '-'){
            return a-b;
        } else {
            return a*b;
        }
    }
}