import java.io.*;
import java.util.*;

/**
 * recommend x
 * x가 1인 경우 추천 리스트에서 가장 어려운 문제 번호 출력
 * 여러개라면 문제 번호가 큰 순대로
 *
 * x가 -1 인 경우 가장 쉬운 문제
 * 여러개라면 문제 번호가 작은 순대로
 *
 * add P L
 * 난이도가 L인 문제 번호 P 추가
 *
 * solved P
 * 추천 문제 리스트에서 문제 번호 P 제거
 *
 * 입력
 * N 문제 갯수
 * N 개의 문제 번호 P, 난이도 L
 * 명령문 갯수 M
 *
 * 풀이법
 * 문제 리스트 -> TreeSet<problem>,
 * problem -> 문제 번호, 난이도, comparable 로 난이도, 문제 번호 순 정렬
 *
 * recommend -> firstpoll or lastpoll
 *
 * add -> add in treeSet
 * 중복으로 다른 난이도로 들어올 수 있다.
 *
 * solved P
 * treeset에서 특정 번호 제거는 O(n) 이 걸림. 100,000 * 10,000 = 1억 -> 시간초과
 * lazy 하게 제거. 제거된 목록을 관리하는 트리셋을 생성, 같은 우선순위로 정렬,
 * recommend가 일어날 때 트리셋의 head와 비교
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();
        StringTokenizer st;

        treeSet = new TreeSet<>();
        deleted = new HashSet<>();
        hashMap = new HashMap<>();

        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(reader.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            add(P, L);
        }

        int M = Integer.parseInt(reader.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            String order = st.nextToken();

            switch (order) {
                case "add" :
                    int number = Integer.parseInt(st.nextToken());
                    int level = Integer.parseInt(st.nextToken());
                    add(number, level);
                    break;
                case "recommend" :
                    int x = Integer.parseInt(st.nextToken());
                    sb.append(recommend(x)).append("\n");
                    break;
                case "solved" :
                    number = Integer.parseInt(st.nextToken());
                    solved(number);
                    break;
            }
        }
        System.out.print(sb);
    }

    static TreeSet<Problem> treeSet;
    static HashSet<Integer> deleted;
    static HashMap<Integer, Integer> hashMap;

    //treeSet에서 문제를 뽑고, deleted와 비교하며 유효한 문제 출력
    static int recommend(int x) {
        if(x == -1) {
            while (!(treeSet.isEmpty())) {
                Problem problem = treeSet.first();
                if(deleted.contains(problem.number)) { //삭제해야 한다면? 무시
                    treeSet.pollFirst();
                    deleted.remove(problem.number);
                    hashMap.remove(problem.number);
                }
                else return problem.number;
            }
        }
        else {
            while (!(treeSet.isEmpty())) {
                Problem problem = treeSet.last();
                if(deleted.contains(problem.number)) { //삭제해야 한다면?
                    treeSet.pollLast();
                    deleted.remove(problem.number);
                    hashMap.remove(problem.number);
                }
                else return problem.number;
            }
        }
        return -1;
    }

    //hashmap과 treeset에 문제 저장
    static void add(int number, int level) {
        if(hashMap.containsKey(number)) { //중복된 삽입이라면, 덮어 씌우기
            treeSet.remove(new Problem(number, hashMap.get(number)));
            deleted.remove(number); //만약 삭제될 목록에 있었다면 삭제
        }
        treeSet.add(new Problem(number, level));
        hashMap.put(number, level); //hashMap에도 저장
    }

    //삭제된 문제 저장
    static void solved(int P) {
        deleted.add(P);
    }

    static class Problem implements Comparable<Problem> {
        int number;
        int level;

        public Problem(int number, int level) {
            this.number = number;
            this.level = level;
        }

        @Override
        public int compareTo(Problem o) { //난이도 오름차순, 번호 오름차순
            return this.level == o.level ? this.number - o.number : this.level - o.level;
        }

        @Override
        public String toString() {
            return "Problem{" +
                    "number=" + number +
                    ", level=" + level +
                    '}';
        }
    }

}
