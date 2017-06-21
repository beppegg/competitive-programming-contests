package tasks;

import java.io.PrintWriter;
import java.util.Scanner;

public class HardQuestions {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        char[] vincent = in.next().toCharArray();
        char[] cath = in.next().toCharArray();

        int points = 0;
        for (int i = 0; i < n; i++) {
            if (vincent[i] != '.' && vincent[i] != cath[i]) {
                ++points;
            }
        }

        out.println(points);
    }
}
