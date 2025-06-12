import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author SSAFY
 *
 * 전체 문자열이 펠린드롬인지 체크
 * 끝자리를 하나씩 빼가면서 체크
 * 처음으로 펠린드롬을 만족할 때 길이 출력
 *
 * 생각해볼점
 * 시작점이 0이 아닐 때 최대 길이가 나올 수 있나?
 * 끝점에서 결정되는 것은 펠린드롬 인지/아닌지
 * 만약 펠린드롬 수를 발견했다면, 그이전 값은 항상 펠린드롬이 아니다!
 * 따라서 greedy하게 처리 가능
 *
 * 1. 이 문자열이 펠린드롬인지 아닌지?
 * -> 아니면 len
 *
 * 2. 펠린드롬이다? (len-1이 답이 되기 위한 검증)
 * -> 다른 문자열로 이루어져 있다 -> len-1
 * -> 모두 같은 문자열이다 -> -1
 *
 * 즉 O(n)으로 펠린드롬 여부를 한번만 검증하고,
 * 좋은 반례
 * AABABAAA
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        String input = reader.readLine();
        int len = input.length();

        //같은지 여부 체크
        boolean isSame = true;
        for (int i = 0; i < input.length()/2; i++) {

            //처음과 끝이 다를 때, 즉 펠린드롬이 아님
            if(input.charAt(i) != input.charAt(len-1-i)) {
                System.out.println(len);
                return;
            }

            //첫문자가 회문이 아닐 때, 이 문자열이 다른 문자로 이루어져 있는지 체크
            if(input.charAt(i) != input.charAt(i+1)) {
                isSame = false;
            }
        }

        //모두 같은 문자라면 -1, 그렇지 않다면 len-1
        if(isSame) System.out.println(-1);
        else System.out.println(len-1);
    }
}

