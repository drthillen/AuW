import java.util.Arrays;

class Main {

    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        // In.open("public/sample.in");

        int t = In.readInt();
        for (int i = 0; i < t; i++) {
            testCase();
        }

        // Uncomment this line if you want to read from a file
        // In.close();
    }

    public static void testCase() {
        // Input using In.java class
        int n = In.readInt();
        int m = In.readInt();
        // initializing
        double[] valU = new double[n];
        double[] valV = new double[m];
        // Filling arrays
        for (int i = 0; i < n; i++) {
            valU[i] = In.readDouble();
        }
        for (int i = 0; i < m; i++) {
            valV[i] = In.readDouble();
        }
        int x = In.readInt();
        int y = In.readInt();
        int z = In.readInt();
        // END READING Input
        //
        double expectedSum1 = ExpectedSum(valU, valV, n, m);
        double prob1 = ProbabilityQuery(valU, valV, n, m, x, y);
        double expectedSum2 = ExpectedSum2(valU, valV, n, m, z);

//        System.out.println("valU: " + Arrays.toString(valU) + "  valV: " + Arrays.toString(valV));
        // Output using Out.java class
        Out.println("" + expectedSum1 + " " + prob1 + " " + expectedSum2);
    }

    private static double ExpectedSum2(double[] valU, double[] valV, int n, int m, int z) {
        // expected second
        double expectedV = 0;
        for (double v: valV) {
            expectedV += v;
        }
        expectedV = expectedV / m;
       // System.out.println("expV: " + expectedV);

        // expected first w/ condition
        double expectedU = 0;
        int cardU = 0;
        for (double u: valU) {
            if (u < z) {
                expectedU += u;
                cardU++;
            }
        }
        expectedU = expectedU / cardU;
//        System.out.println("expU: " + expectedU);

        return expectedU + expectedV;
    }

    private static double ProbabilityQuery(double[] valU, double[] valV, int n, int m, int x, int y) {
        int cardSuccess = 0;
        int cardOmega = 0;
        for (double u: valU) {
            if (u < y) {
                for (double v: valV) {
                    cardOmega++;
                    if (u + v > x) {
                        cardSuccess++;
                    }
                }
            }
        }
        return (double) cardSuccess / cardOmega;
    }

    private static double ExpectedSum(double[] valU, double[] valV, int n, int m) {
        double sum = 0;
        for (double u: valU) {
            sum += u/n;
        }
        for (double v: valV) {
            sum += v/m;
        }
        return sum;
    }

}
