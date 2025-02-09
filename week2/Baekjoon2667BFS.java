import java.io.*;
import java.util.*;

public class Baekjoon2667BFS {
    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] array = new int[N][N];
        for(int i = 0; i < N; i++){
            String[] tmp = br.readLine().split("");
            for(int j = 0; j < N; j++){
                array[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        int answer = 0;
        boolean[][] visited = new boolean[N][N];
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if (!visited[i][j] && array[i][j] == 1){
                    answer += 1;
                    int res = 0;
                    Queue<Node> q = new LinkedList<>();
                    q.offer(new Node(i, j));
                    visited[i][j] = true;
                    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                    while(!q.isEmpty()){
                        Node now = q.poll();
                        int x = now.x;
                        int y = now.y;
                        res += 1;
                        for(int k = 0; k < 4; k++){
                            int nx = x + dir[k][0];
                            int ny = y + dir[k][1];
                            if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && array[nx][ny] == 1){
                                visited[nx][ny] = true;
                                q.offer(new Node(nx, ny));
                            }
                        }
                    }
                    ans.add(res);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(answer).append("\n");
        Collections.sort(ans);
        for(int i = 0; i < ans.size(); i++){
        	sb.append(ans.get(i)).append("\n");
        }
        System.out.println(sb);
    }
}