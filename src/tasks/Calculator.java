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

        List<List<Display>> values = new ArrayList<>(n+1);
        values.add(Collections.emptyList());// padding
        for (int i = 1; i <= b; i++) {
            values.add(Collections.singletonList(new Display(i, 0)));
        }
        for (int i = b + 1; i <= n; i++) {
            List<Display> possibilities = new ArrayList<>();
            possibilities.addAll(values.get(i - b).stream().map(Display::pushSecond).collect(Collectors.toList()));
            possibilities.addAll(values.get(i - 1).stream().map(Display::pushFirst).collect(Collectors.toList()));
            possibilities.sort((one, two) ->  two.first + two.second - one.first - one.second);
            int max = possibilities.get(0).first + possibilities.get(0).second;
            values.add(possibilities.stream().filter(item -> item.first + item.second == max).collect(Collectors.toList()));
        }

        out.println(values.get(n).stream().mapToInt(Display::getSecond).max().getAsInt());
    }

    private static class Display {
        private final int first;
        private final int second;

        @Override
        public String toString() {
            return "(" +
                    first +
                   ", " + second +
                   ')';
        }

        public Display(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }

        public Display pushFirst() {
            return new Display(first + 1, second);
        }

        public Display pushSecond() {
            return new Display(first, first + second);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Display display = (Display) o;

            if (first != display.first) {
                return false;
            }
            return second == display.second;
        }

        @Override
        public int hashCode() {
            int result = first;
            result = 31 * result + second;
            return result;
        }
    }
    /*
     * hef has a calculator which has two screens and two buttons. Initially, each screen shows the number zero. Pressing the first button increments the number on the first screen by 1, and each click of the first button consumes 1 unit of energy.

Pressing the second button increases the number on the second screen by the number which is currently appearing on the first screen. Each click of the second button consumes B units of energy.

Initially the calculator has N units of energy.

Now chef wonders what the maximum possible number is, that he gets on the second screen of the calculator, with the limited energy.

Input
The first line of the input contains an integer T denoting the number of test cases.

Each test case is described using a single line containing two integers, N and B.

Output
For each test case, output a single line containing the answer to this test case.

Constraints
1 ≤ T ≤ 10,000
1 ≤ N, B ≤ 1,000,000,000
Subtasks
Subtask 1 (20 points): 1 ≤ N, B ≤ 1,000
Subtask 2 (80 points): Original constraints
Example
Input:
3
10 2
8 5
6 1

Output:
12
3
9
Explanation
Example case 1. There are 10 units of energy available. Pressing second button takes 2 units of energy. Chef can achieve 12 on the second screen as follows.

Press first button to get scores (1, 0). 9 units of energey is left.
Press first button to get scores (2, 0). 8 units of energy remaining.
Press first button to get scores (3, 0). 7 units of energy remaining.
Press first button to get scores (4, 0). 6 units of energy remaining.
Press second button to get scores (4, 4). 4 units of energy remaining.
Press second button to get scores (4, 8). 2 units of energy remaining.
Press second button to get scores (4, 12). 0 units of energy remaining.
     */
}
