package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Baekjoon7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int M = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                String DI = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                // ! 값 추가
                if (DI.equals("I"))
                    treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
                // ! 값 제거
                else if (DI.equals("D")) {
                    if (!treeMap.isEmpty()) {
                        // ! -1일 경우 최솟값 삭제
                        if (num < 0) {
                            int minKey = treeMap.firstKey();
                            if (treeMap.get(minKey) == 1)
                                treeMap.remove(minKey);
                            else
                                treeMap.put(minKey, treeMap.get(minKey) - 1);
                        }
                        // ! 1일 경우 최대값 삭제
                        else if (num > 0) {
                            int maxKey = treeMap.lastKey();
                            if (treeMap.get(maxKey) == 1)
                                treeMap.remove(maxKey);
                            else
                                treeMap.put(maxKey, treeMap.get(maxKey) - 1);
                        }
                    }
                }
            }
            if (!treeMap.isEmpty())
                sb.append(treeMap.lastKey() + " " + treeMap.firstKey()).append('\n');
            else
                sb.append("EMPTY").append('\n');
        }
        System.out.println(sb.toString());
    }
}