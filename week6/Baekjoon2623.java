package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 메모리 13140KB 실핼시간 124ms
public class Baekjoon2623 {

    static int[] dist;
    static int[] result;
    static int n, m;
    static List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        dist = new int[n + 1];
        result = new int[n];
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int size = Integer.parseInt(input[0]);
            for (int j = 1; j < size; j++) {
                int from = Integer.parseInt(input[j]);
                int to = Integer.parseInt(input[j + 1]);
                list.get(from).add(to);
                dist[to]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (dist[i] == 0) {
                queue.offer(i);
            }
        }

        int idx = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result[idx++] = curr;
            for (int next : list.get(curr)) {
                dist[next]--;
                if (dist[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        if (idx != n) {
            System.out.println(0);
            return;
        }


        for (int i = 0; i < idx; i++) {
            System.out.println(result[i]);
        }
    }
}
