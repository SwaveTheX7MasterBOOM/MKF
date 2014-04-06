package control;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import logicalGameObjects.Coordinate;
import logicalGameObjects.Setpiece;



/**
 * 
 * The map maker class creates random paths in a Cartesian coordinate system and adds objects to it.
 * 
 * @author Thomas "Cobra Commander" Capach
 *
 */
public class MapMaker
{


	/*Algorithm
	 * 
	 * Randomly pick unoccupied adjacent tile
	 * 1 1 R
	 * 1 0 1
	 * 1 1 1
	 * 
	 * shift center to the new tile
	 * 1 1 1
	 * 1 0 1
	 * 0 1 1
	 * 
	 * 	-REPEAT-
	 * 
	 * 
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
	 * Cardinal direction - int conversion (North = 1 and so on)
	 * 8 1 2
	 * 7   3
	 * 6 5 4
	 * 
	 *  
	 * MAP Keys
	 * 
	 * 0 = path
	 * 1 = empty space
	 * 2-14 reserved for path tiles
	 */



	int count = 0;

	private boolean a1 = true;
	private boolean a2 = true;
	private boolean a3 = true;
	private boolean a4 = true;
	private boolean a5 = true;
	private boolean a6 = true;
	private boolean a7 = true;
	private boolean a8 = true;
	private boolean allFalse = false;
	private boolean clumping = false;
	private boolean go = true;

	private int ax;
	private int ay;
	private int n = 0;
	private int size = 0;
	private int moves = 0;
	private int northSouthThreshold = 1;
	private int southNorthThreshold = 1;
	private int eastWestThreshold = 1;
	private int westEastThreshold = 1;

	private double usageThreshold = 17.00;

	private int [][] world;

	private Stack<Coordinate> path = new Stack<Coordinate>();

	private List<Integer> numberTrail = new ArrayList<Integer>();

	private Random r = new Random();

	private Map<Coordinate, Set<Setpiece>> worldObjects = new HashMap<Coordinate, Set<Setpiece>>();

	
	/**
	 * Basic map maker constructor.  Takes an int as a parameter that determines the size of the Cartesian plane.
	 * Default start position of (0,0) 
	 * Default plane usage 17% 
	 * Clumping avoidance is in effect	
	 * 
	 * @param size - int
	 */
	public MapMaker(int size)
	{

		this.size = size;

		world = new int [size][size];

	}	

	
	/**
	 * Constructor.  Takes an int as a parameter that determines the size of the Cartesian plane as well as 
	 * the x and y axis coordinates of where the algorithm will start.
	 * Default plane usage 17% 
	 * Clumping avoidance is in effect	
	 * 
	 * @param size - int
	 * @param x - int
	 * @param y - int
	 */
	public MapMaker(int size, int x, int y)
	{

		this.size = size;

		world = new int [size][size];

		ax = x;
		ay = y;

	}	

	
	/**
	 * Constructor.  Takes an int as a parameter that determines the size of the Cartesian plane, 
	 * the x and y axis coordinates of where the algorithm will start and the desired percentage
	 *  of the map to be used. 
	 * Clumping avoidance is in effect	
	 * 
	 * @param size - int
	 * @param x - int
	 * @param y - int
	 * @param toBeUsed - double
	 */
	public MapMaker(int size, int x, int y, double toBeUsed)
	{

		this.size = size;

		world = new int [size][size];

		usageThreshold = toBeUsed;

		ax = x;
		ay = y;

	}	

	
	/**
	 * Constructor.  Takes an int as a parameter that determines the size of the Cartesian plane, 
	 * the x and y axis coordinates of where the algorithm will start, the desired percentage
	 *  of the map to be used and whether clumping avoidance will be allowed. 	
	 * 
	 * @param size - int
	 * @param x - int
	 * @param y - int
	 * @param percentUsed - double
	 * @param avoidClumping - boolean
	 */
	public MapMaker(int size, int x, int y, double percentUsed, boolean avoidClumping)
	{

		this.size = size;

		world = new int [size][size];

		usageThreshold = percentUsed;

		clumping = avoidClumping;

		ax = x;
		ay = y;

	}

	
	/**
	 * 
	 * populate the level with randomly created paths
	 * 
	 */
	public void MakeMeALevel()
	{
		//create new multidimensional array
		newWorld();

		//set starting point equal to zero
		world[ay][ax] = 0;

		//add starting coordinate to
		path.push(new Coordinate(ax,ay));

		//add 0 to list to indicate the start
		numberTrail.add(n);



		Thread t = new Thread()
		{

			public void run()
			{

				while(go == true)
				{
					//move to a random, unoccupied tile
					move();	

					//set new position to indicate path tile
					world[ay][ax] = 0;
					
					//check the percentage of the map occupied by path tiles
					if(usage() - usageThreshold >= 0)
					{

						go = false;

					}

					//System.out.println(moves++);
					//System.out.println("used "+usage());
					//seeTheWorld();

					/*try
					{
						sleep(1000);
					} 
					catch (InterruptedException e)
					{


					}*/

				}

					try
					{
						//fill in adjacent tiles
						identifyPathBorderSquares();
						
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
	
					}
					
			}
			
		};

			t.start();

	}	

