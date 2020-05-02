class Main {
  static final long MAX_VAL = (long) Math.pow(10, 10);
  public static void main(String[] args) {
      // Uncomment this line if you want to read from a file
      int t = In.readInt();
      for (int i = 0; i < t; i++) {
          testCase();
      }
      
      // Uncomment this line if you want to read from a file
      // In.close();
  }

  // Recursive method to return gcd of a and b 
  static long gcd(long a, long b) 
  { 
  if (a == 0) 
      return b;  
  return gcd(b % a, a);  
  } 
    
  // method to return LCM of two numbers 
  static long lcm(long a, long b) 
  { 
      return (a/gcd(a, b)*b); 
  }

  static long cardinalityUnion(long[] divisors) {
    if (divisors.length == 1) {
      return (long) MAX_VAL/divisors[0];
   }
    if (divisors.length == 2) {
      return ((long) MAX_VAL/divisors[0]+ (long) MAX_VAL/divisors[1] - (long) MAX_VAL/(lcm(divisors[0], divisors[1])));
    } else if (divisors.length > 2) {
      long[] newDivisors = new long[divisors.length-1];
      long[] divisorsSmall = new long[divisors.length-1];
      
      for (int i = 0; i < divisors.length-1; i++) {
        newDivisors[i] = lcm(divisors[i], divisors[divisors.length-1]);
        //
        divisorsSmall[i] = divisors[i];
      }
      return cardinalityUnion(divisorsSmall) + (long) MAX_VAL/(divisors[divisors.length-1]) - cardinalityUnion(newDivisors);
    } else {
      return -1;
    }
  }

  public static void testCase() {
      // Input using In.java class
      int k = In.readInt();
      long[] divisors = new long[k];
      for (int i = 0; i < k; i++) {
        divisors[i] = In.readInt();
      }
      System.out.println((long) ((1. - (double) cardinalityUnion(divisors)/MAX_VAL)*1000_000)/1000_000.);
      // Output using Out.java class   
  }
}