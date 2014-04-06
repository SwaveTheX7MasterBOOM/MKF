package physicalGameObjects;

import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

import logicalGameObjects.Enemy;

import control.EnemyAI;
import control.WhenShitHits;

/**@author Andy Rodriguez */
public class MeleeEnemy extends Enemy {
	
	//image of the enemy
	private Image pic;
	
	//enemy's hitpoints/life
	private int hitpoints;
	
	//enemy's attack strength
	private int attack;
	
	//enemy's defense level
	private int defense;
	
	//enemy's speed
	private int speed;
	
	//enemy's x position
	private int xPos;
	
	//enemy's y position
	private int yPos;
	
	//direction enemy is moving
	private int direction; 
	
	//enemy's hitbox
	private Rectangle hitbox;
	
	//random generator
	private Random r = new Random();
	
	//the x and y counters for the creeping line search pattern
	private int CLX = 0;
	private int CLY = 0;
	
	//the maximum length of the x and y of the creeping line patrtern
	private int maxCLX;
	private int maxCLY;
	
	//the intended direction of the creeping line pattern (example: right to left, top to bottom)
	private int intendedDirection;
	
	//what fram of the walking animation the character is currently in
	private int anamationFrame = 0;
	
	//how many screen refreshes since last the animation frame was updated
	private int anamationUpdateCount = 0;

	//the collision boxes for the characters physical position
	private Polygon upBox;
	private Polygon downBox;
	private Polygon leftBox;
	private Polygon rightBox;
	
	//melee attack boxes
	private Polygon northAttackBox;
	private Polygon northEastAttackBox;
	private Polygon eastAttackBox;
	private Polygon southEastAttackBox;
	private Polygon southAttackBox;
	private Polygon southWestAttackBox;
	private Polygon westAttackBox;
	private Polygon northWestAttackBox;
	
	//collision boxes for the characters movements around other objects
	private Polygon upPathBox;
	private Polygon downPathBox;
	private Polygon leftPathBox;
	private Polygon rightPathBox;
	
	//walking animations
	private Image[] north;
	private Image[] northEast;
	private Image[] east;
	private Image[] southEast;
	private Image[] south;
	private Image[] southWest;
	private Image[] west;
	private Image[] northWest;


	public MeleeEnemy(int x, int y, int dir, int clx, int cly)
	{
		
		this.pic = new ImageIcon("src/images/idle_south.png").getImage();
		
		attack = 20;
		defense = 20;
		speed = 1;
		xPos = x;
		yPos = y;
		direction = 0;
		hitbox = new Rectangle(xPos - (pic.getWidth(null)/3), yPos, pic.getWidth(null)+((2*pic.getWidth(null))/3), pic.getHeight(null));	
		
	intendedDirection = dir;
	maxCLX = clx;
	maxCLY = cly;

		
		//set to 100 for now -- testing
		hitpoints = 100;

	}
	
	//SETTERS AND GETTERS
	/**
	 * Returns the image of the enemy
	 * @return ImageIcon pic
	 */
	public Image getPic() {	return pic;	}
	
	/**
	 * Sets the image of the enemy
	 * @param pic image of the enemy
	 */
	public void setPic(Image pic) {	this.pic = pic;	}
	
	/**
	 * Returns the enemy's current hitpoints
	 * @return hitpoints
	 */
	public int getHitpoints() {	return hitpoints;	}
	
	/**
	 * Sets the enemy's hitpoints
	 * @param hitpoints enemy's hp
	 */
	public void setHitpoints(int hitpoints) {	this.hitpoints = hitpoints;	}
	
	/**
	 * Returns the enemy's current attack strength
	 * @return attack
	 */
	public int getAttack() {	return attack;	}
	
	/**
	 * Sets the enemy's attack strength
	 * @param attack enemy's attack str
	 */
	public void setAttack(int attack) {	this.attack = attack;	}
	
	/**
	 * Returns the enemy's current defense level
	 * @return defense
	 */
	public int getDefense() {	return defense;	}
	
	/**
	 * Sets the enemy's defense level
	 * @param defense enemy's defense lvl
	 */
	public void setDefense(int defense) {	this.defense = defense;	}
	
