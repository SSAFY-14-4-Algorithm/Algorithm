import java.io.*;
import java.util.*;


/**
 * 시간: 2,364ms
 * 메모리: 439,744KB
 */
public class Baekjoon_7662_이중우선순위큐 {
	// IO
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int T;
	static int N;
	static TreeMap<Integer, Integer> q = new TreeMap<Integer, Integer>(); 
	
    public static void main(String[] args) throws IOException {
    	T = Integer.parseInt(br.readLine());
    	while(T-- > 0) {
    		q.clear();
    		N = Integer.parseInt(br.readLine());
    		while(N-- > 0) {
	    		st = new StringTokenizer(br.readLine());
	    		String cmd = st.nextToken();
	    		int val = Integer.parseInt(st.nextToken());
	    		
	    		switch(cmd) {
	    		case "I": {
	    			q.put(val, q.getOrDefault(val, 0)+1);
	    			break;
	    		} 
	    		case "D": {
	    			if(q.isEmpty()) break;
	    			
	    			if(val == 1) val = q.lastKey();
	    			else val = q.firstKey();
	    				
	    			int cnt = q.get(val);
	    			if(cnt == 1) q.remove(val); 
	    			else if(cnt > 1) q.put(val, q.get(val)-1);
	    			break;
	    		}
	    		}
    		}
    		if(q.isEmpty()) sb.append("EMPTY\n");
    		else sb.append(q.lastKey()).append(" ").append(q.firstKey()).append("\n");
    	}
    	System.out.print(sb);
    }
}