import java.io.*;
import java.util.Arrays;

public class Baekjoon1182 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,S;

    static int [] numbers;
    static boolean [] visited;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        S = Integer.parseInt(tokens[1]);

        numbers = new int[N];
        visited = new boolean[N];
        Arrays.fill(visited,false);
        tokens = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            numbers[i] = Integer.parseInt(tokens[i]);
        }
        backtracking(0,0);
        if(S == 0) count--;
        System.out.println(count);
    }
    static void backtracking(int index, int sum){
        if(index == N){
            if(sum == S){
                count++;
            }
            return;
        }

        backtracking(index+1,sum+numbers[index]);
        backtracking(index+1,sum);
    }
}
