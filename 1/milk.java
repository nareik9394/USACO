/*
ID: kieranc1
LANG: JAVA
TASK: milk
*/
import java.io.*;
import java.util.*;

class milk {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("milk.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
	
	StringTokenizer st = new StringTokenizer(f.readLine());
	int required_unit = Integer.parseInt(st.nextToken());
	int farmer_nums = Integer.parseInt(st.nextToken());
	
	HashMap<Integer, Integer> supply = new HashMap<Integer, Integer>();
	
	int unit_price = 0;
	int units = 0;
	for(int i = 0; i < farmer_nums; i++){
		st = new StringTokenizer(f.readLine());
		
		unit_price = Integer.parseInt(st.nextToken());
		units = Integer.parseInt(st.nextToken());
		
		if(supply.containsKey(unit_price)){
			supply.put(unit_price, supply.get(unit_price) + units);
		} else {
			supply.put(unit_price, units);
		}
	}
	
	int bought_units = 0;
	int spent_money = 0;
	int available_units = 0;
	for(int i = 0; i <= 1000; i++){
		if(supply.containsKey(i)){
			available_units = supply.get(i);
			
			//System.out.println(i + " " + available_units + " " + (required_unit - bought_units));
			
			if(required_unit - bought_units > available_units){
				spent_money += available_units * i;
				bought_units += available_units;
			} else{
				spent_money += (required_unit - bought_units) * i;
				bought_units += required_unit - bought_units;
			}
		}
		
		if(bought_units == required_unit){
			break;
		}
	}
	
	out.println(spent_money);
	
    out.close();                                  // close the output file
  }
}