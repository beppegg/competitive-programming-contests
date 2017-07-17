package tasks;

import java.util.Scanner;
import java.io.PrintWriter;

public class OnceInATram {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int input = in.nextInt();
        int res = input + 1;
        while (digitSum(res / 1000) != digitSum(res % 1000)) {
            ++res;
        }
        out.printf("%06d\n", res);
    }

    private int digitSum(int i) {
        return i / 100 + (i % 100 / 10) + (i % 10);
    }
}
