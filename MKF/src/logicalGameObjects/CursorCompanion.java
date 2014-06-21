package logicalGameObjects;

import java.awt.AWTException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import physicalGameObjects.Bomb;
import physicalGameObjects.Bush;
import physicalGameObjects.DialogBox;
import physicalGameObjects.GumTree;
import physicalGameObjects.MeleeEnemy;
import physicalGameObjects.Player;

import control.CoreClass;
import control.Magellon;
import control.Mathariffic;


/**
 * Custom animated Cursor
 * 
 * @author Cobra Fucking Capach
 *
 */
public class CursorCompanion implements Runnable, DrawableObjects
{

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
	
	
	private List<Object>  hoverObjects = new ArrayList<Object>();
	private Font font;
	
	public CursorCompanion()
	{
		//fonts should be registered only when needed
		InputStream myStream = null;
	
			try {
				myStream = new BufferedInputStream(new FileInputStream("Assets/cad.ttf"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		
	
			try {
				font = Font.createFont(Font.TRUETYPE_FONT , myStream);
			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		font = font.deriveFont(Font.BOLD, 18);
		
		System.out.println(font);
		
		  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		      System.out.println( ge.registerFont(font));
		
	}

	/**
	 * handles the custom cursor animations as well as automates the cursor when the player is not using it to return to the player
	 */
	public void run()
	{

		//Animation index
		int index = 0;

		//coordinates to compare
		Coordinate one = CoreClass.mightyMouse.getMouseCoordinate();
		Coordinate two = CoreClass.mightyMouse.getMouseCoordinate();

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
			if(CoreClass.mightyMouse.isOnScreen() == true)
			{
				
				if(CoreClass.mightyMouse.isMoving() == true)
				{
					//get current coordinates
					two = CoreClass.mightyMouse.getMouseCoordinate();

					//get the angle between the two coordinates in degrees				
					degree = Mathariffic.angleBetween2Coordinates(one, two);

				}
				else if(CoreClass.mightyMouse.isMouseInactive() == true)
				{//if you move the player and the mouse is no longer moving have the mouse face the player

					//find where the player is
					one.update(CoreClass.mainCharacter.getxPos(), CoreClass.mainCharacter.getyPos());

					//find where the mouse is
					two = CoreClass.mightyMouse.getMouseCoordinate();

					//find angel towards player
					degree = Mathariffic.angleBetween2Coordinates(two, one);

				}

					//string containing the cardinal direction
					String s = Magellon.findCardinalDirection(degree);

		
						switch(s)
						{
		
							case "north":
		
								currentArray = north;//change animation arrays
		
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)//the mouse has been left far away from the player
									{
				
										//ignore the next event so it doesn't reset the timers
										CoreClass.mightyMouse.setIgnoreNextMove(true);
										
											//move mouse
											robot.mouseMove(two.getX(), two.getY() - 6);
	
									}
		
		
							break;
		
							case "northNorthEast":
			
								currentArray = northNorthEast;
			
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{

										CoreClass.mightyMouse.setIgnoreNextMove(true);
										
											robot.mouseMove(two.getX() + 2, two.getY() - 4);

									}
			
							break;
		
							case "northEast":
			
								currentArray = northEast;
			
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
		
										CoreClass.mightyMouse.setIgnoreNextMove(true);
										
											robot.mouseMove(two.getX() + 3, two.getY() - 3);
	
									}
		
							break;
		
							case "northEastEast":
			
								currentArray = northEastEast;
			
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
		
										CoreClass.mightyMouse.setIgnoreNextMove(true);
										
											robot.mouseMove(two.getX() + 4, two.getY() -2);
	
									}
		
							break;
		
							case "east":
			
								currentArray = east;
			
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{

										CoreClass.mightyMouse.setIgnoreNextMove(true);
										
											robot.mouseMove(two.getX() + 6, two.getY());
										
									}
		
							break;
		
							case "southEastEast":
			
								currentArray = southEastEast;
				
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
		
										CoreClass.mightyMouse.setIgnoreNextMove(true);
										
											robot.mouseMove(two.getX() + 4, two.getY() + 2);

									}
		
							break;
		
							case "southEast":
			
								currentArray = southEast;
			
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
							
										CoreClass.mightyMouse.setIgnoreNextMove(true);
										
											robot.mouseMove(two.getX() + 3, two.getY() + 3);

									}
		
							break;
		
							case "southSouthEast":
			
								currentArray = southSouthEast;
			
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
			
										CoreClass.mightyMouse.setIgnoreNextMove(true);
										
											robot.mouseMove(two.getX() + 2, two.getY() + 4);

									}
		
							break;
			
							case "south":
			
								currentArray = south;
			
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
				
										CoreClass.mightyMouse.setIgnoreNextMove(true);
										
											robot.mouseMove(two.getX(), two.getY() + 6);
			
									}
		
