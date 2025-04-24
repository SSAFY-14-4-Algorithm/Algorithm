import java.io.*;
/*
 * 메모리 11528KB
 * 시간 64ms
 */
public class Baekjoon16637 {
    static int N;
    static char[] expression;
    static int maxResult = Integer.MIN_VALUE;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // static 선언

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine()); 
        expression = br.readLine().toCharArray(); 

        dfs(0, expression[0] - '0'); // DFS 탐색 시작
        System.out.println(maxResult); // 결과 출력
    }

    // DFS 탐색 함수
    static void dfs(int index, int currentResult) {
        if (index >= N - 1) { // 수식 끝까지 탐색 완료
            maxResult = Math.max(maxResult, currentResult);
            return;
        }

        // 괄호 없이 계산
        char operator = expression[index + 1];
        int nextNum = expression[index + 2] - '0';
        dfs(index + 2, calculate(currentResult, nextNum, operator));

        // 괄호 추가 계산 (index+4까지 가능할 때만)
        if (index + 4 < N) {
            char nextOperator = expression[index + 3];
            int afterNextNum = expression[index + 4] - '0';
            int bracketResult = calculate(nextNum, afterNextNum, nextOperator);
            dfs(index + 4, calculate(currentResult, bracketResult, operator));
        }
    }

    // 연산 함수
    static int calculate(int a, int b, char operator) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
        }
        return 0; 
    }
}