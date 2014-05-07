package logicalGameObjects;

import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Random;

/**@author Andy Rodriguez **/
public abstract class Enemy implements Actor
{

	public Enemy()
	{

	}
	
	//SETTERS AND GETTERS
	/**
	 * Returns the image of the enemy
	 * @return ImageIcon pic
	 */
	public abstract Image getPic();
	
	/**
	 * Sets the image of the enemy
	 * @param pic image of the enemy
	 */
	public abstract void setPic(Image pic);
	
	/**
	 * Returns the enemy's current hitpoints
	 * @return hitpoints
	 */
	public abstract int getHitpoints();
	
	/**
	 * Sets the enemy's hitpoints
	 * @param hitpoints enemy's hp
	 */
	public abstract void setHitpoints(int hitpoints);
	
	/**
	 * Returns the enemy's current attack strength
	 * @return attack
	 */
	public abstract int getAttack();
	
	/**
	 * Sets the enemy's attack strength
	 * @param attack enemy's attack str
	 */
	public abstract void setAttack(int attack);
	
	/**
	 * Returns the enemy's current defense level
	 * @return defense
	 */
	public abstract int getDefense();
	
	/**
	 * Sets the enemy's defense level
	 * @param defense enemy's defense lvl
	 */
	public abstract void setDefense(int defense);
	
	/**
	 * Returns the enemy's current speed
	 * @return speed
	 */
	public abstract int getSpeed();
	
	/**
	 * Sets the enemy's speed
	 * @param speed enemy's speed
	 */
	public abstract void setSpeed(int speed);
	
	/**
	 * Returns the enemy's current x position
	 * @return xPos
	 */
	public abstract int getxPos();
	
	/**
	 * Sets the enemy's current x position
	 * @param xPos x position
	 */
	public abstract void setxPos(int xPos);
	
	/**
	 * Returns the enemy's current y position
	 * @return yPos
	 */
	public abstract int getyPos();
	
	/**
	 * Sets the enemy's current y position
	 * @param yPos y position
	 */
	public abstract void setyPos(int yPos);
	
	/**
	 * Returns the direction the enemy is facing
	 * @return 
	 */
	public abstract int getDirection();
	
	/**
	 * Sets the direction the enemy is facing
	 * @param 
	 */
	public abstract void setDirection(int direction);	

	/**
	 * Returns the enemy's side to side hitbox
	 * @return hitbox
	 */
	public abstract Rectangle getHitbox();

	/**
	 * Sets the enemy's side to side hitbox
	 * @param hitbox the enemy's hitbox
	 */
	public abstract void setHitbox(Rectangle hitbox);
	
	/** Artificial Intelligence **/
	public abstract void aI();
	
	/** get how far the character can see */
	public abstract int getSightRange();
	
	/** set how far the character can see */
	public abstract void setSightRange(int sightRange);
	
	/** get how far the character can hear */
	public abstract int getHearingRange();
	
	/** set how far the character can hear */
	public abstract void setHearingRange(int hearingRange);
	
	/** get the center position of the characters actual position **/
	public abstract Coordinate getActualCenter(); 
	
	/** get the hearing collision box **/
	public abstract Polygon gethearingBox();
	
	/** set the hearing collision box **/
	public abstract void sethearingBox(Polygon hearingBox);
	
	/** get the sight collision box **/
	public abstract Polygon getseeingBox();
	
	/** set the sight collision box **/
	public abstract void setseeingBox(Polygon seeingBox);	

	/** make the character stop what it is doing **/
	public abstract void stopMoving();
	
	/** get the upper collision box **/
	public abstract Polygon getUpBox();

	/** set the upper collision box **/
	public abstract void setUpBox(Polygon upBox);

	/** get the bottom collision box **/
	public abstract Polygon getDownBox();

	/** set the bottom collision box **/
	public abstract void setDownBox(Polygon downBox);

	/** get the left collision box **/
	public abstract Polygon getLeftBox();

	/** set the left collision box **/
	public abstract void setLeftBox(Polygon leftBox);

	/** get the right collision box **/
	public abstract Polygon getRightBox();

	/** set the right collision box **/
	public abstract void setRightBox(Polygon rightBox);
	
	/** northern movement images **/
	public abstract Image[] northAnamation();

	/** north eastern movement images **/
	public abstract Image[] northEastAnamation();
	
	/** eastern movement images **/
	public abstract Image[] eastAnamation();

	/** south eastern movement images **/
	public abstract Image[] southEastAnamation();

	/** southern movement images **/
	public abstract Image[] southAnamation();

	/** south western movement images **/
	public abstract Image[] southWestAnamation();

	/** western movement images **/
	public abstract Image[] westAnamation();

	/** north western movement images **/
	public abstract Image[] northWestAnamation();

	/** northern melee attack box **/
	public abstract Polygon getNorthAttackBox();
	
	/** north eastern melee attack box **/
	public abstract Polygon getNorthEastAttackBox();
	
	/** eastern melee attack box **/
	public abstract Polygon getEastAttackBox();
	
	/** south eastern melee attack box **/
	public abstract Polygon getSouthEastAttackBox();
	
	/** southern melee attack box **/
	public abstract Polygon getSouthAttackBox();
	
	/** south western melee attack box **/
	public abstract Polygon getSouthWestAttackBox();
	
	/** western melee attack box **/
	public abstract Polygon getWestAttackBox();
	
	/** north western melee attack box **/
	public abstract Polygon getNorthWestAttackBox();

	/** characters idle images **/
	public abstract Image idleImages(int i);

	/** characters melee attack images **/
	public abstract Image attackImage(int i);

	/** direction of creeping line search pattern **/
	public abstract int getIntendedDirection();

	/** current x counter in the creeping line search pattern **/
	public abstract int getCLX();

	/** maximum value of the x counter **/
	public abstract int getMaxCLX();

	/** current y counter in the creeping line search pattern **/
	public abstract int getCLY();

	/** maximum value of the y counter **/
	public abstract int getMaxCLY();

	/** set the CL x counter **/
	public abstract void setCLX(int i);

	/** set the search pattern intended direction **/
	public abstract void setIntendedDirection(int i);

	/** set the CL y counter **/
	public abstract void setCLY(int i);

	/** attempt northern movement **/
	public abstract void moveNorth();
	
	/** attempt north eastern movement **/
	public abstract void moveNorthEast();
	
	/** attempt eastern movement **/
	public abstract void moveEast();
	
	/** attempt south eastern movement **/
	public abstract void moveSouthEast();
	
	/** attempt southern movement **/
	public abstract void moveSouth();

	/** attempt south western movement **/
	public abstract void moveSouthWest();

	/** attempt western movement **/
	public abstract void moveWest();

	/** attempt north western movement **/
	public abstract void moveNorthWest();

	/** get upper pathing collision box **/
	public abstract Polygon getUpPathBox();

	/** set upper pathing collision box **/
	public abstract void setUpPathBox(Polygon upPathBox);

	/** get bottom pathing collision box **/
	public abstract Polygon getDownPathBox();

	/** set bottom pathing collision box **/
	public abstract void setDownPathBox(Polygon downPathBox);

	/** get left pathing collision box **/
	public abstract Polygon getLeftPathBox();

	/** set left pathing collision box **/
	public abstract void setLeftPathBox(Polygon leftPathBox);

	/** get right pathing collision box **/
	public abstract Polygon getRighPathtBox();

	/** set right pathing collision box **/
	public abstract void setRighPathtBox(Polygon righPathtBox);
	

}