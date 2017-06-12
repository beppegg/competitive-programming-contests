import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author G. Guarnieri
 */
public class Solution {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TwinArrays solver = new TwinArrays();
        solver.solve(1, in, out);
        out.close();
    }

    static class TwinArrays {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            int n = in.nextInt();
            int[] first = new int[n];
            int[] second = new int[n];

            Integer[] firstIdx = new Integer[n];
            Integer[] secondIdx = new Integer[n];

            for (int i = 0; i < n; i++) {
                first[i] = in.nextInt();
                firstIdx[i] = i;
            }

            for (int i = 0; i < n; i++) {
                second[i] = in.nextInt();
                secondIdx[i] = i;
            }

            Arrays.sort(firstIdx, Comparator.comparingInt(a -> first[a]));
            Arrays.sort(secondIdx, Comparator.comparingInt(a -> second[a]));

            int minIdx1 = 0;
            int minIdx2 = 0;
            while ((minIdx1 < n && minIdx2 < n) && firstIdx[minIdx1] == secondIdx[minIdx2]) {
                if (minIdx1 == n - 1) {
                    ++minIdx2;
                } else if (minIdx2 == n - 1) {
                    ++minIdx1;
                } else if (first[firstIdx[minIdx1 + 1]] < second[secondIdx[minIdx2 + 1]]) {
                    ++minIdx1;
                } else {
                    ++minIdx2;
                }
            }

            if (minIdx1 == n || minIdx2 == n || firstIdx[minIdx1] == secondIdx[minIdx2]) {
                out.println("0");
            } else {
                out.println(first[firstIdx[minIdx1]] + second[secondIdx[minIdx2]]);
            }
        }

    }
}

