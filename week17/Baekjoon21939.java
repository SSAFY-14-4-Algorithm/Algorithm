import java.io.*;
import java.util.*;

/**
 * 63944kb, 604ms
 */
public class Baekjoon21939 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static TreeSet<Problem> problemTreeSet;
    static HashMap<Integer, Problem> problemHashMap;

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());    //문제 수
        problemHashMap = new HashMap<>();
        problemTreeSet = new TreeSet<>();
        for(int i=0;i<N;i++){
            String [] tokens = br.readLine().split(" ");
            int p = Integer.parseInt(tokens[0]);
            int l = Integer.parseInt(tokens[1]);

            Problem problem = new Problem(p, l);
            problemHashMap.put(p,problem);
            problemTreeSet.add(problem);
        }

        int M = Integer.parseInt(br.readLine());

        for(int i=0;i<M;i++){
            String [] tokens = br.readLine().split(" ");
            String command = tokens[0];

            if(command.equals("recommend")){
                int x = Integer.parseInt(tokens[1]);
                Problem answer = null;
                if(x == -1){    //가장 쉬운 문제 번호 출력
                    answer = problemTreeSet.first();
                }else{          //가장 어려운 문제 번호 출력
                    answer = problemTreeSet.last();
                }
                bw.write(answer.number+"\n");
            }else if(command.equals("add")){
                int p = Integer.parseInt(tokens[1]);
                int l = Integer.parseInt(tokens[2]);

                Problem problem = new Problem(p, l);
                problemHashMap.put(p,problem);
                problemTreeSet.add(problem);
            }else if(command.equals("solved")){
                int problemNumber = Integer.parseInt(tokens[1]);
                Problem problem = problemHashMap.get(problemNumber);

                problemHashMap.remove(problemNumber);
                problemTreeSet.remove(problem);
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static class Problem implements Comparable<Problem>{
        int number;
        int difficult;

        public Problem(int number, int difficult){
            this.difficult = difficult;
            this.number = number;
        }

        public int compareTo(Problem o){
            if(o.difficult == this.difficult){
                return this.number - o.number;
            }
            return this.difficult - o.difficult;
        }
    }
}
