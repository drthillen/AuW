import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        // In.open("public/sample.in");

        int t = In.readInt();
        for (int i = 0; i < t; i++) {
            // System.out.println("Testcase " + (i+1));
            testCase();
        }

        // Uncomment this line if you want to read from a file
        // In.close();
    }

    public static void testCase() {
        // Input using In.java class
        int n = In.readInt(); // leaves
        //  System.out.println("n: " + n);
        int k = In.readInt(); // starting point
        //   System.out.println("k: " + k);
        int m = In.readInt(); // jumps
        // System.out.println("m: " + m);
        //

        double[][] DPTable = new double[m + 1][n];

        DPTable[0][k] = 1;


        double flies[] = new double[n];
        for (int f = 0; f < n; f++) {
            flies[f] = In.readDouble();
        }
        double prob[] = new double[n];
        for (int f = 0; f < n; f++) {
            prob[f] = In.readDouble();
        }

        for (int i = 1; i < m + 1; i++) { // TODO: verify m+1 or m?
            for (int j = 0; j < n; j++) {
                DPTable[i][j] = (j - 1 >= 0 ? DPTable[i - 1][j - 1] * (1 - prob[j - 1]) : 0) + (j + 1 <= n - 1 ? DPTable[i - 1][j + 1] * prob[j + 1] : 0);
            }
        }

        double sum = 0;
        for (int i = 0; i < m + 1; i++) { // TODO: verify m+1 or m?
            for (int j = 0; j < n; j++) {
                sum += DPTable[i][j] * flies[j];
            }
        }

        // printArray(DPTable);
        // Output using Out.java class
        Out.println(sum);
    }

    public static void printArray(double[][] arr) {
        for (double[] i : arr) {
            System.out.println(Arrays.toString(i));
        }
    }
}