import java.io.*;
import java.util.*;
/*
 * 메모리: 29284KB	
 * 시간: 364ms
 */
public class Baekjoon1253_좋다 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static ArrayList<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            list.add(Long.parseLong(s[i]));
        }

        // 오름차순으로 정렬
        Collections.sort(list);

        int goodCount = 0; 

        A:for(int i=0;i<N;i++){
            long good = list.get(i);  // 검사할 좋은 수 후보
            boolean isGood = false; // i번째가 좋은 수인지 표시할 플래그
            for(int j=0;j<N;j++){
            	//자기 자신이면 넘김
                if(j==i) 
                	continue;     
                // target - list.get(j) 값을 이진탐색으로 찾기
                long remain = good - list.get(j);
                int idx = Collections.binarySearch(list, remain);
                if(idx < 0) continue;      // 존재하지 않으면 다음 j

                if (idx == i || idx == j) {
                    // binarySearch가 자기 자신 i나 이미 한번 사용한 j를 가리키면,
                    // 양쪽으로 연속된 값을 스캔하여
                    // (i,j) 외에 또 다른 인덱스가 있는지 확인
                    boolean found = false;

                    // 왼쪽으로 스캔
                    int leftIdx = idx - 1;
                    while (leftIdx>=0 && list.get(leftIdx)==remain) {
                        if (leftIdx!=i && leftIdx!=j) {
                            found = true;
                            break;
                        }
                        leftIdx--;
                    }

                    // 오른쪽으로 스캔
                    int rightIdx = idx + 1;
                    while (!found && rightIdx<N && list.get(rightIdx)==remain) {
                        if (rightIdx!=i && rightIdx!=j) {
                            found = true;
                            break;
                        }
                        rightIdx++;
                    }

                    if (!found)
                    	continue;
                } 
                // 여기까지 오면 good을 서로다른 두수 형태로 표현 가능
                isGood = true;
                break;  
            }
            if (isGood) goodCount++;
        }

        System.out.println(goodCount);
    }
}
