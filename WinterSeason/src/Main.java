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
        int k = In.readInt();
        double[] probs = new double[n];
        for (int i = 0; i < n; i++) {
            probs[i] = In.readDouble();
        }
        Out.println(probability(probs, k));
    }

    public static double probabilityUtil(double[] probs, int index, int nP) {

        if (nP > probs.length-index) {
            return 0;
        }

        if (index == probs.length) {
            if (nP <= 0) return 1.;
            else return 0.;
        } else {
            index++;
            if (probs[index-1] == 0) {
                return 0;
            }
            double p1 = probs[index-1]*probabilityUtil(probs, index, nP-1);
            double p2 = (1 - probs[index-1])*probabilityUtil(probs, index, nP);
//            System.out.println(index + " p1 " + p1+ " p2 " + p2 + "probs index: " + probs[index-1]);
            return p1 + p2;
        }
    }

    public static double probability(double[] probs, int nP) {
        double [][] DP = new double[probs.length][probs.length+1];
        DP[0][0] = (1-probs[0]);
        DP[0][1] = probs[0];
        for (int i = 1; i < probs.length; i++) {
            DP[i][0] = (1-probs[i]) * DP[i-1][0];
        }

        for (int i = 1; i < probs.length; i++) {
                for (int j = 1; j < probs.length + 1; j++) {
                    DP[i][j] = DP[i - 1][j] * (1 - probs[i]) + DP[i - 1][j - 1] * probs[i];
                }

        }
        double sum = 0;
        for (int i = nP; i < probs.length+1; i++) {
            sum += DP[probs.length-1][i];
        }
//        System.out.println();
//        printArray(DP);
        return sum;
    }

    public static void printArray(double[][] arr) {
        for (double[] i: arr) {
            System.out.println(Arrays.toString(i));
        }
    }
}