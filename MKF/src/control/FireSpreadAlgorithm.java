package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.Timer;

import logicalGameObjects.Coordinate;
import logicalGameObjects.Setpiece;




/**
 * fire propagation algorithm
 * 
 * 1. Every map tile is split into 9, 3 X 3 tiles.
 * 
 *  |0| = |000|
 *  	  |000|
 *  	  |000|
 *  
 *  2. Then the cells are given a positive int value based on tile type and the objects contained within its' boundaries.
 *  
 *  [Tile type constant] - [(# of objects) X (object constant)]
 *  
 *  3. Random tile is chosen for the fire to start and set to 0. *int values <= 0 are considered on fire*
 *  
 *  4. all adjacent tiles begin losing health. Depending on wind direction some tiles will take extra damage.
 *  
 *  5. fire propagates as the adjacent tiles health hits 0 and start to damage tiles adjacent to them.
 * 
 * @author Thomas FUCKING Capach
 */
public class FireSpreadAlgorithm extends Thread
{
	//wind direction
	private int wDirection = 0;
	
	//Abstract view of the fire spread matrix
	private int [][] fireGrid = null;
	
	//the levels tile map
	private int [][] tileMap = null;
	
	//all objects from the level and their positions on the map
	private Map<Coordinate, Set<Setpiece>> theObjects = null;
	
	// where the fire starts
	private Coordinate start = null;
	
	//random number generator
	private Random r = new Random();
	

	/**
	 * CONSTRUCTOR
	 * 
	 * @param s
	 * @param tileMap
	 * @param theObjects
	 */
	public FireSpreadAlgorithm(int s, int[][] tileMap, Map<Coordinate, Set<Setpiece>> theObjects)
	{
			
		this.tileMap = tileMap;
		
		this.theObjects = theObjects;
		
		fireGrid = new int [s*3][s*3];
		
			populateGrid();
		
		start = new Coordinate(r.nextInt(s), r.nextInt(s));	
		
		youDontNeedAWeatherMan();
		
			this.start();
		
			
	}


	/**
	 *loops through array calling fire damage method
	 */
	public void run()
	{
		
		while(true)
		{
			
			for(int y = 0; y < fireGrid.length; y++)
			{
				
				for(int x = 0; x < fireGrid.length; x++)
				{
					
					if(fireGrid[y][x] <= 0 && fireGrid[y][x] > -100)
					{
					
						burnBabyBurn(x,y);
						
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			
					}
					else
					{
						
						continue;
						
					}
					
				}
				
			}
			printGrid();

		}
		
	}
	
	
	
