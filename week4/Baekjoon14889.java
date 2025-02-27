import java.io.*;
import java.util.*;

public class Baekjoon14889 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[] arr;
    public static boolean[] visited;
    public static ArrayList<Integer> scoreList = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        arr = new int[N/2];
        visited = new boolean[N];

        for(int i=0;i<N;i++){
            String[] tokens = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        dfs(N, map, 0, 0);
        Collections.sort(scoreList);
        bw.write(scoreList.get(0)+"\n");
        bw.flush();
    }

    public static void dfs(int N, int[][] map, int depth, int start) throws IOException{
        if (depth == N/2) {
            ArrayList<Integer> firstTeam = new ArrayList<>();
            ArrayList<Integer> secondTeam = new ArrayList<>();
            int firstTeamScore = 0;
            int secondTeamScore = 0;
            for (int val : arr) {
                firstTeam.add(val);
            }
            for(int i=0;i<N;i++){
                if(!firstTeam.contains(i)){
                    secondTeam.add(i);
                }
            }

            for(int i=0;i<firstTeam.size();i++){
                for(int j=i+1;j<firstTeam.size();j++){
                    int mem_1 = firstTeam.get(i);
                    int mem_2 = firstTeam.get(j);

                    firstTeamScore+=(map[mem_1][mem_2]+map[mem_2][mem_1]);
                }
            }
            for(int i=0;i<secondTeam.size();i++){
                for(int j=i+1;j<secondTeam.size();j++){

                    int mem_1 = secondTeam.get(i);
                    int mem_2 = secondTeam.get(j);

                    secondTeamScore+=(map[mem_1][mem_2]+map[mem_2][mem_1]);
                }
            }
            scoreList.add(Math.abs(firstTeamScore-secondTeamScore));
            return;
        }
        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = i;
                dfs(N, map, depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}
