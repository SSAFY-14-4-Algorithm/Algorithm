import java.io.*;
import java.util.*;
/*
 * 메모리:59172KB
 * 시간:436ms
 */
public class Baekjoon15922 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
       
        int[][] lines = new int[n][2];
        
        for (int i=0;i<n;i++) {
            String[] s = br.readLine().split(" ");
            lines[i][0] = Integer.parseInt(s[0]);  // 시작점
            lines[i][1] = Integer.parseInt(s[1]);  // 끝점
        }
        
        int total = 0;
        int currentStart = lines[0][0];
        int currentEnd = lines[0][1];
        
        for (int i=1;i<n;i++) {
            int start = lines[i][0];
            int end = lines[i][1];
            
            //현재 선분과 다음 선분이 겹치면
            if (start <= currentEnd) {
                currentEnd = Math.max(currentEnd, end);
            } else {
                // 안겹치면 현재까지 합친 길이를 더하고, 새로운 선분으로 설정
                total += currentEnd - currentStart;
                currentStart = start;
                currentEnd = end;
            }
        }
        
        // 마지막 남은 선분 길이 추가
        total += currentEnd - currentStart;

        System.out.println(total);
    }
}
