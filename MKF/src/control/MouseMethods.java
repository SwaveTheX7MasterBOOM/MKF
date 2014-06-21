package control;


import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import physicalGameObjects.DialogBox;
import physicalGameObjects.Player;

import logicalGameObjects.Actor;
import logicalGameObjects.Coordinate;
import logicalGameObjects.DrawableObjects;
import logicalGameObjects.Enemy;
import logicalGameObjects.Setpiece;

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
	
	private List<DrawableObjects> lastHoveredOver = new ArrayList<DrawableObjects>();

	//action listener for mouse stopped
	private ActionListener moveAL = new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			
			moving = false;
			
				t.stop();
			
				findHover();
				
		}

		private void findHover()
		{
			
			List<DrawableObjects> temp = CoreClass.getSortedObjectList();
			
		
			
			for(DrawableObjects obj:lastHoveredOver)
			{
				obj.setImageEffect("");

			}
			
			lastHoveredOver.clear();
			CoreClass.cc.clearHoverObjects();
			
			for(DrawableObjects obj:temp)
			{
				if(obj instanceof Player)
				{
				//System.out.println("act");	
				//System.out.println(((Actor) obj).getHitbox().contains(getMouseCoordinate().getPoint()));
						
					Rectangle r = new Rectangle(CoreClass.mainCharacter.getxPos(), CoreClass.mainCharacter.getyPos(), CoreClass.mainCharacter.getHitbox().width,  CoreClass.mainCharacter.getHitbox().height);
					
			
					if(r.contains(getMouseCoordinate().getPoint()))
					{
					
					lastHoveredOver.add(obj);
					
					CoreClass.cc.addHoverObject(obj);
					
					((Player) obj).setImageEffect("emboss");
					
					
					}
					
					
					
				}
				else// if(obj instanceof Enemy)
				{
					
					Rectangle r = new Rectangle( (int)( obj.getHitbox().getX() + (-(CoreClass.mapUpperLeftOffset.getX())) - (CoreClass.onScreenTile[0].getX() * 102)) ,
							(int)( obj.getHitbox().getY() + (-(CoreClass.mapUpperLeftOffset.getY())) - (CoreClass.onScreenTile[0].getY() * 102)) , 
							(int)obj.getWidth() , 
							(int)obj.getHeight() );
				
					if(r.contains(getMouseCoordinate().getPoint()))
					{
					
					lastHoveredOver.add(obj);
					
					CoreClass.cc.addHoverObject(obj);
					
					obj.setImageEffect("emboss");
					
					}
					
					
				}
			/*	else if(obj instanceof Setpiece)
				{
					
					//(int)((Setpiece) s).getBox().getX()+ cameraOffX - (tempUL.getX() * 102)
				

				
				
				
				Rectangle r = new Rectangle( (int)( ( (Setpiece) obj ).getX() + (-(CoreClass.mapUpperLeftOffset.getX())) - (CoreClass.onScreenTile[0].getX() * 102)) ,
						(int)( ( (Setpiece) obj ).getY() + (-(CoreClass.mapUpperLeftOffset.getY())) - (CoreClass.onScreenTile[0].getY() * 102)) , 
						(int)( (Setpiece) obj ).getWidth() , 
						(int)( (Setpiece) obj ).getHeight() );
				

					if(r.contains(getMouseCoordinate().getPoint()))
					{

						lastHoveredOver.add(obj);
						
						CoreClass.cc.addHoverObject(obj);
						
						((Setpiece) obj).setImageEffect("emboss");
						
						
				
						
					//	System.out.println(obj);
				//System.out.println();
				
			//	((Setpiece) obj).setThing(new ImageIcon("src/images/bomb.png").getImage());
				
					}
					
				
				
				
			
					
				}*/
			}
			
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
		
		CoreClass.cc.talk();
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
		//System.out.println("+++++++++++++++++++++++++++++++");
		CoreClass.fame.setCursor(c);
		
	}

}
