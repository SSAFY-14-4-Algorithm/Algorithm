package week13;

/**
 * <h1>BAEKJOON 2866번 문자열 잘라내기</h1>
 * <p>
 * JAVA 8 : 메모리 20,644KB, 시간 128ms <br>
 * JAVA11 : 메모리 22,804KB, 시간 148ms
 * </p>
 * 
 * @author KIM MINGYU jun3021303@gmail.com
 * @since 2025-04-28
 */
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Baekjoon2866 {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        // pow 배열
        long BASE = 59L;
        long[] pow = new long[N + 1];
        pow[0] = 1;
        for (int i = 1; i <= N; i++)
            pow[i] = pow[i - 1] * BASE;

        System.in.read();
        long[][] h = new long[M][N + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                h[j][i + 1] = h[j][i] * BASE + System.in.read();
            System.in.read();
            System.in.read();
        }

        Set<Long> set = new HashSet<>(M, 1.0f);
        int low = 1, high = N - 1, found = N;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            set.clear();
            boolean duplicated = false;
            int len = N - mid;
            for (int j = 0; j < M; j++) {
                long hash = h[j][N] - (h[j][mid] * pow[len]);
                if (!set.add(hash)) {
                    duplicated = true;
                    break;
                }
            }
            if (duplicated) {
                found = mid; // 중복 발생
                high = mid - 1; // 더 작은 k에도 중복 있는지 확인
            } else {
                low = mid + 1; // 아직 중복 없으니 k 키우기
            }
        }
        System.out.print((found < N) ? (found - 1) : (N - 1));
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
