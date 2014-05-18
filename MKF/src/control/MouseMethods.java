package control;


import java.awt.Cursor;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import logicalGameObjects.Coordinate;

/**
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
	
	//
	private Image[] northNorthEast = {new ImageIcon("src/images/CC11.png").getImage(), new ImageIcon("src/images/CC11_2.png").getImage(), new ImageIcon("src/images/CC11_3.png").getImage()};
	
	//
	private Image[]northEast = {new ImageIcon("src/images/CC10.png").getImage(), new ImageIcon("src/images/CC10_2.png").getImage(), new ImageIcon("src/images/CC10_3.png").getImage()};
	
	//
	private Image[] northEastEast = {new ImageIcon("src/images/CC9.png").getImage(), new ImageIcon("src/images/CC9_2.png").getImage(), new ImageIcon("src/images/CC9_3.png").getImage()};
			
	//
	private Image[] east = {new ImageIcon("src/images/CC8.png").getImage(), new ImageIcon("src/images/CC8_2.png").getImage(), new ImageIcon("src/images/CC8_3.png").getImage()};
	
	//
	private Image[] southEastEast = {new ImageIcon("src/images/CC7.png").getImage(), new ImageIcon("src/images/CC7_2.png").getImage(), new ImageIcon("src/images/CC7_3.png").getImage()};
	
	//
	private Image[] southEast = {new ImageIcon("src/images/CC6.png").getImage(), new ImageIcon("src/images/CC6_2.png").getImage(), new ImageIcon("src/images/CC6_3.png").getImage()};
	
	//
	private Image[] southSouthEast = {new ImageIcon("src/images/CC5.png").getImage(), new ImageIcon("src/images/CC5_2.png").getImage(), new ImageIcon("src/images/CC5_3.png").getImage()};
	
	//
	private Image[] south = {new ImageIcon("src/images/CC4.png").getImage(), new ImageIcon("src/images/CC4_2.png").getImage(), new ImageIcon("src/images/CC4_3.png").getImage()};
	
	//
	private Image[] southSouthWest = {new ImageIcon("src/images/CC3.png").getImage(), new ImageIcon("src/images/CC3_2.png").getImage(), new ImageIcon("src/images/CC3_3.png").getImage()};
	
	//
	private Image[] southWest = {new ImageIcon("src/images/CC2.png").getImage(), new ImageIcon("src/images/CC2_2.png").getImage(), new ImageIcon("src/images/CC2_3.png").getImage()};
	
	//
	private Image[] southWestWest = {new ImageIcon("src/images/CC1.png").getImage(), new ImageIcon("src/images/CC1_2.png").getImage(), new ImageIcon("src/images/CC1_3.png").getImage()};
	
	//
	private Image[] west = {new ImageIcon("src/images/CC16.png").getImage(), new ImageIcon("src/images/CC16_2.png").getImage(), new ImageIcon("src/images/CC16_3.png").getImage()};
	
	//
	private Image[] northWestWest = {new ImageIcon("src/images/CC15.png").getImage(), new ImageIcon("src/images/CC15_2.png").getImage(), new ImageIcon("src/images/CC15_3.png").getImage()};
	
	//
	private Image[]northWest = {new ImageIcon("src/images/CC14.png").getImage(), new ImageIcon("src/images/CC14_2.png").getImage(), new ImageIcon("src/images/CC14_3.png").getImage()};
	
	//
	private Image[] northNorthWest = {new ImageIcon("src/images/CC13.png").getImage(), new ImageIcon("src/images/CC13_2.png").getImage(), new ImageIcon("src/images/CC13_3.png").getImage()};
			
	//
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
	
	//timer fot mouse stopped
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

	@Override
	public void run()
	{
		
		
		int index = 0;
		
		Coordinate one = getMouseCoordinate();
		Coordinate two = getMouseCoordinate();
		
		while(true)
		{
//System.out.println("working");
			Image[] currentArray = east;
			
			
				if(moving == true)
				{
					//get current coordinates
					two = getMouseCoordinate();
					

//get the angle between the two coordinates in degrees				
double degree = Mathariffic.angleBetween2Coordinates(one, two);
					System.out.println(degree);

					
					if(Double.compare(degree, 0.0000) > 0)//positive number
					{
						
						if(Double.compare(degree, 90.000) > 0)//greater than 90.0
						{
							
							if(Double.compare(degree, 135.000) > 0)//greater than 135.0
							{
								
								if(Double.compare(degree, 146.2500) < 0)//less than 146.25
								{
									
									currentArray = southWest;
									System.out.println("sw");
									
								}
								else if(Double.compare(degree, 146.2500) == 0)//Exactly 146.25
								{
									
									currentArray = southWest;
									System.out.println("sw");
								}
								else if(Double.compare(degree, 168.7500) > 0)//greater than 168.75
								{
									
									currentArray = west;
									System.out.println("w");
								}
								else if(Double.compare(degree, 168.7500) == 0)//exactly 168.75
								{
									
									currentArray = west;
									System.out.println("w");
								}
								else// must be somewhere around 157.5
								{
									
									currentArray = southWestWest;
									System.out.println("sww");
								}

								
							}
							else if(Double.compare(degree, 135.000) < 0)//less than 135.0
							{
								
								if(Double.compare(degree, 123.7500) > 0)//greater than 123.75
								{
									
									currentArray = southWest;
									System.out.println("sw");
								}
								else if(Double.compare(degree, 123.7500) == 0)//exactly 123.75
								{
									
									currentArray = southWest;
									System.out.println("sw");
								}
								else if(Double.compare(degree, 101.2500) < 0)//less than 101.25
								{
									
									currentArray = south;
									System.out.println("s");
								}
								else if(Double.compare(degree, 101.2500) == 0)//exactly 101.25
								{
									
									currentArray = south;
									System.out.println("s");
								}
								else// must be somewhere around 112.5
								{
									
									currentArray = southSouthWest;
									System.out.println("ssw");
								}
								
							}
							else//exactaly 135.0
							{
								
								currentArray = southWest;
								System.out.println("sw");
								
							}
							
						}
						else if(Double.compare(degree, 90.000) < 0)//less than 90.0
						{
							
							
							if(Double.compare(degree, 45.000) > 0)//greater than 45.0
							{
								
								if(Double.compare(degree, 78.7500) > 0)//greater than 78.75
								{
									
									currentArray = south;
									System.out.println("s");
									
								}
								else if(Double.compare(degree, 78.7500) == 0)//exactly 78.75
								{
									
									currentArray = south;
									System.out.println("s");
								}
								else if(Double.compare(degree, 56.2500) < 0)//less than 56.25
								{
									
									currentArray = southEast;
									System.out.println("se");
								}
								else if(Double.compare(degree, 56.2500) == 0)//exactly 56.25
								{
									
									currentArray = southEast;
									System.out.println("se");
								}
								else// must be somewhere around 67.5
								{
									
									currentArray = southSouthEast;
									System.out.println("sse");
								}
								
							}
							else if(Double.compare(degree, 45.000) < 0)//less than 45.0
							{
								
								if(Double.compare(degree, 33.7500) > 0)//greater than 33.75
								{
									
									currentArray = southEast;
									System.out.println("se");
								}
								else if(Double.compare(degree, 33.7500) == 0)//exactly 33.75
								{
									
									currentArray = southEast;
									System.out.println("se");
								}
								else if(Double.compare(degree, 11.2500) < 0)//less than 11.25
								{
									
									currentArray = east;
									System.out.println("e");
								}
								else if(Double.compare(degree, 11.2500) == 0)//exactly 11.25
								{
									
									currentArray = east;
									System.out.println("e");
								}
								else// must be somewhere around 22.5
								{
									
									currentArray = southEastEast;
									System.out.println("see");
								}
								
							}
							else//exactaly 45,0
							{
								
								currentArray = southEast;
								System.out.println("se");
							}
							
							
						}
						else//exactaly 90.0
						{
							
							currentArray = south;
							System.out.println("s");
						}
						
					}
					else if(Double.compare(degree, 0.0000) < 0)//negative number
					{
					
						if(Double.compare(degree, -90.000) > 0)//greater than -90.0
						{
							
							if(Double.compare(degree, -45.000) > 0)//greater than -45.0
							{

								if(Double.compare(degree, -11.2500) > 0)//greater than -11.25
								{
									
									currentArray = east;
									System.out.println("e");
								}
								else if(Double.compare(degree, -11.2500) == 0)//exactly -11.25
								{
									
									currentArray = east;
									System.out.println("e");
								}
								else if(Double.compare(degree, -33.7500) > 0)//less than -33.75
								{
									
									currentArray = northEast;
									System.out.println("ne");
								}
								else if(Double.compare(degree, -33.7500) == 0)//exactly -33.75
								{
									
									currentArray = northEast;
									System.out.println("ne");
								}
								else// must be somewhere around -22.5
								{
									
									currentArray = northEastEast;
									System.out.println("nee");
								}
								
							}
							else if(Double.compare(degree, -45.000) < 0)//less than -45.0
							{
								
								if(Double.compare(degree, -56.2500) > 0)//greater than -56.25 
								{
									
									currentArray = northEast;
									System.out.println("ne");
								}
								else if(Double.compare(degree, -56.2500) == 0)//exactly -56.25
								{
									
									currentArray = northEast;
									System.out.println("ne");
								}
								else if(Double.compare(degree, -78.7500) > 0)//less than -78.75
								{
									
									currentArray = north;
									System.out.println("n");
								}
								else if(Double.compare(degree, -78.7500) == 0)//exactly -78.75
								{
									
									currentArray = north;
									System.out.println("n");
								}
								else// must be somewhere around -67.5
								{
									
									currentArray = northNorthEast;
									System.out.println("nne");
								}
								
							}
							
						}
						else if(Double.compare(degree, -90.000) < 0)//less than -90.0
						{	
							
							if(Double.compare(degree, -135.000) > 0)//greater than -135.0
							{
								
								if(Double.compare(degree, -101.25) > 0)//greater than -101.25
								{
									
									currentArray = north;
									System.out.println("n");
								}
								else if(Double.compare(degree, -101.25) == 0)//exactly -101.25
								{
									
									currentArray = north;
									System.out.println("n");
								}
								else if(Double.compare(degree, -123.75) > 0)//less than -123.75
								{
									
									currentArray = northWest;
									System.out.println("nw");
								}
								else if(Double.compare(degree, -123.75) == 0)//exactly -123.75
								{
									
									currentArray = northWest;
									System.out.println("nw");
								}
								else// must be somewhere around -112.5
								{
									
									currentArray = northNorthWest;
									System.out.println("nnw");
								}
								
							}
							else if(Double.compare(degree, -135.000) < 0)//less than -135.0
							{
								
								if(Double.compare(degree, -146.25) > 0)//greater than -146.25
								{
									
									currentArray = northWest;
									System.out.println("nw");
								}
								else if(Double.compare(degree, -146.25) == 0)//exactly -146.25
								{
									
									currentArray = northWest;
									System.out.println("nw");
								}
								else if(Double.compare(degree, -168.7500) > 0)//less than -168.75
								{
									
									currentArray = west;
									System.out.println("w");
								}
								else if(Double.compare(degree, -168.7500) == 0)//exactly -168.75
								{
									
									currentArray = west;
									System.out.println("w");
								}
								else// must be somewhere around -157.5
								{
									
									currentArray = northWestWest;
									System.out.println("nww");
								}
								
							}
							
						}
						else//exactaly -90.0
						{
							
							currentArray = north;
							System.out.println("n");
						}
						
						
					}
					else//Exactly 0
					{
						
						currentArray = east;
						System.out.println("e");
					}
					
					
					

	
				
						
						
				}
					//update anmation index
					if(index + 1 < currentArray.length)
					{
						
						index = index +1;
						
					}
					else
					{
						
						index = 0;
						
					}
				//set cursor	
			stCursor(CoreClass.coolkit.createCustomCursor(currentArray[index], new Point(0,0), "Cursor"));
			
			//get coordinates to compare later
			one = getMouseCoordinate();
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
	}

}
