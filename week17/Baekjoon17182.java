import java.io.*;
/**
 * 250616
 * Java8 | 실행시간: 96 ms, 메모리: 11,912KB
 */
public class Baekjoon17182 {

    static int N, dt[][], result = Integer.MAX_VALUE;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        // 모든 행성을 탐사하기 위한 최소 시간 출력
        // 다시 시작 행성으로 돌아올 필요 X, 중복 방문 가능
        N = readInt();
        int K = readInt();  // 0<=K<N
        dt = new int[N][N];
        for (int i=0;i<N;i++) for(int j=0;j<N;j++) dt[i][j] = readInt();
        
        for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++){
            dt[i][j] = Math.min(dt[i][j], dt[i][k]+dt[k][j]);
        }

        isVisited = new boolean[N];
        isVisited[K] = true;
        System.out.print(recursive(K, 0, dt[K][K]));
    }

    private static int recursive(int v, int ct, int sum){
        if (sum>=result) return result;   // 가지치기
        if (ct==N-1) return sum;

        for (int i=0;i<N;i++){
            if (!isVisited[i]){
                isVisited[i] = true;
                result = Math.min(result, recursive(i, ct+1, sum+dt[v][i]));
                isVisited[i] = false;
            }
        }
        return result;
    }

    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }

}
