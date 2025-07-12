/*
 * BOJ 2357번 : 최소값과 최댓값
 * 메모리 : 62,348kb
 * 시간 : 536ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_2357_최솟값과최댓값 {
    static int N, M;
    static int[] input;
    static int[] minTree, maxTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new int[N];
        for(int n = 0; n < N; n++) {
            input[n] = Integer.parseInt(br.readLine());
        }

        // 세그먼트 트리의 크기 = 2 * 2^k (k는 N 이상의 최소 2의 제곱수
        int treeSize = 1;
        while(treeSize < N) treeSize *= 2; // 가장 가까운 2의 제곱수
        treeSize *= 2; // 전체 트리 배열 크기 설정

        minTree = new int[treeSize];
        maxTree = new int[treeSize];

        // 트리 생성
        buildMinTree(1, 0, N-1); // node, start, end
        buildMaxTree(1, 0, N-1); // node, start, end

        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken())-1;
            int right = Integer.parseInt(st.nextToken())-1;

            // 해당 구간의 최소값, 최대값 찾기
            int minValue = queryMin(1, 0, N-1, left, right); // node, start, end, left, right
            int maxValue = queryMax(1, 0, N-1, left, right); // node, start, end, left, right

            sb.append(minValue).append(" ").append(maxValue).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static void buildMinTree(int node, int start, int end) {
        if(start == end) {
            // 1. 리프 노드일 경우
            minTree[node] = input[start];
        } else {
            int mid = (start + end) / 2;
            // 2. 왼쪽 자식 트리 생성
            buildMinTree(node*2, start, mid);
            // 3. 오른쪽 자식 트리 생성
            buildMinTree(node*2+1, mid+1, end);
            // 4. 현재 노드에 왼쪽과 오른쪽 중 최소값 저장
            minTree[node] = Math.min(minTree[node*2], minTree[node*2+1]);
        }
    }

    static void buildMaxTree(int node, int start, int end) {
        if(start == end) {
            // 1. 리프 노드일 경우
            maxTree[node] = input[start];
        } else {
            int mid = (start + end) / 2;
            // 2. 왼쪽 자식 트리 생성
            buildMaxTree(node*2, start, mid);
            // 3. 오른쪽 자식 트리 생성
            buildMaxTree(node*2+1, mid+1, end);
            // 4. 현재 노드에 왼쪽과 오른쪽 중 최대값 저장
            maxTree[node] = Math.max(maxTree[node*2], maxTree[node*2+1]);
        }
    }

    static int queryMin(int node, int start, int end, int left, int right) {
        if(right < start || end < left) {
            // 현재 노드의 구간이 [left, right] 와 겹치지 않으면 큰 값 리턴
            return Integer.MAX_VALUE;
        }
        if(left <= start && end <= right) {
            // 현재 노드의 구간이 [left, right] 에 완전히 포함되면 값 리턴
            return minTree[node];
        }

        // 일부만 겹칠 경우 왼쪽, 오른쪽 자식 쿼리 후 최소값 선택
        int mid = (start + end) / 2;
        int leftMin = queryMin(node*2, start, mid, left, right);
        int rightMin = queryMin(node*2+1, mid+1, end, left, right);
        return Math.min(leftMin, rightMin);
    }

    static int queryMax(int node, int start, int end, int left, int right) {
        if(right < start || end < left) {
            // 현재 노드의 구간이 [left, right] 와 겹치지 않으면 작은 값 리턴
            return Integer.MIN_VALUE;
        }
        if(left <= start && end <= right) {
            // 현재 노드의 구간이 [left, right] 에 완전히 포함되면 값 리턴
            return maxTree[node];
        }

        // 일부만 겹칠 경우 왼쪽, 오른쪽 자식 쿼리 후 최소값 선택
        int mid = (start + end) / 2;
        int leftMax = queryMax(node*2, start, mid, left, right);
        int rightMax = queryMax(node*2+1, mid+1, end, left, right);
        return Math.max(leftMax, rightMax);
    }
}
