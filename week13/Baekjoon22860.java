import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon22860 {

    static int n, m, q;
    // 각 폴더 이름, 자식 이름을 리스트에 저장
    static Map<String, List<String>> folderMap = new HashMap<>();
    static Map<String, Boolean> isFolder = new HashMap<>();
    static Set<String> fileSet; // 서로 다른 파일 이름 저장용
    static int fileCount;       // 탐색 중 만난 파일 총 개수

    //메모리 95204kb 시간 876ms
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 폴더, 파일 수
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 폴더 구조 구성
        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            String child = st.nextToken();
            int c = Integer.parseInt(st.nextToken());

            //map에 parent, child 추가
            folderMap.putIfAbsent(parent, new ArrayList<>());
            folderMap.get(parent).add(child);

            // 자식이 폴더인지 파일인지 표시
            isFolder.put(child, c == 1);
        }

        // 쿼리 수
        q = Integer.parseInt(br.readLine());

        for (int i = 0; i < q; i++) {
            // 마지막 요소가 탐색 시작 폴더
            String[] folders = br.readLine().split("/");
            String current = folders[0];
            for (int j = 1; j < folders.length; j++) {
                current = folders[j];
            }

            fileSet = new HashSet<>();
            fileCount = 0;

            dfs(current);
            System.out.println(fileSet.size() + " " + fileCount);
        }
    }

    static void dfs(String folderName) {

        // folderMap에 해당 키가 없으면 종료
        if (!folderMap.containsKey(folderName)) return;

        for (String child : folderMap.get(folderName)) {
            if (isFolder.getOrDefault(child, false)) {
                dfs(child); // 자식이 폴더면 재귀 탐색
            } else {
                // 자식이 파일이면
                fileSet.add(child); // 파일 이름 저장
                fileCount++; //전체 파일 카운트 증가
            }
        }
    }
}
