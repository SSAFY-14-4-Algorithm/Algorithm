import java.io.*;
import java.util.*;
/*
 * 메모리:76056KB
 * 시간:	656ms
 */
public class Baekjoon_2357_최솟값과최댓값 {
    static int[] arr;
    static int[] minTree, maxTree;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] nm = br.readLine().split(" ");
        N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 2. 세그먼트 트리 초기화
        int size = 4 * N;
        minTree = new int[size];
        maxTree = new int[size];

        build(1, 1, N);

        // 3. 쿼리 처리
        for (int i = 0; i < M; i++) {
            String[] ab = br.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);

            int minVal = queryMin(1, 1, N, a, b);
            int maxVal = queryMax(1, 1, N, a, b);
            sb.append(minVal).append(" ").append(maxVal).append("\n");
        }

        System.out.print(sb);
    }

    // 4. 세그먼트 트리 구축
    static void build(int node, int start, int end) {
        if (start == end) {
            minTree[node] = arr[start];
            maxTree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(node * 2, start, mid);
            build(node * 2 + 1, mid + 1, end);
            minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
            maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
        }
    }

    // 5. 최소값 쿼리
    static int queryMin(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return Integer.MAX_VALUE;
        if (l <= start && end <= r) return minTree[node];
        int mid = (start + end) / 2;
        return Math.min(queryMin(node * 2, start, mid, l, r),
                        queryMin(node * 2 + 1, mid + 1, end, l, r));
    }

    // 6. 최대값 쿼리
    static int queryMax(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return Integer.MIN_VALUE;
        if (l <= start && end <= r) return maxTree[node];
        int mid = (start + end) / 2;
        return Math.max(queryMax(node * 2, start, mid, l, r),
                        queryMax(node * 2 + 1, mid + 1, end, l, r));
    }
}
