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
	
	final int TOLERANCE = 17;
    int n = Integer.parseInt(st.nextToken());
	
	int[] hills = new int[n];
	for(int i = 0; i < n; i++){
		st = new StringTokenizer(f.readLine());
		
		hills[i] = Integer.parseInt(st.nextToken());
	}
	
	int sum = Integer.MAX_VALUE;
	for(int min = 0; min <= 100; min++){
		int temp_sum = 0;
		
		for(int i = 0; i < n; i++){
			int diff = hills[i] - min;
			
			if(diff > TOLERANCE){
				diff -= TOLERANCE;
				temp_sum += diff * diff;
			} else if(diff < 0){
				temp_sum += diff * diff;
			}
		}
		
		sum = Math.min(sum, temp_sum);
	}
	
	
	out.println(sum);
	
	f.close();
    out.close();                                  // close the output file
  }
}