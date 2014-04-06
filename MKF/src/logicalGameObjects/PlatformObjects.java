package logicalGameObjects;

import java.awt.Image;

/**@author Andy Rodriguez**/
public class PlatformObjects
{
	//image of the platform's normal state - no player on it
	private Image normState;
	
	//image of the platform's stepped on state - player on it
	private Image steppedState;
	
	//true if platform is stepped on
	private boolean steppedOn;
	
	//platform's x position
	private int xPos;
	
	//platform's y position
	private int yPos;	
		
	/**
	 * Constructor
	 * @param nState - image of the platform's normal state
	 * @param sState - image of the platform's stepped state, can be null if same as nState
	 * @param x - the x position of the platform
	 * @param y - the y position of the platform
	 */
	public PlatformObjects(Image nState, Image sState, int x, int y)
	{		
		normState = nState;
		if(sState == null)
			steppedState = nState;
		
		xPos = x;
		yPos = y;
		steppedOn = false;
	}

	/**
	 * Returns the image of the platforms based on whether its stepped on or not
	 * @return Image normState or steppedState
	 */
	public Image getImage() {
		if(!steppedOn)
		{
			return normState;
		}
		else
		{
			return steppedState;
		}
	}

	/**
	 * Sets the platforms normal state image
	 * @param normState the platform's normal state image
	 */
	public void setNormState(Image normState) {
		this.normState = normState;
	}

	/**
	 * Sets the platforms normal state image
	 * @param steppedState the platform's stepped state image
	 */
	public void setSteppedState(Image steppedState) {
		this.steppedState = steppedState;
	}
	
	/**
	 * Returns the platform's x position
	 * @return xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * Sets the platform's x position
	 * @param xPos the x position
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * Returns the platform's y position
	 * @return yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * Sets the platform's y position
	 * @param yPos the y position
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * Checks to see if the platform is currently stepped on
	 * @return true if platform is stepped on, false if not
	 */
	public boolean isSteppedOn() {
		return steppedOn;
	}

	/**
	 * Sets the platform's steppedOn variable if its stepped on or not
	 * @param steppedOn true if platform is stepped on, false if not
	 */
	public void setSteppedOn(boolean steppedOn) {
		this.steppedOn = steppedOn;
	}
}