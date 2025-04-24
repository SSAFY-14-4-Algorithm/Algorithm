import java.io.*;
/*
 * 메모리 61384KB
 * 시간 536ms
 */
public class Baekjoon17281 {
    static int N;
    static int[][] inning;
    static boolean[] selected = new boolean[9];
    static int[] order = new int[9];
    static int maxScore = 0;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        inning = new int[N][9];
        for(int i=0;i<N;i++) {
            String[] input = br.readLine().split(" ");
            for(int j=0;j<9;j++) {
                inning[i][j] = Integer.parseInt(input[j]);
            }
        }

        order[3] = 0; // 1번 선수는 4번 타자 고정
        selected[0] = true;

        Permutation(0); // 순열 생성 시작
        System.out.println(maxScore);
    }
    //타순 정하기
    public static void Permutation(int count) {
        if(count==9) {
            simulate();
            return;
        }
        //4번은 건너뜀
        if (count==3) {            
            Permutation(count+1);
            return;
        }
        //나머지 채우기
        for(int i=1;i<9;i++) {
        	if (!selected[i]) {
                selected[i] = true;
                order[count] = i;
                Permutation(count+1);
                selected[i] = false;
            }
        }
    }

    // 경기 시뮬레이션
    public static void simulate() {
        int score = 0;
        int idx = 0; // 현재 타자 순서
        for(int i=0;i<N;i++) {
            int out = 0;
            boolean[] base = new boolean[3]; // 1루, 2루, 3루
            while(out < 3) {
                int player = order[idx];
                int result = inning[i][player];
                
                if(result == 0) {//아웃
                    out++;
                } else if(result == 1) {//안타
                    if(base[2]) { 
                    	score++; 
                    	base[2] = false; 
                    }
                    if(base[1]) { 
                    	base[2] = true; 
                    	base[1] = false; 
                    }
                    if(base[0]) { 
                    	base[1] = true; 
                    	base[0] = false; 
                    }
                    base[0] = true;
                } else if(result == 2) {//2루타
                    if(base[2]) { 
                    	score++; 
                    	base[2] = false; 
                    }
                    if(base[1]) { 
                    	score++; 
                    	base[1] = false; 
                    }
                    if(base[0]) { 
                    	base[2] = true; 
                    	base[0] = false; 
                    }
                    base[1] = true;
                } else if(result == 3) {//3루타
                    for(int b=0;b<3;b++) {
                        if(base[b]) { 
                        	score++; 
                        	base[b] = false; 
                        }
                    }
                    base[2] = true;
                } else if(result == 4) { //홈런
                    for(int b=0;b<3;b++) {
                        if(base[b]) { 
                        	score++; 
                        	base[b] = false; 
                        }
                    }
                    score++;
                }
                //다음 타자로
                idx = (idx + 1) % 9;
            }
        }
        maxScore = Math.max(maxScore, score);
    }
}