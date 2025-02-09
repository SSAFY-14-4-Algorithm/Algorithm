import java.util.*;
import java.math.*;
import java.io.*;

class Baekjoon18115
{	
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> q = new ArrayDeque<>();
		int[] answer = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			q.add(i);
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int cnt = N; cnt >= 1; cnt--) {
			String s = st.nextToken();
			switch(s) {
			case "1":
				answer[q.poll()] = cnt;
				break;
			case "2":
				int temp = q.poll();
				answer[q.poll()] = cnt;
				q.addFirst(temp);
				break;
			case "3":
				answer[q.pollLast()] = cnt;
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
	}
}