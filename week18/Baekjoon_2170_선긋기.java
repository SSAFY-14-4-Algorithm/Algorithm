/*
 * BOJ 2170번 : 선 긋기
 * 메모리 : 325,556kb
 * 시간 : 1,196ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_2170_선긋기 {
    static int N;
    static class Line implements Comparable<Line> {
        int start, end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o) {
            return this.start - o.start;
        }
    }
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        List<Line> lines = new ArrayList<>();

        for(int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lines.add(new Line(start, end));
        }

        Collections.sort(lines);

        int answer = 0;
        int start = lines.get(0).start;
        int end = lines.get(0).end;

        for(int n = 1; n < lines.size(); n++) {
            Line curr = lines.get(n);

            if(curr.start > end) {
                answer += end-start;
                start = curr.start;
                end = curr.end;
            } else {
                end = Math.max(end, curr.end);
            }
        }

        answer += end-start;
        sb.append(answer);
        System.out.println(sb);
        br.close();
    }
}
