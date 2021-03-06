package logicalGameObjects;

import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public interface Actor extends DrawableObjects {
	
	//SETTERS AND GETTERS
	/** Returns the image of the character */
	public Image getPic();
	/** Sets the image of the character */
	public void setPic(Image pic);
	/** Returns the hp of the character */
	public int getHitpoints();
	/** Sets the hp of the character */
	public void setHitpoints(int hitpoints);
	/** Returns the attack of the character */
	public int getAttack();
	/** Sets the attack of the character */
	public void setAttack(int attack);
	/** Returns the defense of the character */
	public int getDefense();
	/** Sets the defense of the character */
	public void setDefense(int defense);
	/** Returns the speed of the character */
	public int getSpeed();
	/** Sets the speed of the character */
	public void setSpeed(int speed);
	/** Returns the x position of the character */
	public int getxPos();
	/** Sets the x position of the character */
	public void setxPos(int xPos);
	/** Returns the y position of the character */
	public int getyPos();
	/** Sets the y position of the character */
	public void setyPos(int yPos);
	/** Returns the direction the character is facing*/
	public int getDirection();
	/** Sets the direction the character is facing*/
	public void setDirection(int direction);
	
	
	public void setImageEffect(String s);
	
	public String getImageEffect();
	
	
	/** get the actual center of the character */
	public Coordinate getActualCenter();

	/**
	 * get specified idle image
	 * 
	 * @param i
	 * @return
	 */
	public Image idleImages(int i);
	
	/**
	 * get specified attack image
	 * 
	 * @param i
	 * @return
	 */
	public Image attackImage(int i);
	
	/**
	 * get the movement animation for northern movement
	 * 
	 * @return Image[]
	 */
	public Image[] northAnamation();
	
	/**
	 * get the movement animation for north eastern movement
	 * 
	 * @return Image[]
	 */
	public Image[] northEastAnamation();
	
	/**
	 * get the movement animation for eastern movement
	 * 
	 * @return Image[]
	 */
	public Image[] eastAnamation();
	
	/**
	 * get the movement animation for south eastern movement
	 * 
	 * @return Image[]
	 */
	public Image[] southEastAnamation();
	
	/**
	 * get the movement animation for southern movement
	 * 
	 * @return Image[]
	 */
	public Image[] southAnamation();
	
	/**
	 * get the movement animation for south western movement
	 * 
	 * @return Image[]
	 */
	public Image[] southWestAnamation();
	
	/**
	 * get the movement animation for western movement
	 * 
	 * @return Image[]
	 */
	public Image[] westAnamation();
	
	/**
	 * get the movement animation for north western movement
	 * 
	 * @return Image[]
	 */
	public Image[] northWestAnamation();
	
	/**
	 * get northern attack box
	 * 
	 * @return
	 */
	public Polygon getNorthAttackBox();
	
	/**
	 * get north eastern attack box
	 * 
	 * @return
	 */
	public Polygon getNorthEastAttackBox();
	
	/**
	 * get eastern attack box
	 * 
	 * @return
	 */
	public Polygon getEastAttackBox();
	
	/**
	 * get south eastern attack box
	 * 
	 * @return
	 */
	public Polygon getSouthEastAttackBox();
	
	/**
	 * get southern attack box
	 * 
	 * @return
	 */
	public Polygon getSouthAttackBox();
	
	/**
	 * get south western attack box
	 * 
	 * @return
	 */
	public Polygon getSouthWestAttackBox();
	
	/**
	 * get western attack box
	 * 
	 * @return
	 */
	public Polygon getWestAttackBox();
	
	/**
	 * get north western attack box
	 * 
	 * @return
	 */
	public Polygon getNorthWestAttackBox();
	
	/**
	 * get the up attack box
	 * 
	 * @return
	 */
	public Polygon getUpBox();
	
	/**
	 * set the up attack box
	 * 
	 * @param upBox
	 */
	public void setUpBox(Polygon upBox);
	
	/**
	 * get the down attack box
	 * 
	 * @return
	 */
	public Polygon getDownBox();
	
	/**
	 * set the down attack box
	 * 
	 * @param downBox
	 */
	public void setDownBox(Polygon downBox);
	
	/**
	 * get the left attack box
	 * 
	 * @return
	 */
	public Polygon getLeftBox();
	
	/**
	 * set the left attack box
	 * 
	 * @param leftBox
	 */
	public void setLeftBox(Polygon leftBox);
	
	/**
	 * get the right attack box
	 * 
	 * @return
	 */
	public Polygon getRightBox();
	
	/**
	 * set the right attack box
	 * 
	 * @param rightBox
	 */
	public void setRightBox(Polygon rightBox);
	
	/**
	 * get rectangle object with the bounds of the characters image
	 * 
	 * @return rectangle 
	 */
	public Rectangle getHitbox();
	
	/** remotely control character*/
	public void moveNorth();
	
	/** remotely control character*/
	public void moveNorthEast();
	
	/** remotely control character*/
	public void moveEast();
	
	/** remotely control character*/
	public void moveSouthEast();
	
	/** remotely control character*/
	public void moveSouth();
	
	/** remotely control character*/
	public void moveSouthWest();
	
	/** remotely control character*/
	public void moveWest();
	
	/** remotely control character*/
	public void moveNorthWest();
	
	
}