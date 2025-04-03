package week9;

/**
 * JAVA11 : 메모리 14,324 KB 시간 120ms
 * @author KIM MINGYU jun3021303@gmail.com
 */
import java.io.IOException;

public class Baekjoon14500 {
    public static void main(String[] args) throws IOException {
        // 입력 및 초기화
        int max = 0;
        int N = readInt();
        int M = readInt();
        int[][] arr = new int[N + 3][M + 3];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                arr[i][j] = readInt();

        // 계산
        int num = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // ㅁ
                // ㅁ
                // ㅁㅁ
                num = arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 2][j + 1];
                if (num > max)
                    max = num;
                // ----ㅁ
                // ㅁㅁㅁ
                num = arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 1][j + 2] + arr[i][j + 2];
                if (num > max)
                    max = num;
                // ㅁㅁ
                // --ㅁ
                // --ㅁ
                num = arr[i][j] + arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 2][j + 1];
                if (num > max)
                    max = num;
                // ㅁㅁㅁ
                // ㅁ
                num = arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j];
                if (num > max)
                    max = num;
                // --ㅁ
                // --ㅁ
                // ㅁㅁ
                num = arr[i + 2][j] + arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 2][j + 1];
                if (num > max)
                    max = num;
                // ㅁ
                // ㅁㅁㅁ
                num = arr[i][j] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 1][j + 2];
                if (num > max)
                    max = num;
                // ㅁㅁ
                // ㅁ
                // ㅁ
                num = arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i][j + 1];
                if (num > max)
                    max = num;
                // ㅁㅁㅁ
                // ----ㅁ
                num = arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 2];
                if (num > max)
                    max = num;
                // ㅁ
                // ㅁㅁ
                // --ㅁ
                num = arr[i][j] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 2][j + 1];
                if (num > max)
                    max = num;
                // --ㅁㅁ
                // ㅁㅁ
                num = arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j] + arr[i + 1][j + 1];
                if (num > max)
                    max = num;
                // --ㅁ
                // ㅁㅁ
                // ㅁ
                num = arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 2][j];
                if (num > max)
                    max = num;
                // ㅁㅁ
                // --ㅁㅁ
                num = arr[i][j] + arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 1][j + 2];
                if (num > max)
                    max = num;
                // ㅁㅁㅁㅁ
                num = arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i][j + 3];
                if (num > max)
                    max = num;
                // ㅁ
                // ㅁ
                // ㅁ
                // ㅁ
                num = arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 3][j];
                if (num > max)
                    max = num;
                // ㅁㅁ
                // ㅁㅁ
                num = arr[i][j] + arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1];
                if (num > max)
                    max = num;
                // ㅁㅁㅁ
                // --ㅁ
                num = arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 1][j + 2];
                if (num > max)
                    max = num;
                // ㅁ
                // ㅁㅁ
                // ㅁ
                num = arr[i][j] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 2][j];
                if (num > max)
                    max = num;
                // ㅁㅁㅁ
                // --ㅁ
                num = arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 1];
                if (num > max)
                    max = num;
                // --ㅁ
                // ㅁㅁ
                // --ㅁ
                num = arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 2][j + 1];
                if (num > max)
                    max = num;
            }
        }
        System.out.print(max);
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