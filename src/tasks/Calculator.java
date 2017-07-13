package tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class Calculator {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int b = in.nextInt();

        if (n <= b) {
            out.println("0");
            return;
        }

        int possibleMax = n / 2;
        possibleMax -= possibleMax % b;

        int max = -1;
        int outcome = 0;
        while (max <= outcome && possibleMax <= n - b) {
            outcome = getSecondDisplayNum(n, possibleMax++, b);
            max = max < outcome ? outcome : max;
        }
        out.println(max);
    }

    private int getSecondDisplayNum(int n, int x, int b) {
        return (n - x) / b * x;
    }

}