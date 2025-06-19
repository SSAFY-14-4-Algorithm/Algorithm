import java.io.*;
import java.util.*;


/**
 * 시간: 240ms
 * 메모리: 41,540KB
 */
public class Baekjoon_2812_크게만들기 {
	// IO
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	// variables
	static int N, K;
	
	
    public static void main(String[] args) throws IOException {
    	N = readInt();
    	K = readInt();
    	
    	Deque<Character> stk = new ArrayDeque<Character>();
    	for(char digit : br.readLine().toCharArray()) {
    		while(K > 0 && (!stk.isEmpty() && stk.peekLast() < digit)) {
    			stk.pollLast();
    			--K;
    		}
    		stk.offerLast(digit);
    	}
    	
    	for(int i = 0, n = stk.size() - K; i < n; ++i) sb.append(stk.pollFirst());
    	System.out.print(sb);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}