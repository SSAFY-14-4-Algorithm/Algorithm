import java.util.*;
import java.io.*;

public class Baekjoon2579 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<Integer> oneStep = new ArrayList<>();
    public static ArrayList<Integer> twoStep = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int [] score = new int [N+3];
        for(int i=0;i<N;i++){
            score[i]=Integer.parseInt(br.readLine());
        }

        //0번째 계단
        oneStep.add(0);
        twoStep.add(0);
        //1번째 계단
        oneStep.add(score[0]);
        twoStep.add(score[0]);
        //2번째 계단
        oneStep.add(score[0]+score[1]);
        twoStep.add(score[1]);

        for(int i=2;i<N;i++){
            int oneStepMax = twoStep.get(i)+score[i];
            int twoStepMax = Math.max(oneStep.get(i-1),twoStep.get(i-1))+score[i];
            oneStep.add(oneStepMax);
            twoStep.add(twoStepMax);
        }
        bw.write(Integer.toString(Math.max(oneStep.get(oneStep.size()-1),twoStep.get(twoStep.size()-1)))+"\n");
        bw.flush();
        bw.close();
    }
}
