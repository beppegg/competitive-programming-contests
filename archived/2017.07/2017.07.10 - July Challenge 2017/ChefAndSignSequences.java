package tasks;

import java.util.Scanner;
import java.io.PrintWriter;

public class ChefAndSignSequences {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        final char[] symbols = in.next().toCharArray();
        final int p = findPv2(symbols);
        out.println(p);
    }

    private int findPv2(char[] symbols) {
        int longestStreak = 0;
        int currentStreak = 1;
        char lastSymbol = '\0';
        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] != '=') {
                if (symbols[i] == lastSymbol) {
                    ++currentStreak;
                } else {
                    currentStreak = 1;
                }
                if (longestStreak < currentStreak) {
                    longestStreak = currentStreak;
                }
                lastSymbol = symbols[i];
            }
        }

        return longestStreak + 1;
    }

    private int findPv1(char[] symbols) {
        int current = 1, max = 1, padding = 0;
        for (int i = 0; i < symbols.length; i++) {
            switch (symbols[i]) {
                case '>':
                    current = 1;
                    ++padding;
                    break;
                case '<':
                    --padding;
                    if (max < ++current) {
                     max = current;
                    }
                    break;
            }
        }
        return max + Math.max(0, padding);
    }
}
