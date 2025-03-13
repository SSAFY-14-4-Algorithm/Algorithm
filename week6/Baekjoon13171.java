package week6;

/**
 * <h1>BAEKJOON 13171번 A SILVER III</h1>
 * <p>
 * JAVA 8 : 메모리 11,412KB, 시간 68ms <br>
 * JAVA11 : 메모리 14,060KB, 시간 104ms
 * </p>
 * 
 * @author KIM MINGYU jun3021303@gmail.com
 * @since 2025-03-12
 */

import java.io.IOException;

public class Baekjoon13171 {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        long A = readLong() % MOD;
        long B = readLong();
        long result = 1;
        while (B > 0) {
            if ((B & 1) == 1) {
                result = (result * A) % MOD;
            }
            A = (A * A) % MOD;
            B >>= 1;
        }
        System.out.print(result);
    }

    private static long readLong() throws IOException {
        long c;
        long n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n * 10) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}
