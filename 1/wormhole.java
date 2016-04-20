/*
ID: kieranc1
LANG: JAVA
TASK: wormhole
*/
import java.io.*;
import java.util.*;

class wormhole {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
	StringTokenizer st = new StringTokenizer(f.readLine());
		
    int n = Integer.parseInt(st.nextToken());
	
	ArrayList<Point> pts = new ArrayList<Point>();
	
	int x = 0, y = 0;
	for(int i = 0; i < n; i++){
		st = new StringTokenizer(f.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		
		pts.add(new Point(x,y));
	}
	
	out.println(countLoopCombo(n, pts, new ArrayList<Point>()));
	
	f.close();
    out.close();                                  // close the output file
  }
  
  public static int traverse(ArrayList<Point> pts){
	Point current;
	ArrayList<Point> visited = new ArrayList<Point>();
	  
	int step_count = 0;
	int next_loc = 0;
	for(int i = 0; i < pts.size(); i++){
		current = pts.get(i);
		visited.add(current);
		  
		while(next_loc >= 0){
		  ++step_count;
		  current = current.warp_target;
		  visited.add(current);
			  
		  next_loc = step(current, pts);
			  
		  if(next_loc >= 0){
			  Point temp = pts.get(next_loc);
				  
			  if(visited.contains(temp) && step_count >= 2 * pts.size()){
				  return 1;
			  } else {
				  current = temp;
			  }
		  }
		}
		 
		step_count = 0;
		next_loc = 0;
		visited.clear();
	}
	 
	return 0;
  }
  
  public static int step(Point pt, ArrayList<Point> pts){
	int result = -1;
	int start_point = pts.indexOf(pt) + 1;
		
	for(int i = start_point; i < pts.size(); i++){
		if(pts.get(i).y == pt.y)
			return i;
	}
		
	return result;
  }
	
  public static ArrayList<Point> sortPoints(ArrayList<Point> pts){
	ArrayList<Point> sorted = new ArrayList<Point>();
	
    sorted = (ArrayList<Point>)pts.clone();
	
	Collections.sort(sorted, new Comparator<Point>(){
		public int compare(Point a, Point b){
			Integer a_x = a.x;
			Integer b_x = b.x;
			return a_x.compareTo(b_x);
		}
	});
	
	return sorted;
  }

  public static int countLoopCombo(int n, ArrayList<Point> pts, ArrayList<Point> linked){
	  Point p1, p2;
	  int result = 0;
	  
	  if(n == 2){
		  p1 = pts.get(0);
		  p2 = pts.get(1);
		  
		  Point.link(p1, p2);
		  linked.add(p1);
		  linked.add(p2);
		  
		  ArrayList<Point> sorted_pts = sortPoints(linked);
		  
		  result = traverse(sorted_pts);
		  
		  Point.unlink(p1, p2);
		  
		  linked.remove(p1);
		  linked.remove(p2);
		  
		  return result;
	  }
	  
	  ArrayList<Point> temp = new ArrayList<Point>();
	  
	  for(int i = 1; i < n; i++){
		  p1 = pts.get(0);
		  p2 = pts.get(i);
		  
		  Point.link(p1, p2);
		  
		  linked.add(p1);
		  linked.add(p2);
		  
		  temp = (ArrayList<Point>)pts.clone();
		  temp.remove(p1);
		  temp.remove(p2);
		  
		  result += countLoopCombo(n-2, temp, linked);
		  Point.unlink(p1, p2);
		  linked.remove(p1);
		  linked.remove(p2);
	  }
	  
	  return result;
  }
}

class Point{
	int x, y;
	Point warp_target;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
		warp_target = null;
	}
	
	public static boolean link(Point pt_x, Point pt_y){
		if(pt_x.warp_target == null && pt_y.warp_target == null){
			pt_x.warp_target = pt_y;
			pt_y.warp_target = pt_x;
			
			return true;
		}
		
		return false;
	}
	
	public static void unlink(Point pt_x, Point pt_y){
		pt_x.warp_target = null;
		pt_y.warp_target = null;
	}
}