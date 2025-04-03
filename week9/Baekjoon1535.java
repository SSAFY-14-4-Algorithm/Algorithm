import java.io.*;
import java.util.*;
/*
 * 메모리: 14468KB
 * 시간: 128ms
 * 
 * N(N<=20)
 * 체력 100 -L[i], 0 혹은 음수시 기쁨도 0 
 * 기쁨 0 + J[i]
 * 주어진 체력 내 최대한의 기쁨 
 * 
 */

public class Main{
	static int N;
	static int[] L,J;
	static int Mjoy = 0;
    public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   
    N = Integer.parseInt(br.readLine());
    L = new int[N];
    J = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=0;i<N;i++) {
    	L[i] = Integer.parseInt(st.nextToken());
    }
    st = new StringTokenizer(br.readLine());
    for(int i=0;i<N;i++) {
    	J[i] = Integer.parseInt(st.nextToken());
    }
    
    dfs(0,100,0);
    
    
    System.out.print(Mjoy);
    }
    
    static void dfs(int per, int cnt , int joy) {
    	if(cnt <=0) return;
    	if(per == N) {
    		Mjoy = Math.max(Mjoy,joy);
    		return;
    	}
    	
    	dfs(per+1,cnt,joy); 
    	
    	if(cnt-L[per]>0) {
    		dfs(per+1,cnt-L[per],joy+J[per]);
    	}
    	
    }
    
}
