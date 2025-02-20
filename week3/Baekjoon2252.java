package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon2252 {

    public static StringBuilder stringBuilder = new StringBuilder();
    public static ArrayList<ArrayList<Integer>> arr;
    public static void main(String[] args) throws IOException {

//        기본 세팅
//        - 그래프 정보 저장
//        - 진입 차수 정보 저장
//                - 큐에 진입차수 0인 정보 저장
//
//        큐가 빌때까지 반복
//        1. 큐에서 하나 꺼내기
//        2. 꺼낸 정보 사용하기
//                - 출력
//        3. 인접한 정점(to)들 체크하기
//                - to 정점의 진입차수 -1
//                - -1했을 때 진입차수가 0이라면 큐에 넣기


        /**
         * 기본세팅
         *
         * 1. N과 M 입력받기
         * 2. 각 노드의 arr[][]배열 만들기
         * 3. 연결된 간선이 있을시 arr배열에 1로 연결하기
         */


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new ArrayList<ArrayList<Integer>>();
        int[] inputNum = new int[N + 1];
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0;i<=N;i++){
            arr.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int firstNum = Integer.parseInt(st.nextToken());
            int secondNum = Integer.parseInt(st.nextToken());
            arr.get(firstNum).add(secondNum);
        }
        for (int i =1; i <=N; i++) {
            for (int j = 0; j < arr.get(i).size(); j++) {
                if (arr.get(i).get(j) >= 0) {
                    inputNum[arr.get(i).get(j)]++;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (inputNum[i] == 0) {
                queue.add(i);
            }
        }


        while (!queue.isEmpty()) {
//            System.out.println(queue);
            int pollNum = queue.poll();
            stringBuilder.append(pollNum).append(" ");
            for(int a:arr.get(pollNum)){
                if (--inputNum[a] == 0) {
                    queue.add(a);
                }
            }
        }
        System.out.println(stringBuilder);

    }
}