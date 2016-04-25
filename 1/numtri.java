/*
ID: kieranc1
LANG: JAVA
TASK: numtri
*/
import java.io.*;
import java.util.*;

class numtri {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
	
	StringTokenizer st = new StringTokenizer(f.readLine());
	int r = Integer.parseInt(st.nextToken());
	
	int[] previous = null;
	int[] current = null;
	
	for(int i = 1; i <= r; i++){
		if(i != 1)
			previous = current;
		
		current = new int[r+2];
		current[0] = 0;
		current[r+1] = 0;
		
		st = new StringTokenizer(f.readLine());
		
		for(int j = 1; j <= i; j++){
			current[j] = Integer.parseInt(st.nextToken());
		}
		
		if(i != 1){
			for(int j = 1; j <= i; j++){
				current[j] += Math.max(previous[j-1], previous[j]);
			}
		}
	}
	
	int max = 0;
	for(int i = 0; i < current.length; i++){
		max = Math.max(current[i], max);
	}
	
	out.println(max);
	
	f.close();
    out.close();                                  // close the output file
  }
}