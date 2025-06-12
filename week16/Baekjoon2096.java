import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 42192kb, 372ms
 */
public class Baekjoon2096 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // DP 배열: 각 열에 도달하는 최대/최소 점수를 저장
        int[] maxDp = new int[3];
        int[] minDp = new int[3];

        // N개의 행을 순회
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n0 = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            // 첫 번째 행은 그대로 초기값으로 사용
            if (i == 0) {
                maxDp[0] = n0;
                maxDp[1] = n1;
                maxDp[2] = n2;

                minDp[0] = n0;
                minDp[1] = n1;
                minDp[2] = n2;
            } else {
                // 이전 행의 DP 값을 임시로 저장
                int prevMax0 = maxDp[0];
                int prevMax1 = maxDp[1];
                int prevMax2 = maxDp[2];

                int prevMin0 = minDp[0];
                int prevMin1 = minDp[1];
                int prevMin2 = minDp[2];
                
                // 현재 행의 DP 값을 이전 행의 값을 이용해 계산
                // 최대 점수 갱신
                maxDp[0] = n0 + Math.max(prevMax0, prevMax1);
                maxDp[1] = n1 + Math.max(prevMax0, Math.max(prevMax1, prevMax2));
                maxDp[2] = n2 + Math.max(prevMax1, prevMax2);

                // 최소 점수 갱신
                minDp[0] = n0 + Math.min(prevMin0, prevMin1);
                minDp[1] = n1 + Math.min(prevMin0, Math.min(prevMin1, prevMin2));
                minDp[2] = n2 + Math.min(prevMin1, prevMin2);
            }
        }

        // 최종 결과 계산
        int finalMax = Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]));
        int finalMin = Math.min(minDp[0], Math.min(minDp[1], minDp[2]));
        
        System.out.println(finalMax + " " + finalMin);
    }
}
