package study5;

import java.io.*;
import java.util.*;

/**
 * 이건 걍 증가하는 수열 뭐시기 뭐시기 하면 되는거 아님?
 * 걍 은 아니네 어렵노
 * 
 * 
 * 메모리 : 17312 KB
 * 시간 : 144 ms
 */

public class Baekjoon2550 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] switches = new int[N + 1]; // 스위치 번호 (1-based)
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] bulbPos = new int[N + 1]; // 전구 번호의 위치 (1-based)
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int bulb = Integer.parseInt(st.nextToken());
            bulbPos[bulb] = i;
        }
        
        int[] sequence = new int[N + 1]; // 스위치 순서에 따른 전구 위치
        for (int i = 1; i <= N; i++) {
            sequence[i] = bulbPos[switches[i]];
        }
        
        // LIS를 찾기 위한 자료구조
        int[] lis = new int[N + 1];
        int[] parent = new int[N + 1];
        Arrays.fill(parent, -1);
        int length = 0;
        
        for (int i = 1; i <= N; i++) {
            int low = 1;
            int high = length;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (sequence[lis[mid]] < sequence[i]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            
            int pos = low;
            parent[i] = (pos > 1) ? lis[pos - 1] : -1;
            
            if (pos > length) {
                lis[pos] = i;
                length = pos;
            } else if (sequence[i] < sequence[lis[pos]]) {
                lis[pos] = i;
            }
        }
        
        // LIS 구성 요소 추적
        int[] result = new int[length];
        int current = lis[length];
        for (int i = length - 1; i >= 0; i--) {
            result[i] = switches[current];
            current = parent[current];
        }
        
        Arrays.sort(result);
        
        // 출력
        System.out.println(length);
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}