	/**
	 * 
	 * checks possible moves and randomly change the current position on the level
	 * 
	 */
	public void move()
	{

		//update possible moves
		checkNeighbors();

		//randomly choose a possible movement direction
		n = r.nextInt(8)+1;

		//add to the list of random numbers
		numberTrail.add(n);


		switch(n)//move to selected tile
		{

			case 1:
	
				if(a1 == true)
				{
	
					ax = ax - 1;
					ay = ay + 1;
	
				}
	
				break;
	
			case 2:
	
				if(a2 == true)
				{
	
	
					ay = ay + 1;
	
				}
	
				break;
	
			case 3:
	
				if(a3 == true)
				{
	
	
					ax = ax + 1;
					ay = ay + 1;
	
	
				}
	
				break;
	
			case 4:
	
				if(a4 == true)
				{
	
					ax = ax - 1;
	
				}
	
				break;
	
			case 5:
	
				if(a5 == true)
				{
	
					ax = ax + 1;
	
				}
	
				break;
	
	
			case 6:
	
				if(a6 == true)
				{
	
					ax = ax - 1;
					ay = ay - 1;
	
				}
	
				break;
	
	
			case 7:
	
				if(a7 == true)
				{
	
					ay = ay - 1;
	
				}
	
				break;
	
			case 8:
	
				if(a8 == true)
				{
	
					ax = ax + 1;
					ay = ay - 1;
	
				}
	
				break;

		}

		//add coordinate to to the path list
		path.push(new Coordinate(ax,ay));


	}

	
	/**
	 * check the possible moves that can be made from the current position and if none are
	 * available move to previous positions until a possible move can be made
	 * 
	 */
	public void checkNeighbors()
	{

		do
		{
			//set everything true
			a1 = true;
			a2 = true;
			a3 = true;
			a4 = true;
			a5 = true;
			a6 = true;
			a7 = true;
			a8 = true;

			allFalse = false;

			//determine which tiles are unoccupied
			try
			{

				//1
				if(ax - 1 > world.length-1 || ax - 1 < 0 || ay + 1 > world.length-1 || ay + 1 < 0)
				{

					a1 = false;

				}
				else if(world[ay + 1][ax - 1] == 0)
				{

					a1 = false;

				}

				//2
				if(ay + 1 > world.length-1 || ay + 1 < 0)
				{

					a2 = false;

				}
				else if(world[ay + 1][ax] == 0)
				{

					a2 = false;

				}

				//3
				if(ax + 1 > world.length-1 || ax + 1 < 0 || ay + 1 > world.length-1 || ay + 1 < 0)
				{

					a3 = false;

				}
				else if(world[ay + 1][ax + 1] == 0)
				{

					a3 = false;

				}

				//4
				if(ax - 1 > world.length-1 || ax - 1 < 0)
				{

					a4 = false;

				}
				else if(world[ay][ax - 1] == 0)
				{

					a4 = false;

				}

				//5
				if(ax + 1 > world.length-1 || ax + 1 < 0)
				{

					a5 = false;

				}
				else if(world[ay][ax + 1] == 0)
				{

					a5 = false;

				}

				//6
				if(ax - 1 > world.length-1 || ax - 1 < 0 || ay - 1 > world.length-1 || ay - 1 < 0)
				{

					a6 = false;

				}
				else if(world[ay - 1][ax - 1] == 0)
				{

					a6 = false;

				}


				//7
				if(ay - 1 > world.length-1 || ay - 1 < 0)
				{

					a7 = false;

				}
				else if(world[ay - 1][ax] == 0)
				{

					a7 = false;

				}

				//8
				if(ax + 1 > world.length-1 || ax + 1 < 0 || ay - 1 > world.length-1 || ay - 1 < 0)
				{

					a8 = false;

				}
				else if(world[ay - 1][ax + 1] == 0)
				{

					a8 = false;

				}


			}
			catch(ArrayIndexOutOfBoundsException e)
			{

			}

			//call anit-cluster method if specified
			if(clumping == false)
			{

				toUnchartedTerriotory(ax, ay);
			}


			//if no adjacent tile is available
			if(a1 == false && a2 == false && a3 == false && a4 == false && a5 == false && a6 == false && a7 == false && a8 == false)
			{

				allFalse = true;

				//remove current tile from path stack
				path.pop();

				//update variables
				Coordinate temp = path.peek();

				ax = temp.getX();
				ay = temp.getY();

			}

		}while(allFalse == true);

	}

	
	/**
	 * clear the map
	 * 
	 */
	public void newWorld()
	{
		//set everything equal to zero
		for(int y = 0; y < world.length; ++y)
		{

			for(int x = 0; x < world.length; ++x)
			{

				world[y][x] = 1;

			}

		}

	}

	
	/**
	 * print out the map on the console
	 * 
	 */
	public void seeTheWorld()
	{

		for(int y = 0; y < world.length; ++y)
		{

			for(int x = 0; x < world.length; ++x)
			{

				System.out.print(world[y][x] + " ");

			}

			System.out.println();

		}

	}

	
	/**
	 * Get the generated level after one has been created
	 * 
	 * @return int[][]
	 */
	public int[][] getTheWorld()
	{

		return world;

	}

	
	/**
	 * see the possible moves
	 * 
	 */
	public void seePossibleMoves()
	{

		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
		System.out.println(a4);
		System.out.println(a5);
		System.out.println(a6);
		System.out.println(a7);
		System.out.println(a8);

	}

	
	/**
	 * directs the algorithm to move toward less populated portions of the map helping to prevent clumping and wide open space on the map
	 * 
	 * @param x - int
	 * @param y - int
	 */
	public void toUnchartedTerriotory(int x, int y)
	{

		int north = 0;
		int south = 0;
		int east = 0;
		int west = 0;

		boolean up = true;
		boolean down = true;
		boolean left = true;
		boolean right = true;

		//north
		for(int ny = 0; ny <= world.length/2 - 1; ny++)
		{

			for(int nx = 0; nx < world.length; nx++)
			{

				if(world[ny][nx] == 0)
				{
					north++;
				}

			}

		}

		//south
		for(int ny = world.length/2; ny < world.length; ny++)
		{

			for(int nx = 0; nx < world.length; nx++)
			{

				if(world[ny][nx] == 0)
				{
					south++;
				}

			}

		}

		//west
		for(int ny = 0; ny < world.length; ny++)
		{

			for(int nx = 0; nx < world.length/2-1; nx++)
			{

				if(world[ny][nx] == 0)
				{
					west++;
				}

			}

		}

		//east
		for(int ny = 0; ny < world.length; ny++)
		{

			for(int nx = world.length/2; nx < world.length; nx++)
			{

				if(world[ny][nx] == 0)
				{
					east++;
				}

			}

		}


		//test north vs south
		if(north - south > northSouthThreshold)
		{

			up = false;

		}
		else if(south - north > southNorthThreshold)
		{

			down = false;

		}

		//test easy vs west
		if(east - west > eastWestThreshold)
		{

			right = false;

		}
		else if(west - east > westEastThreshold)
		{

			left = false;

		}


		if(up == false && left == false)
		{

			a4 = false;
			a6 = false;
			a7 = false;
			//System.out.println("NE OFF");

		}
		else if(up == false && right == false)
		{

			a5 = false;
			a8 = false;
			a7 = false;
			//System.out.println("NW off");

		}
		else if(down == false && left == false)
		{

			a4 = false;
			a1 = false;
			a2 = false;
			//System.out.println("SW off");

		}
		else if(down == false && right == false)
		{

			a2 = false;
			a3 = false;
			a5 = false;
			//System.out.println("SE off");

		}
		else if(up == false)
		{

			a6 = false;
			a7 = false;
			a8 = false;
			//System.out.println("N off");

		}
		else if(down == false)
		{

			a1 = false;
			a2 = false;
			a3 = false;
			//System.out.println("S off");

		}
		else if(right == false)
		{

			a3 = false;
			a5 = false;
			a8 = false;
			//System.out.println("E off");

		}
		else if(left == false)
		{

			a1 = false;
			a4 = false;
			a6 = false;
			//System.out.println("W off");

		}


	}

	
	/**
	 * determine the percent of the map used
	 * 
	 * @return - double
	 */
	public double usage()
	{

		int area = size * size;
		int count = 0;
		double percentUsed = 0.00;


		for(int y = 0; y < world.length; ++y)
		{

			for(int x = 0; x < world.length; ++x)
			{

				if(world[y][x] == 0)
				{

					count++;

				}

			}

		}

		percentUsed = (count * 100) / area;


		return percentUsed;

	}


