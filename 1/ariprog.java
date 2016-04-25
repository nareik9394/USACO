/*
ID: kieranc1
LANG: JAVA
TASK: ariprog
*/
import java.io.*;
import java.util.*;

class ariprog {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
	
	StringTokenizer st = new StringTokenizer(f.readLine());
	int n = Integer.parseInt(st.nextToken());
	
	st = new StringTokenizer(f.readLine());
	int m = Integer.parseInt(st.nextToken());
	
	int range = 2*m*m;
	int count = 0;
	
	//ArrayList<Integer> bisquares = generate(m);
	boolean[] bisquares = new boolean[range + 1];
	for(int p = 0; p <= m ; p++){
		for(int q = 0; q <= m; q++){
			bisquares[p*p + q*q] = true;
		}
	}
	
	/*
	String result = "";
	int temp_product = 0;
	for(int b = 1; b < range/(n-1) + 1; b++){
		for(int a = 0; a < range; a++){
			for(int i = 0; i < n; i++){
				temp_product = a + i*b;
				
				if(!(temp_product <= range))
					break;
				else if(!bisquares[temp_product])
					break;
				else if(i == n-1){
					count++;
					result += a + " " + b + "\n";
				}
			}
		}
	}
	
	if(count == 0)
		out.println("NONE");
	else
		out.println(result.trim());
	*/
	
	// search and pruning
	List<int[]> res = new ArrayList<int[]>();
	for(int a = 0; a < range; a++){
		if(!bisquares[a]) continue;
		label:
		for(int b = 1; b <= (range-a)/ (n-1); b++){ // a+ (N-1) * b <= range
			for(int i = 1; i<= n-1; i++){
				if(!bisquares[a + b * i])
					continue label;
			}
			res.add(new int[]{a,b});
		}
	}

	// sort and print
	Collections.sort(res,new Comparator<int[]>(){
		public int compare(int[] o1, int[] o2) {
			if(o1[1] < o2[1]) return -1;
			if(o1[1] > o2[1]) return 1;
			if(o1[0] < o2[0]) return -1;
			if(o1[0] < o2[0]) return 1;
			return 0;
		}});
	if(res.size() == 0) out.println("NONE");
	for(int[] ab : res)
		out.println(ab[0]+ " " + ab[1]);
	
	f.close();
    out.close();                                  // close the output file
  }
  
  public static ArrayList<Integer> generate(int m){
	  ArrayList<Integer> bisquares = new ArrayList<Integer>();
	  
	  int prod = 0;
	  for(int p = 0; p <= m; p++){
		  for(int q = 0; q <= m; q++){
			  prod = p*p + q*q;
			  
			  if(!bisquares.contains(prod))
				bisquares.add(prod);
		  }
	  }
	  
	  return bisquares;
  }
}