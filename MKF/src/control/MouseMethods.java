package control;


import java.awt.Cursor;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import logicalGameObjects.Coordinate;

/**
 * For all your mouse related needs.
 * 
 * This class provides information about the mouse as well as runs the custom cursor animation
 * 
 * 
 * @author Cobrah "Fucking" ComMANder
 *
 */
public class MouseMethods extends MouseInputAdapter implements Runnable
{
	
	//the only instantiated object
	private static MouseMethods instance = new MouseMethods();
	
	//Animations
	private Image[] north = {new ImageIcon("src/images/CC12.png").getImage(), new ImageIcon("src/images/CC12_2.png").getImage(), new ImageIcon("src/images/CC12_3.png").getImage()};
	private Image[] northNorthEast = {new ImageIcon("src/images/CC11.png").getImage(), new ImageIcon("src/images/CC11_2.png").getImage(), new ImageIcon("src/images/CC11_3.png").getImage()};
	private Image[]northEast = {new ImageIcon("src/images/CC10.png").getImage(), new ImageIcon("src/images/CC10_2.png").getImage(), new ImageIcon("src/images/CC10_3.png").getImage()};
	private Image[] northEastEast = {new ImageIcon("src/images/CC9.png").getImage(), new ImageIcon("src/images/CC9_2.png").getImage(), new ImageIcon("src/images/CC9_3.png").getImage()};
	private Image[] east = {new ImageIcon("src/images/CC8.png").getImage(), new ImageIcon("src/images/CC8_2.png").getImage(), new ImageIcon("src/images/CC8_3.png").getImage()};
	private Image[] southEastEast = {new ImageIcon("src/images/CC7.png").getImage(), new ImageIcon("src/images/CC7_2.png").getImage(), new ImageIcon("src/images/CC7_3.png").getImage()};
	private Image[] southEast = {new ImageIcon("src/images/CC6.png").getImage(), new ImageIcon("src/images/CC6_2.png").getImage(), new ImageIcon("src/images/CC6_3.png").getImage()};
	private Image[] southSouthEast = {new ImageIcon("src/images/CC5.png").getImage(), new ImageIcon("src/images/CC5_2.png").getImage(), new ImageIcon("src/images/CC5_3.png").getImage()};
	private Image[] south = {new ImageIcon("src/images/CC4.png").getImage(), new ImageIcon("src/images/CC4_2.png").getImage(), new ImageIcon("src/images/CC4_3.png").getImage()};
	private Image[] southSouthWest = {new ImageIcon("src/images/CC3.png").getImage(), new ImageIcon("src/images/CC3_2.png").getImage(), new ImageIcon("src/images/CC3_3.png").getImage()};
	private Image[] southWest = {new ImageIcon("src/images/CC2.png").getImage(), new ImageIcon("src/images/CC2_2.png").getImage(), new ImageIcon("src/images/CC2_3.png").getImage()};
	private Image[] southWestWest = {new ImageIcon("src/images/CC1.png").getImage(), new ImageIcon("src/images/CC1_2.png").getImage(), new ImageIcon("src/images/CC1_3.png").getImage()};
	private Image[] west = {new ImageIcon("src/images/CC16.png").getImage(), new ImageIcon("src/images/CC16_2.png").getImage(), new ImageIcon("src/images/CC16_3.png").getImage()};
	private Image[] northWestWest = {new ImageIcon("src/images/CC15.png").getImage(), new ImageIcon("src/images/CC15_2.png").getImage(), new ImageIcon("src/images/CC15_3.png").getImage()};
	private Image[]northWest = {new ImageIcon("src/images/CC14.png").getImage(), new ImageIcon("src/images/CC14_2.png").getImage(), new ImageIcon("src/images/CC14_3.png").getImage()};
	private Image[] northNorthWest = {new ImageIcon("src/images/CC13.png").getImage(), new ImageIcon("src/images/CC13_2.png").getImage(), new ImageIcon("src/images/CC13_3.png").getImage()};
	private boolean moving = false;
	
