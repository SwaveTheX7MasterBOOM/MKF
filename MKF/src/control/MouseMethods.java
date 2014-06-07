package control;


import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

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
public class MouseMethods extends MouseInputAdapter
{
	
	//the only instantiated object
	private static MouseMethods instance = new MouseMethods();

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
		
		System.out.println("pressed - (" + getMouseCoordinate().getX() + "," + getMouseCoordinate().getY() + ")");
		
	}

	/**
	 * detect mouse released
	 */
	public void mouseReleased(MouseEvent arg0)
	{
		
		System.out.println("released - (" + getMouseCoordinate().getX() + "," + getMouseCoordinate().getY() + ")");
		
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
		//System.out.println("+++++++++++++++++++++++++++++++");
		CoreClass.fame.setCursor(c);
		
	}

}
