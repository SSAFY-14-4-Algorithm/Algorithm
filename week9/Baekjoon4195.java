import java.io.*;
import java.util.*;

public class Baekjoon4195 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static HashMap<String, Integer> memberIndexMap;
    private static ArrayList<Integer> parents;
    private static ArrayList<Integer> sizes;

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            solution();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() throws IOException{
        memberIndexMap = new HashMap<>();
        parents = new ArrayList<>();
        sizes = new ArrayList<>();
        parents.add(0);
        sizes.add(0);
        int numberOfPeople = 0;

        int F = Integer.parseInt(br.readLine());
        for(int f=0;f<F;f++){
            String [] tokens = br.readLine().split(" ");
            String personA = tokens[0];
            String personB = tokens[1];

            if(!memberIndexMap.containsKey(personA)){
                //기존에 등장하지 않은 친구 이름일 경우
                memberIndexMap.put(personA,++numberOfPeople);
                parents.add(numberOfPeople);
                sizes.add(1);
            }
            if(!memberIndexMap.containsKey(personB)){
                //기존에 등장하지 않은 친구 이름일 경우
                memberIndexMap.put(personB,++numberOfPeople);
                parents.add(numberOfPeople);
                sizes.add(1);
            }

            //유니온 연산
            int personAIndex = memberIndexMap.get(personA);
            int personBIndex = memberIndexMap.get(personB);

            union(personAIndex,personBIndex);
        }
    }

    private static void union(int i, int j) throws IOException {
        int rootA = find(i);
        int rootB = find(j);

        if(rootA != rootB){
            if(sizes.get(rootA) < sizes.get(rootB)){
                parents.set(rootA,rootB);
                sizes.set(rootB,sizes.get(rootA) + sizes.get(rootB));
                bw.write(sizes.get(rootB)+"\n");
            }else{
                parents.set(rootB,rootA);
                sizes.set(rootA,sizes.get(rootA) + sizes.get(rootB));
                bw.write(sizes.get(rootA)+"\n");
            }
        }else{
            bw.write(sizes.get(rootA)+"\n");
        }
    }

    private static int find(int index){
        if(index == parents.get(index)) return index;

        int root = find(parents.get(index));
        parents.set(index,root);
        return root;
    }
}
