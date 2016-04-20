/*
ID: kieranc1
LANG: JAVA
TASK: crypt1
*/
import java.io.*;
import java.util.*;

class crypt1 {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    StringTokenizer st = new StringTokenizer(f.readLine());
						  // Get line, break into tokens
    int set_size = Integer.parseInt(st.nextToken());    // first integer
	
	int[] num_set = new int[set_size];
	
	st = new StringTokenizer(f.readLine());
	for(int i = 0; i < set_size; i++){
		num_set[i] = Integer.parseInt(st.nextToken());
	}
	
	ArrayList<Integer> abc = permutation(3, num_set);
	ArrayList<Integer> de = permutation(2, num_set);
	
	int success_counter = 0;
	int p1 = 0, p2 = 0, prod = 0;
	for(int i : abc){
		for(int j : de){
			p1 = i * (j % 10);
			if(!hasPassed(p1, num_set, 3))
				continue;
			
			p2 = i * (j / 10);
			if(!hasPassed(p2, num_set, 3))
				continue;
			
			prod = i * j;
			if(!hasPassed(prod, num_set, 4))
				continue;
			
			System.out.println(" abc: " + i);
			System.out.println("  de: " + j);
			System.out.println("  p1: " + p1);
			System.out.println("  p2: " + p2);
			System.out.println("prod: " + prod + "\n");
			
			success_counter++;
		}
	}
	
	out.println(success_counter);
	
	f.close();
    out.close();                                  // close the output file
  }
  
  public static ArrayList<Integer> permutation(int n, int[] set){
	  ArrayList<Integer> perm = new ArrayList<Integer>();
	  
	  int multiplier = 0;
	  int current_num = 0;
	  
	  if(n == 3){
		  for(int i = 0; i < set.length; i++){
			  for(int j = 0; j < set.length; j++){
				  for(int k = 0; k < set.length; k++){
					  current_num = set[i] * 100 + set[j] * 10 + set[k];
					  perm.add(current_num);
				  }
			  }
		  }
	  } else {
		  for(int i = 0; i < set.length; i++){
			  for(int j = 0; j < set.length; j++){
				  current_num = set[i] * 10 + set[j];
				  perm.add(current_num);
			  }
		  }
	  }
	  
	  return perm;
  }
  
  public static boolean hasPassed(int p, int[] set, int length){
	boolean result = true;
	int p_length = ("" + p).length();
	
	if(p_length != length){
		return false;
	}
  
	int denom = 0;
	int quotient = 0, remainder = p;
	for(int i = p_length - 1; i >= 0; i--){
		denom = (int)Math.pow(10, i);
		
		quotient = remainder / denom;
		remainder %= denom;
		
		if(indexOf(quotient, set) == -1){
			return false;
		}
	}
	
	return result;
  }
  
  public static int indexOf(int n, int[] set){
	  int result = -1;
	  
	  for(int i = 0; i < set.length; i++){
		  if(set[i] == n){
			  result = i;
			  return result;
		  }
	  }
	  
	  return result;
  }
}