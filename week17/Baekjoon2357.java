package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2357 {
    protected static int N;
    protected static int[] arr;
    protected static int[] minTree;
    protected static int[] maxTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        StringBuilder sb = new StringBuilder();
        makeTree();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            int minVal = queryMin(0, N - 1, 0, l, r);
            int maxVal = queryMax(0, N - 1, 0, l, r);
            sb.append(minVal).append(' ').append(maxVal).append('\n');
        }
        System.out.print(sb.toString());
    }

    private static void makeTree() {
        int height = (int) (Math.ceil(Math.log(N) / Math.log(2)) + 1);
        int maxSize = 2 * (int) Math.pow(2, height) - 1;
        minTree = new int[maxSize];
        maxTree = new int[maxSize];
        buildMinTree(0, N - 1, 0);
        buildMaxTree(0, N - 1, 0);
    }

    private static void buildMinTree(int start, int end, int node) {
        if (start == end) {
            minTree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            buildMinTree(start, mid, 2 * node + 1);
            buildMinTree(mid + 1, end, 2 * node + 2);
            minTree[node] = Math.min(minTree[2 * node + 1], minTree[2 * node + 2]);
        }
    }

    private static void buildMaxTree(int start, int end, int node) {
        if (start == end) {
            maxTree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            buildMaxTree(start, mid, 2 * node + 1);
            buildMaxTree(mid + 1, end, 2 * node + 2);
            maxTree[node] = Math.max(maxTree[2 * node + 1], maxTree[2 * node + 2]);
        }
    }

    private static int queryMin(int start, int end, int node, int l, int r) {
        if (r < start || end < l) {
            return Integer.MAX_VALUE;
        }
        if (l <= start && end <= r) {
            return minTree[node];
        }
        int mid = (start + end) / 2;
        int leftQuery = queryMin(start, mid, 2 * node + 1, l, r);
        int rightQuery = queryMin(mid + 1, end, 2 * node + 2, l, r);
        return Math.min(leftQuery, rightQuery);
    }

    private static int queryMax(int start, int end, int node, int l, int r) {
        if (r < start || end < l) {
            return Integer.MIN_VALUE;
        }
        if (l <= start && end <= r) {
            return maxTree[node];
        }
        int mid = (start + end) / 2;
        int leftQuery = queryMax(start, mid, 2 * node + 1, l, r);
        int rightQuery = queryMax(mid + 1, end, 2 * node + 2, l, r);
        return Math.max(leftQuery, rightQuery);
    }
}
