/*
ID: kieranc1
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

class gift1 {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    StringTokenizer st = new StringTokenizer(f.readLine());
	int players_num = Integer.parseInt(st.nextToken());
	String[] players = new String[players_num];
	Map<String, Integer> accounts = new HashMap<String, Integer>();
	
	String player_name;
	for(int i = 0; i < players_num; i++){
		st = new StringTokenizer(f.readLine());
		player_name = st.nextToken();
		players[i] = player_name;
		accounts.put(player_name, 0);
	}
	
	String current;
	int bank, gift_num, change, gift_amount;
	for(int i = 0; i < players_num; i++){
		st = new StringTokenizer(f.readLine());
		current = st.nextToken();
		
		st = new StringTokenizer(f.readLine());
		bank = Integer.parseInt(st.nextToken());
		gift_num = Integer.parseInt(st.nextToken());
		
		if(gift_num != 0){
			change = bank % gift_num;
			gift_amount = (bank - change) / gift_num;
			accounts.put(current, accounts.get(current) - bank + change);
			
			for(int j = 0; j < gift_num; j++){
				st = new StringTokenizer(f.readLine());
				current = st.nextToken();
				
				accounts.put(current, accounts.get(current) + gift_amount);
			}
		}
	}
	
	for(String s : players){
		out.println(s + " " + accounts.get(s));
	}
	
    out.close();                                  // close the output file
  }
}