import java.util.*;
import java.math.*;
import java.io.*;

class Baekjoon5430
{	
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		Deque<String> deq = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		for(int testCase = 0; testCase < T; testCase++) {
			boolean reverseCheck = false;
			boolean deleteCheck = false;
			char[] arrC = br.readLine().toCharArray();
			int N = Integer.parseInt(br.readLine());
			String s = br.readLine();
			if(N > 0) {
				String[] arr = s.substring(1, s.length()-1).split(",");
				for(int i = 0; i < N; i++) {
					deq.add(arr[i]);
				}
			}
			
			
			A: for(char c : arrC) {
				switch(c) {
				case 'R':
					reverseCheck = !reverseCheck;
					break;
				case 'D':
					if(!deq.isEmpty()) {
						if(reverseCheck) {
							deq.pollLast();
						} else {
							deq.poll();
						}
					} else {
						deleteCheck = true;
						break A;
					}
					break;
				}
			}
			
			
			if(deleteCheck) {
				sb.append("error").append("\n");
			} else {
				int len = deq.size();
				sb.append("[");
				if(reverseCheck) {
					for(int i = 0; i < len; i++) {
						if(i != len-1) {
							sb.append(deq.pollLast()).append(",");
						} else {
							sb.append(deq.pollLast());
						}
					}
				} else {
					for(int i = 0; i < len; i++) {
						if(i != len-1) {
							sb.append(deq.poll()).append(",");
						} else {
							sb.append(deq.poll());
						}
					}
				}
				sb.append("]").append("\n");
			}
			
			
			deq.clear();
		}
		System.out.print(sb);
	}
}