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
        // Input using In.java class
        int n = In.readInt();
        
        // Output using Out.java class
        Out.println(n + n);
    }
}