package logicalGameObjects;

import java.awt.Image;

/**
 * 
 * @author Thomas Capach
 *
 */
public abstract class Food
{
	//Image of the food object
	private Image pic;
	
	// the x axis position of the food object
	private int pX;
	
	//the y axis posistion of the food object
	private int pY;
	
	//poop attack modifier
	private int modifier;
	
	//if the food is inside a destructable 
	boolean insideObject;
		
	/**
	 * constructor
	 * @param i - image of the food object
	 * @param x - x axis position
	 * @param y - y axis position
	 * @param m - poop attack modifier
	 */
	public Food(Image i, int x, int y, int m, boolean hidden)
	{		
		pic = i;		
		pX = x;		
		pY = y;		
		modifier = m;		
		insideObject = hidden;		
	}	
	
	/**
	 * get the image of the food object
	 * @return pic - the image of the food object
	 */
	public Image getImage() 
	{		
		return pic;		
	}	
	
	/**
	 * set the image of the food object
	 * @param ii - image of the object
	 */
	public void setImage(Image ii)
	{		
		pic = ii;		
	}
		
	/**
	 * get the x axis position of the  food object
	 * @return int pX
	 */
	public int getXPosistion()
	{		
		return pX;		
	}
		
	/**
	 * get the y position of the food object
	 * @return int pY
	 */
	public int getYPosition()
	{		
		return pY;		
	}
		
	/**
	 * get the value of the poop attack modifier
	 * @return int modifier
	 */
	public int getMod()
	{		
		return modifier;		
	}
		
	/**
	 * set the vale of the poop attack modifier
	 * @param x - int value of poop modifier
	 */
	public void setMod(int x)
	{		
		modifier = x;		
	}
		
	/**
	 * gets the visibility status of the food object
	 * @return boolean - if the object is viable
	 */
	public boolean isViable()
	{		
		return insideObject;		
	}	
	
	/**
	 * set if the the food object is visable
	 * @param a - boolean
	 */
	public void setVisable( boolean a)
	{		
		insideObject = a;		
	}	
}