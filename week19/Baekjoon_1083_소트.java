import java.io.*;
/*
 * 메모리:11564KB
 * 시간:	64ms
 */
public class Baekjoon_1083_소트 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }
        int S = Integer.parseInt(br.readLine());  

        for (int i = 0; i < n && S > 0; i++) {
            // i 부터 i+S 범위 내 최댓값 인덱스 찾기
            int maxIdx = i;
            int limit = Math.min(n - 1, i + S);
            for (int j = i + 1; j <= limit; j++) {
                if (arr[j] > arr[maxIdx]) {
                    maxIdx = j;
                }
            }
            // 2) maxIdx 값을 i 위치로 swap
            int val = arr[maxIdx];
            for (int j = maxIdx; j > i; j--) {
                arr[j] = arr[j - 1];
            }
            arr[i] = val;
            S -= (maxIdx - i);
        }
        for(int i = 0; i < n; i++){
            System.out.print(arr[i]+" ");
        }
    }
}