package week9;

/**
 * JAVA11 : 메모리 14,984 KB 시간 120ms
 * @author KIM MINGYU jun3021303@gmail.com
 */

import java.io.IOException;

public class Baekjoon1138 {
    private static boolean[] check;
    private static int[] frontData;
    private static int[] lineResult;
    private static int N;
    private static boolean found;

    public static void main(String[] args) throws IOException {
        N = readInt();
        check = new boolean[N + 1];
        frontData = new int[N + 1];
        lineResult = new int[N + 1];
        for (int i = 1; i <= N; i++)
            frontData[i] = readInt();
        dfs(1);
    }

    public static void dfs(int num) {
        if (found)
            return;
        if (num == N + 1) {
            // 만들어진 줄 경우가 사람들이 기억정보와 일치하는지 확인
            for (int i = 1; i <= N; i++) {
                int frontCount = 0;
                for (int j = 1; j <= i; j++)
                    if (lineResult[j] > lineResult[i])
                        frontCount++;
                if (frontCount != frontData[lineResult[i]])
                    return;
            }
            found = true;

            // 출력
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= N; i++)
                sb.append(lineResult[i]).append(" ");
            System.out.print(sb.toString().trim());
            return;
        }

        // 줄 서는 경우의 수
        // 본인앞에 키큰 사람이 n 명 있다면 그 사람은 최소한 n 이후에 줄을 설 수 있음
        for (int i = frontData[num] + 1; i <= N; i++) {
            if (!check[i]) {
                check[i] = true;
                lineResult[i] = num;
                dfs(num + 1);
                check[i] = false;
            }
        }
    }

    private static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 47)
            n = (n * 10) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}