	/*
	 * movement diagram
	 * 6 7 8
	 * 4 0 5
	 * 1 2 3
	 * 
	 * 
	 * movement values( to # from 0 position)
	 * 1  ax - 1  ay + 1
	 * 2  ay + 1
	 * 3  ax + 1  ay + 1
	 * 4  ax - 1
	 * 5  ax + 1
	 * 6  ax - 1  ay - 1
	 * 7  ay - 1
	 * 8  ax + 1  ay - 1
	 * 
	 * 
	 * wind blowing toward the upper right 
	 * 
	 * |-5 -15 -30|
	 * |-5   0 -15|
	 * |-5  -5  -5|
	 * 
	 * */
	/**
	 * This method handles the fire damage done to adjacent tiles
	 * 
	 * @param x - x axis position
	 * @param y - y axis position
	 */
	private void burnBabyBurn(int x, int y)
	{
		//depending on wind direction the tiles take differing amounts of damage
		//refer to the above diagrams for further clarification
		if(wDirection == 1)
		{
			//check to make sure the tile is not out of bounds
			if(y + 1 < fireGrid.length)
			{
				
				//2
				fireGrid[y + 1][x] = fireGrid[y + 1][x] - 5;
				
				if(x - 1 > 0)
				{
					
					//1
					fireGrid[y + 1][x - 1] = fireGrid[y + 1][x - 1] - 5;

				}
				
				if(x + 1 < fireGrid.length)
				{
					
					//3
					fireGrid[y + 1][x + 1] = fireGrid[y + 1][x + 1] - 5;
				
				}
				
			}
			
			if(x - 1 > 0)
			{
				
				//4
				fireGrid[y][x - 1] = fireGrid[y][x - 1] - 15;
			
			}
			
			if(x + 1 < fireGrid.length)
			{
				
				//5
				fireGrid[y][x + 1] = fireGrid[y][x + 1] - 5;
			
			}
			
			if(y - 1 > 0)
			{	

				//7
				fireGrid[y - 1][x] = fireGrid[y - 1][x] - 15;
			
				if(x - 1 > 0)
				{
					
					//6
					fireGrid[y - 1][x - 1] = fireGrid[y - 1][x - 1] - 30;
					
				}
				
				
				if(x + 1 < fireGrid.length)
				{
					
					//8
					fireGrid[y - 1][x + 1] = fireGrid[y - 1][x + 1] - 5;
					
				}
				
			}

		}
		else if(wDirection == 2)
		{
	
			if(y + 1 < fireGrid.length)
			{
				
				//2
				fireGrid[y + 1][x] = fireGrid[y + 1][x] - 5;
				
				if(x - 1 > 0)
				{
					
					//1
					fireGrid[y + 1][x - 1] = fireGrid[y + 1][x - 1] - 5;

				}
				
				if(x + 1 < fireGrid.length)
				{
					
					//3
					fireGrid[y + 1][x + 1] = fireGrid[y + 1][x + 1] - 5;
				
				}
				
			}
			
			if(x - 1 > 0)
			{
				
				//4
				fireGrid[y][x - 1] = fireGrid[y][x - 1] - 5;
			
			}
			
			if(x + 1 < fireGrid.length)
			{
				
				//5
				fireGrid[y][x + 1] = fireGrid[y][x + 1] - 15;
			
			}
			
			if(y - 1 > 0)
			{	

				//7
				fireGrid[y - 1][x] = fireGrid[y - 1][x] - 15;
			
				if(x - 1 > 0)
				{
					
					//6
					fireGrid[y - 1][x - 1] = fireGrid[y - 1][x - 1] - 5;
					
				}
				
				
				if(x + 1 < fireGrid.length)
				{
					
					//8
					fireGrid[y - 1][x + 1] = fireGrid[y - 1][x + 1] - 30;
					
				}
				
			}
			
			

			
		}
		else if(wDirection == 3)
		{
		
			if(y + 1 < fireGrid.length)
			{
				
				//2
				fireGrid[y + 1][x] = fireGrid[y + 1][x] - 15;
				
				if(x - 1 > 0)
				{
					
					//1
					fireGrid[y + 1][x - 1] = fireGrid[y + 1][x - 1] - 30;

				}
				
				if(x + 1 < fireGrid.length)
				{
					
					//3
					fireGrid[y + 1][x + 1] = fireGrid[y + 1][x + 1] - 5;
				
				}
				
			}
			
			if(x - 1 > 0)
			{
				
				//4
				fireGrid[y][x - 1] = fireGrid[y][x - 1] - 15;
			
			}
			
			if(x + 1 < fireGrid.length)
			{
				
				//5
				fireGrid[y][x + 1] = fireGrid[y][x + 1] - 5;
			
			}
			
			if(y - 1 > 0)
			{	

				//7
				fireGrid[y - 1][x] = fireGrid[y - 1][x] - 5;
			
				if(x - 1 > 0)
				{
					
					//6
					fireGrid[y - 1][x - 1] = fireGrid[y - 1][x - 1] - 5;
					
				}
				
				
				if(x + 1 < fireGrid.length)
				{
					
					//8
					fireGrid[y - 1][x + 1] = fireGrid[y - 1][x + 1] - 5;
					
				}
				
			}
			
			
		}
		else if(wDirection == 4)
		{
			
			if(y + 1 < fireGrid.length)
			{
				
				//2
				fireGrid[y + 1][x] = fireGrid[y + 1][x] - 15;
				
				if(x - 1 > 0)
				{
					
					//1
					fireGrid[y + 1][x - 1] = fireGrid[y + 1][x - 1] - 5;

				}
				
				if(x + 1 < fireGrid.length)
				{
					
					//3
					fireGrid[y + 1][x + 1] = fireGrid[y + 1][x + 1] - 30;
				
				}
				
			}
			
			if(x - 1 > 0)
			{
				
				//4
				fireGrid[y][x - 1] = fireGrid[y][x - 1] - 5;
			
			}
			
			if(x + 1 < fireGrid.length)
			{
				
				//5
				fireGrid[y][x + 1] = fireGrid[y][x + 1] - 15;
			
			}
			
			if(y - 1 > 0)
			{	

				//7
				fireGrid[y - 1][x] = fireGrid[y - 1][x] - 5;
			
				if(x - 1 > 0)
				{
					
					//6
					fireGrid[y - 1][x - 1] = fireGrid[y - 1][x - 1] - 5;
					
				}
				
				
				if(x + 1 < fireGrid.length)
				{
					
					//8
					fireGrid[y - 1][x + 1] = fireGrid[y - 1][x + 1] - 5;
					
				}
				
			}

			
		}
		
	}
	
	
	
