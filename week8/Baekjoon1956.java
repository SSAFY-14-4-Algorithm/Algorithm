import java.io.*;
import java.util.*;

public class Baekjoon1956 {
    static int v, e;
    static final int INF = 987654321;

    //메모리 66580kb 시간700ms
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        
        int[][] arr = new int[v+1][v+1];
        
        for (int i = 1; i <= v; i++) {
        	for (int j = 1; j <= v; j++) {
        		if(i != j) arr[i][j] = INF;
        	}
        }
        
        for (int i = 0; i < e; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	arr[a][b] = c;
        }
        
        for (int k = 1; k <= v; k++) {
        	for (int i = 1; i <= v; i++) {
        		for (int j = 1; j <= v; j++) {
        			if(i == j) continue;
        			if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
        		}
        	}
        }
        
        int ans = INF;
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i == j) continue;

                if (arr[i][j] != INF && arr[j][i] != INF) {
                    ans = Math.min(ans, arr[i][j] + arr[j][i]);
                }
            }
        }
        
        ans = (ans == INF) ? -1 : ans;
        System.out.println(ans);
    }


    
}
