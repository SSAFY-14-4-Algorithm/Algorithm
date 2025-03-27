package ct2;
import java.util.*;
import java.io.*;
/*
 * 14304kb, 108ms
 */
class Baekjoon14888
{	
	static int[] oper = new int[4];
	static int[] num;
	static int N;
	static int maxAns = Integer.MIN_VALUE;
	static int minAns = Integer.MAX_VALUE;
	public static void Calc(int cnt, int cal) {
		if(cnt == N) {
			maxAns = Math.max(maxAns, cal);
			minAns = Math.min(minAns, cal);
			return;
		}
		for(int i = 0; i < 4; i++) {
			if(oper[i] >= 1) {
				oper[i]--;
				Calc(cnt+1, calculate(cal, num[cnt], i));
				oper[i]++;
			}
		}
	}
	
	public static int calculate(int a, int b, int op) {
		if(op == 0){
			return a + b;
		} else if(op == 1) {
			return a - b;
		} else if(op == 2) {
			return a * b;
		} else {
			return a / b;
		}
	}
	
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}
		Calc(1, num[0]);
		System.out.println(maxAns);
		System.out.println(minAns);
	}
}