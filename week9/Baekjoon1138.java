import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1138 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static int [] line;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        line = new int[N];

        String [] tokens = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            int count = Integer.parseInt(tokens[i]);

            for(int j=0;j<N;j++){
                //자신보다 큰 사람이 없는 경우
                if(count == 0){
                    if(line[j] == 0){
                        line[j] = i+1;
                        break;
                    }else{
                        continue;
                    }
                }else if(line[j] == 0){
                    //자신보다 큰 사람이 존재하는 경우...-> 다음 칸으로 이동
                    count--;
                }
            }
        }

        for(int i=0;i<N;i++){
            bw.write(line[i]+" ");
        }bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
