/*
ID: kieranc1
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;

class ride {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("ride.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
	
    String comet = (new StringTokenizer(f.readLine())).nextToken();
	String group = (new StringTokenizer(f.readLine())).nextToken();
	
	out.println(signal(comet, group));
	
    /*int i1 = Integer.parseInt(st.nextToken());    // first integer
    int i2 = Integer.parseInt(st.nextToken());    // second integer
    out.println(i1+i2);                           // output result
	*/
	
    out.close();                                  // close the output file
  }
  
  public static String signal(String c, String g){
	  return eval(c) == eval(g) ? "GO" : "STAY";
  }
  
  public static int eval(String s){
	  int result = 1;
	  for(int i = 0; i < s.length(); i++){
		  result *= s.charAt(i) - 'A' + 1;
	  }
	  
	  return result % 47;
  }
}