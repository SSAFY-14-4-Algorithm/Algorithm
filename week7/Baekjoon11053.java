import java.io.*;
import java.util.*;

public class Baekjoon11053 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<Integer> arrayList = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        String [] input = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            arrayList.add(Integer.parseInt(input[i]));
        }
        
        int[] answer = new int[N];
        Arrays.fill(answer,1);
        for(int i=0;i<arrayList.size();i++){
            for(int j=i+1;j<arrayList.size();j++){
                if(arrayList.get(i)<arrayList.get(j)){
                    answer[j] = Math.max(answer[i]+1,answer[j]);
                }
            }
        }
        int max =Integer.MIN_VALUE;
        for (int i=0;i<answer.length;i++){
            if(max<answer[i]){
                max = answer[i];
            }
        }
        bw.write(Integer.toString(max)+"\n");
        bw.flush();
    }
}
