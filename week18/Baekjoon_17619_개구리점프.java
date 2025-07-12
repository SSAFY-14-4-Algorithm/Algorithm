/*
 * BOJ 17619번 : 개구리 점프
 * 메모리 : 91,204kb
 * 시간 : 1,080ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_17619_개구리점프 {
    static int N, Q;
    static Tree[] trees;
    static class Tree implements Comparable<Tree> {
        int idx, start, end, groupNum;
        Tree(int idx, int start, int end) {
            this.idx = idx;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Tree o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        trees = new Tree[N];
        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            trees[n] = new Tree(n+1, x1, x2);
        }

        Arrays.sort(trees);

        int lastEnd = trees[0].start;
        int group = 1;

        for(int n = 0; n < N; n++) {
            if(lastEnd < trees[n].start) {
                group++;
            }

            lastEnd = Math.max(lastEnd, trees[n].end);
            trees[n].groupNum = group;
        }

        // Arrays.sort(trees, Comparator.comparingInt(o -> o.idx));
        Arrays.sort(trees, (a, b) -> Integer.compare(a.idx, b.idx));

        for(int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int q1 = Integer.parseInt(st.nextToken());
            int q2 = Integer.parseInt(st.nextToken());
            if(trees[q1-1].groupNum == trees[q2-1].groupNum) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }
}
