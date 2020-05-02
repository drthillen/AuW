

class Main {
    private static int SUCCESS = 0;
    private static int NO_SUCCESS = 1;
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
        int n = In.readInt(); // people
        int m = In.readInt(); // start message

        double[][] DP_Table = new double[n+1][2];
        DP_Table[0][SUCCESS] = 1.;
        DP_Table[0][NO_SUCCESS] = 0.;
        //
        for (int i = 1; i <= n; i++) {
            double P_i = In.readDouble();
            DP_Table[i][SUCCESS] = DP_Table[i-1][SUCCESS] * (1-P_i) + DP_Table[i-1][NO_SUCCESS] * P_i;
            DP_Table[i][NO_SUCCESS] = DP_Table[i-1][SUCCESS] * (P_i) + DP_Table[i-1][NO_SUCCESS] * (1-P_i);
        }


        // Output using Out.java class
        Out.println(DP_Table[n][SUCCESS]);
    }
}