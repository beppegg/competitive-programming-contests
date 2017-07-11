package tasks;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaskC {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int[] outcome = new int[n];
        int mid = n / 2;
        int switchPoint = n % 2;
        for (int i = 0; i < n; i++) {
            int index = mid - Math.floorDiv(-i, 2) * (i % 2 == switchPoint ? 1 : -1);
            outcome[index] = a[i];
        }

        final String collect = Arrays.stream(outcome).mapToObj(Integer::toString).collect(Collectors.joining(" "));
        out.println(collect);
    }
}
