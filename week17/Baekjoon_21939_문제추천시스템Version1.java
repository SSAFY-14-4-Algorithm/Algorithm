/*
 * BOJ 21939번 : 문제 추천 시스템 Version 1
 * 메모리 : 52,688kb
 * 시간 : 472ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_21939_문제추천시스템Version1 {
    static TreeSet<Node> treeSet = new TreeSet<Node>();
    static Map<Integer, Integer> levelMap = new HashMap<>();
    static class Node implements Comparable<Node> {
        int num;
        int level;
        public Node(int num, int level) {
            this.num = num;
            this.level = level;
        }

        @Override
        public int compareTo(Node o) {
            if(this.level == o.level) return this.num - o.num;
            return this.level - o.level;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for(int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            treeSet.add(new Node(P, L));
            levelMap.put(P, L);
        }

        int M = Integer.parseInt(br.readLine());
        for(int m = 0; m < M; m++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            switch(cmd) {
                case "add":
                    int P = Integer.parseInt(st.nextToken());
                    int L = Integer.parseInt(st.nextToken());
                    treeSet.add(new Node(P, L));
                    levelMap.put(P, L);
                    break;
                case "recommend":
                    int x = Integer.parseInt(st.nextToken());
                    if(x == 1) sb.append(treeSet.last().num).append("\n");
                    else sb.append(treeSet.first().num).append("\n");
                    break;
                case "solved":
                    int removeP = Integer.parseInt(st.nextToken());
                    int removeL = levelMap.get(removeP);
                    treeSet.remove(new Node(removeP, removeL));
                    levelMap.remove(removeP);
                    break;
            }
        }

        System.out.println(sb);
        br.close();
    }
}
