/*
ID: kieranc1
LANG: JAVA
TASK: namenum
*/
import java.io.*;
import java.util.*;

class namenum {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
	StringTokenizer st = new StringTokenizer(f.readLine());
	String input = st.nextToken();
	
	ArrayList<String> valid = produce(input);
	
	if(valid.isEmpty()){
		out.println("NONE");
	} else {
		for(int i = 0; i < valid.size(); i++){
			out.println(valid.get(i));
		}
	}
	
    out.close();                                  // close the output file
  }
  
  public static ArrayList<String> produce(String input) throws IOException{
	final HashMap<Character, String> map = new HashMap<Character, String>();
	map.put('2', "ABC");
	map.put('3', "DEF");
	map.put('4', "GHI");
	map.put('5', "JKL");
	map.put('6', "MNO");
	map.put('7', "PRS");
	map.put('8', "TUV");
	map.put('9', "WXY");
	
	String input_range = map.get(input.charAt(0));
	
    BufferedReader f = new BufferedReader(new FileReader("dict.txt"));
	String[] dict = new String[5000];
	String current;
	int dict_num = 0;
	while((current = f.readLine()) != null){
		if(input_range.indexOf(current.charAt(0)) >= 0){
			dict[dict_num++] = current;
		}
	}
	
	ArrayList<String> combo = new ArrayList<String>();
	ArrayList<String> temp = new ArrayList<String>();
	combo.add("");
	
	String letters, new_str;
	int str_len;
	String[] reduced_dict = new String[dict_num];
	int rdict_num = 0;
	for(int i = 0; i < input.length(); i++){
		rdict_num = 0;
		
		for(String str : combo){
			letters = map.get(input.charAt(i));
			
			for(int j = 0; j < letters.length(); j++){
				new_str = str + letters.charAt(j);
				temp.add(new_str);
				
				str_len = new_str.length();
				
				for(int k = 0; k < dict_num; k++){
					if(dict[k].length() >= str_len && new_str.equals(dict[k].substring(0, str_len))){
						reduced_dict[rdict_num++] = dict[k];
					}
				}
			}
		}
		
		combo = temp;
		temp = new ArrayList<String>();
		
		for(int j = rdict_num; j < dict_num; j++){
			reduced_dict[j] = null;
		}
		
		if(rdict_num == 1){
			break;
		}
	}
	
	ArrayList<String> result = new ArrayList<String>();
	for(int i = 0; i < rdict_num; i++){
		if(reduced_dict[i].length() == input.length()){
			result.add(reduced_dict[i]);
		}
	}
	
	return result;
  }
}