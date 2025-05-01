public class App {
    static final int M = 7, N = 7;
    static int[][] LAB = {
        {1,1,1,1,1,1,1},
        {1,0,0,0,1,1,1},
        {1,0,1,0,1,1,1},
        {1,0,0,0,0,1,1},
        {1,1,1,1,0,1,1},
        {0,0,0,0,0,0,0},
        {1,1,1,1,1,0,1}
    };
    static int[] CX = {-1, 0, 1, 0}; // R1, R2, R3, R4
    static int[] CY = {0, -1, 0, 1};
    static int L = 2, TRIAL = 0;
    static boolean YES = false;
    static int traceCounter = 1;
    static StringBuilder traceLog = new StringBuilder();
    static StringBuilder rulePath = new StringBuilder();
    static StringBuilder nodePath = new StringBuilder();

    public static void main(String[] args) {
        int X = 4, Y = 3; // 0-indexed, corresponds to X=5, Y=4
        LAB[Y][X] = L;
        nodePath.append("[X=").append(X+1).append(",Y=").append(Y+1).append("], ");
        TRY(X, Y);
        printResults(X, Y);
    }

    static void TRY(int x, int y) {
        if (x == 0 || x == M - 1 || y == 0 || y == N - 1) {
            YES = true;
            return;
        }

        for (int k = 0; k < 4 && !YES; k++) {
            int u = x + CX[k];
            int v = y + CY[k];
            traceLog.append(traceCounter++).append(") ");
            traceLog.append(ruleIndent()).append("R").append(k + 1).append(". U=").append(u + 1).append(", V=").append(v + 1).append(". ");
            if (isInside(u, v)) {
                if (LAB[v][u] == 0) {
                    TRIAL++;
                    L++;
                    LAB[v][u] = L;
                    traceLog.append("Free. L:=L+1=").append(L).append(". LAB[").append(u + 1).append(",").append(v + 1).append("]:=").append(L).append(".\n");
                    nodePath.append("[X=").append(u + 1).append(",Y=").append(v + 1).append("], ");
                    rulePath.append("R").append(k + 1).append(", ");
                    TRY(u, v);
                    if (!YES) {
                        traceLog.append(ruleIndent()).append("Backtrack from X=").append(u + 1).append(", Y=").append(v + 1)
                                .append(", L=").append(L).append(". LAB[").append(u + 1).append(",").append(v + 1).append("]:=-1. L:=L-1=").append(L - 1).append(".\n");
                        LAB[v][u] = -1;
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
        return "-".repeat(L - 2);
    }

    static void removeLastNode() {
        int idx = nodePath.lastIndexOf("[");
        if (idx >= 0) nodePath.delete(idx, nodePath.length());
    }

    static void removeLastRule() {
        int idx = rulePath.lastIndexOf("R");
        if (idx >= 0) rulePath.delete(idx, rulePath.length());
    }

    static void printResults(int startX, int startY) {
        System.out.println("PART 1. Data");
        System.out.println(" 1.1. Labyrinth\n Y, V\n ^");
        for (int y = N - 1; y >= 0; y--) {
            System.out.printf(" %d |", y + 1);
            for (int x = 0; x < M; x++) {
                System.out.printf("%3d", LAB[y][x]);
            }
            System.out.println();
        }
    
        // Print bottom axis
        System.out.println(" -------------------------------> X, U");
        System.out.print("    ");
        for (int x = 1; x <= M; x++) {
            System.out.printf("%3d", x);
        }    
        System.out.println();
        System.out.printf("1.2. Initial position X=%d, Y=%d. L=2.\n", startX + 1, startY + 1);
    
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
            System.out.printf(" %d |", y + 1);
            for (int x = 0; x < M; x++) {
                System.out.printf("%3d", LAB[y][x]);
            }
            System.out.println();
        }
    
        // Print bottom axis again
        System.out.println(" -------------------------------> X, U");
        System.out.print("    ");
        for (int x = 1; x <= M; x++) {
            System.out.printf("%3d", x);
        }    
        System.out.println();
        System.out.println(" 3.3. Rules: " + rulePath.toString().replaceAll(", $", "") + ".");
        System.out.println(" 3.4. Nodes: " + nodePath.toString().replaceAll(", $", "") + ".");
    }
    
}


