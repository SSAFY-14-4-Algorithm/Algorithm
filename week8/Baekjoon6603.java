import java.io.*;
import java.util.Arrays;

public class Baekjoon6603 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int [] arr;
    public static boolean [] visited;
    public static void main(String[] args) throws IOException{
        while(true){
            String [] tokens = br.readLine().split(" ");
            int N = Integer.parseInt(tokens[0]);
            arr = new int[7];
            visited = new boolean[N];
            if(N==0){
                break;
            }
            int [] nums = new int[N];
            for(int i=0;i<N;i++){
                nums[i]=Integer.parseInt(tokens[i+1]);
            }
            Arrays.sort(nums);

            dfs(N,nums,1);
            bw.write("\n");
        }
        bw.flush();
    }
    public static void dfs(int N,int [] nums,int depth) throws IOException{
        if(depth==7){
            for(int i=1;i<7;i++){
                bw.write(nums[arr[i]]+" ");
            }
            bw.write("\n");
            return;
        }
        for(int i=0;i<N;i++){
            if(arr[depth-1] > i){
                continue;
            }
            if(!visited[i]){
                visited[i]=true;
                arr[depth]=i;
                dfs(N,nums,depth+1);
                visited[i]=false;
            }
        }
        return;
    }
}
