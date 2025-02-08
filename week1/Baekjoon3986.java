package week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Baekjoon3986 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int result = 0;
		Stack<String> stack;
		
		for(int i=0;i<n;i++) {
			stack = new Stack<>();
			String str = br.readLine();
			
			for(String s: str.split("")) {
				if(!stack.isEmpty()) {
					String p = stack.peek();
					if(p.equals(s)) stack.pop();
					else stack.add(s);
				}
				else {
					stack.add(s);
				}
			}
			 
			if(stack.isEmpty()) result++;
		}
		bw.write(result+"");
		bw.flush();
		bw.close();
	}
}
