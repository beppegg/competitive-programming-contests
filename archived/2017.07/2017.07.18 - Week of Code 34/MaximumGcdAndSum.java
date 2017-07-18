package tasks;

import java.util.*;
import java.io.PrintWriter;

public class MaximumGcdAndSum {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }

        Arrays.sort(a);
        Arrays.sort(b);

        int maxPossibleGcd = Math.max(a[n - 1], b[n - 1]);
        int[] f_a = new int[maxPossibleGcd + 1];
        int[] f_b = new int[maxPossibleGcd + 1];

        for (int i = 0; i < n; i++) {
            ++f_a[a[i]];
            ++f_b[b[i]];
        }

        int maxSum = -1;
        for (int tentativeGcd = 1; tentativeGcd <= maxPossibleGcd; ++tentativeGcd) {
            boolean dividesA = false, dividesB = false;
            int maxA = -1, maxB = -1;
            // like the sieve, we consider all the multiples of the current tentative GCD
            for (int i = tentativeGcd; i <= maxPossibleGcd;  i += tentativeGcd) {
                if (f_a[i] > 0) {
                    dividesA = true;
                    maxA = i;
                }
                if (f_b[i] > 0) {
                    dividesB = true;
                    maxB = i;
                }
            }
            // if it divides at least a couple, it could be our maximum
            if (dividesA && dividesB) {
                assert maxA > 0;
                assert maxB > 0;

                maxSum = maxA + maxB;
            }
        }

        out.println(maxSum);
    }
}
