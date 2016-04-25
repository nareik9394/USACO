/*
ID: kieranc1
LANG: JAVA
TASK: pprime
*/
import java.io.*;
import java.util.*;

class pprime {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
	
	StringTokenizer st = new StringTokenizer(f.readLine());
	int a = Integer.parseInt(st.nextToken());
	int b = Integer.parseInt(st.nextToken());
	
	String[] seeds = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"00", "11", "22", "33", "44", "55", "66", "77", "88", "99"};
	
	ArrayList<Integer> result = new ArrayList<Integer>();
	
	for(String seed : seeds)
		find(seed, a, b, result);
	
	Collections.sort(result);
	
	for(int num : result)
		out.println(num);
	
	f.close();
    out.close();                                  // close the output file
  }
  
  public static void find(String seed, int low_lim, int up_lim, ArrayList<Integer> r){
	  checkPrime(seed, low_lim, up_lim, r);
	  
	  if(seed.length() + 2 > Integer.toString(up_lim).length())
		  return;
	  
	  for(int i = 0; i < 10; i++)
		  find(i + seed + i, low_lim, up_lim, r);
  }
  
  public static void checkPrime(String num_str, int low_lim, int up_lim, ArrayList<Integer> r){
	  int num = Integer.parseInt(num_str);
	  
	  //out of range
	  if(num < low_lim || num > up_lim)
		  return;
	  
	  //leading zeros but have trailing zeros => not palindrome
	  if(num_str.length() != Integer.toString(num).length())
		  return;
	  
	  //divisible by 2 and 3
	  if(num % 2 == 0 || num % 3 == 0)
		  return;
	  
	  for(int i = 5; i*i <= num; i += 2){
		  if(num % i == 0)
			  return;
	  }
	  
	  r.add(num);
  }
}