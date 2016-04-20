/*
ID: kieranc1
LANG: JAVA
TASK: skidesign
*/
import java.io.*;
import java.util.*;

class skidesign {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
	StringTokenizer st = new StringTokenizer(f.readLine());
	
    int n = Integer.parseInt(st.nextToken());
	
	int[] hills = new int[n];
	for(int i = 0; i < n; i++){
		st = new StringTokenizer(f.readLine());
		
		hills[i] = Integer.parseInt(st.nextToken());
	}
	
	Arrays.sort(hills);
	
	out.println();
	
	f.close();
    out.close();                                  // close the output file
  }
}