package week13;

/**
 * <h1>BAEKJOON 2179번 비슷한 단어</h1>
 * <p>
 * JAVA11 : 메모리 18,716KB, 시간 192ms
 * </p>
 * 
 * @author KIM MINGYU jun3021303@gmail.com
 * @since 2025-05-01
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Baekjoon2179 {
    private static class Word {
        String word;
        int index;

        Word(String word, int idx) {
            this.word = word; // 실제 단어
            this.index = idx; // 입력된 순서
        }
    }

    // 공통 접두사 길이 계산 (인접 비교용)
    private static int lcp(String a, String b) {
        int len = Math.min(a.length(), b.length());
        int i = 0;
        while (i < len && a.charAt(i) == b.charAt(i))
            i++;
        return i;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Word[] arr = new Word[N];
        String[] original = new String[N];
        for (int i = 0; i < N; i++) {
            String w = br.readLine();
            arr[i] = new Word(w, i);
            original[i] = w;
        }

        // 사전 순 정렬(비슷한 접두사 단어모으기)
        Arrays.sort(arr, Comparator.comparing(o -> o.word));

        // 인접한 쌍 최대 LCP 찾기
        int maxLCP = 0;
        for (int i = 0; i < N - 1; i++) {
            maxLCP = Math.max(maxLCP, lcp(arr[i].word, arr[i + 1].word));
        }

        // maxLCP == 0 인 경우, 답은 입력 순서 0,1
        if (maxLCP == 0) {
            sb.append(original[0]).append("\n").append(original[1]);
            System.out.print(sb.toString());
            return;
        }

        // 길이 maxLCP의 접두사 그룹화
        // Map<접두사, {첫번째 단어, 두번째 단어}>
        Map<String, int[]> prefixMap = new HashMap<>();
        for (Word w : arr) {
            if (w.word.length() < maxLCP)
                continue;
            String prefix = w.word.substring(0, maxLCP);
            int[] indices = prefixMap.get(prefix);
            if (indices == null) {
                // 처음: indices[0]=현재 순서, indices[1]=무한대
                prefixMap.put(prefix, new int[] { w.index, Integer.MAX_VALUE });
            } else {
                // 이미 그룹 존재: 두번째 순서 업데이트
                if (w.index < indices[0]) {
                    indices[1] = indices[0]; // 기존 1위 → 2위로
                    indices[0] = w.index; // 새로 본 w.index를 1위에
                } else if (w.index < indices[1]) {
                    indices[1] = w.index; // 기존 2위보다 앞서면 2위를 갱신
                }
            }
        }

        // 입력 순서 기준으로 최종 선택
        int bestS = N;
        int bestT = N;
        for (int[] indices : prefixMap.values()) {
            // 두번째 순서가 있어야 유효
            if (indices[1] == Integer.MAX_VALUE)
                continue;
            int s = Math.min(indices[0], indices[1]);
            int t = Math.max(indices[0], indices[1]);
            if (s < bestS || (s == bestS && t < bestT)) {
                bestS = s;
                bestT = t;
            }
        }

        // 결과 출력
        sb.append(original[bestS]).append("\n").append(original[bestT]);
        System.out.print(sb.toString());
    }
}