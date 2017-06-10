package tasks;

import java.io.PrintWriter;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TaskC {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        /*
        Rating 1-399 : gray
        Rating 400-799 : brown
        Rating 800-1199 : green
        Rating 1200-1599 : cyan
        Rating 1600-1999 : blue
        Rating 2000-2399 : yellow
        Rating 2400-2799 : orange
        Rating 2800-3199 : red
         */
        Set<Integer> colors = new HashSet<>();
        int n = in.nextInt();
        int choosers = 0;
        for (int i = 0; i < n; i++) {
            int current = getColor(in.nextInt());
            if (current < 0) {
                ++choosers;
            } else {
                colors.add(current);
            }
        }
        out.printf("%d %d\n",
                   Math.max(colors.size(), 1),
                   colors.size() + choosers);
    }

    private int getColor(int score) {
        return score < 3200 ? score / 400 : -1;
    }
}
