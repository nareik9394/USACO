/*
ID: kieranc1
LANG: JAVA
TASK: friday
*/
import java.io.*;
import java.util.*;

class friday {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("friday.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
	int n = Integer.parseInt(f.readLine());
	
	int[] weekdays = new int[7];
	int day = 0;
	for(int year = 1900; year < 1900 + n; year++){
		for(int month = 1; month <= 12; month++){
			if(year == 1900 && month == 1){
				day = 6;
			} else {
				day = (next(month, year) + day) % 7;
			}
			
			weekdays[day]++;
		}
	}
	
    //String comet = (new StringTokenizer(f.readLine())).nextToken();
	//String group = (new StringTokenizer(f.readLine())).nextToken();
	
	out.print(weekdays[6] + " ");
	for (int i = 0; i <= 4; i++)
		out.print(weekdays[i] + " ");
	out.println(weekdays[5]);
	
    /*int i1 = Integer.parseInt(st.nextToken());    // first integer
    int i2 = Integer.parseInt(st.nextToken());    // second integer
    out.println(i1+i2);                           // output result
	*/
	
    out.close();                                  // close the output file
  }
  
	private static boolean isLeapYear(int year) {
		if (year % 4 != 0)
			return false;
		if (year % 100 == 0)
			if (year % 400 == 0)
				return true;
			else
				return false;
		return true;
	}
	
	private static int next(int month, int year) {
		switch (month) {
		case 2:
			return 31;
		case 3:
			return isLeapYear(year) ? 29 : 28;
		case 4:
			return 31;
		case 5:
			return 30;
		case 6:
			return 31;
		case 7:
			return 30;
		case 8:
			return 31;
		case 9:
			return 31;
		case 10:
			return 30;
		case 11:
			return 31;
		case 12:
			return 30;
		case 1:
			return 31;
		}
		
		return -1;
	}
}