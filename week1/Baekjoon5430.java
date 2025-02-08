package week1;

public class Baekjoon5430 {
  public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) { 
            String p = br.readLine();
            int N = Integer.parseInt(br.readLine());
            
            String input = br.readLine();
            Deque<Integer> deque = new LinkedList<>();
            
            // 입력 배열 처리
            input = input.substring(1, input.length() - 1);
            if (!input.isEmpty()) {
                String[] nums = input.split(",");
                for (String num : nums) {
                    deque.add(Integer.parseInt(num));
                }
            }
            
            boolean r = false; 
            boolean e = false; 
            
            for (char func : p.toCharArray()) {
                if (func == 'R') {
                    r = !r;
                } else if (func == 'D') {
                    if (deque.isEmpty()) {
                        e = true;
                        break;
                    }
                    if (r) {
                        deque.pollLast();
                    } else {
                        deque.pollFirst();
                    }
                }
            }
            
            
            if (e) {
                bw.write("error\n");
            } else {
                bw.write("[");
                while (!deque.isEmpty()) {
                    bw.write(String.valueOf(r ? deque.pollLast() : deque.pollFirst()));
                    if (!deque.isEmpty()) {
                        bw.write(",");
                    }
                }
                bw.write("]\n");  
            }

            bw.flush();  
        }

        bw.close();
        br.close();
    }

}
