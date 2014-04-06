package control;

/**
 * identifies adjacent map tiles as path tiles(code 0) or other
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
	
}
