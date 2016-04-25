/*
ID: kieranc1
LANG: JAVA
TASK: sprime
*/
import java.io.*;
import java.util.*;

class sprime {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
	int n = Integer.parseInt(f.readLine());
	int[] primes = new int[]{2, 3, 5, 7};
	
	for(int p : primes)
		generate(p, n, out);
	
	f.close();
    out.close();                                  // close the output file
  }
  
  public static void generate(int seed, int n, PrintWriter out){
	  if(Integer.toString(seed).length() == n){
		  out.println(seed);
		  return;
	  }
	  
	  int num = 0;
	  for(int i = 0; i < 10; i++){
		  num = 10*seed + i;
		  if(isPrime(num))
			  generate(num, n, out);
	  }
  }
  
  public static boolean isPrime(int n){
	  if(n % 2 == 0 || n % 3 == 0)
		  return false;
	  
	  for(int i = 5; i*i <= n; i += 2){
		  if(n % i == 0)
			  return false;
	  }
	  
	  return true;
  }
}