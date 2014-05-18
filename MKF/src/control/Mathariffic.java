package control;

import logicalGameObjects.Coordinate;


public class Mathariffic
{

	/**recursive method to find greatest common factor.  Used in MeleeAi to find the path for the
	 *  melee enemy will take toward the player
	 *  
	 * @param a - int representing x Position of the player
	 * @param b - int representing y Position of the player
	 * 
	 * @return GCF - int representing greatest common factor**/
	public static int GCF(int a, int b)
	{	
		
		if (b == 0)
		{	  
			
			return a; 
			
		}
		else
		{	  
			
			return (GCF (b, a % b));
			
		}
		
	}
	
	public static double angleBetween2Coordinates(Coordinate one, Coordinate two)
	{
		
		
		double xDiff = two.getX() - one.getX(); 
		double yDiff = two.getY() - one.getY();
		
		return Math.toDegrees(Math.atan2(yDiff, xDiff));
		
			
		
	}

}
