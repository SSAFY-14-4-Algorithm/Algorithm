import java.io.*;
import java.util.*;
/*
 * 메모리:35428KB
 * 시간:	240ms
 */
public class Baekjoon2467 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		int[] liquid = new int[n];
		String[] s = br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			liquid[i] = Integer.parseInt(s[i]);
		}
		//포인터 2개 잡고
		int left = 0;
		int right = n-1;
		int[] result = new int[] {0,0,Integer.MAX_VALUE};
		
		//두 포인터가 만날때까지 반복
		while(left!=right)	{
			int sum = liquid[left] + liquid[right];
			
			//두 포인터 위치 합 절대값이 작으면 결과값에 저장
			if(Math.abs(sum)<Math.abs(result[2]))
				result = new int[] {liquid[left],liquid[right],sum};
			//0보다 작으면 왼쪽포인터 오른쪽으로 이동
			if(sum<0) {
				left++;
			}
			//크면 오른쪽포인터 왼쪽으로 이동
			else
				right--;
		}
		System.out.println(result[0] +" "+ result[1]);
	}
}
