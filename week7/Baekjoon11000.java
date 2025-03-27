import java.io.*;
import java.util.*;

public class Baekjoon11000 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static ArrayList<Study> studyArrayList;

    public static void main(String[] args) throws IOException{
        studyArrayList = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            String [] tokens = br.readLine().split(" ");
            int start = Integer.parseInt(tokens[0]);
            int end = Integer.parseInt(tokens[1]);

            Study study = new Study(start,end);
            studyArrayList.add(study);
        }

        Collections.sort(studyArrayList,(o1,o2) -> {
            if(o1.start == o2.start) return o1.end - o2.end;
            return o1.start - o2.start;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(studyArrayList.get(0).end);

        for(int i=1;i<N;i++){
            Study target = studyArrayList.get(i);

            if(target.start >= pq.peek()) pq.poll();

            pq.add(target.end);
        }

        bw.write(pq.size()+"\n");
        bw.flush();
        bw.close();
        br.close();
    }


    private static class Study{
        int start,end;
        public Study(int start,int end){
            this.start = start;
            this.end = end;
        }
    }
}