	/**
	 * Returns the enemy's current speed
	 * @return speed
	 */
	public int getSpeed() {	return speed;	}
	
	/**
	 * Sets the enemy's speed
	 * @param speed enemy's speed
	 */
	public void setSpeed(int speed) {	this.speed = speed;	}
	
	/**
	 * Returns the enemy's current x position
	 * @return xPos
	 */
	public int getxPos() {	return xPos;	}
	
	/**
	 * Sets the enemy's current x position
	 * @param xPos x position
	 */
	public void setxPos(int xPos) {	this.xPos = xPos;	}
	
	/**
	 * Returns the enemy's current y position
	 * @return yPos
	 */
	public int getyPos() {	return yPos;	}
	
	/**
	 * Sets the enemy's current y position
	 * @param yPos y position
	 */
	public void setyPos(int yPos) {	this.yPos = yPos;	}
	
	/**
	 * Returns the direction the enemy is moving
	 * @return -1 for left, 1 for right, 0 for not moving
	 */
	public int getDirection() {	return direction;	}
	
	/**
	 * Sets the direction the enemy is moving
	 * @param direction -1 for left, 1 for right, 0 for not moving
	 */
	public void setDirection(int direction) {
		if(direction == 1 || direction == 0 || direction == -1)
			this.direction = direction;	
	}	

	/**
	 * Returns the enemy's side to side hitbox
	 * @return hitbox
	 */
	public Rectangle getHitbox() {
		return hitbox;
	}

