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
        Out.println(probabilityUtil(probs, 0, k));
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
}