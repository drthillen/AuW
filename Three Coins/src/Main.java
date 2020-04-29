import java.util.HashSet;

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
        int n = In.readInt(); // repetitions
        int x = In.readInt();
        /* experiment: 1) probability of red; 2) probability of alpha, given result is red;
        3) after n experiments, each resulting in red: probability of obtaining beta at least once.
            (i.e. = 1 - probability of never obtaining beta)
         */
        //
        assert (x >= 1 && x <= 3);

        double p = In.readDouble();
        double a = In.readDouble();
        double b = In.readDouble();
        //
        if (x == 1) {
            double prob1 = p * a + (1-p) * b;
            Out.println(prob1);
        } else if (x == 2 || x == 3) {
            double prob2;
            double probAlphaAndRed = p*a;
            double probRed = p * a + (1-p) * b;
            prob2 = probAlphaAndRed / probRed;
            if (x == 2) Out.println(prob2);
            else Out.println(1 - Math.pow(prob2, n));
        }

        //



//        Out.println(n + n);
    }
}