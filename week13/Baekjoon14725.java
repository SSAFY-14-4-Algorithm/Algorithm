import java.io.*;
import java.util.*;
/*
 * 메모리:13352KB
 * 시간:104ms
 */
public class Baekjoon14725 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 트리의 노드
    static class Node {
        // 자식 노드를 사전 순으로 순회하기 위해 TreeMap 사용
        Map<String, Node> children = new TreeMap<>();
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().split(" ")[0]);
        Node root = new Node();

        // 입력받은 경로 따라 트리에 삽입
        for (int i = 0; i < N; i++) {
            String[] parts = br.readLine().split(" ");
            int K = Integer.parseInt(parts[0]);
            Node cur = root;
            for (int j = 1; j <= K; j++) {
                String food = parts[j];
                Node next = cur.children.get(food);
                if (next == null) {
                    next = new Node();
                    cur.children.put(food, next);
                }
                cur = next;
            }
        }

        StringBuilder sb = new StringBuilder();
        dfs(root, 0, sb);
        System.out.print(sb);
    }

    static void dfs(Node node, int depth, StringBuilder sb) {
        for (String key : node.children.keySet()) {
            // 깊이에 따라 "--" 반복
            for (int i = 0; i < depth; i++) {
                sb.append("--");
            }
            sb.append(key).append("\n");
            dfs(node.children.get(key), depth + 1, sb);
        }
    }
}
