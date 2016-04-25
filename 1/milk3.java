/*
ID: kieranc1
LANG: JAVA
TASK: milk3
*/
import java.io.*;
import java.util.*;

class milk3 {
	private static boolean[][] searched = new boolean[21][21];
	private static boolean[] amount = new boolean[21];
	private static int a,b,c;
	
	public static void main(String[] args) throws Exception{
		BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")),true);
		
		//read data
		StringTokenizer st = new StringTokenizer(f.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		dfs(0,0,c);
		for (int i = 0;i < c;i++) 
			if (amount[i]) 
				out.print(i + " ");
		out.println(c);

		f.close();
		out.close();
	}
	
	private static void dfs(int x, int y, int z){
		if(searched[x][y]) 
			return;
		searched[x][y] = true;
		
		if(x == 0) 
			amount[z] = true;
		
		if (x>0 && y<b) 
			dfs(Math.max(0,x+y-b), Math.min(b,x+y), z);
		
		if (x>0 && z<c) 
			dfs(Math.max(0,x+z-c), y, Math.min(c,x+z));
		
		if (y>0 && x<a) 
			dfs(Math.min(a,y+x), Math.max(0,y+x-a), z);
		
		if (y>0 && z<c) 
			dfs(x, Math.max(0,y+z-c), Math.min(c,y+z));
		
		if (z>0 && x<a) 
			dfs(Math.min(a,z+x), y, Math.max(0,z+x-a));
		
		if (z>0 && y<b) 
			dfs(x, Math.min(b,z+y), Math.max(0,z+y-b));
	}
}