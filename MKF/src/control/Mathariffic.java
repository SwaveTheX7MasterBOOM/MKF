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
	
	/**
	 * get the angel between two points
	 * 
	 * see diagram in the drive for a visual representation of angels and their corresponding cardinal direction
	 * 
	 * @param one - Coordinate, first one to compare
	 * @param two - Coordinate, second one to compare
	 * 
	 * @return double - the angle between the two points
	 */
	public static double angleBetween2Coordinates(Coordinate one, Coordinate two)
	{
			
		double xDiff = two.getX() - one.getX(); 
		double yDiff = two.getY() - one.getY();
		
		return Math.toDegrees(Math.atan2(yDiff, xDiff));
		
	}

	public static double distanceBetween2Coordinates(Coordinate one, Coordinate two)
	{

		double xDiff = Math.pow((two.getX() - one.getX()), 2); 
		double yDiff = Math.pow((two.getY() - one.getY()), 2);
		
		return Math.sqrt((xDiff + yDiff));
		
	}

}
