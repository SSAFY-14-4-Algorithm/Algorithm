import java.io.*;
import java.util.*;
/*
 * 메모리:17684KB	
 * 시간: 372ms
 */
public class Baekjoon14888 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] operands;
	static int[] operators;	
	static boolean[] visited;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;	
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		operands = new int[n];
		for(int i=0;i<n;i++) {
			operands[i] = Integer.parseInt(s[i]);
		}
		String[] s2 = br.readLine().split(" ");
		int[] op = new int[4];
		for(int i=0;i<4;i++) {
			op[i] = Integer.parseInt(s2[i]);
		}
		operators = new int[n-1];
		visited = new boolean[n-1];
		int idx=0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<op[i];j++) {
				operators[idx] = i;
				idx++;
			}
		}
		Permutation(0,n-1,n-1,operands[0]);
		System.out.println(max);
		System.out.println(min);
	}
	public static void Permutation(int depth, int n, int r, int sum) {
		if(depth == r) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		for(int i=0;i<n;i++) {
			if(!visited[i]) {
				visited[i] = true;
				if(operators[i]==0) {
					Permutation(depth+1,n,r,sum+operands[depth+1]);					
				}
				else if(operators[i]==1)
					Permutation(depth+1,n,r,sum-operands[depth+1]);
				else if(operators[i]==2)
					Permutation(depth+1,n,r,sum*operands[depth+1]);
				else {
					if(sum<0)
						Permutation(depth+1,n,r,0 - (Math.abs(sum)/operands[depth+1]));
					else
						Permutation(depth+1,n,r,sum/operands[depth+1]);
				}					
				visited[i] = false;
			}
		}
	}
}