	/**
	 * This method detects the border tiles of the randomly generated path and sets them to their proper title code
	 * 
	 */
	public void identifyPathBorderSquares()
	{

		/*
		 * For future reference:  Should have made a iterator to traverse the array with methods to grab the values of the blocks around it such as a getLeft()
		 * method. Would have simplified the if statements to a more readable form and could have depending on the construction of the method made the need for multiple
		 * FOR loop unnecessary(return null if the getLeft() was out of the array bounds) not that technically the different for loops were in this version necessary 
		 * anyways, more for the simplicity of the ever increasing IF statement logical structure.  Although, not sure of the over all efficiency of the above described
		 *  VS this implementation.  The major concern of the aforementioned is just for easy reading.
		 *  
		 *  Follow up:  implemented through the Magellon class
		 *  
		 */


		for(int y = 0; y < world.length-1; ++y)
		{

			for(int x = 0; x < world.length-1; ++x)
			{

				if(world[y][x] ==1)
				{

					boolean tl = Magellon.findTopLeft(world, x, y);
					boolean t = Magellon.findTop(world, x, y);
					boolean tr = Magellon.findTopRight(world, x, y);
					boolean l = Magellon.findLeft(world, x, y);
					boolean r = Magellon.findRight(world, x, y);
					boolean bl = Magellon.findBotLeft(world, x, y);
					boolean b = Magellon.findBot(world, x, y);
					boolean br = Magellon.findBotRight(world, x, y);


					if((tl == false && t == false &&  l == false && bl == false) && (tr == true && r == true && b == true))//5//////
					{

						world[y][x] = 5;

						continue;

					}
					else if((tl == false && t == false &&  l == false && bl == false && b == false) && (tr == true && r == true && br == true))//5
					{

						world[y][x] = 5;

						continue;

					}
					else if((tl == false && l == false &&  bl == false) && (t == true && r == true && b == true))//5
					{

						world[y][x] = 5;

						continue;

					}
					else if((tl == false && l == false && bl == false && b == false) && (t == true && r == true && br == true))//5
					{

						world[y][x] = 5;

						continue;

					}

					////////////////////////////////////////////

					else if((tl == false && t == false &&  tr == false) && (r == true && l == true && b == true))//2
					{

						world[y][x] = 3;

						continue;

					}
					else if((l == false && tl == false &&  t == false && tr == false && r == false) && (br == true && b == true && bl == true))//2
					{

						world[y][x] = 3;

						continue;

					}
					else if((tl == false && t == false &&  tr == false && r == false) && (l == true && b == true && br == true))//2
					{

						world[y][x] = 3;


						continue;

					}
					else if((l == false && tl == false &&  t == false && tr == false) && (bl == true && b == true && r == true))//2
					{

						world[y][x] = 3;

						continue;


					}


					/////////////////////////////////////////////


					else if((tr == false && r == false &&  br == false) && (t == true && l == true && b == true))//7
					{

						world[y][x] = 6;

						continue;

					}
					else if((t == false && tr == false &&  r == false && br == false && b == false) && (tl == true && l == true && bl == true))//7
					{

						world[y][x] = 6;

						continue;

					}
					else if((tr == false && r == false &&  br == false && b == false) && (t == true && l == true && bl == true))//7
					{

						world[y][x] = 6;

						continue;

					}
					else if((t == false && tr == false &&  r == false && br == false) && (tl == true && l == true && b == true))//7
					{

						world[y][x] = 6;

						continue;

					}


					//////////////////////////////////////////


					else if((bl == false && b == false &&  br == false) && (r == true && l == true && t == true))//11
					{

						world[y][x] = 8;

						continue;

					}

					else if((l == false && bl == false &&  b == false && br == false && r == false) && (tr == true && t == true && tl == true))//11
					{

						world[y][x] = 8;

						continue;

					}
					else if((l == false && bl == false &&  b == false && br == false) && (tl == true && t == true && r == true))//11
					{

						world[y][x] = 8;

						continue;

					}
					else if((bl == false && b == false &&  br == false && r == false) && (l == true && t == true && tr == true))//11
					{

						world[y][x] = 8;

						continue;

					}


					///////////////////////////////////////////


					else if((tl == false && t == false &&  tr == false && l == false ) && (r == true && b == true))//1
					{

						world[y][x] = 2;

						continue;

					}
					else if((tl == false && t == false &&  tr == false && l == false && bl == false && b == false) && (r == true && br == true))//1
					{

						world[y][x] = 2;

						continue;

					}


					/////////////////////////////////////////


					else if((tr == false && r == false &&  br == false && b == false && bl == false && b == false) && (l == true && t == true))//12
					{

						world[y][x] = 9;

						continue;

					}
					else if((t == false && tr == false &&  r == false && br == false && b == false && bl == false) && (tl == true && l == true))//12
					{

						world[y][x] = 9;

						continue;

					}


					/////////////////////////////////////////


					else if((tl == false && t == false &&  tr == false && r == false) && (l == true && b == true))//3
					{

						world[y][x] = 4;

						continue;

					}
					else if((tl == false && t == false && tr == false &&  r == false && br == false && b == false) && (bl == true && l == true))//3
					{

						world[y][x] = 4;

						continue;

					}


					////////////////////////////////////////


					else if((tl == false && l == false &&  bl == false && b == false && br == false) && (t == true && r == true))//10
					{

						world[y][x] = 7;

						continue;

					}
					else if((t == false && tl == false &&  l == false && bl == false && b == false && br == false) && (tr == true && r == true))//10
					{

						world[y][x] = 7;

						continue;

					}


					///////////////////////////////////////


					else if((l == false && bl == false && b == false) && (tl == true && t == true && r == true && br == true))//13
					{

						world[y][x] = 12;

						continue;

					}


					///////////////////////////////////////


					else if((l == false && tl == false && t == false) && (tr == true && r == true && b == true && bl == true))//8
					{

						world[y][x] = 10;

						continue;

					}


					///////////////////////////////////////


					else if((t == false && tr == false && r == false) && (tl == true && l == true && b == true && br == true))//9
					{

						world[y][x] = 11;

						continue;

					}


					///////////////////////////////////////


					else if((b == false && br == false && r == false) && (bl == true && l == true && t == true && tr == true))//14
					{

						world[y][x] = 13;

						continue;

					}


					///////////////////////////////////////


					else if((l == true && r == true) || (t == true && b == true))//gap
					{

						world[y][x] = 0;

						continue;

					}


					//////////////////////////////////


					else if((tr == false && r == false &&  br == false && b == false && bl == false && l == false) && (tl == true && t == true))//
					{

						world[y][x] = 9;

						continue;

					}
					else if((tl == false && l == false &&  bl == false && b == false && br == false && r == false) && (t == true && tr == true))//
					{

						world[y][x] = 7;

						continue;

					}
					else if((l == false && tl == false &&  t == false && tr == false && r == false && br == false) && (bl == true && b == true))//
					{

						world[y][x] = 4;

						continue;

					}
					else if((bl == false && l == false &&  tl == false && t == false && tr == false && r == false) && (b == true && br == true))//
					{

						world[y][x] = 2;

						continue;

					}


				}

			}

		}

		//seeTheWorld();
	}

