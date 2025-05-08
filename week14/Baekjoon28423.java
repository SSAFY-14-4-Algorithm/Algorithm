import java.io.*;
import java.util.*;

/* 게임
 * DFS(재귀)없이 반복문으로 풀이
 * 14904kb 112ms
 */

public class Baekjoon28423 {
	public static int next(int num) { //숫자 변환
	    int sum = 0;
	    int multiple = 1;
	    int temp = num;

	    while (temp > 0) {
	        int digit = temp % 10;
	        sum += digit;
	        multiple *= digit;
	        temp /= 10;
	    }
	    int pow = 10;
	    while(multiple >= pow) {
	    	pow *= 10;
	    }
	    return sum*pow + multiple;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //사이클이 되는 경우 : 0, 10만 이상이 되는 경우 : -1, f(N) = N을 만족하는 경우 : 1
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int sum = 0;
        int[] visited = new int[100001]; //어떤 숫자일 때 방문했는 지 체크
        int[] nums = new int[R+1]; //각 숫자의 결과값
        //DFS 대신 for문과 while문으로 풀이
        for(int i = L; i <= R; i++) {
        	int num = i;
        	while(visited[num] == 0) { //이미 방문한 곳이 아닐 때 반복
        		visited[num] = i;
        		int nextNum = next(num);
        		if(num == nextNum) {
        			nums[i] = 1;
        			break;
        		}
        		if(nextNum > 100000) {
        			nums[i] = -1;
        			break;
        		}
        		if(visited[nextNum] == i) {
        			nums[i] = 0;
        			break;
        		}
        		num = nextNum;
        	}
        	nums[i] = nums[visited[num]]; //이미 방문한 곳을 방문했을 때를 위한 수정
        	sum += nums[i];
        }
        System.out.println(sum);
    }
}