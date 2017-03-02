/**
 * Return the Query result based on keyword and location using InvertedIndex.java
 * 
 */
import java.io.*;
import java.util.*;

public class Demo {
	static ArrayList<Integer> result2;
	public Demo() {
		// TODO Auto-generated constructor stub
	}
	/**Removing punctuation and other symbols. Only words and numbers stays. 
	 * Run Stemmer and write final words to output file.
	 * @param inFile1
	 * @param sWord
	 * @return
	 */
	public static String[] getDocs(BufferedReader inFile1){
		String str = new String();  
		String line="";
		String[] wordC1 = null;
		String[] str1 = new String[100];
		try {
			while((line = inFile1.readLine()) != null){
				str1=line.split(",");
				str=str+" "+str1[2];
				//System.out.println("ADDED : "+str1[2]);
			}
			inFile1.close();
			//String newDoc1 = str.replaceAll("[^\\w\\s]","").replaceAll("\\s+", " ");
			//Array of doc words
			wordC1 = str.split(" ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wordC1;
	}

	/**
	 * Query the invertedIndex results based given parameter
	 * @param invertedInd
	 * @param keywordList
	 * 
	 */
	public static void getResult(InvertedIndex invertedInd, String keyWList){
		ArrayList<Integer> result = invertedInd.search(keyWList);
		ArrayList<Integer> result1=new ArrayList<Integer>();
		result2=new ArrayList<Integer>();
			if(result!=null){
				for(Integer i:result)
				{
					result1.add((i.intValue()));
				}
				int cluster_number=404;
				cluster_number=identifyCluster();
				for(int i=0;i<Cluster.clusterList.get(cluster_number).size();i++)
				{
					result2.add(Cluster.clusterList.get(cluster_number).get(i).getIndex());
				}
				System.out.println();
				System.out.println("Documents filtered using search keyword:"+result1.size());
				for(Integer i:result1)
				{
					System.out.printf("doc "+(i.intValue())+" ");
				}
				System.out.println();
				System.out.println("Documents filtered using location:"+result2.size());
				for(Integer i:result2)
				{
					System.out.printf("doc "+(i.intValue())+" ");
				}
				result2.retainAll(result1);
				System.out.println();
				System.out.println("Documents filtered using both search keyword and location:"+result2.size());
				for(Integer i:result2)
				{
					System.out.printf("doc "+(i.intValue())+" ");
					System.out.println(new Point().array1[i.intValue()-1][0]+" "+new Point().array1[i.intValue()-1][1]);
				}
			}
			else
				System.out.println("No match!");
			System.out.printf("\n--------------------------------------------------- \n");
	}
	/*
	 * This method identifies the cluster in which the user location belongs to.
	 */
	public static int identifyCluster()
	{
		Cluster userLoc=null;
		int index=404;
		double uLong=(39.752713);
		double uLat=(-104.996337);
		double minDistance=Double.MAX_VALUE;
		int userCluster=404;//default or no cluster found
		double minX=999,maxX=-999;
		double minY=999,maxY=-999;
		int count=0;
		for(int i=0;i<Cluster.clusterCentroids.size();i++)
		{
			Point a=Cluster.clusterCentroids.get(i);
			double ax=a.getX();
			double ay=a.getY();
			double distance=calDistance(ax,ay,uLong,uLat);
			if(distance<minDistance)
			{
				minDistance=distance;
				userCluster=i;
			}	
		}
		return userCluster;
	}
		/*
		 * This method computes the distance between two points based on their latitude
		 * and longitude information.
		 */
		public static double calDistance(double lat1,double lon1,double lat2,double lon2)
		{
			double dlon = lon2 - lon1;
			double dlat = lat2 - lat1;
			double a = Math.pow((Math.sin(dlat/2)),2) + (Math.cos(lat1) * Math.cos(lat2) * (Math.pow((Math.sin(dlon/2)),2)));
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)) ;
			double R = 3961;
			double  d = R*c;
			return d;
		}
	/**
	 * main()
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.print("Inverse Indexing - Step 1 : reading documents, tokenize and running stemmer \n");
		try {
			System.out.println("Reading documents");
			BufferedReader inFile1 = new BufferedReader(new FileReader("C:/SampleDemo1.csv"));
			System.out.println("Running stemmer and preparing the docs String array");
			String[] docs = getDocs(inFile1);
			KMeans kmeans1 = new KMeans();
	    	kmeans1.init();
	    	kmeans1.calculate();
	    	
			System.out.println("Inverted Index Function call");
			InvertedIndex invertedInd = new InvertedIndex(docs);
			System.out.println(invertedInd);
			//---------------------------------------------------------------
			String str2= args[0];
			System.out.println("Query 1 : Single Keyword - Instead of args - using: "+str2);
			getResult(invertedInd,str2);
			//----------------------------------------------------------
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}