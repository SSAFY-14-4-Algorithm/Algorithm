import java.io.*;
import java.util.*;

/* 회문은 회문아니야!!
 * 25248kb 256ms
 */
//manacher를 이용해서 부분 회문을 다 구하고 거기서 회문 아닌 최대 길이를 구했으나
//이 문제는 애드혹 문제
//회문인 경우
//-> 한글자로만 이루어진 회문 : -1
//-> 한글자로만 이루어지지 않은 회문 : 전체길이-1
//회문이 아닌 경우 : 전체 길이
//로 풀면 풀린다.
//이유는 생각해보면 간단하다.

public class Baekjoon15927 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int slen = s.length();
        int len = slen*2+1;
        //들어온 문자열에 # 추가해주기
        //ex) ABCBA -> #A#B#C#B#A#
        char[] c = new char[len];
        for(int i = 0, j = 0; i < slen; i++, j+= 2) {
        	c[j] = '#';
        	c[j+1] = s.charAt(i);
        }
        c[len-1] = '#';
        
        //manacher(매내처) 알고리즘
        int[] mnc = new int[len];
        int p = -1; //중심값
        int r = -1; //오른쪽 값
        for(int i = 0; i < len; i++) {
        	//팰린드롬 문자열 내에 있다면
        	if(i <= r) {
        		//대칭 위치의 팰린드롬 수 확인 및 현재 팰린드롬 블록의 길이 고려
        		int j = 2*p-i;
        		mnc[i] = Math.min(r-i,  mnc[j]);
        	}
        	//팰린드롬수가 몇개인지 체크
        	while(i + mnc[i] + 1 < len && i - mnc[i] - 1 >= 0 && c[i+mnc[i]+1] == c[i-mnc[i]-1]) {
        		mnc[i]++;
        	}
        	//현재 i가 있는 팰린드롬 박스보다, i가만든 팰린드롬 박스가 더 클 때
        	if(i + mnc[i] > r) {
        		r = i + mnc[i];
        		p = i;
        	}
        }
        //중앙부터 팰린드롬 길이 체크
        int index = len/2;
        int ans = slen;
        for(int i = 0; i <= index; i++) {
        	if(mnc[index-i] != ans || mnc[index+i] != ans) {
        		break;
        	}
        	ans--;
        }
        System.out.println(ans);
    }
}