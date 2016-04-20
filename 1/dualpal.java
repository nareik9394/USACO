/*
ID: kieranc1
LANG: JAVA
TASK: dualpal
*/
import java.io.*;
import java.util.*;

class dualpal {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
	
	StringTokenizer input = new StringTokenizer(f.readLine());
	int num = Integer.parseInt(input.nextToken());
	int start = Integer.parseInt(input.nextToken());
	
	ArrayList<Integer> nums = new ArrayList<Integer>();
	
	String str = "";
	int count = 0;
	
	while(count < num){
		if(isDualPal(++start)){
			nums.add(start);
			count++;
		}
	}
	
	for(int i = 0; i < nums.size(); i++){
		out.println(nums.get(i));
	}
	
    out.close();                                  // close the output file
  }
  
  public static boolean isDualPal(int num){
	  boolean result = false;
	  
	  int pal_num = 0;
	  for(int i = 2; i <= 10; i++){
		  if(isPal(getNumInBase(num, i))){
			  pal_num++;
		  }
		  
		  if(pal_num == 2){
			  return true;
		  }
	  }
	  
	  return result;
  }
  
  public static String getNumInBase(int num, int base){
	  String result = "";
	  String dict[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	  
	  if(base == 10){
		  return num + result;
	  }
	  
	  int remainder = 0;
	  int quotient = num;
	  
	  do{
		  remainder = quotient % base;
		  quotient = quotient / base;
		  
		  result = dict[remainder] + result;
	  } while (quotient != 0);
	  
	  return result;
  }
  
  public static boolean isPal(String str){
	  boolean result = true;
	  
	  if(str.length() == 1){
		  return true;
	  }
	  
	  char f_char = ' ';
	  char s_char = ' ';
	  for(int i = 0; i < (str.length()/2); i++){
		  f_char = str.charAt(i);
		  s_char = str.charAt(str.length() - 1 - i);
		  
		  if(f_char != s_char){
			  return false;
		  }
	  }
	  
	  return result;
  }
}