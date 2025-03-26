import java.io.*;

public class Baekjoon14888 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int [] numbers;
    static int [] operators = new int[4];

    static int minValue = Integer.MAX_VALUE;
    static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        String [] tokens = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            numbers[i] = Integer.parseInt(tokens[i]);
        }
        tokens = br.readLine().split(" ");
        for(int i=0;i<4;i++){
            operators[i] = Integer.parseInt(tokens[i]);
        }

        solution(numbers[0],1);

        bw.write(maxValue+"\n");
        bw.write(minValue+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
    static void solution(int num, int depth){

        if (depth == N) {
            maxValue = Math.max(maxValue, num);
            minValue = Math.min(minValue, num);
            return;
        }

        for(int i=0;i<4;i++){
            if(operators[i] > 0){
                operators[i]--;

                switch(i){
                    case 0 :
                        solution(num+numbers[depth], depth+1);
                        break;
                    case 1:
                        solution(num-numbers[depth], depth+1);
                        break;
                    case 2:
                        solution(num*numbers[depth], depth+1);
                        break;
                    case 3:
                        solution(num/numbers[depth], depth+1);
                        break;
                }
                operators[i]++;
            }
        }
    }
}
