package control;


import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
	
	//Animation arrays
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
	
	//if the mouse is moving
	private boolean moving = false;
	
	//if the mouse is on screen
	private boolean onScreen = false;
	
	//if the mouse has been used in the last 5 seconds
	private boolean mouseInactive = false;
	
	//if the next mouseMoved event should be ignored
	private boolean ignoreNextMove = false;

	//action listener for mouse stopped
	private ActionListener moveAL = new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			
			moving = false;
			
				t.stop();
			
		}
		
	};
	
	//action listener for inactive mouse
	private ActionListener inactiveAL = new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			
			mouseInactive = true;
			
				t2.stop();
			
		}
		
	};
	
	//timer for mouse stopped
	private Timer t = new Timer(50, moveAL);
	
	// timer for inactive mouse
	private Timer t2 = new Timer(5000, inactiveAL);

	

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

		if(ignoreNextMove == false)
		{

			moving = true;
			
			mouseInactive = false;
			
			if(t.isRunning() == true)
			{
				
				t.restart();
				
			}
			else
			{
				
				t.start();
				
			}
			
			if(t2.isRunning() == true)
			{
				
				t2.restart();
				
			}
			else
			{
				
				t2.start();
				
			}
		
		}
		else
		{
			
			ignoreNextMove = false;
			
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
	public void mouseClicked(MouseEvent arg0)
	{
		
		//System.out.println("click at - (" + getMouseCoordinate().getX() + "," + getMouseCoordinate().getY() + ")");
		
	}

	/**
	 * detect mouse entering component
	 */
	public void mouseEntered(MouseEvent arg0)
	{
		
		onScreen = true;
		
	}

	/**
	 * detect mouse leaving component
	 */
	public void mouseExited(MouseEvent arg0)
	{
		
		onScreen = false;
		
	}

	/**
	 * detect mouse pressed
	 */
	public void mousePressed(MouseEvent arg0)
	{
		
		//System.out.println("pressed - (" + getMouseCoordinate().getX() + "," + getMouseCoordinate().getY() + ")");
		
	}

	/**
	 * detect mouse released
	 */
	public void mouseReleased(MouseEvent arg0)
	{
		
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
	 * @return boolean - if the mouse is moving
	 */
	public boolean isMoving()
	{
		
		return moving;
		
	}
	
	/**
	 * have the mouse been used in the last 5 seconds
	 * 
	 * @return boolean - if the mouse is actively being used
	 */
	public boolean isMouseInactive()
	{
		
		return mouseInactive;
		
	}

	/**
	 * is the mouse on screen
	 * 
	 * @return boolean -  if the mouse is within the confines of the component
	 */
	public boolean isOnScreen()
	{
		
		return onScreen;
		
	}
	
	/**
	 * is the next mouseMove event fired going to be ignored
	 * 
	 * @return boolean - if the next mouse moved event is to be ignored or not
	 */
	public boolean isIgnoreNextMove()
	{
		
		return ignoreNextMove;
		
	}

	/**
	 * set whether the next mouse moved event is going to be ignored or not
	 * 
	 * @param ignoreNextMove - boolean
	 */
	public void setIgnoreNextMove(boolean ignoreNextMove)
	{
		
		this.ignoreNextMove = ignoreNextMove;
		
	}

	/**
	 * set the games oncreen cursor
	 * 
	 * @param c - Cursor
	 */
	public void setCursor(Cursor c)
	{

		CoreClass.fame.setCursor(c);
		
	}
	

	/**
	 * handles the custom cursor animations as well as automates the cursor when the player is not using it to return to the player
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

		//degrees between two points
		double degree = 0.00;

		//for automated mouse movement
		Robot robot = null;

			try
			{
				
				robot = new Robot();
	
			} 
			catch (AWTException e1)
			{
	
				e1.printStackTrace();
	
			}

			//fix this later
		while(true)
		{
			//only do things if the mouse is on the screen		
			if(onScreen == true)
			{
				
				if(moving == true)
				{
					//get current coordinates
					two = getMouseCoordinate();

					//get the angle between the two coordinates in degrees				
					degree = Mathariffic.angleBetween2Coordinates(one, two);

				}
				else if(mouseInactive == true)
				{//if you move the player and the mouse is no longer moving have the mouse face the player

					//find where the player is
					one.update(CoreClass.mainCharacter.getxPos(), CoreClass.mainCharacter.getyPos());

					//find where the mouse is
					two = getMouseCoordinate();

					//find angel towars player
					degree = Mathariffic.angleBetween2Coordinates(two, one);

				}

					//string containing the cardinal direction
					String s = Magellon.findCardinalDirection(degree);

		
						switch(s)
						{
		
							case "north":
		
								currentArray = north;//change animation arrays
		
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)//the mouse has been left far away from the player
									{
				
										//move mouse
										robot.mouseMove(two.getX(), two.getY() - 6);
										
											//ignore the next event so it doesn't reset the timers
											ignoreNextMove = true;
										
									}
		
		
							break;
		
							case "northNorthEast":
			
								currentArray = northNorthEast;
			
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{

										robot.mouseMove(two.getX() + 2, two.getY() - 4);
										
											ignoreNextMove = true;
										
									}
			
							break;
		
							case "northEast":
			
								currentArray = northEast;
			
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
		
										robot.mouseMove(two.getX() + 3, two.getY() - 3);
										
											ignoreNextMove = true;
										
									}
		
							break;
		
							case "northEastEast":
			
								currentArray = northEastEast;
			
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
		
										robot.mouseMove(two.getX() + 4, two.getY() -2);
										
											ignoreNextMove = true;
										
									}
		
							break;
		
							case "east":
			
								currentArray = east;
			
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{

										robot.mouseMove(two.getX() + 6, two.getY());
										
											ignoreNextMove = true;
										
									}
		
							break;
		
							case "southEastEast":
			
								currentArray = southEastEast;
				
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
		
										robot.mouseMove(two.getX() + 4, two.getY() + 2);
										
											ignoreNextMove = true;
										
									}
		
							break;
		
							case "southEast":
			
								currentArray = southEast;
			
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
							
										robot.mouseMove(two.getX() + 3, two.getY() + 3);
										
											ignoreNextMove = true;
											
									}
		
							break;
		
							case "southSouthEast":
			
								currentArray = southSouthEast;
			
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
			
										robot.mouseMove(two.getX() + 2, two.getY() + 4);
										
											ignoreNextMove = true;
										
									}
		
							break;
			
							case "south":
			
								currentArray = south;
			
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
				
										robot.mouseMove(two.getX(), two.getY() + 6);
										
											ignoreNextMove = true;
										
									}
		
							break;
		
							case "southSouthWest":
			
								currentArray = southSouthWest;
			
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
		
										robot.mouseMove(two.getX() - 2, two.getY() + 4);
										
											ignoreNextMove = true;
										
									}
		
							break;
		
							case "southWest":
			
								currentArray = southWest;
			
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
				
										robot.mouseMove(two.getX() - 3, two.getY() + 3);
										
											ignoreNextMove = true;
										
									}
		
							break;
		
							case "southWestWest":
			
								currentArray = southWestWest;
			
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
					
										robot.mouseMove(two.getX() - 4, two.getY() + 2);
										
											ignoreNextMove = true;
										
									}
			
							break;
		
							case "west":
			
								currentArray = west;
				
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
										
										robot.mouseMove(two.getX() - 6, two.getY());
										
											ignoreNextMove = true;
										
									}
		
							break;
		
							case "northWestWest":
			
								currentArray = northWestWest;
			
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
				
										robot.mouseMove(two.getX() - 4, two.getY() - 2);
										
											ignoreNextMove = true;
									
									}
		
							break;
		
							case "northWest":
			
								currentArray = northWest;
			
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{

										robot.mouseMove(two.getX() - 3, two.getY() - 3);
										
											ignoreNextMove = true;
										
									}
		
							break;
		
							case "northNorthWest":
			
								currentArray = northNorthWest;
			
									if(mouseInactive == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
										
										robot.mouseMove(two.getX() - 2, two.getY() - 4);
										
											ignoreNextMove = true;
									
									}
		
							break;
		
						}


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
				setCursor(CoreClass.coolkit.createCustomCursor(currentArray[index], hotSpot.getPoint(), name));

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
			else
			{//wait to see if the mouse returns to the componet
				
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



}
