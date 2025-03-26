import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon16234 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int [] dx = {0,0,-1,1};
    private static int [] dy = {-1,1,0,0};

    private static int N,R,L;
    private static int [][] population;
    private static boolean [][] visited;

    public static void main(String[] args) throws IOException {
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        L = Integer.parseInt(tokens[1]);
        R = Integer.parseInt(tokens[2]);

        population = new int[N][N];

        for(int i=0;i<N;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                population[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        int day = 0;
        while (true){
            visited = new boolean[N][N];
            boolean moved = false;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(visited[i][j]) continue;

                    if(bfs(i,j)){
                        moved = true;
                    }
                }
            }
            if(!moved) break;
            day++;
        }

        bw.write(day+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean bfs(int x,int y){
        Queue<Node> queue = new ArrayDeque<>();
        LinkedList<Node> union = new LinkedList<>();
        Node startNode = new Node(x,y);
        union.add(startNode);
        queue.add(startNode);
        visited[x][y] = true;

        int totalPopulation = population[x][y];
        while (!queue.isEmpty()){
            Node curNode = queue.poll();

            for(int d=0;d<4;d++){
                int nx = curNode.x + dx[d];
                int ny = curNode.y + dy[d];

                if(!inRange(nx,ny) || visited[nx][ny] || !checkInRange(population[curNode.x][curNode.y],population[nx][ny])) continue;

                totalPopulation += population[nx][ny];
                Node newNode = new Node(nx,ny);
                queue.add(newNode);
                union.add(newNode);
                visited[nx][ny] = true;
            }
        }

        if(union.size() > 1){
            int newPopulation = totalPopulation / union.size();
            for(Node node : union){
                population[node.x][node.y] = newPopulation;
            }

            return true;
        }
        return false;
    }

    private static boolean checkInRange(int i, int j) {
        int diff = Math.max(i,j) - Math.min(i,j);

        return diff >= L && diff <= R;
    }

    private static boolean inRange(int x,int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}