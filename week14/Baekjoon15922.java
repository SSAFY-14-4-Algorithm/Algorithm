import java.io.*;
import java.util.*;

/* 아우으 우아으이야!!
 * 	39896kb, 352ms
 */

public class Baekjoon15922 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());
        int sum = 0;
        //정렬된 상태로 입력이 들어오므로
        //그대로 순서대로 체크해서 값을 더해주면 됨
        for(int i = 1; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int l = Integer.parseInt(st.nextToken());
        	int r = Integer.parseInt(st.nextToken());
        	if(l <= right) {
        		if(right < r) {
        			right = r;
        		}
        	} else {
        		sum += right-left;
        		left = l;
        		right = r;
        	}
        }
        sum += right-left;
        System.out.println(sum);
    }
}