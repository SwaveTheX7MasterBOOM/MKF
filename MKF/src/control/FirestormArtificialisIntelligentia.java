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
 * fire propagation algorithm,,
 * 
 * @author Thomas FUCKING Capach
 */
public class FirestormArtificialisIntelligentia extends Thread
{
	//wind direction
	private int wDirection = 0;
	
	//
	private int [][] fireGrid = null;
	
	//
	private int [][] tileMap = null;
	
	//
	private Map<Coordinate, Set<Setpiece>> theObjects = null;
	
	//
	private Coordinate start = null;
	
	//
	private Random r = new Random();
	

	/**
	 * CONSTRUCTOR
	 * 
	 * @param s
	 * @param tileMap
	 * @param theObjects
	 */
	public FirestormArtificialisIntelligentia(int s, int[][] tileMap, Map<Coordinate, Set<Setpiece>> theObjects)
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
	 * 
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
						printGrid();

						try {
							Thread.sleep(600);
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
	 * */
	/**
	 * 
	 * 
	 * @param x
	 * @param y
	 */
	private void burnBabyBurn(int x, int y)
	{
		
		if(wDirection == 1)
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
					fireGrid[y - 1][x - 1] = fireGrid[y - 1][x - 1] - 20;
					
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
					fireGrid[y - 1][x + 1] = fireGrid[y - 1][x + 1] - 20;
					
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
					fireGrid[y + 1][x - 1] = fireGrid[y + 1][x - 1] - 20;

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
					fireGrid[y + 1][x + 1] = fireGrid[y + 1][x + 1] - 20;
				
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
	 * 1   2
	 *   0 
	 * 3   4
	 * 
	 * 
	 */
	/**
	 * 
	 */
	private void youDontNeedAWeatherMan()
	{
		
		
		System.out.println("Starting point  = " + start.getX() + " " + start.getY());
		
		fireGrid[start.getY()][start.getX()] = 0;
		
		if(start.getX() < (fireGrid.length / 2))
		{
			
			if(start.getY() < (fireGrid.length / 2))
			{
				
				wDirection = 4;
				
			}
			else
			{
				
				wDirection = 2;
				
			}
			
		}
		else
		{
			
			if(start.getY() < (fireGrid.length / 2))
			{
				
				wDirection = 3;
				
			}
			else
			{
				
				wDirection = 1;
				
			}
			
		}
		
		
		System.out.println("The wind blows to the " + wDirection);
		
		
	}



	/**
	 * 
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
	 * 
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
	 * 
	 * @author Thomas "I'm fucking drunk right now" Capach
	 *
	 */
	public class FireTile implements ActionListener
	{
		
		private Timer t;
		private Coordinate co;
		
		//
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











