package logicalGameObjects;

import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;

public interface Actor {
	
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
	
	

	
	public Image idleImages(int i);
	
	public Image attackImage(int i);
	
	public Image[] northAnamation();
	
	public Image[] northEastAnamation();
	
	public Image[] eastAnamation();
	
	public Image[] southEastAnamation();
	
	public Image[] southAnamation();
	
	public Image[] southWestAnamation();
	
	public Image[] westAnamation();
	
	public Image[] northWestAnamation();
	
	
	public Polygon getNorthAttackBox();
	
	public Polygon getNorthEastAttackBox();
	
	public Polygon getEastAttackBox();
	
	public Polygon getSouthEastAttackBox();
	
	public Polygon getSouthAttackBox();
	
	public Polygon getSouthWestAttackBox();
	
	public Polygon getWestAttackBox();
	
	public Polygon getNorthWestAttackBox();
	
	
	public Polygon getUpBox();
	public void setUpBox(Polygon upBox);
	
	public Polygon getDownBox();
	public void setDownBox(Polygon downBox);
	
	public Polygon getLeftBox();
	public void setLeftBox(Polygon leftBox);
	
	public Polygon getRightBox();
	public void setRightBox(Polygon rightBox);
	
	
}