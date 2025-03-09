import java.io.IOException;

public class Baekjoon1038 {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int decNumCount = -1;
        for (int numLength = 1; numLength <= 10; numLength++) {
            for (int subset = 1; subset < (1 << 10); subset++) {
                if (Integer.bitCount(subset) == numLength) {
                    decNumCount++;
                    if (N == decNumCount) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 9; i >= 0; i--) {
                            if ((subset & (1 << i)) != 0) {
                                sb.append(i);
                            }
                        }
                        System.out.print(sb.toString());
                        return;
                    }
                }
            }
        }
        System.out.print(-1);
    }

    private static int readInt() throws IOException {
        int c;
        int n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n * 10) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}
