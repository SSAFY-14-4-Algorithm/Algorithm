import java.io.*;
import java.util.*;
/*
 * 메모리: 55504
 * 시간: 352
 * 
 * hashmap, 이분탐색
 * 
 * 
 */
public class Main {
	static int R,C;
	static char[][] words;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        words = new char[R][C];
        
        for(int i=0;i<R;i++) {
        	String line = br.readLine();
        	for(int j=0;j<C;j++) {
        		words[i][j] = line.charAt(j);
        	}
        }
        
        
       int l=0,r=R,cnt=0;
       
       while(l<=r) {
    	   int m = (l+r)/2;
    	   if(check(m)) {
    		   cnt=m;
    		   l = m+1;
    	   }
    	   else {
    		   r=m-1;
    	   }
       }
       System.out.print(cnt);
       
    }
    
    static boolean check(int mid) {
    	Set<String> set = new HashSet<>();
    	for(int col = 0;col<C;col++) {
    		StringBuilder sb = new StringBuilder();
    		for(int row = mid;row<R;row++) {
    			sb.append(words[row][col]);
    		}
    		if(!set.add(sb.toString())) {
    			return false;
    		}
    		
    	}
    	return true;
    }
}

