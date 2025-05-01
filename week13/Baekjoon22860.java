import java.io.*;
import java.util.*;
/*
 * 메모리:101472KB
 * 시간:920ms
 */
public class Baekjoon22860 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 폴더 노드
    static class Folder {
        Map<String, Folder> sub = new HashMap<>();
        List<String> files = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);

        Folder root = new Folder();
        // 폴더 이름으로 노드 참조 
        Map<String, Folder> map = new HashMap<>();
        map.put("main", root);

        // 입력된 폴더/파일 구조 구성
        for (int i = 0; i < N + M; i++) {
            String[] parts = br.readLine().split(" ");
            String parentName = parts[0];
            String name = parts[1];
            int s2 = Integer.parseInt(parts[2]);
            Folder parent = map.computeIfAbsent(parentName, k -> new Folder());
            if (s2 == 1) {
                // map에 없으면 새로 만들고, 꺼내서 parent.sub에 연결
            	Folder child = map.computeIfAbsent(name, k -> new Folder());
                parent.sub.put(name, child);
            } else {
                // 파일
                parent.files.add(name);
            }
        }

        int idx = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (idx-- > 0) {
            String[] path = br.readLine().split("/");
            Folder cur = root;
            // main 이후 경로로 폴더 이동
            for (int i = 1; i < path.length; i++) {
                cur = cur.sub.get(path[i]);
            }
            // 하위 모든 파일 탐색
            Set<String> types = new HashSet<>();
            int[] total = {0};
            dfs(cur, types, total);
            sb.append(types.size()).append(" ").append(total[0]).append("\n");
        }
        System.out.print(sb);
    }

    // DFS로 폴더 하위 모든 파일 탐색
    static void dfs(Folder node, Set<String> types, int[] total) {
        for (String f : node.files) {
            types.add(f);
            total[0]++;
        }
        for (Folder child : node.sub.values()) {
            dfs(child, types, total);
        }
    }
}