	/**
	 * Sets the enemy's side to side hitbox
	 * @param hitbox the enemy's hitbox
	 */
	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}	

	
	/**
	 * get the characters upper hit box
	 */
	public Polygon getUpBox()
	{
					
		int [] tempX;
		int [] tempY;
		
			tempX = new int[] {xPos, xPos + 25, xPos + 25, xPos};
			tempY = new int[] {yPos + 10, yPos + 10, yPos + 15, yPos + 15};
		
				upBox = new Polygon(tempX, tempY, tempX.length);
			
					return upBox;
		
	}


	/**
	 * set the characters upper hit box
	 */
	public void setUpBox(Polygon upBox) {
		this.upBox = upBox;
	}


	/**
	 * get the characters bottom hit box
	 */
	public Polygon getDownBox()
	{

		int [] tempX;
		int [] tempY;

			tempX = new int[] {xPos, xPos + 25, xPos + 25, xPos};
			tempY = new int[] {yPos + 24, yPos + 24, yPos + 29, yPos + 29};
		
				downBox = new Polygon(tempX, tempY, tempX.length);
		
					return downBox;
					
	}


	/**
	 * set the characters bottom hit box
	 */
	public void setDownBox(Polygon downBox) {
		this.downBox = downBox;
	}


	/**
	 * get the characters left side hit box
	 */
	public Polygon getLeftBox()
	{
		
		int [] tempX;
		int [] tempY;

			tempX = new int[] {xPos - 2, xPos + 3, xPos + 3, xPos - 2};
			tempY = new int[] {yPos + 12, yPos + 12, yPos + 27, yPos + 27,};
		
				leftBox = new Polygon(tempX, tempY, tempX.length);
		
					return leftBox;
		
	}


	/**
	 * set the characters left side hit box
	 */
	public void setLeftBox(Polygon leftBox) {
		this.leftBox = leftBox;
	}


	/**
	 * get the characters right side hit box
	 */
	public Polygon getRightBox()
	{

		int [] tempX;
		int [] tempY;

			tempX = new int[] {xPos + 22, xPos + 27, xPos + 27, xPos + 22};
			tempY = new int[] {yPos + 12, yPos + 12, yPos + 27, yPos + 27};
		
				rightBox = new Polygon(tempX, tempY, tempX.length);
		
					return rightBox;
	}


	/**
	 * set the characters right side hit box
	 */
	public void setRightBox(Polygon rightBox) {
		this.rightBox = rightBox;
	}
	
	
	/**
	 * get all necessary animation images for north movement
	 */
	public Image[] northAnamation()
	{
		
		north = new Image[]{new ImageIcon("src/images/north_one.png").getImage(), new ImageIcon("src/images/north_two.png").getImage(), 
							new ImageIcon("src/images/idle_north.png").getImage()};
		
		return north;
		
	}



	/**
	 * get all necessary animation images for north east movement
	 */
	public Image[] northEastAnamation()
	{
		
		northEast = new Image[]{new ImageIcon("src/images/north_east_one.png").getImage(), new ImageIcon("src/images/north_east_two.png").getImage(),
								new ImageIcon("src/images/idle_north_east.png").getImage()};
		
		return northEast;
		
	}



	/**
	 * get all necessary animation images for east movement
	 */
	public Image[] eastAnamation()
	{
		
		east = new Image[]{new ImageIcon("src/images/east_one.png").getImage(), new ImageIcon("src/images/east_two.png").getImage(),
						   new ImageIcon("src/images/idle_east.png").getImage()};
		
			return east;
		
	}



	/**
	 * get all necessary animation images for south east movement
	 */
	public Image[] southEastAnamation()
	{
		
		southEast = new Image[]{new ImageIcon("src/images/south_east_one.png").getImage(), new ImageIcon("src/images/south_east_two.png").getImage(),
								new ImageIcon("src/images/idle_south_east.png").getImage()};
		
			return southEast;
		
	}



	/**
	 * get all necessary animation images for south movement
	 */
	public Image[] southAnamation()
	{

		south = new Image[]{new ImageIcon("src/images/south_one.png").getImage(), new ImageIcon("src/images/south_two.png").getImage(),
							new ImageIcon("src/images/idle_south.png").getImage()};

			return south;
		
	}



	/**
	 * get all necessary animation images for sout west movement
	 */
	public Image[] southWestAnamation()
	{
		
		southWest= new Image[]{new ImageIcon("src/images/south_west_one.png").getImage(), new ImageIcon("src/images/south_west_two.png").getImage(),
							   new ImageIcon("src/images/idle_south_west.png").getImage()};
		
			return southWest;
		
	}



	/**
	 * get all necessary animation images for west movement
	 */
	public Image[] westAnamation()
	{
		
		west = new Image[]{new ImageIcon("src/images/west_one.png").getImage(), new ImageIcon("src/images/west_two.png").getImage(),
						   new ImageIcon("src/images/idle_west.png").getImage()};
		
			return west;
		
	}



	/**
	 * get all necessary animation images for north west movement
	 */
	public Image[] northWestAnamation()
	{
		
		northWest = new Image[]{new ImageIcon("src/images/north_west_one.png").getImage(), new ImageIcon("src/images/north_west_two.png").getImage(),
								new ImageIcon("src/images/idle_north_west.png").getImage()};
		
			return northWest;
		
	}



	/**
	 * get the specified idle image
	 * 
	 * @param  int - the direction of the character
	 * @return  Image - the image of the idle character
	 */
	public Image idleImages(int i)
	{
		
		Image temp = null;
		
	
			switch(i)
			{
			
				case 1:
					
						temp = new ImageIcon("src/images/idle_north.png").getImage();
					
					break;
					
				case 2:
					
						temp = new ImageIcon("src/images/idle_north_east.png").getImage();
					
					break;
					
				case 3:
					
						temp = new ImageIcon("src/images/idle_east.png").getImage();
					
					break;
					
				case 4:
					
						temp = new ImageIcon("src/images/idle_south_east.png").getImage();
					
					break;
					
				case 5:
					
						temp = new ImageIcon("src/images/idle_south.png").getImage();
					
					break;
					
				case 6:
					
						temp = new ImageIcon("src/images/idle_south_west.png").getImage();
					
					break;
					
				case 7:
					
						temp = new ImageIcon("src/images/idle_west.png").getImage();
					
					break;
					
				case 8:
					
						temp = new ImageIcon("src/images/idle_north_west.png").getImage();
					
					break;
					
				default:
					
					
					
					break;
			
			}
		
		return temp;
		
	}
	
	/**
	 * get the specified character attack image
	 * 
	 * @param  int - the direction of the attack
	 * @return  Image - the image of the melee attack
	 */
	public Image attackImage(int i)
	{
		
		Image temp = null;
		
			switch(i)
			{
			
				case 1:
					
						temp = new ImageIcon("src/images/north_attack.png").getImage();
					
					break;
					
				case 2:
					
						temp = new ImageIcon("src/images/north_east_attack.png").getImage();
					
					break;
					
				case 3:
					
						temp = new ImageIcon("src/images/east_attack.png").getImage();
					
					break;
					
				case 4:
					
						temp = new ImageIcon("src/images/south_east_attack.png").getImage();
					
					break;
					
				case 5:
					
						temp = new ImageIcon("src/images/south_attack.png").getImage();
					
					break;
					
				case 6:
					
						temp = new ImageIcon("src/images/south_west_attack.png").getImage();
					
					break;
					
				case 7:
					
						temp = new ImageIcon("src/images/west_attack.png").getImage();
					
					break;
					
				case 8:
					
						temp = new ImageIcon("src/images/north_west_attack.png").getImage();
					
					break;
					
				default:
					
					
					
					break;
			
			}
			
			return temp;
		
	}


	/**
	 * attempt to move the character north
	 * 
	 * this method is currently different then the other movement methods.
	 * testing to make the movement more natural
	 */
	public void moveNorth()
	{

		boolean [] temp = WhenShitHits.Enemy2Setpiece(this, 1);
		
		
			if(temp[0] == false)
			{
	
				yPos = yPos - speed;
			
			}
			else if(temp[1] == false && temp[3] == true)
			{
			
				moveEast();
				
			}
			else if(temp[3] == false  && temp[1] == true)
			{
			
				moveWest();
				
			}
			else
			{
				
				if(intendedDirection == 1 || intendedDirection == 2)
				{
					
					if(intendedDirection == 1)
					{
					
						if(temp[1] == false)
						{
							
							moveEast();
							
						}
						else if(temp[3] == false)
						{
							
							moveWest();
							
						}
					
					}
					else
					{
						
						if(temp[1] == false)
						{
							
							moveWest();
							
						}
						else if(temp[3] == false)
						{
							
							moveEast();
							
						}
						
					}
					
				}
				else
				{
					
					if(temp[1] == false)
					{
						
						moveEast();
						
					}
					else if(temp[3] == false)
					{
						
						moveWest();
						
					}
					
				}
				//System.out.println("unable to proceed");
			}
		
	}	
	
	/**
	 * attempt to move north easterly
	 */
	public void moveNorthEast()
	{
		
		boolean [] temp = WhenShitHits.Enemy2Setpiece(this, 2);
	
		
			if(temp[0] == false && temp[1] == false)
			{
				
				xPos = xPos + speed;
				yPos = yPos - speed;
			
			}
			else if(temp[0] == true && temp[1] == false)
			{
				
				moveEast();
				
			}
			else if(temp[0] == false && temp[1] == true)
			{
				
				moveNorth();
				
			}
			else
			{
				
				System.out.println("unable to proceed");
				
			}

	}
	
	/**
	 * attempt to move east
	 */
	public void moveEast()
	{
		
		boolean [] temp = WhenShitHits.Enemy2Setpiece(this, 3);
		
		
			if(temp[1] == false)
			{
				
				xPos = xPos + speed;
	
			}
			else if(temp[0] == false)
			{
				
				moveNorth();
				
			}
			else if(temp[2] == false)
			{
				
				moveSouth();
				
			}
			else
			{
				
				System.out.println("unable to proceed");
				
			}
		
	}
	
	/**
	 * attempt to move south easterly
	 * 
	 * this method is attempting to test adding animation to this movement
	 */
	public void moveSouthEast()
	{
		
		boolean [] temp = WhenShitHits.Enemy2Setpiece(this, 4);

		
			if(temp[1] == false && temp[2] == false)
			{
				if(direction != 4)
				{
					
					direction = 4;
					anamationFrame  = 0;
					
				}
				
				if(anamationUpdateCount == 0)
				{
					
					pic = southEastAnamation()[anamationFrame];
					
						if(anamationFrame + 1 < southEastAnamation().length)
						{
							
							anamationFrame  = anamationFrame + 1;
						
						}
						else
						{
							
							anamationFrame  = 0;
							
						}
					
	
							anamationUpdateCount++;
					
				}
				else
				{
					
					if(anamationUpdateCount < 10)
					{
						
						anamationUpdateCount++;
						
					}
					else
					{
						
						anamationUpdateCount = 0;
						
					}
					
				}
								
					xPos = xPos + speed;
					yPos = yPos + speed;
					
			
			}
			else if(temp[1] == true && temp[2] == false)
			{
	
				moveSouth();
				
			}
			else if(temp[1] == false && temp[2] == true)
			{
				
				moveEast();
				
			}
			else
			{
				
				System.out.println("unable to proceed");
				
			}
		
	}
	
	/**
	 * attempt to move south
	 */
	public void moveSouth()
	{
		
		boolean [] temp = WhenShitHits.Enemy2Setpiece(this, 5);
		
			
			if(temp[2] == false)
			{
	
				yPos = yPos + speed;
			
			}
			else if(temp[1] == false)
			{
				
				moveEast();
				
			}
			else if(temp[3] == false)
			{
			
				moveWest();
			}
			else
			{
				
				System.out.println("unable to proceed");
				
			}
		
	}

	/**
	 * attempt to move south westerly
	 */
	public void moveSouthWest()
	{
		
		boolean [] temp = WhenShitHits.Enemy2Setpiece(this, 6);
		
		
			if(temp[2] == false && temp[3] == false)
			{
				
				xPos = xPos - speed;
				yPos = yPos + speed;
			
			}
			else if(temp[2] == true && temp[3] == false)
			{
	
				moveWest();
				
			}
			else if(temp[2] == false && temp[3] == true)
			{
		
				moveSouth();
				
			}
			else
			{
				
				System.out.println("unable to proceed");
				
			}
		
	}

	/**
	 * attempt to move west
	 */
	public void moveWest()
	{
		
		boolean [] temp = WhenShitHits.Enemy2Setpiece(this, 7);

		
			if(temp[3] == false)
			{
				
				xPos = xPos - speed;
		
			}
			else if(temp[0] == false)
			{
				
				moveNorth();
				
			}
			else if(temp[2] == false)
			{
				
				moveSouth();
				
			}
			else
			{
				
				System.out.println("unable to proceed");
				
			}
		
	}

	/**
	 * attempt to move north westerly
	 */
	public void moveNorthWest()
	{
		
		boolean [] temp = WhenShitHits.Enemy2Setpiece(this, 8);
			
			if(temp[0] == false && temp[3] == false)
			{
				
				xPos = xPos - speed;
				yPos = yPos - speed;
			
			}
			else if(temp[0] == true && temp[3] == false)
			{
				
				moveWest();
				
			}
			else if(temp[0] == false && temp[3] == true)
			{
				
				moveNorth();
				
			}
			else
			{
				
				System.out.println("unable to proceed");
				
			}
		
	}

	/**
	 * get the up pathing collision box
	 */
	public Polygon getUpPathBox()
	{
		
		int [] tempX;
		int [] tempY;
		
			tempX = new int[] {xPos - 10, xPos + 35, xPos + 35, xPos - 10};
			tempY = new int[] {yPos, yPos, yPos + 5, yPos + 5};
		
				upPathBox = new Polygon(tempX, tempY, tempX.length);
			
					return upPathBox;
		
	}

	/**
	 *  set the up collision box for pathing
	 */
	public void setUpPathBox(Polygon upPathBox)
	{
		
		this.upPathBox = upPathBox;
		
	}

	/**
	 * get the down pathing collision box
	 */
	public Polygon getDownPathBox()
	{

		int [] tempX;
		int [] tempY;

			tempX = new int[] {xPos - 10, xPos + 35, xPos + 35, xPos - 10};
			tempY = new int[] {yPos + 34, yPos + 34, yPos + 39, yPos + 39};
		
				downPathBox = new Polygon(tempX, tempY, tempX.length);
		
					return downPathBox;
		
	}

	/**
	 *  set the down collision box for pathing
	 */
	public void setDownPathBox(Polygon downPathBox)
	{
		
		this.downPathBox = downPathBox;
		
	}

	/**
	 * get the left pathing collision box
	 */
	public Polygon getLeftPathBox()
	{
		
		int [] tempX;
		int [] tempY;

			tempX = new int[] {xPos - 12, xPos - 7, xPos - 7, xPos - 12};
			tempY = new int[] {yPos + 2, yPos + 2, yPos + 37, yPos + 37};
		
				leftPathBox = new Polygon(tempX, tempY, tempX.length);
		
					return leftPathBox;
					
	}

	/**
	 *  set the left collision box for pathing
	 */
	public void setLeftPathBox(Polygon leftPathBox)
	{
		
		this.leftPathBox = leftPathBox;
		
	}

	/**
	 * get the right pathing collision box
	 */
	public Polygon getRighPathtBox()
	{
		
		int [] tempX;
		int [] tempY;

			tempX = new int[] {xPos + 32, xPos + 37, xPos + 37, xPos + 32};
			tempY = new int[] {yPos + 2, yPos + 2, yPos + 37, yPos + 37};
		
				rightPathBox = new Polygon(tempX, tempY, tempX.length);
		
					return rightPathBox;
		
	}

	/**
	 * set the right collision box for pathing
	 */
	public void setRighPathtBox(Polygon righPathtBox)
	{
		
		this.rightPathBox = righPathtBox;
		
	}
	
	/**
	 * get the collision box for the north melee attack
	 */
	public Polygon getNorthAttackBox()
	{

		int startX = xPos - (idleImages(1).getWidth(null));

			int tempX[] = {startX, startX + attackImage(1).getWidth(null), startX + attackImage(1).getWidth(null), startX};
			int tempY[] = {yPos, yPos, yPos + idleImages(1).getHeight(null), yPos + idleImages(1).getHeight(null)};
		
				northAttackBox = new Polygon(tempX, tempY, tempX.length);
		
					return northAttackBox;
		
	}
	
	/**
	 * get the collision box for the north east melee attack
	 */
	public Polygon getNorthEastAttackBox()
	{

		int [] tempX = {xPos,  xPos + 10, xPos + 77,  xPos + 60};
		int [] tempY = {yPos + 15, yPos - 10, yPos +20, yPos + 45};
		
			northEastAttackBox = new Polygon(tempX, tempY, tempX.length);
		
				return northEastAttackBox;
		
	}
	
	/**
	 * get the collision box for the east melee attack
	 */
	public Polygon getEastAttackBox()
	{
		
		int startX = xPos + idleImages(3).getWidth(null) + 3;
		int startY = yPos;
		
			int [] tempX = {startX, startX + idleImages(3).getWidth(null), startX + idleImages(3).getWidth(null), startX};
			int [] tempY = {startY, startY, startY + attackImage(3).getHeight(null) - 4, startY + attackImage(3).getHeight(null) - 4};
		
				eastAttackBox = new Polygon(tempX, tempY, tempX.length);
		
					return eastAttackBox;
		
	}
	
	/**
	 * get the collision box for the south east melee attack
	 */
	public Polygon getSouthEastAttackBox()
	{

		int [] tempX = {(xPos - 52) + 40, (xPos - 32) + 40, (xPos + attackImage(8).getWidth(null) - 40) + 40, (xPos + attackImage(8).getWidth(null) - 60) + 40};
		int [] tempY = {(yPos + attackImage(8).getHeight(null) / 2 - 5) + 40, (yPos + attackImage(8).getHeight(null) - 15) + 40, (yPos + 15) + 40, (yPos - 5) + 40};
		
			southEastAttackBox = new Polygon(tempX, tempY, tempX.length);
		
				return southEastAttackBox;
		
	}
	
	/**
	 * get the collision box for the south melee attack
	 */
	public Polygon getSouthAttackBox()
	{
		
		int startX = xPos - (idleImages(5).getWidth(null));
		int startY = yPos + attackImage(5).getHeight(null) - 5;
		
			int [] tempX = {startX, startX + attackImage(5).getWidth(null), startX + attackImage(5).getWidth(null), startX};;
			int [] tempY = {startY, startY, startY + attackImage(5).getHeight(null) / 2, startY + attackImage(5).getHeight(null) / 2};
		
				southAttackBox = new Polygon(tempX, tempY, tempX.length);
		
					return southAttackBox;
		
	}
	
	/**
	 * get the collision box for the south west melee attack
	 */
	public Polygon getSouthWestAttackBox()
	{
			
		int [] tempX = {(xPos) - 40,  (xPos + 10) - 40, (xPos + 77) - 40,  (xPos + 60) - 40};
		int [] tempY = {(yPos + 15) + 48, (yPos - 10) + 48, (yPos +20) + 48, (yPos + 45) + 48};
		
			southWestAttackBox = new Polygon(tempX, tempY, tempX.length);
		
				return southWestAttackBox;
		
	}
	
	/**
	 * get the collision box for the west melee attack
	 */
	public Polygon getWestAttackBox()
	{
		
		int startX = xPos - attackImage(7).getWidth(null)/2;
		int startY = yPos;// + idleImages(7).getWidth(null) / 2;
		
			int [] tempX = {startX, startX + idleImages(7).getWidth(null), startX + idleImages(7).getWidth(null), startX};
			int [] tempY = {startY, startY, startY + attackImage(7).getHeight(null) - 4, startY + attackImage(7).getHeight(null) - 4};
		
				westAttackBox = new Polygon(tempX, tempY, tempX.length);
		
					return westAttackBox;
		
	}
	
	/**
	 * get the collision box for the north west melee attack
	 */
	public Polygon getNorthWestAttackBox()
	{
		
		int [] tempX = {(xPos) - 52, (xPos + 20) - 52, (xPos + attackImage(8).getWidth(null)) - 40, (xPos + attackImage(8).getWidth(null) - 20) - 40};
		int [] tempY = {(yPos + attackImage(8).getHeight(null) / 2) - 5, (yPos + attackImage(8).getHeight(null)) - 15, (yPos + 20) - 5, (yPos) - 5};
		
			northWestAttackBox = new Polygon(tempX, tempY, tempX.length);
		
				return northWestAttackBox;
		
	}

	/**
	 * Artificial intelligence for the character
	 * 
	 * currently just puts the character in a endless creeping line search pattern
	 */
	public void aI()
	{
		//
		EnemyAI.searchCL(this);
		
	}

	/**
	 * get the characters current intended direction
	 */
	public int getIntendedDirection()
	{
		
		return intendedDirection;
		
	}

	/**
	 * get the current x counter for the creeping line pattern
	 */
	public int getCLX()
	{
		
		return CLX;
		
	}

	/**
	 * get the maximum x counter for the creeping line pattern
	 */
	public int getMaxCLX()
	{
		
		return maxCLX;
		
	}

	/**
	 * get the current y counter for the creeping line pattern
	 */
	public int getCLY()
	{
		
		return CLY;
		
	}

	/**
	 * get the maximum y counter for the creeping line pattern
	 */
	public int getMaxCLY()
	{
		
		return maxCLY;
		
	}

	/**
	 *  set the x counter for the creeping line search pattern
	 * 
	 * @param - int
	 */
	public void setCLX(int i)
	{
		
		CLX = i;
		
	}

	/**
	 * set the intended direction this character is to travel (example: left to right, top to bottom)
	 * 
	 * @param int - 1 = left to right, 2 = right to left, 3 = top to bottom, 4 = bottom to top
	 */
	public void setIntendedDirection(int i)
	{
		
		intendedDirection = i;
		
	}

	/**
	 * set the y counter for the creeping line search pattern
	 * 
	 * @param int 
	 */
	public void setCLY(int i)
	{
		
		CLY = i;
		
	}

	/**
	 * NOT IN USE
	 * fully stop this characters movements until told otherwise
	 * 
	 */
	public void stopMoving()
	{
		
		
		
	}
	

}