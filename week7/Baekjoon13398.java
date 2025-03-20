import java.io.*;
import java.util.*;
/*
 * 메모리 27244KB
 * 시간 204ms
 */
public class Baekjoon13398 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        String[] s = br.readLine().split(" ");
        for (int i=0;i<n;i++) {
            nums[i] = Integer.parseInt(s[i]);
        }
        if(n==1)
        	System.out.println(nums[0]);
        else {
        	int[] dp = new int[n]; // 제거하지 않은 경우
        	int[] dpRemove = new int[n]; // 하나를 제거한 경우
        	
        	dp[0] = nums[0];
        	dpRemove[0] = 0;
        	
        	int result = nums[0];
        	
        	for(int i=1;i<n;i++) {
        		//이전까지 합+ 현재값 이랑 현재값으로 새로 시작하는 경우 중에 최대값
        		dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
        		//제거된 상태에서 + 현재값 이랑 이번에 제거하는 경우 중에 최대값
        		dpRemove[i] = Math.max(dpRemove[i-1]+nums[i], dp[i-1]);
        		//둘중 최대값 저장
        		result = Math.max(result, Math.max(dp[i], dpRemove[i]));
        	}
        	System.out.println(result);
        }
    }
}
