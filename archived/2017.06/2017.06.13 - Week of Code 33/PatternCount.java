package tasks;

import java.util.Scanner;
import java.io.PrintWriter;

public class PatternCount {

    private enum State {
        OUT, START, IN
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        char[] query = in.nextLine().toCharArray();
        while (query.length == 0) {
            query = in.nextLine().toCharArray();
        }
        int count = 0;
        State state = State.OUT;
        for (int i = 0; i < query.length; i++) {
            if (query[i] == '1') {
                if (state == State.OUT) {
                    state = State.START;
                } else if (state == State.IN) {
                    ++count;
                    state = State.START;
                }
            } else if (query[i] == '0') {
                if (state == State.START) {
                    state = State.IN;
                }
            } else {
                if (state == State.IN || state == State.START) {
                    state = State.OUT;
                }
            }
        }
        out.println(count);
    }
}
