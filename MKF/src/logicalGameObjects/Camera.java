package logicalGameObjects;

import control.CoreClass;


/**
 * 
 * @author Thomas "CoBRAHhhh FUcking CoMANdah" Capach
 *
 */
public class Camera 
{

	//map position
	private int mapX;
	private int mapY;

	//screen position
	private int screenX;
	private int screenY;

	
	/**
	 * CONSTRUCTOR
	 */
	public Camera()
	{
		
		mapX = CoreClass.fame.getWidth() / 2;
		mapY = (CoreClass.fame.getHeight() / 2);
		
		screenX = CoreClass.fame.getWidth() / 2;
		screenY = CoreClass.fame.getHeight() / 2;

	}

	/**
	 * get the x axis map position
	 * 
	 * @return int
	 */
	public int getMapX()
	{
		
		return mapX;
		
	}

	/**
	 * set the x axis map position
	 * 
	 * @param mapX - int
	 */
	public void setMapX(int mapX)
	{
		
		this.mapX = mapX;
		
	}

	/**
	 * set the y axis map position
	 * 
	 * @return int
	 */
	public int getMapY()
	{
		
		return mapY;
		
	}

	/**
	 * set the y axis map position
	 * 
	 * @param mapY - int
	 */
	public void setMapY(int mapY)
	{
		
		this.mapY = mapY;
		
	}

	/**
	 * get the on screen x position of the camera
	 * 
	 * @return int
	 */
	public int getCenterX()
	{
		
		return screenX;
		
	}

	/**
	 * get the on screen y position of the camera
	 * 
	 * @return int
	 */
	public int getCenterY()
	{
		
		return screenY;
		
	}

	/**
	 * 	
	 * @return double - distance between the camera and the main player
	 */
	public double getDistance()
	{
		//A^2 + B^2 = C^2
		 double dis = Math.sqrt(Math.pow((getCenterX() - CoreClass.mainCharacter.getxPos()), 2)
				 	+ Math.pow((getCenterY() - CoreClass.mainCharacter.getyPos()), 2));			
		
		
		return dis;
		
	}
	
}
