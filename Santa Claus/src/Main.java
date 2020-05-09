class Main {
    // Vertex Indices
    // S: 0
    // T: 1
    // children: 2 TO 2 + m - 1
    // toys: 2 + m TO 2 + m + n -1
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
        // Input using In.java class
        int n = In.readInt(); // nr. children
        int m  = In.readInt(); // nr. toys
        int d[] = new int[n]; // deserves
        int c[] = new int[m]; // count toys

        Algorithms.Graph G = new Algorithms.Graph(n + m + 2);

        for (int j = 0; j < n; j++) { // j child index
            d[j] = In.readInt();
        }

        for (int i = 0; i < n; i++) { // i toy index
            c[i] = In.readInt();
        }

        for (int i = 0; i < n; i++) {
            // connecting children to T

        }

        for (int j = 0; j < m; j++) {
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                G.addEdge();
            }
        }




        // Output using Out.java class
        Out.println(n + n);
    }
}