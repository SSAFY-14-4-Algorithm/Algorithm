import java.io.*;
import java.util.*;

// Trie에서 사용될 노드
class Node {
    Map<Character, Node> childNode = new HashMap<>();
    boolean endOfWord = false;
}

// Trie 자료구조
class Trie {
    private final Node rootNode = new Node();

    // 문자열 삽입 시 접두어 충돌 여부 확인
    public boolean insert(String word) {
        Node node = rootNode;
        for (char ch : word.toCharArray()) {
            if (node.endOfWord) {
                // 기존에 끝났던 단어가 현재 단어의 접두어인 경우
                return false;
            }
            // 자식 노드가 없으면 새로 만들고, 있으면 그냥 이동
            node = node.childNode.computeIfAbsent(ch, c -> new Node());
        }

        // 현재 단어가 기존 단어의 접두어인 경우
        if (!node.childNode.isEmpty()) {
            return false;
        }

        // 접두어가 아닌 경우, 이 지점을 단어 끝으로 표시
        node.endOfWord = true;
        return true;
    }
}

public class Baekjoon5052 {

    //메모리 193052kb 시간 432ms
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            Trie trie = new Trie();
            int n = Integer.parseInt(br.readLine());
            String[] phones = new String[n];
            for (int i = 0; i < n; i++) {
                phones[i] = br.readLine();
            }

            boolean flag = true;  // 플래그: 접두어 충돌 여부
            for (String phone : phones) {
                if (!trie.insert(phone)) {
                    System.out.println("NO");
                    flag = false;     // 충돌 발생
                    break;         // 내부 루프만 탈출
                }
            }

            if (flag) {
                System.out.println("YES");
            }
        }

    }

}