	//fix the point(x,y) at which things are drawn
	/**
	 * Add set piece to the board, randomly
	 * 
	 * @param allObjects - Set<Setpiece>
	 */
	public void addSetpiece(Set<Setpiece> allObjects)
	{

		List<Setpiece> list = new ArrayList<Setpiece>(allObjects);

		Setpiece temp = null;


		for(int a = 0; a < allObjects.size();  a++)
		{
			
			temp = list.get(a);

			int x = r.nextInt(world.length*102);
			int y = r.nextInt(world.length*102);
			int length = temp.getHeight();
			int width = temp.getWidth();

			temp.setX(x);
			temp.setY(y);
			

			if(temp.getX() >= 0 && temp.getX() <= (world.length * 102 - width) && temp.getY() >= 0 && temp.getY() <= (world.length * 102 - length))
			{

				if(world[temp.getY()/102][temp.getX()/102] == 1 && world[y/102][x/102] == 1)// && world[y/102][x/102] == 1)
				{
					//System.out.println(a+"++++++++" + temp.getX()/102 + " / " + temp.getY()/102 + " / " + (world[temp.getY()/102][temp.getX()/102] == 1));
					
					if(WhenShitHits.setpiece2setpiece(temp, list, a) == false)
					{

						if(worldObjects.containsKey(new Coordinate(length/102,width/102)))
						{

							temp.setX(x);
							temp.setY(y);

							Set<Setpiece> temp2 = worldObjects.get(new Coordinate(length/102,width/102));

							temp2.add(temp);

							count++;

						}
						else
						{

							HashSet<Setpiece> hs = new HashSet<Setpiece>();

							temp.setX(x);
							temp.setY(y);

							hs.add(temp);

							count++;

							worldObjects.put(new Coordinate(temp.getX()/102, temp.getY()/102), hs);

							hs = null;

						}

					}
					else
					{
						//System.out.println("collision!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");	
					}

				}
				else 
				{
					//System.out.println("path");

					temp.setX(0);
					temp.setY(0);

					a--;

				}


			}
			else
			{
				//System.out.println("out of bounds");
				temp.setX(0);
				temp.setY(0);

				a--;

			}


		}

		list = null;
		temp = null;

	}


	/**
	 * Return the map structure containing the objects on each tile and their position
	 * 
	 * @return Set<Setpiece>>
	 */
	public Map<Coordinate, Set<Setpiece>> getTheObjects()
	{

		return worldObjects;

	}

}