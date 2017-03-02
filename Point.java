import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
 
public class Point {
 
    private double x = 0;
    private double y = 0;
    private int index = 0;
    private int cluster_number = 0;
    static int rows,col;
    static String array[][];
    static double array1[][];
    static String array3[][];
    

    public Point() {
		super();
	}

	public Point(double x, double y, int ind)
    {
        this.setX(x);
        this.setY(y);
        this.setIndex(ind);
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public double getX()  {
        return this.x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setIndex(int indx) {
        this.index = indx;
    }
    
    public int getIndex() {
        return this.index;
    }
    public void setCluster(int n) {
        this.cluster_number = n;
    }
    
    public int getCluster() {
        return this.cluster_number;
    }
    
    //Calculates the distance between two points.    
	public static double distance(Point p,Point centroid)
	{
		double lat1,lon1,lat2,lon2;
		lat1=deg2rad(p.getX());
		lon1=deg2rad(p.getY());
		lat2=deg2rad(centroid.getX());
		lon2=deg2rad(centroid.getY());
		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow((Math.sin(dlat/2)),2) + (Math.cos(lat1) * Math.cos(lat2) * (Math.pow((Math.sin(dlon/2)),2)));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)) ;
		double R = 3961;
		double  d = R*c;
		return d;
	}

	private static double deg2rad(double deg)
	{
		double rad = deg * Math.PI/180; // radians = degrees * pi/180
		return rad;
	}
	
    //Selects a random point from the input as centroid
    protected static Point createRandomPoint(int min, int max) throws FileNotFoundException {
    	Random r = new Random();
    	double[][] array1=new double[rows][2];
    	array1=readCSV();
    	int x =  r.nextInt(max);
    	//int y =  r.nextInt(max);
    	double a = array1[x][0];
    	double b = array1[x][1];
    	return new Point(a,b,x); 
    }
    // Adding points from the input
    protected static List createRandomPoints() throws FileNotFoundException {
    	List points = new ArrayList(rows);
    	double[][] array1=new double[rows][2];
    	array1=readCSV();
    	for(int i = 0; i < rows; i++) {
    		System.out.println();
    		points.add(new Point(array1[i][0],array1[i][1],i));
    	}
    	return points;
    }
    /*
     * Identify the points from the input file
     */
    public static double[][] readCSV() throws FileNotFoundException
    {
    Scanner file1=new Scanner(new File("C:/SampleDemo1.csv"));
	detRowCol(file1);
	Scanner file=new Scanner(new File("C:/SampleDemo1.csv"));
	array=new String[rows][col+2];
	int i=0;
	
	// Ignoring the file line and reading rest of the array1 to a 2D array
	while(file.hasNext())
	{
		try
		{
		String str=file.nextLine();
		String str1[]=new String[col];
		str1=str.split(",");
		for(int j=0;j<col;j++)
		{
		array[i][j] = (str1[j]);
		}
		i++;
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			file.nextLine();
		}
	}
	Point centroid=new Point(43.084520,-77.679954,0);
	for(int k=1;k<rows;k++)
	{
		Point p=new Point(Double.parseDouble(array[k][0]),Double.parseDouble(array[k][1]),k);
		array[k][10]= Double.toString(Point.distance(p, centroid));
	}
	
	for(int k=1;k<rows;k++)
	{
		double price= Double.parseDouble(array[k][7]);
		double avgMileage=22.4;
		double distance= Double.parseDouble(array[k][10]);
		double gasPrice=1.91;
		double gallons = distance/avgMileage;
		double totPrice = price + (gallons*gasPrice);
		array[k][11]= Double.toString(totPrice);
	}
    
	double array1[][]=new double[rows][2];
	array1=stod(array);
	file.close();
	file1.close();
	return array1;
}

// Determine the number of rows and columns in the input file
public static void detRowCol(Scanner file)
{
	int i=0;
	int COL=0;
	while(file.hasNext())
	{
	try
	{
	String str=file.nextLine();
	String str1[]=new String[100];
	str1=str.split(",");
	COL=10;
	for(int j=0;j<COL;j++)
	{
	}
	i++;
	}
	catch(Exception e)
	{
		//e.printStackTrace();
		file.nextLine();
	}
	}
	col=COL;
	rows=i;
	file.close();
}

// String array to double array conversion
public static double[][] stod(String array[][])
{
	array1=new double[rows][2];
	for(int i=1;i<rows;i++)
	{
		for(int j=0;j<2;j++)
		{
				array1[i][j]=Double.parseDouble(array[i][j]);
		}
	}
	return array1;
}
public static void putDistance()
{
	array3=new String[rows][9];
	for(int i=0;i<rows;i++)
	{
		for(int j=0;j<8;j++)
		{
		array3[i][j] = array[i][j];
		}
	}
	Point centroid=new Point(43.084520,-77.679954,0);
	for(int i=1;i<rows;i++)
	{
		Point p=new Point(Double.parseDouble(array[i][0]),Double.parseDouble(array[i][1]),i);
		array[i][10]= Double.toString(Point.distance(p, centroid));
	}
}
    public String toString() {
    	return "("+x+","+y+","+index+")";
    }
}