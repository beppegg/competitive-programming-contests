import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Vector;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author G. Guarnieri
 */
public class Solution {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TransformToPalindrome solver = new TransformToPalindrome();
        solver.solve(1, in, out);
        out.close();
    }

    static class TransformToPalindrome {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.readInt();
            int k = in.readInt();
            int m = in.readInt();

            Map<Integer, Set<Integer>> transformations = new TreeMap<>();
            for (int i = 0; i < k; i++) {
                int from = in.readInt();
                int to = in.readInt();

                final Set<Integer> fromList = transformations.containsKey(from)
                        ? transformations.get(from)
                        : new HashSet<>();
                final Set<Integer> toList = transformations.containsKey(to) ? transformations.get(to) : new HashSet<>();

                fromList.add(to);
                toList.add(from);
            }

            int[] word = new int[m];
            for (int i = 0; i < m; i++) {
                word[i] = in.readInt();
            }

            int[][] d = new int[m][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    d[i][j] = i < j ? -1 : 0;
                }
            }
            out.println(palindromize(word, transformations));
        }

        private int palindromize(int[] word, Map<Integer, Set<Integer>> transformations) {
            int[][] memo = new int[word.length + 1][word.length + 1];
            for (int i = 0; i <= word.length; i++) {
                memo[i][0] = i;
                memo[0][i] = i;
            }

            for (int i = 1; i <= word.length; i++) {
                for (int j = 1; j <= word.length - 1; j++) {

                    if (canTransform(word[i - 1], word[word.length - j - 1], transformations)) {
                        memo[i][j] = memo[i - j][i - j];
                    } else {
                        memo[i][j] = 1 + Math.min(memo[i - 1][j - 1], Math.min(memo[i - 1][j], memo[i][j - 1]));
                    }
                }
            }

            return memo[word.length][word.length];

        }

        private boolean canTransform(int first, int second, Map<Integer, Set<Integer>> transformation) {
            boolean canBeTransformed = first == second;
            if (!canBeTransformed) {
                final Set<Integer> firstTransformations = evaluateTransformationsFor(first, transformation);
                final Set<Integer> secondTransformations = evaluateTransformationsFor(first, transformation);

                firstTransformations.retainAll(secondTransformations);
                canBeTransformed = !firstTransformations.isEmpty();
            }
            return canBeTransformed;
        }

        private Set<Integer> evaluateTransformationsFor(int first,
                                                        Map<Integer, Set<Integer>> transformation) {

            Set<Integer> transformationSet = new HashSet<>();
            final Stack<Integer> transitive = new Stack<>();
            transformationSet.add(first);
            transitive.push(first);
            while (!transitive.isEmpty()) {
                final int current = transitive.pop();
                if (transformation.containsKey(current)) {
                    for (final int currentTransformation : transformation.get(current)) {
                        if (!transformationSet.contains(currentTransformation)) {
                            transformationSet.add(currentTransformation);
                            transitive.push(currentTransformation);
                        }
                    }
                }
            }

            return transformationSet;
        }

    }

    static class InputReader {
        public InputReader;

        public int readInt();

    }
}

