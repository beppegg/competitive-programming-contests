package tasks;

import java.util.Scanner;
import java.io.PrintWriter;

public class WhatsInTheName {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String fullName = "";
        while ("".equals(fullName)) {
            fullName = in.nextLine();
        }
        final String[] nameParts = fullName.split(" ");
        final StringBuilder formattedName = new StringBuilder();
        int i = 0;
        for (; i < nameParts.length - 1; i++) {
            formattedName.append(Character.toUpperCase(nameParts[i].charAt(0))).append(". ");
        }
        formattedName.append(Character.toUpperCase(nameParts[i].charAt(0)))
                     .append(nameParts[i].substring(1).toLowerCase());

        out.println(formattedName);
    }
}
