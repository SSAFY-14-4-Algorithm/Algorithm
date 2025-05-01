import java.io.*;
import java.util.*;

public class Main {
    static class TrieNode {
        Map<String, TrieNode> children = new TreeMap<>();
    }

    static TrieNode root = new TrieNode();
    static StringBuilder sb = new StringBuilder();

    public static void insert(String[] path) {
        TrieNode current = root;
        for (String s : path) {
            current = current.children.computeIfAbsent(s, k -> new TrieNode());
        }
    }

    public static void print(TrieNode node, int depth) {
        for (Map.Entry<String, TrieNode> entry : node.children.entrySet()) {
            for (int i = 0; i < depth; i++) sb.append("—");
            sb.append(entry.getKey()).append("\n");
            print(entry.getValue(), depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            String[] path = new String[K];
            for (int j = 0; j < K; j++) {
                path[j] = st.nextToken();
            }
            insert(path);
        }
        print(root, 0);
        System.out.print(sb.toString());
    }
}
