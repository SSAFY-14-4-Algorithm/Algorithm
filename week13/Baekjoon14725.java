package week13;

/**
 * <h1>BAEKJOON 14725번 개미굴</h1>
 * <p>
 * JAVA11 : 메모리 15972KB, 시간 128ms
 * </p>
 * 
 * @author KIM MINGYU jun3021303@gmail.com
 * @since 2025-04-29
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Baekjoon14725 {
    private static class Node {
        // 자식 노드들을 이름 순으로 정렬하기 위해 TreeMap 사용
        TreeMap<String, Node> nodes = new TreeMap<>();

        /**
         * 단어 배열을 트리에 삽입하는 재귀 메서드
         * 
         * @param word  경로(먹이 이름) 배열
         * @param index 현재 깊이(레벨)
         */
        public void insert(String[] word, int index) {
            // 배열 범위를 벗어나지 않았다면
            if (index < word.length) {
                // word[index]에 해당하는 자식 노드가 있으면 가져오고,
                // 없으면 새 노드를 생성하고, 다음 깊이로 재귀 호출
                nodes.computeIfAbsent(word[index], k -> new Node()).insert(word, index + 1);
            }
        }

        public void print(int depth) {
            // 자식 노드들을 사전 순으로 순회
            for (Map.Entry<String, Node> e : nodes.entrySet()) {
                // 깊이만큼 "--"를 반복하여 들여쓰기
                sb.append("--".repeat(depth)).append(e.getKey()).append("\n");
                // 자식 노드로 내려가면서 깊이 + 1
                e.getValue().print(depth + 1);
            }
        }
    }

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Node root = new Node();
        while (N-- > 0) {
            String[] parts = br.readLine().split(" ");
            // parts[0] == K
            // parts[1]부터 시작
            root.insert(parts, 1);
        }
        root.print(0);
        System.out.print(sb.toString());
    }
}