	//action listener for mouse stopped
	private ActionListener moveAL = new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			
			moving = false;
			t.stop();
			//System.out.println("stopped");
			
		}
		
	};
	
	//timer for mouse stopped
	private Timer t = new Timer(50, moveAL);

	

	/**
	 * Singleton accessor the only means of getting the instantiated object.
	 * @return One and only InputManager object.
	 */
	public static MouseMethods getInstance()
	{
		
		return instance;
		
	}
	
	/**
	 * detect mouse movement
	 */
	public void mouseMoved(MouseEvent arg0)
	{


		moving = true;
		
		if(t.isRunning() == true)
		{
			
			t.restart();
			
		}
		else
		{
			
			t.start();
			
		}
		
	}
	
	/**
	 * detect mouse dragged
	 */
	public void mouseDragged(MouseEvent arg0)
	{
		
		//System.out.println("dragged");
		
	}

	/**
	 * detect mouse clicked
	 */
	public void mouseClicked(MouseEvent arg0) {
		//System.out.println("click at - (" + getMouseCoordinate().getX() + "," + getMouseCoordinate().getY() + ")");
		//stCursor(CoreClass.coolkit.createCustomCursor(new ImageIcon("src/images/CC16.png").getImage(), new Point(0,0), "Cursor"));
	}

	/**
	 * detect mouse entering component
	 */
	public void mouseEntered(MouseEvent arg0) {
		//System.out.println("entered");
		
	}

	/**
	 * detect mouse leaving component
	 */
	public void mouseExited(MouseEvent arg0) {
		//System.out.println("exited");
		
	}

	/**
	 * detect mouse pressed
	 */
	public void mousePressed(MouseEvent arg0) {
		//System.out.println("pressed - (" + getMouseCoordinate().getX() + "," + getMouseCoordinate().getY() + ")");
	}

	/**
	 * detect mouse released
	 */
	public void mouseReleased(MouseEvent arg0) {
		//System.out.println("released - (" + getMouseCoordinate().getX() + "," + getMouseCoordinate().getY() + ")");
		
	}
	
	/**
	 * get where the mouse cursor is on screen
	 * 
	 * @return Coordinate
	 */
	public Coordinate getMouseCoordinate()
	{

		return new Coordinate(MouseInfo.getPointerInfo().getLocation());
		
	}
	
	/**
	 * get boolean value moving
	 * 
	 * @return
	 */
	public boolean isMoving()
	{
		return moving;
		
	}
	
	/**
	 * set the games oncreen cursor
	 * 
	 * @param c
	 */
	public void stCursor(Cursor c)
	{


		    CoreClass.fame.setCursor(c);
		
		
	}

	/**
	 * handles the custom cursor animations as well as automates the cursor when the player is not using it
	 */
	public void run()
	{
		
		//Animation index
		int index = 0;
		
		//coordinates to compare
		Coordinate one = getMouseCoordinate();
		Coordinate two = getMouseCoordinate();
		
		//current animation array being used
		Image[] currentArray = east;
		
		//custom cursors hotspot
		Coordinate hotSpot = new Coordinate(0,0);
		
		//cursors name
		String name = "cursor";
		
		
		while(true)
		{
//System.out.println("working");

			
			
				if(moving == true)
				{
					//get current coordinates
					two = getMouseCoordinate();
					

					//get the angle between the two coordinates in degrees				
					double degree = Mathariffic.angleBetween2Coordinates(one, two);
					

					//I want to move this to another class and have it as it's own method but i'm not sure which it fits into, coreclass maybe?
					if(Double.compare(degree, 0.0000) > 0)//positive number, must be between 0-180
					{System.out.println("positive");
						
						if(Double.compare(degree, 90.000) > 0)//greater than 90.0, must be between 90-180
						{System.out.println("greater than 90.0");
							
							if(Double.compare(degree, 135.000) > 0)//greater than 135.0, must be between 135-180
							{System.out.println("greater than 135.0");
								
							//w, sww, sw
							
										if(Double.compare(degree, 157.5000) > 0)//greater than 157.5, must be between 157.5-180
										{
											
											if(Double.compare(degree, 168.7500) < 0)//less than 168.75, must be southwestwest
											{
												
												currentArray = southWestWest;
												//hotspot.update();
												//name = "";
												System.out.println("sww "+ "degree - "+degree+" should be between 146.25-168.75");
												
		
												
											}
											else//greater than or equal to 168.75, must be west
											{
												
												currentArray = west;
												System.out.println("w "+ "degree - "+degree+" should be between -168.75~168.75");
											
												
											}
											
										}
										else if(Double.compare(degree, 157.5000) < 0)//less than 157.5 must be between 135.0-157.5
										{
											
											if(Double.compare(degree, 146.2500) > 0)//greater than 146.25 must be southwestwest
											{
												
												currentArray = southWestWest;
												System.out.println("ssw "+ "degree - "+degree+" should be between 146.25-168.75");
												
												
											}
											else//less than or equal to 146.25 must be south west 
											{
												
												currentArray = southWest;
												System.out.println("sw "+ "degree - "+degree+" should be between 123.75-146.25");
												
												
											}
											
										}
										else//exactly 157.5 must be sww
										{
											
											currentArray = southWestWest;
											System.out.println("sww equal "+ "degree - "+degree+" should be between 146.25-168.75");
											
											
										}

							}
							else if(Double.compare(degree, 135.000) < 0)//less than 135.0, greater than 90, must be between 90-135
							{System.out.println("less than 135.0");
								
							//sw, ssw, s
												
										if(Double.compare(degree, 112.500) > 0)//greater than 112.5000, must be between 112.5-135
										{
											
											if(Double.compare(degree, 123.7500) < 0)//less than 123.75, must be southsouthwest
											{
												
												currentArray = southSouthWest;
												System.out.println("ssw "+ "degree - "+degree+" should be between 123.75-101.25");
												
			
												
											}
											else//greater than or equal to 123.75, must be southwest
											{
												
												currentArray = southWest;
												System.out.println("sw "+ "degree - "+degree+" should be between 123.75-146.25");
											
												
											}
											
										}
										else if(Double.compare(degree, 112.500) < 0)//less than 112.5 must be between 90-112.5
										{
											
											if(Double.compare(degree, 101.2500) > 0)//greater than 101.25 must be southsouthwest
											{
												
												currentArray = southSouthWest;
												System.out.println("ssw "+ "degree - "+degree+" should be between 123.75-101.25");
												
												
											}
											else//less than or equal to 101.25 must be south
											{
												
												currentArray = south;
												System.out.println("s "+ "degree - "+degree+" should be between 101.25-78.75");
												
												
											}
											
										}
										else//exactly 112.5 must be ssw
										{
											
											currentArray = southSouthWest;
											System.out.println("ssw equal "+ "degree - "+degree+" should be between 123.75-101.25");
											
											
										}
								
							}
							else//Exactly 135.0
							{
								
								currentArray = southWest;
								System.out.println("sw equal");
								
							}
							
							
						}
						else if(Double.compare(degree, 90.000) < 0)//less than 90.0, between 0-90
						{System.out.println("less than 90.0");
							
							if(Double.compare(degree, 45.000) > 0)//greater than 45.0, between 45-90
							{System.out.println("greater than 45.0");
								
							//s, sse, se
								
										if(Double.compare(degree, 67.5000) > 0)//greater than 67.5, must be between 67.5-90
										{
											
											if(Double.compare(degree, 78.7500) < 0)//less than 78.75, must be southsoutheast
											{
												
												currentArray = southSouthEast;
												System.out.println("sse "+ "degree - "+degree+" should be between 78.75-56.25");
												
			
												
											}
											else//greater than or equal to 78.75, must be south
											{
												
												currentArray = south;
												System.out.println("s "+ "degree - "+degree+" should be between 101.25-78.75");
											
												
											}
											
										}
										else if(Double.compare(degree, 67.5000) < 0)//less than 67.5 must be between 67.5-45
										{
											
											if(Double.compare(degree, 56.2500) > 0)//greater than 56.25 must be 
											{
												
												currentArray = southSouthEast;
												System.out.println("sse "+ "degree - "+degree+" should be between 78.75-56.25");
												
												
											}
											else//less than or equal to 56.25 must be 
											{
												
												currentArray = southEast;
												System.out.println("se "+ "degree - "+degree+" should be between 56.25-33.75");
												
												
											}
											
										}
										else//exactly 67.5 must be sse
										{
											
											currentArray = southSouthEast;
											System.out.println("sse equal "+ "degree - "+degree+" should be between 78.75-56.25");
											
											
										}

							
							}
							else if(Double.compare(degree, 45.000) < 0)//less than 45.0 between 0-45
							{System.out.println("less than 45.0");
							
							//se, see, e
										
										if(Double.compare(degree, 22.5000) > 0)//greater than 22.5, must be between 22.5-45
										{
											
											if(Double.compare(degree, 33.7500) < 0)//less than 33.75, must be southeasteast
											{
												
												currentArray = southEastEast;
												System.out.println("see "+ "degree - "+degree+" should be between 33.75-11.25");
												
			
												
											}
											else//greater than or equal to 33.75, must be south east
											{
												
												currentArray = southEast;
												System.out.println("se "+ "degree - "+degree+" should be between 56.25-33.75");
											
												
											}
											
										}
										else if(Double.compare(degree, 22.5000) < 0)//less than must be between 0-22.5
										{
											
											if(Double.compare(degree, 11.2500) > 0)//greater than 11.25 must be southeasteast
											{
												
												currentArray = southEastEast;
												System.out.println("see "+ "degree - "+degree+" should be between 33.75-11.25");
												
												
											}
											else//less than or equal to 11.25 must be east
											{
												
												currentArray = east;
												System.out.println("e "+ "degree - "+degree+" should be between 11.25~(-11.25)");
												
												
											}
											
										}
										else//exactly 22.5 must be southeasteast
										{
											
											currentArray = southEastEast;
											System.out.println("see equal "+ "degree - "+degree+" should be between 33.75-11.25");
											
											
										}
								
							}
							else//exactaly 45,0
							{
								
								currentArray = southEast;
								System.out.println("se equal");
							}
							
							
						}
						else//exactaly 90.0
						{
							
							currentArray = south;
							System.out.println("s equal");
						}
				
					}
					else if(Double.compare(degree, 0.0000) < 0)//negative number, between -1-(-179.9)
					{System.out.println("negative");
					
						if(Double.compare(degree, -90.000) > 0)//greater than -90.0, between -1-(-90)
						{System.out.println("greater than -90.0");
							
							if(Double.compare(degree, -45.000) > 0)//greater than -45.0, between -1-(-45)
							{System.out.println("greater than -45.0");

							//e, nee, ne

										if(Double.compare(degree, -22.5000) < 0)//less than -22.5, must be between -22.5-(-45)
										{
											
											if(Double.compare(degree, -33.7500) > 0)//greater than -33.75, must be northeasteast
											{
												
												currentArray = northEastEast;
												System.out.println("nee "+ "degree - "+degree+" should be between -33.75-(-11.25-");
												
			
												
											}
											else//less than or equal to -33.75, must be northeast
											{
												
												currentArray = northEast;
												System.out.println("ne "+ "degree - "+degree+" should be between -56.25-(-33.75)");
											
												
											}
											
										}
										else if(Double.compare(degree, -22.5000) > 0)//greater than -22.5 must be between -1-(-22.5)
										{
											
											if(Double.compare(degree, -11.2500) < 0)//less than 11.25 must be northeasteast
											{
												
												currentArray = northEastEast;
												System.out.println("nee "+ "degree - "+degree+" should be between -33.75(-11.25)");
												
												
											}
											else//greater than or equal to -11.25 must be east
											{
												
												currentArray = east;
												System.out.println("e "+ "degree - "+degree+" should be between 11.25~(-11.25)");
												
												
											}
											
										}
										else//exactly 22.5 must be nee
										{
											
											currentArray = northEastEast;
											System.out.println("nee equal "+ "degree - "+degree+" should be between -33.75-(-11.25)");
											
											
										}
								
							}
							else if(Double.compare(degree, -45.000) < 0)//less than -45.0, between -90-(-45)
							{System.out.println("less than -45.0");
								
							//ne, nne, n
							
										if(Double.compare(degree, -67.5000) < 0)//less than -67.5, must be between -67.5-(-90)
										{
											
											if(Double.compare(degree, -78.7500) > 0)//greater than -78.75, must be northnortheast
											{
												
												currentArray = northNorthEast;
												System.out.println("nne "+ "degree - "+degree+" should be between -78.75-(-56.25)");
												
			
												
											}
											else//greater than or equal to -78.75, must be north
											{
												
												currentArray = north;
												System.out.println("n "+ "degree - "+degree+" should be between -101.25-(-78.75)");
											
												
											}
											
										}
										else if(Double.compare(degree, -67.5000) > 0)//greater than -67.5 must be between -67.5-(-45)
										{
											
											if(Double.compare(degree, -56.2500) < 0)//less than -56.25 must be northnortheast
											{
												
												currentArray = northNorthEast;
												System.out.println("nne "+ "degree - "+degree+" should be between -78.75-(-56.25)");
												
												
											}
											else//grater than or equal to -56.25 must be northeast
											{
												
												currentArray = northEast;
												System.out.println("ne "+ "degree - "+degree+" should be between -56.25-(-33.75)");
												
												
											}
											
										}
										else//exactly -67.5 must be nne
										{
											
											currentArray = northNorthEast;
											System.out.println("nne equal "+ "degree - "+degree+" should be between -78.75-(-56.25)");
											
											
										}
								
							}
							
						}
						else if(Double.compare(degree, -90.000) < 0)//less than -90.0, between -90-(-179)
						{	System.out.println("less than -90.0");
							
							if(Double.compare(degree, -135.000) > 0)//greater than -135.0, between -90-(-135)
							{System.out.println("greater than -135.0");
								
							//n, nnw, nw
														
										if(Double.compare(degree, -112.500) < 0)//less than -112.5000, must be between -112.5-(-135)
										{
											
											if(Double.compare(degree, -123.7500) > 0)//greater than -123.75, must be northnorthwest
											{
												
												currentArray = northNorthWest;
												System.out.println("nnw "+ "degree - "+degree+" should be between -123.75-(-101.25)");
												
			
												
											}
											else//less than or equal to -123.75, must be northwest
											{
												
												currentArray = northWest;
												System.out.println("nw "+ "degree - "+degree+" should be between -123.75-(-146.25)");
											
												
											}
											
										}
										else if(Double.compare(degree, -112.500) > 0)//greater than -112.5 must be between -90-(-112.5)
										{
											
											if(Double.compare(degree, -101.2500) < 0)//less than 101.25 must be nnwest
											{
												
												currentArray = northNorthWest;
												System.out.println("nnw "+ "degree - "+degree+" should be between -123.75-(-101.25)");
												
												
											}
											else//greater than or equal to -101.25 must be north
											{
												
												currentArray = north;
												System.out.println("n "+ "degree - "+degree+" should be between -101.25-(-78.75)");
												
												
											}
											
										}
										else//exactly -112.5 must be nnw
										{
											
											currentArray = northNorthWest;
											System.out.println("nnw equal "+ "degree - "+degree+" should be between -123.75-(-101.25)");
											
											
										}
								
							}
							else if(Double.compare(degree, -135.000) < 0)//less than -135.0, between -179-(-135)
							{System.out.println("less than -135.0");
							
							//w, nww, nw
										
										if(Double.compare(degree, -157.5000) < 0)//less than -157.5, must be between -157.5-(-180)
										{
											
											if(Double.compare(degree, -168.7500) > 0)//greater than -168.75, must be northwestwest
											{
												
												currentArray = northWestWest;
												System.out.println("nww "+ "degree - "+degree+" should be between -146.25-(-168.75)");
												
			
												
											}
											else//less than or equal to -168.75, must be west
											{
												
												currentArray = west;
												System.out.println("w "+ "degree - "+degree+" should be between -168.75~168.75");
											
												
											}
											
										}
										else if(Double.compare(degree, -157.5000) > 0)//greater than -157.5 must be between -135.0-(-157.5)
										{
											
											if(Double.compare(degree,-146.2500) < 0)//less than -146.25 must be northwestwest
											{
												
												currentArray = northWestWest;
												System.out.println("nwww "+ "degree - "+degree+" should be between -146.25-(-168.75)");
												
												
											}
											else//less than or equal to 146.25 must be south west 
											{
												
												currentArray = northWest;
												System.out.println("nw "+ "degree - "+degree+" should be between -123.75-(-146.25)");
												
												
											}
											
										}
										else//exactly -157.5 must be nww
										{
											
											currentArray = northWestWest;
											System.out.println("nww equal "+ "degree - "+degree+" should be between -146.25-(-168.75");
											
											
										}
								
							}
							
						}
						else//exactaly -90.0
						{
							
							currentArray = north;
							System.out.println("n equal");
						}
						
						
					}
					else//Exactly 0
					{
						
						currentArray = east;
						System.out.println("e equal");
					}
					
					
					

	
				
						
						
				}
				/*else
				{
				
				//if the player is not controlling the mouse the mouse will be still follow the player around
				//without this i feel as thought the player may feel obligated to be constantly moving the mouse
				 //which only serves to divide the players attention. This could be used to great effect later
				 //to fore the player to multi-task with keyboard and mouse simultaneously.
					
				}*/
				
					//update animation index
					if(index + 1 < currentArray.length)
					{
						
						index = index +1;
						
					}
					else
					{
						
						index = 0;
						
					}
					
				//set cursor	
			stCursor(CoreClass.coolkit.createCustomCursor(currentArray[index], hotSpot.getPoint(), name));
			
			//get coordinates to compare later
			one = getMouseCoordinate();
			
			
			/*movement is somewhat smoother at higher speeds but with such poor animations it looks very jittery. 
			also mouse stop timer is currently 50ms if this is set lower then that the cursor will always go back to 
			its east orientation when a player stops moving the mouse*/
			try
			{
				
				Thread.sleep(100);
				
			} 
			catch (InterruptedException e)
			{
				
				e.printStackTrace();
				
			}
			
			
			
		}
		
	}

}
