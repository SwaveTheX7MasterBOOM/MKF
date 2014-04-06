package control;

import java.util.Comparator;

import logicalGameObjects.Actor;
import logicalGameObjects.Setpiece;


/**
 * Comparator to sort objects based on the Y Axis value
 * 
 * @author Thomas "Fucking" Capach
 *
 */
public class YAxisSort implements Comparator<Object>
{

	/**
	 * compare two on screen objects
	 */
	public int compare(Object one, Object two)
	{
		
		int oneY = 0;
		int twoY = 0;
		
			//firsts objects y axis coordinate
		 	if(one instanceof Setpiece)
		 	{
			 
		 		oneY = (int) ((Setpiece) one).getBox().getY();
		 		
		 	}
		 	else if(one instanceof Actor)
		 	{
			 
		 		oneY = (int) ((Actor) one).getUpBox().ypoints[0];
		 		
		 	}
		
		 	//second objects y axis coordinate
		 	if(two instanceof Setpiece)
		 	{

					twoY = (int) ((Setpiece) two).getBox().getY();

		 	}
		 	else if(two instanceof Actor)
		 	{
		 		
		 		twoY = (int) ((Actor) two).getUpBox().ypoints[0];
		 		
		 	}
		
		 		//compare the values
		 		if(oneY < twoY)
		 		{
			
		 			return -1;
			
		 		}
		 		else if(oneY > twoY)
		 		{
			
		 			return 1;
			
		 		}
		 		else
		 		{
			
		 			return 0;
			
		 		}
		
	}

}
