import java.util.ArrayList;
import java.util.List;
public class Cluster {
	
	public ArrayList<Point> points;
	public Point centroid;
	public int id;
	public static ArrayList<ArrayList<Point>> clusterList=new ArrayList<ArrayList<Point>>();
	public static ArrayList<Point> clusterCentroids=new ArrayList<Point>();
	
	//Creates a new Cluster
	public Cluster(int id) {
		this.id = id;
		this.points = new ArrayList();
		this.centroid = null;
	}
 
	public List getPoints() {
		return points;
	}
	
	public void addPoint(Point point) {
		points.add(point);
	}
 
	public void setPoints(ArrayList points) {
		this.points = points;
	}
 
	public Point getCentroid() {
		return centroid;
	}
 
	public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}
 
	public int getId() {
		return id;
	}
	
	public void clear() {
		points.clear();
	}
	/*
	 * Adds the points to their respective clusters
	 */
	public void plotCluster() {
//		System.out.println("[Cluster: " + id+"]");
//		System.out.println("[Centroid: " + centroid + "]");
//		System.out.println("[Points: ");
		clusterCentroids.add(id,centroid);
		clusterList.add(id,points);
		for(Point p : points) {
//			System.out.println(p);
		}
//		System.out.println("]");
	}
 
}