import java.io.*;
/*
 * 메모리:15512KB
 * 시간:	100ms
 */
public class Baekjoon2138_전구와스위치 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static char[] init, target;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        init = br.readLine().toCharArray();
        target = br.readLine().toCharArray();
        //첫 스위치 누를때와 안누를때중 최소
        int ans = Math.min(simulate(true), simulate(false));
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static int simulate(boolean pressFirst) {
        // 현재 전구 상태 복사
        char[] bulbs = new char[N];
        for(int i = 0; i < N; i++) bulbs[i] = init[i];

        int cnt = 0;
        if(pressFirst) {
            onOff(bulbs, 0);
            cnt++;
        }

        // 2번째 전구부터 끝까지, 이전 전구 상태가 목표와 다르면
        // 반드시 그 위치 스위치를 눌러야 함
        for(int i = 1; i < N; i++) {
            if(bulbs[i - 1] != target[i - 1]) {
                onOff(bulbs, i);
                cnt++;
            }
        }

        // 마지막 전구까지 맞췄는지 확인
        if(bulbs[N - 1] != target[N - 1]) return Integer.MAX_VALUE;
        return cnt;
    }

    // 스위치
    static void onOff(char[] bulbs, int idx) {
        for(int i = idx - 1; i <= idx + 1; i++) {
            if(i >= 0 && i < N) {
                bulbs[i] = (bulbs[i] == '0' ? '1' : '0');
            }
        }
    }
}
