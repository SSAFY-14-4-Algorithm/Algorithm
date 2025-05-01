import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Baekjoon14725 {

    //메모리 16760kb 시간 280ms
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        TrieAlgo trie = new TrieAlgo();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()); // 먹이 개수
            String[] words = new String[k];
            for (int j = 0; j < k; j++) {
                words[j] = st.nextToken();
            }
            trie.insert(words);
        }
        trie.print(trie.root, 0);
    }
}

class TrieNode {
    Map<String, TrieNode> childNode = new TreeMap<>();
    boolean isEndOfWord = false;
}

class TrieAlgo {
    TrieNode root;

    public TrieAlgo() {
        root = new TrieNode();
    }

    // 먹이 경로를 트라이에 삽입
    public void insert(String[] words) {
        TrieNode node = root;
        for (String word : words) {
            // 현재 노드의 childNode에 word가 없으면 새 노드 생성
            node.childNode.putIfAbsent(word, new TrieNode());
            // word에 해당하는 자식 노드로 이동
            node = node.childNode.get(word);
        }

    }

    // 재귀적으로 트라이를 순회하며 출력
    public void print (TrieNode node, int depth) {
        for (String key : node.childNode.keySet()) {
            // 현재 깊이(depth)만큼 “--” 반복 출력
            for (int i = 0; i < depth; i++) {
                System.out.print("--");
            }
            System.out.println(key);
            // 그 아래 하위 노드 호출
            print(node.childNode.get(key), depth+1);
        }
    }

}