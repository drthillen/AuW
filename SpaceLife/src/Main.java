class Main {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        // In.open("public/sample.in");
        // Out.compareTo("public/sample.out");

        int t = In.readInt();
        for (int i = 0; i < t; i++) {
            testCase();
        }

        // Uncomment this line if you want to read from a file
        // In.close();
    }

    public static void testCase() {

        int n = In.readInt(); // nr. of planets
        int m = In.readInt(); // nr. of relations between planets
        int s = 2*n;
        int t = 2*n + 1;
        //
        Algorithms.Graph G = new Algorithms.Graph(2*n + 2);
        int P[] = new int[n];
        int F[] = new int[n];

        int sum = 0;

        for (int i = 0; i < n; i++) {
            P[i] = In.readInt();
            F[i] = In.readInt();
            sum += F[i];
            G.addEdge(s, i, P[i]);
            G.addEdge(i, n+i, P[i]);
            G.addEdge(i, t, F[i]);
        }
        for (int j = 0; j < m; j++) {
            int u = In.readInt();
            int v = In.readInt();
            // System.out.println("" + u + ", " + n +", " + m);
            G.addEdge(u+n, v, Integer.MAX_VALUE);
        }

        if (G.computeMaximumFlow(s, t) >= sum) {
            Out.println("yes");
        } else {
            Out.println("no");
        }

    }
}