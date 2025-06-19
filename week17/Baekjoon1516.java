import java.io.*;
import java.util.*;

/**
 * 23764kb, 252ms
 */
public class Baekjoon1516 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();

        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        int [] inDegree = new int[N+1];     //각 건물이 필요로 하는 선행 건물의 수
        int [] buildTime = new int[N+1];    //각 건물을 짓는데 걸리는 시간
        int [] resultTime = new int[N+1];   //각 건물의 최종 완성 시간
        
        for(int i=1;i<=N;i++){
            String [] tokens = br.readLine().split(" ");
            buildTime[i] = Integer.parseInt(tokens[0]);
            
            for(int j=1;j<tokens.length;j++){
                int prerequisite = Integer.parseInt(tokens[j]);
                if(prerequisite == -1) break;

                graph.get(prerequisite).add(i);
                inDegree[i]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=1;i<=N;i++){
            if(inDegree[i] == 0){
                queue.add(i);
                resultTime[i] = buildTime[i];
            }
        }

        //위상정렬 실행
        while(!queue.isEmpty()){
            int current = queue.poll();

            for(int next : graph.get(current)){
                resultTime[next] = Math.max(resultTime[next], resultTime[current] + buildTime[next]);
                inDegree[next]--;

                if(inDegree[next] == 0){
                    queue.add(next);
                }
            }
        }

        for(int i=1;i<=N;i++){
            bw.write(resultTime[i]+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
