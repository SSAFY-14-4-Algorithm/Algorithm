import java.io.*;
import java.util.*;

/**
 * 
 * 메모리 11540 kb
 * 시간 68 ms
 *
 * long 좀 신경 써라 병 ㅅ
 *
 */
public class Baekjoon16953 {
    
	static long A, B, result;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        result = Integer.MAX_VALUE;
        
        mothed(A, 1);
        
        if(result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
        
    }
    
    public static void mothed(long sum, long count) {
    	
    	if(sum > B) return;
    	
    	if (sum == B){
    		
    		result = Math.min(result, count);
    	}
    	
    	mothed(sum * 2, count + 1);
    	mothed(sum * 10 + 1, count + 1);
    	
    }
}