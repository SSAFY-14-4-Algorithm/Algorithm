package week15;

// 신이 도와준 문제입니다.

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Baekjoon2747 {
    private static int[] arr;
    private static int N;

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader(System.in);
        // 입력
        N = fr.nextInt();
        arr = new int[N];

        // 전체 합 S 계산을 위해 long 사용
        long S = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = fr.nextInt();
            S += arr[i];
        }

        // 투포인터, 이분 탐색을 위해 오름차순 정렬
        Arrays.sort(arr);

        long result = 0;

        // ─────────────────────────────────────────────────────────────────────
        // 경우 1) "피벗"이 arr[N-1] (배열에서 최댓값) 일 때
        // 남은 배열에서 이 값이 "좋은 배열"의 기준값이 되므로,
        // 2개 제거된 값의 합 = S - 2 * pivot 이 되어야 함.
        // => arr[0..N-2] 구간에서 두 수의 합이 T1이 되도록 하는 쌍 개수 계산
        // ─────────────────────────────────────────────────────────────────────
        long T1 = S - 2L * arr[N - 1];
        result += countPairs(arr, 0, N - 2, T1);

        // ─────────────────────────────────────────────────────────────────────
        // 경우 2) 피벗이 arr[N-2] 일 때
        // 이 경우에는 반드시 최댓값(arr[N-1])이 제거되어야 함.
        // 따라서 남은 제거 한 개의 값 x는
        // x + arr[N-1] = S - 2 * arr[N-2]
        // 이므로 x = (S - 2*arr[N-2]) - arr[N-1] 로 구할 수 있고,
        // arr[0..N-3] 에서 이 x를 가진 요소 개수를 이분탐색으로 센다.
        // ─────────────────────────────────────────────────────────────────────
        long T2 = S - 2L * arr[N - 2];
        long need = T2 - arr[N - 1];
        int lb = lowerBound(arr, need, 0, N - 2);
        int ub = upperBound(arr, need, 0, N - 2);
        result += ub - lb;

        // ─────────────────────────────────────────────────────────────────────
        // 경우 3) 피벗이 arr[N-3] 일 때
        // 이 경우에는 제거된 두 수가 정확히 arr[N-1], arr[N-2] 여야만 함.
        // 이를 하나의 조건식으로 검사하여 충족하면 +1
        // ─────────────────────────────────────────────────────────────────────
        if ((long) arr[N - 1] + arr[N - 2] == S - 2L * arr[N - 3]) {
            result++;
        }

        // 출력
        System.out.println(result);
    }

    /**
     * a[l..r] 구간에서 서로 다른 두 원소의 합이 target이 되는 쌍의 개수를 셈. - 투포인터를 이용해
     * O(n) 시간에 처리. - 동일한 값이 여러 번 등장할 경우 그룹 단위로 카운팅.
     */
    private static long countPairs(int[] a, int l, int r, long target) {
        long cnt = 0;
        int i = l;
        int j = r;
        while (i < j) {
            long sum = (long) a[i] + a[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                // a[i] + a[j] == target
                if (a[i] == a[j]) {
                    // 남은 모든 값이 동일하다면 조합으로 계산
                    long m = j - i + 1;
                    cnt += m * (m - 1) / 2;
                    break;
                }
                // 값이 다르면 각 값의 중복 개수를 세어서 곱해줌
                int lv = a[i], rv = a[j];
                long c1 = 0, c2 = 0;
                while (i <= j && a[i] == lv) {
                    c1++;
                    i++;
                }
                while (j >= i && a[j] == rv) {
                    c2++;
                    j--;
                }
                cnt += c1 * c2;
            }
        }
        return cnt;
    }

    /**
     * 이분 탐색: a[lo..hi) 구간에서 key 이상이 되는 첫 인덱스를 반환.
     */
    private static int lowerBound(int[] a, long key, int lo, int hi) {
        int l = lo;
        int h = hi;
        while (l < h) {
            int m = (l + h) >>> 1;
            if ((long) a[m] < key)
                l = m + 1;
            else
                h = m;
        }
        return l;
    }

    /**
     * 이분 탐색: a[lo..hi) 구간에서 key 초과가 되는 첫 인덱스를 반환.
     */
    private static int upperBound(int[] a, long key, int lo, int hi) {
        int l = lo;
        int h = hi;
        while (l < h) {
            int m = (l + h) >>> 1;
            if ((long) a[m] <= key)
                l = m + 1;
            else
                h = m;
        }
        return l;
    }

    public static class FastReader {
        private final int BUFFER_SIZE = 1 << 16;
        private final InputStream in;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader(InputStream in) {
            this.in = in;
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = 0;
            bytesRead = 0;
        }

        private int read() throws IOException {
            if (bufferPointer == bytesRead) {
                bytesRead = in.read(buffer, 0, BUFFER_SIZE);
                if (bytesRead == -1)
                    return -1;
                bufferPointer = 0;
            }
            return buffer[bufferPointer++] & 0xff;
        }

        public int nextInt() throws IOException {
            int c = read();
            while (c <= ' ')
                c = read();
            boolean negative = false;
            if (c == '-') {
                negative = true;
                c = read();
            }
            int result = 0;
            while ('0' <= c && c <= '9') {
                result = result * 10 + (c & 15);
                c = read();
            }
            return negative ? -result : result;
        }
    }
}
