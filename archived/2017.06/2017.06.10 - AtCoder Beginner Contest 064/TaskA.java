package tasks;

import java.util.Scanner;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int testNum = in.nextInt() * 100 + in.nextInt() * 10 + in.nextInt();
        if (testNum % 4 == 0) {
            out.println("YES");
        }
        else {
            out.println("NO");
        }
    }
}
