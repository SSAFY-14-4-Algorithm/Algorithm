import java.io.*;
import java.util.*;
/*
 * 메모리: 15848KB
 * 시간: 116ms
 * 
 * N명,한 줄 
 * 큰 사람이 왼쪽에 몇 명인지 
 */

public class Main{
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int N = Integer.parseInt(br.readLine());
    	
    	int[] info = new int[N+1];
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for(int i=1;i<N+1;i++) {
    		info[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	int[] res = new int[N+1];
    	
    	for(int i=1;i<N+1;i++) {
            int num = info[i];
    		for(int j=1;j<N+1;j++) {
    			if(res[j]==0) {
    				if(num ==0) {
    					res[j] = i;
    					break;
    				}
    				num--;
    			}
    		}
    	}
    	
    	for(int i=1;i<N+1;i++) {
    		System.out.print(res[i]+" ");
    	}
    
    	
    }
}
