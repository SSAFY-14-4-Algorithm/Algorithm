import java.io.*;
import java.util.*;

public class Baekjoon1182 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int result = 0;
	static int[] nums;
	public static void main(String[] args) throws IOException{
		String[] s1 = br.readLine().split(" ");
		int n = Integer.parseInt(s1[0]);
		int s = Integer.parseInt(s1[1]);
		String[] s2 = br.readLine().split(" ");
		nums = new int[n];
		for(int i=0;i<n;i++) {
			nums[i] = Integer.parseInt(s2[i]);
		}
		int sum=0;
		// s가 0일때 공집합을 고르면 0이 되므로 하나 빼준다
		if(s==sum) result--;
		SumS(0,n,s,sum);
		System.out.println(result);
	}
	public static void SumS(int cnt,int n,int s,int sum) {
		if(cnt==n) {
			if(sum==s) result++;
			return;
		}
		//선택했을때
		SumS(cnt+1,n,s,sum+nums[cnt]);
		//선택하지 않았을때
		SumS(cnt+1,n,s,sum);		
	}
}