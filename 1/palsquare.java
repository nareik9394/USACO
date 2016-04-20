/*
ID: kieranc1
LANG: JAVA
TASK: palsquare
*/
import java.io.*;
import java.util.*;

class palsquare {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
	
	int base = Integer.parseInt((new StringTokenizer(f.readLine())).nextToken());
	
	ArrayList<String> nums = new ArrayList<String>();
	ArrayList<String> pal = new ArrayList<String>();
	
	String str = "";
	for(int i = 1; i <= 300; i++){
		str = getNumInBase(i*i, base);
		
		if(isPal(str)){
			nums.add(getNumInBase(i, base));
			pal.add(str);
		}
	}
	
	for(int i = 0; i < pal.size(); i++){
		out.println(nums.get(i) + " " + pal.get(i));
	}
	
    out.close();                                  // close the output file
  }
  
  public static String getNumInBase(int num, int base){
	  String result = "";
	  String dict[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
						, "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	  
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