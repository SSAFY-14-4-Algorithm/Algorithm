import java.io.*;
import java.util.*;
/*
 *  메모리:74368KB
 *  시간:	616ms
 */
public class Baekjoon_21939_문제추천시스템Version1 {
    // 문제 번호-> 난이도를 저장하는 해시맵
    static Map<Integer, Integer> problemMap = new HashMap<>();

    // 어려운 문제 추천용 pq
    static PriorityQueue<int[]> difficult = new PriorityQueue<>((a, b) -> {
        if(a[1] != b[1]) return b[1] - a[1]; // 난이도 기준 내림차순
        return b[0] - a[0];                 // 문제 번호 기준 내림차순
    });

    // 쉬운 문제 추천용 pq
    static PriorityQueue<int[]> easy = new PriorityQueue<>((a, b) -> {
        if(a[1] != b[1]) return a[1] - b[1]; // 난이도 기준 오름차순
        return a[0] - b[0];                 // 문제 번호 기준 오름차순
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++) {
            String[] s = br.readLine().split(" ");
            int P = Integer.parseInt(s[0]); 
            int L = Integer.parseInt(s[1]); 
            addProblem(P, L); //문제 추가
        }

        //명령어 개수 입력
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 명령어 처리
        for(int i=0;i<M;i++) {
            String[] command = br.readLine().split(" ");
            if(command[0].equals("add")) {
                // add P L : 새로운 문제 추가
                int P = Integer.parseInt(command[1]);
                int L = Integer.parseInt(command[2]);
                addProblem(P, L);

            } else if(command[0].equals("solved")) {
                // solved P : 문제 제거
                int P = Integer.parseInt(command[1]);
                problemMap.remove(P); // 실질적 제거는 Map에서만

            } else if(command[0].equals("recommend")) {
                // recommend x : 문제 추천
                int x = Integer.parseInt(command[1]);

                if(x == 1) {
                    // 가장 어려운 문제 추천 
                    while(true) {
                        int[] top = difficult.peek();
                        // 현재 top이 유효한 문제인지 검사
                        if(problemMap.containsKey(top[0]) && problemMap.get(top[0]) == top[1]) {
                            sb.append(top[0]).append("\n");
                            break;
                        }
                        difficult.poll(); // 유효하지 않으면 버림 
                    }
                } else {
                    // 가장 쉬운 문제 추천 
                    while(true) {
                        int[] top = easy.peek();
                        // 현재 top이 유효한 문제인지 검사
                        if(problemMap.containsKey(top[0]) && problemMap.get(top[0]) == top[1]) {
                            sb.append(top[0]).append("\n");
                            break;
                        }
                        easy.poll(); // 유효하지 않으면 버림 
                    }
                }
            }
        }

        System.out.print(sb.toString());
    }

    // 문제 추가 함수
    static void addProblem(int P, int L) {
        problemMap.put(P, L);         
        difficult.add(new int[]{P, L}); 
        easy.add(new int[]{P, L}); 
    }
}