	/*diagram
	 * 1 2
	 * 3 4
	 * 
	 *  EX) if the fire starts in quadrant 1 then the wind will be set to try to get the fire to spread more toward quadrant 4
	 */
	/**
	 * You don't need a weather man to know the way the wind blows. You just need this class.(Shameless Bob Dylan reference)
	 * 
	 * Based on the fires starting point the wind direction is set to cause the fire to spread out into the level.
	 */
	private void youDontNeedAWeatherMan()
	{
		
		System.out.println("Starting point  = " + start.getX() + " " + start.getY());
		
		fireGrid[start.getY()][start.getX()] = 0;
		
			//fire started on the left half of the board
			if(start.getX() < (fireGrid.length / 2))
			{
				//fire started on the top half of the board
				if(start.getY() < (fireGrid.length / 2))
				{
					
					wDirection = 4;
					
				}
				else//fire started on the bottom half of the board
				{
					
					wDirection = 2;
					
				}
				
			}
			else//fire started on the right half of the board
			{
				//fire started on the top half of the board
				if(start.getY() < (fireGrid.length / 2))
				{
					
					wDirection = 3;
					
				}
				else//fire started on the bottom half of the board
				{
					
					wDirection = 1;
					
				}
				
			}
		
		
		System.out.println("The wind blows to the " + wDirection);
		
	}



	/**
	 * Create the fire spread grid based on the levels tile map
	 */
	public void populateGrid()
	{
		//
		int tic = 0;
		int tock = 0;
		
		for(int y = 0; y < tileMap.length; y++)
		{
			
			for(int x = 0; x < tileMap.length; x++)
			{
				//System.out.println(" at " + x + "," + y );
				int temp = tileMap[y][x];
				
				//flammable object constant
				int fOC = 5;
				
				//number of flammable objects
				int nFO = 0;
				
				//tile constant
				int tC = 0;
				
				
				//get # of flammable objects
				List<Coordinate> al = new ArrayList<Coordinate> (theObjects.keySet());

				
				//
				for(Coordinate c:al)
				{
					
					if(c.equals(new Coordinate(x, y)))
					{
					
						List<Setpiece> temper  = new ArrayList<Setpiece>(theObjects.get(c));
					
							for(Setpiece s:temper)
							{
								
								if(s.isFlammable())
								{

									nFO++;
									
								}
								
							}
						
						break;
						
					}
				
				}
				

				//set health based on tile
					if(temp == 0)
					{
						
						tC = 150;
						
					}
					else if(temp == 1)
					{
						
						tC = 50;
						
					}
					else if(temp >=2 && temp <= 16)
					{
						
						tC = 100;
						
					}
					
					
					tic = x * 3;
					tock = y * 3;
					
						for(int a = 0; a < 3; a++)
						{
							
							for(int b = 0; b < 3; b++)
							{
	
								//System.out.print("grid at " + tic + "," + tock);
								fireGrid[tock][tic] = tC - (fOC * nFO);
								//System.out.println(" = " + fireGrid[tock][tic]);
								
									tic++;
								
							}
							
								tic = x * 3;					
								tock++;
							
						}		
					
			}
			
		}
		
	}
	
	/**
	 * print out the fire grid in console
	 */
	public void printGrid()
	{
		System.out.println("!!!!!!!!!!!!");
		for(int y = 0; y < fireGrid.length; y++)
		{
			System.out.println("+++++++++++");
			for(int x = 0; x < fireGrid.length; x++)
			{
				
				System.out.print(fireGrid[y][x] + " ");
				
			}
			
		}
		
			System.out.println();
		
	}

	/**
	 * NOT USED ATM
	 * 
	 *  the idea with this inner class was that the fire would have to wait a certain amount of time before the tile that was on fire would start effecting adjacent tiles
	 * 
	 * 
	 * @author Thomas "I'm fucking drunk right now" Capach
	 *
	 */
	public class FireTile implements ActionListener
	{
		
		private Timer t;
		
		private Coordinate co;
		

		public FireTile(Coordinate c)
		{
			//need object modifiers
			t = new Timer(r.nextInt(1000) + 200, this);
			t.start();
			
			co = c;
			
		}

		public void actionPerformed(ActionEvent arg0)
		{

				t = null;
				co = null;
			
		}
		
	}
	
}











