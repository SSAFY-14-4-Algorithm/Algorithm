import java.io.*;
import java.util.*;

public class Baekjoon16938 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] problem;
	static int n;
	static int l;
	static int r;
	static int x;
	static int result;
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		l = Integer.parseInt(s[1]);
		r = Integer.parseInt(s[2]);
		x = Integer.parseInt(s[3]);
		String[] s2 = br.readLine().split(" ");
		problem = new int[n];
		for(int i=0;i<n;i++) {
			problem[i] = Integer.parseInt(s2[i]);
		}
		SelectProblem(0,0,0,Integer.MAX_VALUE,Integer.MIN_VALUE);
		System.out.println(result);
	}
	public static void SelectProblem(int cnt,int sum,int count,int min,int max) {
		//합이 sum보다 크면 그다음 가지 안봐도 됨
		if(sum>r) {
			return;
		}
		//다 선택하면 주어진 조건에 맞을시 result++
		if(cnt==n) {
			if(count>=2 && sum>=l && max-min>=x) {
				result++;
			}
			return;
		}
		//선택했을때
		SelectProblem(cnt+1,sum+problem[cnt],count+1,Math.min(min,problem[cnt]),Math.max(max,problem[cnt]));
		//선택하지 않았을때 
		SelectProblem(cnt+1,sum,count,min,max);
		
	}
}
