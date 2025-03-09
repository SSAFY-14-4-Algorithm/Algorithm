import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int count = 0;
    static int[] queens; // 퀸의 열 위치 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        N = Integer.parseInt(st.nextToken());
        
        queens = new int[N];
        backtrack(0);
        
        System.out.println(count);
    }

    static void backtrack(int row) {
        if (row == N) { // 모든 행에 퀸 있을 경우
            count++;
            return;
        }
        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                queens[row] = col; 
                backtrack(row + 1);
            }
        }
    }

    static boolean isSafe(int row, int col) {
        for (int prevRow = 0; prevRow < row; prevRow++) {
            int prevCol = queens[prevRow];
            if (prevCol == col) return false; // 같은 열에 퀸이 있으면 불가
            if (Math.abs(prevRow - row) == Math.abs(prevCol - col)) return false; // 대각선 체크
        }
        return true;
    }
}
