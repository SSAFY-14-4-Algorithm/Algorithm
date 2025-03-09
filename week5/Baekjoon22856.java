import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int left, right;
        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static Node[] tree;
    static boolean[] visited;
    static int moveCount = 0;
    static int lastNode = 0;
    static int visitedCount = 0;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new Node[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            tree[num] = new Node(left, right);
        }

     
        findLastNode(1);

   
        similarInOrder(1);
    }

    private static void findLastNode(int current) {
        if (tree[current].left != -1) {
            findLastNode(tree[current].left);
        }
        lastNode = current;
        if (tree[current].right != -1) {
            findLastNode(tree[current].right);
        }
    }

    private static void similarInOrder(int current) {
        visited[current] = true;
        visitedCount++;

        if (tree[current].left != -1 && !visited[tree[current].left]) {
            moveCount++;
            similarInOrder(tree[current].left);
            moveCount++;
        }

        if (tree[current].right != -1 && !visited[tree[current].right]) {
            moveCount++;
            similarInOrder(tree[current].right);
            moveCount++;
        }

        if (visitedCount == N && current == lastNode) {
            System.out.println(moveCount);
            System.exit(0);
        }
    }
}
