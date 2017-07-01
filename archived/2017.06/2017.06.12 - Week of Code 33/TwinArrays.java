package tasks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.io.PrintWriter;

public class TwinArrays {
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
