import java.io.*;
import java.util.*;
/*
 * 메모리:24644KB
 * 시간:140ms
 */
public class Baekjoon2179 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int INF = Integer.MAX_VALUE;

    // Trie 노드: 접두사 트리의 각 노드를 정의합니다.
    // next 배열은 알파벳 26자에 대응하는 자식 노드를 가리키고,
    // first와 second에는 이 노드를 거치는 단어들의 입력 순서 중
    // 가장 빠르고 두 번째로 빠른 인덱스를 저장합니다.
    // 이를 통해 중복 없이 두 단어를 빠르게 찾을 수 있습니다.
    static class Node {
        Node[] next = new Node[26];
        int first = INF, second = INF;
    }

    static Node root = new Node();
    // maxLen은 지금까지 찾은 최대 공통 접두사 길이를 저장합니다.
    // DFS 과정에서 새로운 긴 접두사가 발견되거나,
    // 동일 길이인 경우 입력 순서 우선순위가 더 높은 쌍이 나타나면 갱신됩니다.
    static int maxLen = 0;
    static int bestS = INF, bestT = INF;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        String[] words = new String[n];

        // 입력 단어 및 Trie에 삽입
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            insert(words[i], i);
        }

        // Trie 탐색으로 최대 접두사와 단어 쌍 찾기
        dfs(root, 0);

        // 원래 입력 순서대로 출력
        if (bestS > bestT) { int tmp = bestS; bestS = bestT; bestT = tmp; }
        System.out.println(words[bestS]);
        System.out.println(words[bestT]);
    }

    // insert 메서드는 단어를 Trie에 삽입하면서, 각 깊이(접두사 길이) 노드의 first와 second 값을
    // 현재 단어의 입력 순서 idx로 갱신합니다. 이렇게 하면 DFS 시점에 해당 접두사로 시작하는 서로 다른
    // 두 단어를 즉시 알 수 있습니다.
    static void insert(String s, int idx) {
        Node cur = root;
        for (int d = 1; d <= s.length(); d++) {
            int c = s.charAt(d - 1) - 'a';
            if (cur.next[c] == null) cur.next[c] = new Node();
            cur = cur.next[c];
            if (idx < cur.first) {
                cur.second = cur.first;
                cur.first = idx;
            } else if (idx < cur.second) {
                cur.second = idx;
            }
        }
    }

    // dfs 메서드는 Trie 트리를 깊이 우선 탐색하며, depth는 현재 노드까지의 접두사 길이를 나타냅니다.
    // 노드의 second가 INF보다 작다는 것은 최소 두 개의 단어가 이 접두사를 공유한다는 의미입니다.
    // 이때 최댓값(maxLen)과 입력 순서(bestS, bestT) 비교를 통해 후보 쌍을 갱신합니다.
    static void dfs(Node node, int depth) {
        if (node == null) return;
        // 두 개 이상의 단어가 이 접두사를 공유하면 후보가 될 수 있습니다.
        if (node.second < INF) {
            if (depth > maxLen
             || (depth == maxLen && (node.first < bestS
                                     || (node.first == bestS && node.second < bestT)))
            ) {
                maxLen = depth;
                bestS = node.first;
                bestT = node.second;
            }
        }
        for (int i = 0; i < 26; i++) {
            dfs(node.next[i], depth + 1);
        }
    }
}

