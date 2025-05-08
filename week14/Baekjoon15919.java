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
        int minimizedMaxLengthPeriodOfNotTravelingPeriods = N;
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
            minimizedMaxLengthPeriodOfNotTravelingPeriods = Math.min(minimizedMaxLengthPeriodOfNotTravelingPeriods,
                    Math.max(dp[i], endGap[i]));
        }
        System.out.println(minimizedMaxLengthPeriodOfNotTravelingPeriods);
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
// 입력
// 제주도: [20,23]
// 런던: [3,14]
// 도쿄: [5,7]
// 홍콩: [12,15]
// 하와이: [24,29]

// GPT 풀이
// ## 1. 정렬된 여행 목록
// | 인덱스 i | 일정 | 시작 sᵢ | 끝 eᵢ |
// |:--------:|:--------|:--------:|:-----:|
// | 0 | 도쿄 | 5 | 7 |
// | 1 | 런던 | 3 | 14 |
// | 2 | 홍콩 | 12 | 15 |
// | 3 | 제주도 | 20 | 23 |
// | 4 | 하와이 | 24 | 29 |

// ---

// ## 2. DP 및 휴식 구간 계산
// - **dp[i]**: i번째 여행을 마지막으로 선택했을 때, 그 이전까지의 “가장 긴 비여행 기간”
// - **끝구간**: N=31 기준, `31 − eᵢ`

// ---

// ### i = 0 (도쿄 [5,7])
// - 첫 휴식: `1 ~ 4` → 길이 = `5 − 1 = 4` → `dp[0] = 4`

// - 끝구간: `8 ~ 31` → 길이 = `31 − 7 = 24`
// - 이 경우 최댓값 = `max(dp[0], 끝구간) = max(4, 24) = 24`

// ---

// ### i = 1 (런던 [3,14])
// - 첫 휴식: `1 ~ 2` → 길이 = `3 − 1 = 2` → `dp[1] = 2`

// - 이전 j 검사:
// - j=0 (`e₀=7 ≥ s₁=3`) → 겹침 → 전이 없음

// - 끝구간: `15 ~ 31` → 길이 = `31 − 14 = 17`
// - 최댓값 = `max(2, 17) = 17`

// ---

// ### i = 2 (홍콩 [12,15])
// - 첫 휴식: `1 ~ 11` → 길이 = `12 − 1 = 11` → `dp[2] = 11`

// - 이전 j 전이:
// - j=0 (`7 < 12`): gap = `12 − 7 − 1 = 4`, worst = `max(dp[0]=4, 4) = 4` →
// `dp[2] = min(11, 4) = 4`
// - j=1 (`14 ≥ 12`) → 중단

// - 끝구간: `16 ~ 31` → 길이 = `31 − 15 = 16`
// - 최댓값 = `max(4, 16) = 16`

// ---

// ### i = 3 (제주도 [20,23])
// - 첫 휴식: `1 ~ 19` → 길이 = `20 − 1 = 19` → `dp[3] = 19`

// - 이전 j 전이:
// - j=0 (`7 < 20`): gap = `20 − 7 − 1 = 12`, worst = `max(dp[0]=4, 12) = 12` →
// `dp[3] = 12`
// - j=1 (`14 < 20`): gap = `20 − 14 − 1 = 5`, worst = `max(dp[1]=2, 5) = 5` →
// `dp[3] = 5`
// - j=2 (`15 < 20`): gap = `20 − 15 − 1 = 4`, worst = `max(dp[2]=4, 4) = 4` →
// `dp[3] = 4`

// - 끝구간: `24 ~ 31` → 길이 = `31 − 23 = 8`
// - 최댓값 = `max(4, 8) = 8`

// ---

// ### i = 4 (하와이 [24,29])
// - 첫 휴식: `1 ~ 23` → 길이 = `24 − 1 = 23` → `dp[4] = 23`

// - 이전 j 전이:
// - j=0 (`7 < 24`): gap=16, worst=max(4,16)=16 → dp[4]=16
// - j=1 (`14 < 24`): gap=9, worst=max(2,9)=9 → dp[4]=9
// - j=2 (`15 < 24`): gap=8, worst=max(4,8)=8 → dp[4]=8
// - j=3 (`23 < 24`): gap=0, worst=max(4,0)=4 → dp[4]=4

// - 끝구간: `30 ~ 31` → 길이 = `31 − 29 = 2`
// - 최댓값 = `max(4, 2) = 4`

// ---

// ## 3. 최종 답 도출

// | i | 최댓값 (`max(dp[i], 31−eᵢ)`) |
// |:--:|:----------------------------:|
// | 0 | 24 |
// | 1 | 17 |
// | 2 | 16 |
// | 3 | 8 |
// | 4 | **4** |

// → **답: 4**
// (여행 중이 아닌 기간의 최댓값을 최소화한 결과)
