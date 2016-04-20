/*
ID: kieranc1
LANG: JAVA
TASK: combo
*/
import java.io.*;
import java.util.*;

class combo {
	final static int COMBO_LENGTH = 3;
	final static int TOLERANCE = 2;
	static int upper_limit = 50;
	
	public static void main (String [] args) throws IOException {
	
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("combo.in"));
													  // input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
		// Use StringTokenizer vs. readLine/split -- lots faster
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		upper_limit = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(f.readLine());
		int[] farmer_combo = new int[COMBO_LENGTH];
		for(int i = 0; i < COMBO_LENGTH; i++){
			farmer_combo[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(f.readLine());
		int[] master_combo = new int[COMBO_LENGTH];
		for(int i = 0; i < COMBO_LENGTH; i++){
			master_combo[i] = Integer.parseInt(st.nextToken());
		}
		
		int counter = 0;
		int[] test_combo = new int[COMBO_LENGTH];
		for(int i = 1; i <= upper_limit; i++){
			for(int j = 1; j <= upper_limit; j++){
				for(int k = 1; k <= upper_limit; k++){
					test_combo[0] = i;
					test_combo[1] = j;
					test_combo[2] = k;
					
					//if test_combo within range of either one combos, then counter++
					if(isWithinRange(test_combo, farmer_combo, master_combo))
						counter++;
				}
			}
		}
		
		out.println(counter);
		
		f.close();
		out.close();                                  // close the output file
	}
	
	public static boolean isWithinRange(int[] test, int[] first, int[] second){
		boolean first_result = true;
		boolean second_result = true;

		for(int i = 0; i < COMBO_LENGTH; i++){
			if(!isValid(test[i], first[i]))
				first_result = false;
			
			if(!isValid(test[i], second[i]))
				second_result = false;
		}
		
		return first_result || second_result;
	}
	
	public static boolean isValid(int a, int b){
		if(Math.abs(a - b) <= TOLERANCE)
			return true;
		
		if(Math.abs(a - b) >= upper_limit - TOLERANCE)
			return true;
		
		return false;
	}
}