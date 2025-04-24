import java.util.*;
import java.io.*;
/*
 * 14284kb, 112ms
 */
public class Baekjoon16637 {
	static char[] c;
	static int N;
	static boolean[] selected;
	static int max;
	static Deque<Integer> q;
	public static void select(int idx) {
		q.clear();
		for(int i = 0; i < N; i++) {
			//연산자 값 넣어주기
			if(!(c[i] - '0' <= 9 && c[i]-'0' >= 0)) {
				switch(c[i]) {
				case '+':
					q.addLast(0);
					break;
				case '-':
					q.addLast(1);
					break;
				case '*':
					q.addLast(2);
					break;
				}
				continue;
			}
			int ret = c[i] - '0';
			//괄호 있는 값 계산
			if(selected[i]) {
				int num1 = c[i]-'0';
				int num2 = c[i+2]-'0';
				switch(c[i+1]) {
				case '+':
					ret = num1+num2;
					break;
				case '-':
					ret = num1-num2;
					break;
				case '*':
					ret = num1*num2;
					break;
				}
				i+=2;
			}
			//나머지 계산
			if(!q.isEmpty()) {
				int oper = q.pollLast();
				int num1 = q.pollLast();
				int retNum = 0;
				switch(oper) {
				case 0:
					retNum = num1+ret;
					break;
				case 1:
					retNum = num1-ret;
					break;
				case 2:
					retNum = num1*ret;
					break;
				}
				q.add(retNum);
			} else {
				q.add(ret);
			}
		}
		max = Math.max(q.pollLast(), max);
		
		for(int i = idx; i < N-2; i+=2) {
			selected[i] = true;
			select(i+4);
			selected[i] = false;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		c = new char[N];
		for(int i = 0; i < N; i++) {
			c[i] = s.charAt(i);
		}
		selected = new boolean[N];
		q = new ArrayDeque<>();
		max = Integer.MIN_VALUE;
		select(0);
		System.out.println(max);
	}
}
