package week1;

public class Baekjoon18115 {
  public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(new StringBuilder(br.readLine()).reverse().toString());
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 1; i <= N; i++) {  
            int tech = Integer.parseInt(st.nextToken());

            if (tech == 1) {
                deque.addFirst(i);
            } else if (tech == 2) {
                int first = deque.pollFirst();
                deque.addFirst(i);
                deque.addFirst(first);
            } else {
                deque.addLast(i);
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (!deque.isEmpty()) {
            bw.write(deque.pollFirst() + " ");
        }
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

}
