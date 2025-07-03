import java.io.*;
/*
 * 메모리:24548KB
 * 시간:	212ms
 */
public class Baekjoon_20159_동작그만밑장빼기냐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 입력 받기
        int cardCount = Integer.parseInt(br.readLine());             // 카드 개수 (짝수)
        String[] input = br.readLine().split(" ");
        int[] cards = new int[cardCount];                           // 카드 값을 저장할 배열
        for (int i = 0; i < cardCount; i++) {
            cards[i] = Integer.parseInt(input[i]);
        }

        // 2. 밑장 빼기 없이 정훈이가 받는 기본 점수 계산 (짝수 인덱스 카드 합)
        int turnCount = cardCount / 2;                              // 정훈이가 받는 카드 장수
        long baseScore = 0;
        for (int turn = 0; turn < turnCount; turn++) {
            int drawIndex = 2 * turn;                              // 정훈이가 받는 순서 인덱스(0,2,4,...)
            baseScore += cards[drawIndex];                         // 해당 카드 값 누적
        }

        // 3. 뒤쪽 카드들이 밀릴 때 생기는 점수 차이 보정값 계산
        //    penaltyAfter[turn] = turn 이후 카드가 한 칸씩 밀리면서 발생하는 (내 카드 - 상대 카드) 누적 합
        long[] penaltyAfter = new long[turnCount];
        penaltyAfter[turnCount - 1] = 0;                            // 마지막 턴 이후는 변동 없음
        for (int turn = turnCount - 2; turn >= 0; turn--) {
            int myCard = cards[2 * (turn + 1)];                     // 원래 내 차례 카드
            int oppCard = cards[2 * (turn + 1) - 1];                // 원래 상대 차례 카드
            penaltyAfter[turn] = penaltyAfter[turn + 1] + (myCard - oppCard);
        }

        // 4. 한 번의 밑장 빼기 경우 모두 계산해 최댓값 찾기
        //    두 가지 경우 모두 고려:
        //    A) 상대 턴(홀수 배분)에 밑장: 내 점수는 baseScore - penaltyAfter[turn]
        //    B) 내 턴(짝수 배분)에 밑장: baseScore - 원래 받을 카드 + 마지막 카드 - penaltyAfter[turn]
        long maxScore = baseScore;
        int bottomIndex = cardCount - 1;                            // 바닥에 있는 카드 인덱스

        for (int turn = 0; turn < turnCount; turn++) {
            // A) 상대 턴에 밑장
            long scoreIfOpp = baseScore - penaltyAfter[turn];
            if (scoreIfOpp > maxScore) {
                maxScore = scoreIfOpp;
            }

            // B) 내 턴에 밑장
            int originalDraw = 2 * turn;                            // 원래 내가 받을 카드 인덱스
            long scoreIfMe = baseScore
                           - cards[originalDraw]                     // 원래 받을 카드 제거
                           + cards[bottomIndex]                      // 바닥 카드 추가
                           - penaltyAfter[turn];                     // 뒤쪽 밀림 보정
            if (scoreIfMe > maxScore) {
                maxScore = scoreIfMe;
            }
        }

        // 5. 결과 출력
        System.out.println(maxScore);
    }
}
