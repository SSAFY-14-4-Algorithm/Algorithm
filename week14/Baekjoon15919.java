package week14;

import java.io.IOException;
import java.util.Arrays;

public class Baekjoon15919 {
    private static class Trip {
        int start, end;

        Trip(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();
        int minLengthPeriodOfNotTravelingPeriods = N;
        int[] dp = new int[M];
        Trip[] trips = new Trip[M];
        for (int i = 0; i < M; i++) {
            int a = readInt();
            int b = readInt();
            trips[i] = new Trip(a, b);
        }
        // 끝나는 날짜 기준 정렬
        Arrays.sort(trips, (x, y) -> x.end - y.end);

        // 마지막 공백 미리 계산
        int[] endGap = new int[M];
        for (int i = 0; i < M; i++)
            endGap[i] = N - trips[i].end;

        // i번째 여행을 마지막 일정으로 가정하고 계산
        for (int i = 0; i < M; i++) {
            // 1일째부터 i일정 시작 전날까지 쉬는 날
            dp[i] = trips[i].start - 1;

            // i일정 이전에 끝나는 모든 j일정
            for (int j = 0; j < i; j++) {
                if (trips[j].end < trips[i].start) {
                    // j 끝난 다음날부터 i 시작 전날까지 쉬는 날
                    int gap = trips[i].start - trips[j].end - 1;
                    // j까지의 최장 쉬는 날 vs 이번 gap 중 더 큰 값
                    int worst = Math.max(dp[j], gap);
                    // 그중 가장 작은 최장 쉬는 날로 갱신
                    dp[i] = Math.min(dp[i], worst);
                } else {
                    break;
                }
            }
            // 마지막 공백과 비교
            minLengthPeriodOfNotTravelingPeriods = Math.min(minLengthPeriodOfNotTravelingPeriods,
                    Math.max(dp[i], endGap[i]));
        }
        System.out.println(minLengthPeriodOfNotTravelingPeriods);
    }

    private static int readInt() throws IOException {
        int c = System.in.read(), n = 0;
        while (c <= ' ')
            c = System.in.read();
        while ('0' <= c && c <= '9') {
            n = (n * 10) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
