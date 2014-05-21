package control;

/**
 * Named for the famous navigator, this class contains methods to help find the correct path such as
 * 
 * -Identifying adjacent map tiles as path tiles(code 0) or other for map generation
 * -converting a supplied angel to a cardinal direction
 * 
 * @author Thomas "Fucking" Capach
 *
 */
public class Magellon
{
	//tile map logical view
	private static int[][] map;

	/**
	 * identify top left tile
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean findTopLeft(int[][] world, int x, int y)
	{

		map = world;
		
		try
		{
			
			int m = map[y - 1][x - 1];
			
				if(m == 0)
				{
				
					return true;
				
				}
				else
				{
					
					return false;
					
				}
			
		}
		catch(IndexOutOfBoundsException e)
		{
			
			return false;
		
		}
		
	}
	
	
	/**
	 * identify top tile
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean findTop(int[][] world, int x, int y)
	{

		map = world;
		
		try
		{
			
			int m = map[y - 1][x];
			
			if(m == 0)
			{
			
				return true;
			
			}
			else
			{
				
				return false;
				
			}
			
		}
		catch(IndexOutOfBoundsException e)
		{
			
			return false;
		
		}
		
	}
	
	
	/**
	 * identify top right tile
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean findTopRight(int[][] world, int x, int y)
	{

		map = world;
		
		try
		{
			
			int m = map[y - 1][x + 1];
			
			if(m == 0)
			{
			
				return true;
			
			}
			else
			{
				
				return false;
				
			}
			
		}
		catch(IndexOutOfBoundsException e)
		{
			
			return false;
		
		}
		
	}
	
	
	/**
	 * identify left tile
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean findLeft(int[][] world, int x, int y)
	{

		map = world;
		
		try
		{
			
			int m = map[y][x - 1];
			
			if(m == 0)
			{
			
				return true;
			
			}
			else
			{
				
				return false;
				
			}
			
		}
		catch(IndexOutOfBoundsException e)
		{
			
			return false;
		
		}
		
	}
	
	
	/**
	 * identify right tile
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean findRight(int[][] world, int x, int y)
	{

		map = world;
		
		try
		{
			
			int m = map[y][x + 1];
			
			if(m == 0)
			{
			
				return true;
			
			}
			else
			{
				
				return false;
				
			}
			
		}
		catch(IndexOutOfBoundsException e)
		{
			
			return false;
		
		}
		
	}
	
	/**
	 * identify bottom left tile
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean findBotLeft(int[][] world, int x, int y)
	{
		
		map = world;
		
		try
		{
			
			int m = map[y + 1][x - 1];
			
			if(m == 0)
			{
			
				return true;
			
			}
			else
			{
				
				return false;
				
			}
			
		}
		catch(IndexOutOfBoundsException e)
		{
			
			return false;
		
		}
		
	}
	
	
	/**
	 * identify bottom tile
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean findBot(int[][] world, int x, int y)
	{
		
		map = world;
		
		try
		{
			
			int m = map[y + 1][x];
			
			if(m == 0)
			{
			
				return true;
			
			}
			else
			{
				
				return false;
				
			}
			
		}
		catch(IndexOutOfBoundsException e)
		{
			
			return false;
		
		}
		
	}
	
	
	/**
	 * identify bottom right tile
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean findBotRight(int[][] world, int x, int y)
	{
		
		map = world;
		
		try
		{
			
			int m = map[y + 1][x + 1];
			
			if(m == 0)
			{
			
				return true;
			
			}
			else
			{
				
				return false;
				
			}
			
		}
		catch(IndexOutOfBoundsException e)
		{
			
			return false;
		
		}
		
	}


	public static String findCardinalDirection(double degree)
	{
		
		String direction = "";
		
		
		if(Double.compare(degree, 0.0000) > 0)//positive number, must be between 0-180
		{//System.out.println("positive");
			
			if(Double.compare(degree, 90.000) > 0)//greater than 90.0, must be between 90-180
			{//System.out.println("greater than 90.0");
				
				if(Double.compare(degree, 135.000) > 0)//greater than 135.0, must be between 135-180
				{//System.out.println("greater than 135.0");
					
				//w, sww, sw
				
							if(Double.compare(degree, 157.5000) > 0)//greater than 157.5, must be between 157.5-180
							{
								
								if(Double.compare(degree, 168.7500) < 0)//less than 168.75, must be southwestwest
								{
									
									direction = "southWestWest";

									//System.out.println("sww "+ "degree - "+degree+" should be between 146.25-168.75");
									

									
								}
								else//greater than or equal to 168.75, must be west
								{
									
									direction = "west";
								//	System.out.println("w "+ "degree - "+degree+" should be between -168.75~168.75");
								
									
								}
								
							}
							else if(Double.compare(degree, 157.5000) < 0)//less than 157.5 must be between 135.0-157.5
							{
								
								if(Double.compare(degree, 146.2500) > 0)//greater than 146.25 must be southwestwest
								{
									
									direction = "southWestWest";
									//System.out.println("ssw "+ "degree - "+degree+" should be between 146.25-168.75");
									
									
								}
								else//less than or equal to 146.25 must be south west 
								{
									
									direction = "southWest";
									//System.out.println("sw "+ "degree - "+degree+" should be between 123.75-146.25");
									
									
								}
								
							}
							else//exactly 157.5 must be sww
							{
								
								direction = "southWestWest";
								//System.out.println("sww equal "+ "degree - "+degree+" should be between 146.25-168.75");
								
								
							}

				}
				else if(Double.compare(degree, 135.000) < 0)//less than 135.0, greater than 90, must be between 90-135
				{//System.out.println("less than 135.0");
					
				//sw, ssw, s
									
							if(Double.compare(degree, 112.500) > 0)//greater than 112.5000, must be between 112.5-135
							{
								
								if(Double.compare(degree, 123.7500) < 0)//less than 123.75, must be southsouthwest
								{
									
									direction = "southSouthWest";
								//	System.out.println("ssw "+ "degree - "+degree+" should be between 123.75-101.25");
									

									
								}
								else//greater than or equal to 123.75, must be southwest
								{
									
									direction = "southWest";
									//System.out.println("sw "+ "degree - "+degree+" should be between 123.75-146.25");
								
									
								}
								
							}
							else if(Double.compare(degree, 112.500) < 0)//less than 112.5 must be between 90-112.5
							{
								
								if(Double.compare(degree, 101.2500) > 0)//greater than 101.25 must be southsouthwest
								{
									
									direction = "southSouthWest";
									//System.out.println("ssw "+ "degree - "+degree+" should be between 123.75-101.25");
									
									
								}
								else//less than or equal to 101.25 must be south
								{
									
									direction = "south";
									//System.out.println("s "+ "degree - "+degree+" should be between 101.25-78.75");
									
									
								}
								
							}
							else//exactly 112.5 must be ssw
							{
								
								direction = "southSouthWest";
								//System.out.println("ssw equal "+ "degree - "+degree+" should be between 123.75-101.25");
								
								
							}
					
				}
				else//Exactly 135.0
				{
					
					direction = "southWest";
					//System.out.println("sw equal");
					
				}
				
				
			}
			else if(Double.compare(degree, 90.000) < 0)//less than 90.0, between 0-90
			{//System.out.println("less than 90.0");
				
				if(Double.compare(degree, 45.000) > 0)//greater than 45.0, between 45-90
				{//System.out.println("greater than 45.0");
					
				//s, sse, se
					
							if(Double.compare(degree, 67.5000) > 0)//greater than 67.5, must be between 67.5-90
							{
								
								if(Double.compare(degree, 78.7500) < 0)//less than 78.75, must be southsoutheast
								{
									
									direction = "southSouthEast";
									//System.out.println("sse "+ "degree - "+degree+" should be between 78.75-56.25");
									

									
								}
								else//greater than or equal to 78.75, must be south
								{
									
									direction = "south";
									//System.out.println("s "+ "degree - "+degree+" should be between 101.25-78.75");
								
									
								}
								
							}
							else if(Double.compare(degree, 67.5000) < 0)//less than 67.5 must be between 67.5-45
							{
								
								if(Double.compare(degree, 56.2500) > 0)//greater than 56.25 must be 
								{
									
									direction = "southSouthEast";
									//System.out.println("sse "+ "degree - "+degree+" should be between 78.75-56.25");
									
									
								}
								else//less than or equal to 56.25 must be 
								{
									
									direction = "southEast";
									//System.out.println("se "+ "degree - "+degree+" should be between 56.25-33.75");
									
									
								}
								
							}
							else//exactly 67.5 must be sse
							{
								
								direction = "southSouthEast";
								//System.out.println("sse equal "+ "degree - "+degree+" should be between 78.75-56.25");
								
								
							}

				
				}
				else if(Double.compare(degree, 45.000) < 0)//less than 45.0 between 0-45
				{//System.out.println("less than 45.0");
				
				//se, see, e
							
							if(Double.compare(degree, 22.5000) > 0)//greater than 22.5, must be between 22.5-45
							{
								
								if(Double.compare(degree, 33.7500) < 0)//less than 33.75, must be southeasteast
								{
									
									direction = "southEastEast";
									//System.out.println("see "+ "degree - "+degree+" should be between 33.75-11.25");
									

									
								}
								else//greater than or equal to 33.75, must be south east
								{
									
									direction = "southEast";
									//System.out.println("se "+ "degree - "+degree+" should be between 56.25-33.75");
								
									
								}
								
							}
							else if(Double.compare(degree, 22.5000) < 0)//less than must be between 0-22.5
							{
								
								if(Double.compare(degree, 11.2500) > 0)//greater than 11.25 must be southeasteast
								{
									
									direction = "southEastEast";
									//System.out.println("see "+ "degree - "+degree+" should be between 33.75-11.25");
									
									
								}
								else//less than or equal to 11.25 must be east
								{
									
									direction = "east";
									//System.out.println("e "+ "degree - "+degree+" should be between 11.25~(-11.25)");
									
									
								}
								
							}
							else//exactly 22.5 must be southeasteast
							{
								
								direction = "southEastEast";
								//System.out.println("see equal "+ "degree - "+degree+" should be between 33.75-11.25");
								
								
							}
					
				}
				else//exactaly 45,0
				{
					
					direction = "southEast";
					//System.out.println("se equal");
				}
				
				
			}
			else//exactaly 90.0
			{
				
				direction = "south";
				//System.out.println("s equal");
			}
	
		}
		else if(Double.compare(degree, 0.0000) < 0)//negative number, between -1-(-179.9)
		{//System.out.println("negative");
		
			if(Double.compare(degree, -90.000) > 0)//greater than -90.0, between -1-(-90)
			{//System.out.println("greater than -90.0");
				
				if(Double.compare(degree, -45.000) > 0)//greater than -45.0, between -1-(-45)
				{//System.out.println("greater than -45.0");

				//e, nee, ne

							if(Double.compare(degree, -22.5000) < 0)//less than -22.5, must be between -22.5-(-45)
							{
								
								if(Double.compare(degree, -33.7500) > 0)//greater than -33.75, must be northeasteast
								{
									
									direction = "northEastEast";
									//System.out.println("nee "+ "degree - "+degree+" should be between -33.75-(-11.25-");
									

									
								}
								else//less than or equal to -33.75, must be northeast
								{
									
									direction = "northEast";
									//System.out.println("ne "+ "degree - "+degree+" should be between -56.25-(-33.75)");
								
									
								}
								
							}
							else if(Double.compare(degree, -22.5000) > 0)//greater than -22.5 must be between -1-(-22.5)
							{
								
								if(Double.compare(degree, -11.2500) < 0)//less than 11.25 must be northeasteast
								{
									
									direction = "northEastEast";
									//System.out.println("nee "+ "degree - "+degree+" should be between -33.75(-11.25)");
									
									
								}
								else//greater than or equal to -11.25 must be east
								{
									
									direction = "east";
									//System.out.println("e "+ "degree - "+degree+" should be between 11.25~(-11.25)");
									
									
								}
								
							}
							else//exactly 22.5 must be nee
							{
								
								direction = "northEastEast";
								//System.out.println("nee equal "+ "degree - "+degree+" should be between -33.75-(-11.25)");
								
								
							}
					
				}
				else if(Double.compare(degree, -45.000) < 0)//less than -45.0, between -90-(-45)
				{//System.out.println("less than -45.0");
					
				//ne, nne, n
				
							if(Double.compare(degree, -67.5000) < 0)//less than -67.5, must be between -67.5-(-90)
							{
								
								if(Double.compare(degree, -78.7500) > 0)//greater than -78.75, must be northnortheast
								{
									
									direction = "northNorthEast";
									//System.out.println("nne "+ "degree - "+degree+" should be between -78.75-(-56.25)");
									

									
								}
								else//greater than or equal to -78.75, must be north
								{
									
									direction = "north";
									//System.out.println("n "+ "degree - "+degree+" should be between -101.25-(-78.75)");
								
									
								}
								
							}
							else if(Double.compare(degree, -67.5000) > 0)//greater than -67.5 must be between -67.5-(-45)
							{
								
								if(Double.compare(degree, -56.2500) < 0)//less than -56.25 must be northnortheast
								{
									
									direction = "northNorthEast";
									//System.out.println("nne "+ "degree - "+degree+" should be between -78.75-(-56.25)");
									
									
								}
								else//grater than or equal to -56.25 must be northeast
								{
									
									direction = "northEast";
									//System.out.println("ne "+ "degree - "+degree+" should be between -56.25-(-33.75)");
									
									
								}
								
							}
							else//exactly -67.5 must be nne
							{
								
								direction = "northNorthEast";
								//System.out.println("nne equal "+ "degree - "+degree+" should be between -78.75-(-56.25)");
								
								
							}
					
				}
				
			}
			else if(Double.compare(degree, -90.000) < 0)//less than -90.0, between -90-(-179)
			{	//System.out.println("less than -90.0");
				
				if(Double.compare(degree, -135.000) > 0)//greater than -135.0, between -90-(-135)
				{//System.out.println("greater than -135.0");
					
				//n, nnw, nw
											
							if(Double.compare(degree, -112.500) < 0)//less than -112.5000, must be between -112.5-(-135)
							{
								
								if(Double.compare(degree, -123.7500) > 0)//greater than -123.75, must be northnorthwest
								{
									
									direction = "northNorthWest";
									//System.out.println("nnw "+ "degree - "+degree+" should be between -123.75-(-101.25)");
									

									
								}
								else//less than or equal to -123.75, must be northwest
								{
									
									direction = "northWest";
									//System.out.println("nw "+ "degree - "+degree+" should be between -123.75-(-146.25)");
								
									
								}
								
							}
							else if(Double.compare(degree, -112.500) > 0)//greater than -112.5 must be between -90-(-112.5)
							{
								
								if(Double.compare(degree, -101.2500) < 0)//less than 101.25 must be nnwest
								{
									
									direction = "northNorthWest";
									//System.out.println("nnw "+ "degree - "+degree+" should be between -123.75-(-101.25)");
									
									
								}
								else//greater than or equal to -101.25 must be north
								{
									
									direction = "north";
									//System.out.println("n "+ "degree - "+degree+" should be between -101.25-(-78.75)");
									
									
								}
								
							}
							else//exactly -112.5 must be nnw
							{
								
								direction = "northNorthWest";
								//System.out.println("nnw equal "+ "degree - "+degree+" should be between -123.75-(-101.25)");
								
								
							}
					
				}
				else if(Double.compare(degree, -135.000) < 0)//less than -135.0, between -179-(-135)
				{//System.out.println("less than -135.0");
				
				//w, nww, nw
							
							if(Double.compare(degree, -157.5000) < 0)//less than -157.5, must be between -157.5-(-180)
							{
								
								if(Double.compare(degree, -168.7500) > 0)//greater than -168.75, must be northwestwest
								{
									
									direction = "northWestWest";
									//System.out.println("nww "+ "degree - "+degree+" should be between -146.25-(-168.75)");
									

									
								}
								else//less than or equal to -168.75, must be west
								{
									
									direction = "west";
									//System.out.println("w "+ "degree - "+degree+" should be between -168.75~168.75");
								
									
								}
								
							}
							else if(Double.compare(degree, -157.5000) > 0)//greater than -157.5 must be between -135.0-(-157.5)
							{
								
								if(Double.compare(degree,-146.2500) < 0)//less than -146.25 must be northwestwest
								{
									
									direction = "northWestWest";
									//System.out.println("nwww "+ "degree - "+degree+" should be between -146.25-(-168.75)");
									
									
								}
								else//less than or equal to 146.25 must be south west 
								{
									
									direction = "northWest";
									//System.out.println("nw "+ "degree - "+degree+" should be between -123.75-(-146.25)");
									
									
								}
								
							}
							else//exactly -157.5 must be nww
							{
								
								direction = "northWestWest";
								//System.out.println("nww equal "+ "degree - "+degree+" should be between -146.25-(-168.75");
								
								
							}
					
				}
				
			}
			else//exactaly -90.0
			{
				
				direction = "north";
				//System.out.println("n equal");
			}
			
			
		}
		else//Exactly 0
		{
			
			direction = "east";
			//System.out.println("e equal");
		}
		
		
		
		return direction;
	}
	
}
