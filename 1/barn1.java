/*
ID: kieranc1
LANG: JAVA
TASK: barn1
*/
import java.io.*;
import java.util.*;

class barn1 {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
	
	StringTokenizer st = new StringTokenizer(f.readLine());
	int max_board_num = Integer.parseInt(st.nextToken());
	int total_stall_num = Integer.parseInt(st.nextToken());
	int cow_num = Integer.parseInt(st.nextToken());
	
	if(max_board_num > cow_num){
		out.println(cow_num);
		out.close();
		return;
	}
	
	int[] occupied_stall = new int[cow_num];	
	int[] gap_nums = new int[cow_num];
	gap_nums[0] = 0;
	int[] locations = new int[cow_num];
	
	int stall_num = 0;
	for(int i = 0; i < cow_num; i++){
		st = new StringTokenizer(f.readLine());
		stall_num = Integer.parseInt(st.nextToken());
		
		occupied_stall[i] = stall_num;
		locations[i] = i;
	}
	
	Arrays.sort(occupied_stall);
	
	for(int i = 1; i < cow_num; i++){
		gap_nums[i] = occupied_stall[i] - occupied_stall[i-1];
	}
	
	// Bubble Sort
	int sum = 0;
	int start = 0, end = 0;
	
	if(max_board_num != 1){
		int[] gaps_workset = gap_nums.clone();
		
		int temp = 0, temp_loc = -1;
		for(int i = 0; i < gaps_workset.length - 1; i++){
			for(int j = 0; j < gaps_workset.length - i - 1; j++){
				if(gaps_workset[j] < gaps_workset[j+1]){
					temp = gaps_workset[j];
					temp_loc = locations[j];
					
					gaps_workset[j] = gaps_workset[j+1];
					locations[j] = locations[j+1];
					
					gaps_workset[j+1] = temp;
					locations[j+1] = temp_loc;
				}
			}
		}
		
		//System.out.println("BubbleSort Completed...");
		
		int[] break_points = new int[max_board_num - 1];
		
		for(int i = 0; i < break_points.length; i++){
			break_points[i] = locations[i];	// gets the location to break from
		}
		
		Arrays.sort(break_points);
		
		//System.out.println("Break Points Sorting Completed...");
		
		for(int i = 0; i < break_points.length; i++){
			end = break_points[i];
			
			sum += occupied_stall[end - 1] - occupied_stall[start] + 1;
			
			//System.out.println(end + " " + sum);
			start = end;
		}
	}
	
	sum += occupied_stall[occupied_stall.length - 1] - occupied_stall[start] + 1;
	
	out.println(sum);
	
    out.close();                                  // close the output file
  }
}