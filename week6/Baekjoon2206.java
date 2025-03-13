package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
// 메모리 121832KB 시간 552ms
public class Baekjoon2206 {

    static int n, m, answer = -1;
    static int[][] list;
    static boolean[][][] check;

    public static class Node {
        int x;
        int y;
        int cnt;
        int move;

        public Node(int x, int y, int cnt, int move) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.move = move;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        list = new int[n][m];
        check = new boolean[2][n][m];

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                list[i][j] = tmp.charAt(j) - '0';
            }
        }

        bfs();
        System.out.println(answer);
    }

    public static void bfs() {
        Queue<Node> qu = new LinkedList<>();
        qu.offer(new Node(0, 0, 0, 1));
        check[0][0][0] = true;

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        while (!qu.isEmpty()) {
            Node tmp = qu.poll();

            if (tmp.x == n - 1 && tmp.y == m - 1) {
                answer = tmp.move;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int di = tmp.x + dx[i];
                int dj = tmp.y + dy[i];

                if (di >= 0 && di < n && dj >= 0 && dj < m) {
                    if (list[di][dj] == 0 && !check[tmp.cnt][di][dj]) {
                        check[tmp.cnt][di][dj] = true;
                        qu.offer(new Node(di, dj, tmp.cnt, tmp.move + 1));
                    }

                    else if (list[di][dj] == 1 && tmp.cnt == 0 && !check[1][di][dj]) {
                        check[1][di][dj] = true;
                        qu.offer(new Node(di, dj, 1, tmp.move + 1));
                    }
                }
            }
        }
    }
}
