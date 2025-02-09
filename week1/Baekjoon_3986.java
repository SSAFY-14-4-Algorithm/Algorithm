import java.io.*;
import java.util.*;

public class Baekjoon_3986 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
    public static void main(String[] args) throws IOException {
    	int N = Integer.parseInt(br.readLine());
    	int count = 0;
    	for (int i=0;i<N;i++) {
    		Stack<String> stk = new Stack<>();
    		String[] s = br.readLine().split("");
    		for(int j=0;j<s.length;j++) {
    			if(!(stk.empty()) && stk.peek().equals(s[j])) {
    				stk.pop();
    			}
    			else {
    				stk.add(s[j]);
    			}
    		}
    		if(stk.empty()) count++;
    	}
    	System.out.println(count);
    }
}