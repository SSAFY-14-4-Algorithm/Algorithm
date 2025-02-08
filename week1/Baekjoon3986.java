import java.util.*;
import java.math.*;
import java.io.*;

class Baekjoon3986
{	
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<Character> q = new ArrayDeque<>();
		int answer = 0;
		for(int i = 0; i < N; i++) {
			char[] s = br.readLine().toCharArray();
			for(char c : s) {
				if(!q.isEmpty() && q.peekLast() == c) {
					q.pollLast();
				} else {
					q.add(c);
				}
			}
			if(q.isEmpty()) {
				answer += 1;
			}
			q.clear();
		}
		System.out.println(answer);
	}
}