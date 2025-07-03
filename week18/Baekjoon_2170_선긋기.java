import java.io.*;
import java.util.*;
/*
 * 	메모리:324908KB
 *	시간:	2332ms
 */
public class Baekjoon_2170_선긋기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 선분을 나타내는 클래스
	static class Interval{
		int start, end;
		
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine()); // 선을 그은 횟수 입력
        Interval[] lines = new Interval[n];

        // 선분 입력 받기
        for(int i=0;i<n;i++) {
            String[] s = br.readLine().split(" ");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);
            lines[i] = new Interval(x, y);
        }

        // 시작점을 기준으로 정렬
        Arrays.sort(lines, (a, b) -> Integer.compare(a.start, b.start));

        // 초기 구간 설정
        int currentStart = lines[0].start;
        int currentEnd = lines[0].end;
        int totalLength = 0;

        // 모든 선분 순회하며 병합
        for(int i=1;i<n;i++) {
            // 현재 구간과 겹치는 경우 -> 병합
            if(lines[i].start <= currentEnd) {
                currentEnd = Math.max(currentEnd, lines[i].end);
            } else {
                // 겹치지 않는 경우 -> 이전 구간 길이 더하고 새 구간 시작
                totalLength += (currentEnd - currentStart);
                currentStart = lines[i].start;
                currentEnd = lines[i].end;
            }
        }

        // 마지막 구간 길이 추가
        totalLength += (currentEnd - currentStart);

        // 결과 출력
        System.out.println(totalLength);
    }
}
