
public class FindDistance 
{
	public static void main(String[] args) 
	{
		double Latitude1=43.103496,Longitude1=-77.632413;
		double Latitude2=42.886447,Longitude2=-78.878369;
		double distance=0;
		distance=findDistance(Latitude1,Longitude1,Latitude2,Longitude2);
		System.out.println(distance);
	}
	
	public static double findDistance(double lat1,double lon1,double lat2,double lon2)
	{
		lat1=deg2rad(lat1);
		lon1=deg2rad(lon1);
		lat2=deg2rad(lat2);
		lon2=deg2rad(lon2);
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
}
