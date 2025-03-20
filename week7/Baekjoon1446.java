import java.io.*;

public class Baekjoon1446 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Road[] roads;
    private static int N,D;
    private static int [] dp;

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        D = Integer.parseInt(tokens[1]);

        roads = new Road[N];
        dp = new int[D+1];

        for(int i=0;i<N;i++){
            tokens = br.readLine().split(" ");
            int start = Integer.parseInt(tokens[0]);
            int end = Integer.parseInt(tokens[1]);
            int cost = Integer.parseInt(tokens[2]);

            roads[i] = new Road(start,end,cost);
        }
        for(int i=1;i<=D;i++){
            dp[i] = i;
        }

        for(int i=1;i<=D;i++){
            dp[i] = Math.min(dp[i],dp[i-1]+1);

            for(int j=0;j<N;j++){
                if(roads[j].to == i){
                    dp[i] = Math.min(dp[i],dp[roads[j].start] + roads[j].cost);
                }
            }
        }

        bw.write(dp[D]+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Road{
        int start,to,cost;

        public Road(int start,int to, int cost){
            this.start = start;
            this.to = to;
            this.cost = cost;
        }
    }
}
