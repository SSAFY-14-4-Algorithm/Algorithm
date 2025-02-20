import java.io.*;
import java.util.*;

public class Swea14510{
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int test = 1; test<=T;test++) {
			int N = Integer.parseInt(br.readLine());
			int [] trees = new int[N];
			int max =0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=0;i<N;i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				if(trees[i]>max) max=trees[i];
			}
			int odd = 0;
			int even = 0;
			
			int res =0;
			
			for(int i=0;i<N;i++) {
				int diff = max-trees[i];
				odd+=diff%2;
				even+=diff/2;
			}
			
			if(even>odd) {
				while(Math.abs(even-odd)>1) {
					even--;
					odd+=2;
				}
			}
			if(odd>even) res=odd*2-1;
			else if(even>odd) res = even*2;
			else res = odd*2;
			sb.append("#").append(test).append(" ").append(res).append("\n");
			
		}
		System.out.print(sb);
		
	}
	
}
