import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon20159 {

    //메모리 24580 kb 시간 272 ms
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 카드 누적합
        long[] card1 = new long[n / 2 + 1]; //정훈이 카드
        long[] card2 = new long[n / 2 + 1]; //상대방 카드
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n / 2; i++) {
            card1[i] = card1[i - 1] + Long.parseLong(st.nextToken());
            card2[i] = card2[i - 1] + Long.parseLong(st.nextToken());
        }

        // 중간에 밑장 빼기 없을 경우, 최대값이라면 밑장 빼기를 해줌
        long result = card1[n / 2];
        long tmp;
        for (int i = 0; i < n; i++) {
            // 정훈이의 차례에서 밑장 빼기를 한 경우
            if (i % 2 == 1) {
                //밑장 빼기 전 까지의 정훈이 카드합 + 밑장빼기 이후에 가져가는 카드 합
                tmp = card1[i / 2] + (card2[n / 2] - card2[i / 2]);
            }
            // 상대 차례에서 밑장 빼기를 한 경우
            else {
                tmp = card1[i / 2 + 1] + (card2[n / 2 - 1] - card2[i / 2]);
            }
            result = Math.max(result, tmp);
        }

        // 출력
        System.out.println(result);
    }
}