import java.util.*;
import java.io.*;

/**
 * 25/06/16
 * Java8 | 실행시간: 160 ms, 메모리 : 17,424KB
 */
public class Baekjoon1516 {
	
	public static void main(String[] args) throws IOException {
        int N = readInt();

        int[] inDegree = new int[N+1];
        int[] cost = new int[N+1];
        int[] result = new int[N+1];
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i=0;i<=N;i++) arr.add(new ArrayList<>());
        Queue<Integer> q = new ArrayDeque<>();

        for (int v=1;v<=N;v++){
            cost[v] = readInt();
            int u = 0;
            while((u=readInt())!=-1){
                arr.get(u).add(v);
                inDegree[v]++;
            }
        }

        for (int i=1;i<=N;i++) {
            if (inDegree[i]==0){
                q.offer(i);
                result[i] = cost[i];
            }
        }

        while(!q.isEmpty()){
            int c = q.poll();
            for (int nextPoint : arr.get(c)){
                result[nextPoint] = Math.max(result[nextPoint], result[c]+cost[nextPoint]);
                if (--inDegree[nextPoint]==0) q.offer(nextPoint);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i=1;i<=N;i++) sb.append(result[i]).append("\n");
        System.out.print(sb);
    }

    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
