import java.util.Scanner;

public class App {
    static int M, N;
    static int[][] LAB;
    static int[][] INITIAL_LAB;
    static int[] CX = { -1, 0, 1, 0 }; // R1, R2, R3, R4
    static int[] CY = { 0, -1, 0, 1 };
    static int L, TRIAL;
    static boolean YES;
    static int traceCounter;
    static StringBuilder traceLog = new StringBuilder();
    static StringBuilder rulePath = new StringBuilder();
    static StringBuilder nodePath = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select test case (1, 2, or 3): ");
        int test = scanner.nextInt();

        System.out.println("Select variant (1 for V1, 2 for V2): ");
        int variant = scanner.nextInt();
        boolean isV1 = (variant == 1);

        int startX = 0, startY = 0;

        switch (test) {
            case 1:
                M = 7;
                N = 7;
                LAB = new int[][] {
                        { 1, 1, 1, 1, 1, 1, 1 },
                        { 1, 0, 0, 0, 1, 1, 1 },
                        { 1, 0, 1, 0, 1, 1, 1 },
                        { 1, 0, 0, 0, 0, 1, 1 },
                        { 1, 1, 1, 1, 0, 1, 1 },
                        { 0, 0, 0, 0, 0, 0, 0 },
                        { 1, 1, 1, 1, 1, 0, 1 }
                };
                startX = 4;
                startY = 3;
                break;
            case 2:
                M = 17;
                N = 9;
                LAB = new int[][] {
                        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                        { 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
                        { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
                        { 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1 },
                        { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1 },
                        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
                };
                startX = 8;
                startY = 5;
                break;
            case 3:
                M = 20;
                N = 15;
                LAB = new int[][] {
                        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                        { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
                        { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
                        { 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1 },
                        { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1 },
                        { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
                        { 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
                        { 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
                        { 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
                        { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1 },
                        { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
                        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
                        { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1 },
                        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
                };
                startX = 1;
                startY = 13;
                break;
            default:
                System.out.println("Invalid test case.");
                return;
        }

        INITIAL_LAB = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(LAB[i], 0, INITIAL_LAB[i], 0, M);
        }

        L = 2;
        TRIAL = 0;
        YES = false;
        traceCounter = 1;
        traceLog.setLength(0);
        rulePath.setLength(0);
        nodePath.setLength(0);

        LAB[startY][startX] = L;
        nodePath.append("[X=").append(startX + 1).append(",Y=").append(startY + 1).append("], ");
        TRY(startX, startY, isV1);
        printResults(startX, startY);
    }

    static void TRY(int x, int y, boolean isV1) {
        if (x == 0 || x == M - 1 || y == 0 || y == N - 1) {
            YES = true;
            return;
        }

        for (int k = 0; k < 4 && !YES; k++) {
            int u = x + CX[k];
            int v = y + CY[k];
            traceLog.append(String.format("%4d", traceCounter++)).append(") ");
            traceLog.append(ruleIndent()).append("R").append(k + 1).append(". U=").append(u + 1).append(", V=")
                    .append(v + 1).append(". ");
            if (isInside(u, v)) {
                if (LAB[v][u] == 0) {
                    TRIAL++;
                    L++;
                    LAB[v][u] = L;
                    traceLog.append("Free. L:=L+1=").append(L).append(". LAB[").append(u + 1).append(",").append(v + 1)
                            .append("]:=").append(L).append(".\n");
                    nodePath.append("[X=").append(u + 1).append(",Y=").append(v + 1).append("], ");
                    rulePath.append("R").append(k + 1).append(", ");
                    TRY(u, v, isV1);
                    if (!YES) {
                        traceLog.append(ruleIndent()).append("Backtrack from X=").append(u + 1).append(", Y=")
                                .append(v + 1)
                                .append(", L=").append(L).append(". LAB[").append(u + 1).append(",").append(v + 1)
                                .append("]:=").append(isV1 ? "-1" : "0")
                                .append(". L:=L-1=").append(L - 1).append(".\n");
                        LAB[v][u] = isV1 ? -1 : 0;
                        L--;
                        removeLastNode();
                        removeLastRule();
                    }
                } else {
                    traceLog.append(LAB[v][u] > 1 ? "Thread.\n" : "Wall.\n");
                }
            }
        }
    }

    static boolean isInside(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    static String ruleIndent() {
        return "-".repeat(Math.max(0, L - 2));
    }

    static void removeLastNode() {
        int idx = nodePath.lastIndexOf("[");
        if (idx >= 0)
            nodePath.delete(idx, nodePath.length());
    }

    static void removeLastRule() {
        int idx = rulePath.lastIndexOf("R");
        if (idx >= 0)
            rulePath.delete(idx, rulePath.length());
    }

    static void printResults(int startX, int startY) {
        System.out.println("PART 1. Data");
        System.out.println(" 1.1. Labyrinth\n Y, V\n ^");
        for (int y = N - 1; y >= 0; y--) {
            System.out.printf(" %2d |", y + 1);
            for (int x = 0; x < M; x++) {
                System.out.printf("%3d", INITIAL_LAB[y][x]);
            }
            System.out.println();
        }
        System.out.println(" " + "-".repeat(4 + 3 * M) + "> X, U");
        System.out.print("     ");
        for (int x = 1; x <= M; x++) {
            System.out.printf("%3d", x);
        }
        System.out.println();
        System.out.printf(" 1.2. Initial position X=%d, Y=%d. L=2.\n", startX + 1, startY + 1);

        System.out.println("PART 2. Trace");
        System.out.print(traceLog);

        System.out.println("PART 3. Results");
        if (YES) {
            System.out.println(" 3.1. Path is found.");
        } else {
            System.out.println(" 3.1. Path is NOT found.");
        }

        System.out.println(" 3.2. Path graphically:\n Y, V\n ^");
        for (int y = N - 1; y >= 0; y--) {
            System.out.printf(" %2d |", y + 1);
            for (int x = 0; x < M; x++) {
                System.out.printf("%3d", LAB[y][x]);
            }
            System.out.println();
        }

        System.out.println(" " + "-".repeat(4 + 3 * M) + "> X, U");
        System.out.print("     ");
        for (int x = 1; x <= M; x++) {
            System.out.printf("%3d", x);
        }
        System.out.println();
        System.out.println(" 3.3. Rules: " + rulePath.toString().replaceAll(", $", "") + ".");
        System.out.println(" 3.4. Nodes: " + nodePath.toString().replaceAll(", $", "") + ".");
    }
}
