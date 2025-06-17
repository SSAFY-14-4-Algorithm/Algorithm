// 트리 분리하는 게 메모리 성능 더 좋음
import java.io.*;
import java.util.*;

/*
 * Java8 | 실행시간: 384 ms, 메모리: 36716 KB
 */
public class Baekjoon2357 {

    private static class SegTree{
        int size, start;
        Node[] arr;

        SegTree(int n){
            size = n;
            start = 1;

            while(start < size) start <<= 1;
            arr = new Node[start * 2];

            for (int i=0; i<start*2; i++) arr[i] = new Node();
        }
        
        // 선입력 받은 리프 노드로 전체 세그먼트 트리 구성
        // 하나의 공간은 그 구간의 최솟값, 최댓값을 가지고 있음
        void construct(){
            for (int i=start-1; i>0; i--) {
                arr[i].min = Math.min(arr[i*2].min, arr[i*2+1].min);
                arr[i].max = Math.max(arr[i*2].max, arr[i*2+1].max);
            }
        }
        
        // [a, b] 구간의 최댓값, 최솟값 조회
        Node getMinMax(int a, int b){
            a += start;
            b += start;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            while (a <= b){ // a 와 b 가 겹칠 때까지 왼쪽/오른쪽에서 가운데로 좁혀오도록 이동 (최고 높이의 공통 부모 노드까지 이동)
                // 구간을 쪼갰을 때 양쪽 경계에 걸쳐 있는 예외 노드를 따로 챙겨주자
                if ((a&1)==1){ // (1) a가 부모 노드의 오른쪽 노드일 때
                    min = Math.min(min, arr[a].min);
                    max = Math.max(max, arr[a].max);
                    a++;
                }
                if ((b&1)==0){ // (2) b가 부모 노드의 왼쪽 노드일 때
                    min = Math.min(min, arr[b].min);
                    max = Math.max(max, arr[b].max);
                    b--;
                }
                a >>= 1; // 다음 부모 노드로 올라감
                b >>= 1;
            }
            return new Node(min, max);
        }
    }

    private static class Node{
        int min, max;
        Node(){
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
        }
        Node(int min, int max){
            this.min = min;
            this.max = max;
        }
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt(); // 쿼리 개수

        SegTree st = new SegTree(N);

        for (int i=0;i<N;i++){
            st.arr[st.start + i].min = st.arr[st.start + i].max = readInt();    // 리프노드 입력
        }
        st.construct(); // 전체 세그먼트 트리 구성

        StringBuilder sb = new StringBuilder();
        for (int i=0;i<M;i++){
            int a = readInt();
            int b = readInt();
            Node res = st.getMinMax(a-1, b-1);
            sb.append(res.min).append(" ").append(res.max).append("\n");
        }
        System.out.print(sb);
    }
    
    // 입력 로직
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
