import java.io.IOException;
//JAVA8 12156KB	132ms

public class Baekjoon1987 {
    private static final int[] dx = { 1, -1, 0, 0 };
    private static final int[] dy = { 0, 0, 1, -1 };
    private static byte[][] board;
    private static int R;
    private static int C;
    private static int max = 1;
    private static int[][] visited;

    public static void main(String[] args) throws IOException {
        // 입력 및 초기화
        R = readInt();
        C = readInt();
        board = new byte[R][C + 1]; // CRLF인 경우 C+2, LF인 경우 C+1로 처리
        visited = new int[R][C];
        for (int i = 0; i < R; i++)
            System.in.read(board[i]); // 각 행의 데이터를 읽음

        // 시작 위치 (0,0)의 알파벳을 mask에 반영: 알파벳 'A'는 1<<1, 'B'는 1<<2, ... (board[i][j] & 31)
        int startMask = 1 << (board[0][0] & 31);
        dfs(0, 0, startMask, 1);

        // 출력
        System.out.print(max);
    }

    /**
     * 주어진 좌표 (x, y)에서 시작해 dfs를 이용하여 이동 가능한 경로의 최대 길이를 탐색.
     * 
     * @param x           현재 x 좌표
     * @param y           현재 y 좌표
     * @param usedLetters 현재까지 사용한 알파벳들을 나타내는 비트마스크
     * @param count       현재까지 이동한 칸의 수(경로 길이)
     */
    private static void dfs(int x, int y, int usedLetters, int count) {
        // 최대 길이가 26이면 모든 알파벳을 사용한 것이므로 탐색 종료
        if ((max = Math.max(max, count)) == 26) {
            return;
        }
        visited[x][y] = usedLetters;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0 <= nx && nx < R && 0 <= ny && ny < C) {
                int nextLetter = 1 << (board[nx][ny] & 31); // 다음칸 알파벳 mask
                boolean notUsedBefore = (usedLetters & nextLetter) == 0; // 이전에 쓰인 알파벳인가?
                int nextLetterAdded = usedLetters | nextLetter; // 새로운 비트마스크에 다음 알파벳을 추가
                // 이전에 안쓴 알파벳이고
                // 같은 알파벳 집합으로 (nx, ny)에 방문한적 없으면(중복 계산 방지) 재귀 호출
                if (notUsedBefore && (nextLetterAdded != visited[nx][ny])) {
                    dfs(nx, ny, nextLetterAdded, count + 1);
                }
            }
        }
    }

    private static int readInt() throws IOException {
        int c;
        int n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n * 10) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}
