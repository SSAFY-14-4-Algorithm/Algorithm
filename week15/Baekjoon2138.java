import java.io.*;
import java.util.*;
/*
 * 메모리: 16640
 * 시간: 116
 * 
 * 스위치 누르는 횟수 
 * 그리디 
 * 
 */

public class Main {
    static int N;
    static int[] target, original;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        original = new int[N];
        target = new int[N];

        String input = br.readLine();
        for (int i = 0; i < N; i++) {
            original[i] = input.charAt(i) - '0';
        }

        input = br.readLine();
        for (int i = 0; i < N; i++) {
            target[i] = input.charAt(i) - '0';
        }

        int res1 = simulate(false); // 0번 누르지 않는 경우 
        int res2 = simulate(true);  // 0번 누르는 경우 

        if (res1 == -1 && res2 == -1) {
            System.out.println(-1);
        } else if (res1 == -1) {
            System.out.println(res2);
        } else if (res2 == -1) {
            System.out.println(res1);
        } else {
            System.out.println(Math.min(res1, res2));
        }
    }

    static int simulate(boolean pressFirst) {
        int[] curr = Arrays.copyOf(original, N);
        int count = 0;
        if (pressFirst) {
            toggle(curr, 0);
            count++;
        }
        for (int i = 1; i < N; i++) {
            if (curr[i - 1] != target[i - 1]) {
                toggle(curr, i);
                count++;
            }
        }
        if (Arrays.equals(curr, target)) { //목표와 동일한지 확인 
            return count;
        } else {
            return -1;
        }
    }
    static void toggle(int[] arr, int i) { //i번째 스위치 누르기 
        for (int j = i - 1; j <= i + 1; j++) {
            if (j >= 0 && j < N) {
                arr[j] ^= 1; 
            }
        }
    }
}
