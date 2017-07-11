package tasks;

import java.util.Scanner;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s = in.next();

        while (s.length() > 1) {
            do {
                s = s.substring(0, s.length() - 1);
            } while (s.length() % 2 == 1);

            if (isEven(s)) {
                out.println(s.length());
                break;
            }
        }
    }

    private boolean isEven(String s) {
        int mid = s.length() / 2;
        return s.substring(0, mid).equals(s.substring(mid, s.length()));
    }


}