							break;
		
							case "southSouthWest":
			
								currentArray = southSouthWest;
			
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
										
										CoreClass.mightyMouse.setIgnoreNextMove(true);
										
											robot.mouseMove(two.getX() - 2, two.getY() + 4);
	
									}
		
							break;
		
							case "southWest":
			
								currentArray = southWest;
			
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
				
										CoreClass.mightyMouse.setIgnoreNextMove(true);
														
											robot.mouseMove(two.getX() - 3, two.getY() + 3);

									}
		
							break;
		
							case "southWestWest":
			
								currentArray = southWestWest;
			
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
										
										CoreClass.mightyMouse.setIgnoreNextMove(true);
					
											robot.mouseMove(two.getX() - 4, two.getY() + 2);
	
									}
			
							break;
		
							case "west":
			
								currentArray = west;
				
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
										
										CoreClass.mightyMouse.setIgnoreNextMove(true);
										
											robot.mouseMove(two.getX() - 6, two.getY());

									}
		
							break;
		
							case "northWestWest":
			
								currentArray = northWestWest;
			
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
										
										CoreClass.mightyMouse.setIgnoreNextMove(true);
				
											robot.mouseMove(two.getX() - 4, two.getY() - 2);
				
									}
		
							break;
		
							case "northWest":
			
								currentArray = northWest;
			
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
										
										CoreClass.mightyMouse.setIgnoreNextMove(true);
										
											robot.mouseMove(two.getX() - 3, two.getY() - 3);		
										
									}
		
							break;
		
							case "northNorthWest":
			
								currentArray = northNorthWest;
			
									if(CoreClass.mightyMouse.isMouseInactive() == true && Mathariffic.distanceBetween2Coordinates(one, two) >100)
									{
										
										CoreClass.mightyMouse.setIgnoreNextMove(true);
										
											robot.mouseMove(two.getX() - 2, two.getY() - 4);

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
							CoreClass.mightyMouse.setCursor(CoreClass.coolkit.createCustomCursor(currentArray[index], hotSpot.getPoint(), name));
					
									//get coordinates to compare later
									one = CoreClass.mightyMouse.getMouseCoordinate();


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
			{//wait to see if the mouse returns to the component
				
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

	public void addHoverObject(Object o) {
		
		hoverObjects.add(o);
	}
	
	public void clearHoverObjects() {
		
		hoverObjects.clear();
	}
	
	
	public void talk()
	{
		
		String s = "All I do Is Win";
		
if(hoverObjects.size() == 1)
{
		
	for(Object o:hoverObjects)
	{
	
if(o instanceof Bush)
{
	s = "I hate Bush";
}
else if(o instanceof Bomb)
{
	s = "It's a land mine";
}
else if(o instanceof GumTree)
{
	s = "So, this is where Gum comes from?!";
}
else if(o instanceof Player)
{
	s = "Bet you wish your were Donkey Kong.";
}
else if(o instanceof MeleeEnemy)
{
	s = "Loser";
}

	}
}
else if(hoverObjects.size() > 1)
{
	
	s = "Fuck all this shit";
	
}
		
	
		CoreClass.addNotification(new DialogBox(CoreClass.cc, s));
		
		
	}

	@Override
	public Font getFont() {
		// TODO Auto-generated method stub
		  System.out.println(font);
		return font;
	}

	@Override
	public String getImageEffect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getPic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getxPos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getyPos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setImageEffect(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getHitbox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
