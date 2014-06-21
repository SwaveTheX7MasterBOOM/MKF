package physicalGameObjects;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import logicalGameObjects.Actor;
import logicalGameObjects.Coordinate;
import logicalGameObjects.Enemy;

import control.CoreClass;
import control.EnemyAI;
import control.Mathariffic;
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
	
	//sight collision box
	private Polygon seeingBox;
	
	//center point of the characters actual position
	private Coordinate actualCenter;
	
	//how large the sight collision object will be
	private int sightRange = 600;

	//if the character has been heard
	private boolean heard = false;
	
	// how many times the character may look around after hearing something before resuming normal search pattern
	//IDEA:  add functionality to up the number times this character looks around and shorten the duration they take looking in that direction to simulate jumpiness
	private int alerted = 0;
	
	//prevent actions being taken till timer is up
	private boolean stopAndLook = false;
	
	//sets stop and look to false after the timer has run out
	private ActionListener turnAL = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			
			stopAndLook = false;
			
			
		}
		
	};
	
	//whether to update move to next frame in the animation 
	private boolean animate = true;
	
	//set animate to true after timer run out
	private ActionListener animationnAL = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			
			animate = true;
			
		}
		
	};
	
	
	
	private ActionListener attackAL = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			
			attacking = false;

			switch(direction)
			{
			
				case 1:
					

						
						
							attackTimer.stop();
								
						
						xPos = xPos - (idleImages(1).getWidth(null) / 2) + (attackImage(1).getWidth(null) / 2);
						
						yPos=yPos + (attackImage(1).getHeight(null) / 2);
							
						setPic(idleImages(1));
							
						
				
					break;
			
				case 2:


					
					attackTimer.stop();
							
					
		
					setyPos(getyPos() + (attackImage(1).getHeight(null) / 2));
						
					setPic(idleImages(2));
						
					
			
				break;
					
				case 3:
	

					
					attackTimer.stop();
							

					setyPos(getyPos() + (attackImage(1).getHeight(null) / 2));
						
					setPic(idleImages(3));
						
					
			
				break;
					
				case 4:
	
					
					
					attackTimer.stop();
							

					setPic(idleImages(4));
						
					
			
				break;
					
				case 5:
					

					
					attackTimer.stop();
							
					
					setxPos(getxPos() + (idleImages(1).getWidth(null)));
					setPic(idleImages(5));
						
					
			
					
					break;
					
				case 6:

					
					attackTimer.stop();
							
					
					setxPos(getxPos() + (attackImage(1).getWidth(null) / 2));
		
					setPic(idleImages(6));
					
			
				break;
					
				case 7:
					

					
					attackTimer.stop();
							
					
					setxPos(getxPos() + (attackImage(1).getWidth(null) / 2));
					
					setyPos(getyPos() + (attackImage(1).getHeight(null) / 2));
						
					setPic(idleImages(7));
						
					
			
				break;
					
				case 8:
					

					
					attackTimer.stop();
							
					
					setxPos(getxPos() + (attackImage(1).getWidth(null) / 2));
					
					setyPos(getyPos() + (attackImage(1).getHeight(null) / 2));
						
					setPic(idleImages(8));
						
					
			
				break;
			}
			

			
			
		}
		
	};
	
	
	//how long the character takes looking before moving to the next step
	private Timer turnTimer = new Timer(1000, turnAL);
	
	//interval between animation frames
	private int animationInterval = 300;
	
	//how long in between the various animation frames
	private Timer animationTimer = new Timer(animationInterval, animationnAL);

	private String imageEffect = "";

	String objective = "searching";
	
	Object target = null;
	
	boolean attacking = false;

	private Timer attackTimer = new Timer(200, attackAL);

	private Font font;
	
	

	public MeleeEnemy(int x, int y, int dir, int clx, int cly)
	{
		
		this.pic = new ImageIcon("src/images/idle_south.png").getImage();
		
		attack = 25;
		defense = 20;
		speed = 1;
		xPos = x;
		yPos = y;
		direction = 5;
		hitbox = new Rectangle(xPos - (pic.getWidth(null)/3), yPos, pic.getWidth(null)+((2*pic.getWidth(null))/3), pic.getHeight(null));	
		
	intendedDirection = dir;
	maxCLX = clx;
	maxCLY = cly;

		
		//set to 100 for now -- testing
		hitpoints = 100;
		
		
		
		InputStream myStream = null;
		try {
			myStream = new BufferedInputStream(new FileInputStream("Assets/fish.ttf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT , myStream);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		font = font.deriveFont(Font.BOLD, 18);
		
		  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		       ge.registerFont(font);
		

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
	 * Sets the direction the enemy is moving and makes the character face that direction
	 * @param direction
	 */
	public void setDirection(int direction) {
		
			this.direction = direction;	
			
				pic = idleImages(direction);
			
			
	}	

	/**
	 * Returns the characters hitbox
	 * @return hitbox
	 */
	public Rectangle getHitbox() {
		hitbox = new Rectangle(xPos, yPos, pic.getWidth(null), pic.getHeight(null));
		
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
	 * get all necessary animation images for south west movement
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
				
				//if not already moving in this direction
				if(direction != 1)
				{
					
					direction = 1;
					
					anamationFrame  = 0;
					
					pic = northAnamation()[anamationFrame];
					
					animate = false;
					
						if(animationTimer.isRunning())
						{
							
							animationTimer.restart();
						
						}
						else
						{
							
							animationTimer.start();
							
						}
					
				}
				else//already moving this way
				{
					
					if(animate == true)
					{
						
						if((anamationFrame + 1) < northAnamation().length)
						{
							
							anamationFrame++;
							
						}
						else
						{
							
							anamationFrame = 0;
							
						}
						
							pic = northAnamation()[anamationFrame];
						
							animate = false;
				
					}

					
				}

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
				
				if(direction != 2)
				{
					
					direction = 2;
					anamationFrame  = 0;
					
					pic = northEastAnamation()[anamationFrame];
					
					animate = false;
					
					if(animationTimer.isRunning())
					{
						
						animationTimer.restart();
					
					}
					else
					{
						
						animationTimer.start();
						
					}
					
				}
				else
				{
					
					if(animate == true)
					{
						
						if((anamationFrame + 1) < northEastAnamation().length)
						{
							
							anamationFrame++;
							
						}
						else
						{
							
							anamationFrame = 0;
							
						}
						
						pic = northEastAnamation()[anamationFrame];
						
						animate = false;
				
					}

					
				}
				
				
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
				
				if(direction != 3)
				{
					
					direction = 3;
					anamationFrame  = 0;
					
					pic = eastAnamation()[anamationFrame];
					
					animate = false;
					
					if(animationTimer.isRunning())
					{
						
						animationTimer.restart();
					
					}
					else
					{
						
						animationTimer.start();
						
					}
					
				}
				else
				{
					
					if(animate == true)
					{
						
						if((anamationFrame + 1) < eastAnamation().length)
						{
							
							anamationFrame++;
							
						}
						else
						{
							
							anamationFrame = 0;
							
						}
						
						pic = eastAnamation()[anamationFrame];
						
						animate = false;
				
					}

					
				}
				
				
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
					
					pic = southEastAnamation()[anamationFrame];
					
					animate = false;
					
					if(animationTimer.isRunning())
					{
						
						animationTimer.restart();
					
					}
					else
					{
						
						animationTimer.start();
						
					}
					
				}
				else
				{
					
					if(animate == true)
					{
						
						if((anamationFrame + 1) < southEastAnamation().length)
						{
							
							anamationFrame++;
							
						}
						else
						{
							
							anamationFrame = 0;
							
						}
						
						pic = southEastAnamation()[anamationFrame];
						
						animate = false;
				
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
				
				if(direction != 5)
				{
					
					direction = 5;
					anamationFrame  = 0;
					
					pic = southAnamation()[anamationFrame];
					
					animate = false;
					
					if(animationTimer.isRunning())
					{
						
						animationTimer.restart();
					
					}
					else
					{
						
						animationTimer.start();
						
					}
					
				}
				else
				{
					
					if(animate == true)
					{
						
						if((anamationFrame + 1) < southAnamation().length)
						{
							
							anamationFrame++;
							
						}
						else
						{
							
							anamationFrame = 0;
							
						}
						
						pic = southAnamation()[anamationFrame];
						
						animate = false;
				
					}

					
				}
				
				
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
				
				if(direction != 6)
				{
					
					direction = 6;
					anamationFrame  = 0;
					
					pic = southWestAnamation()[anamationFrame];
					
					animate = false;
					
					if(animationTimer.isRunning())
					{
						
						animationTimer.restart();
					
					}
					else
					{
						
						animationTimer.start();
						
					}
					
				}
				else
				{
					
					if(animate == true)
					{
						
						if((anamationFrame + 1) < southWestAnamation().length)
						{
							
							anamationFrame++;
							
						}
						else
						{
							
							anamationFrame = 0;
							
						}
						
						pic = southWestAnamation()[anamationFrame];
						
						animate = false;
				
					}

					
				}
				
				
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
				
				if(direction != 7)
				{
					
					direction = 7;
					anamationFrame  = 0;
					
					pic = westAnamation()[anamationFrame];
					
					animate = false;
					
					if(animationTimer.isRunning())
					{
						
						animationTimer.restart();
					
					}
					else
					{
						
						animationTimer.start();
						
					}
					
				}
				else
				{
					
					if(animate == true)
					{
						
						if((anamationFrame + 1) < westAnamation().length)
						{
							
							anamationFrame++;
							
						}
						else
						{
							
							anamationFrame = 0;
							
						}
						
						pic = westAnamation()[anamationFrame];
						
						animate = false;
				
					}

					
				}
				
				
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
				
				if(direction != 8)
				{
					
					direction = 8;
					anamationFrame  = 0;
					
					pic = northWestAnamation()[anamationFrame];
					
					animate = false;
					
					if(animationTimer.isRunning())
					{
						
						animationTimer.restart();
					
					}
					else
					{
						
						animationTimer.start();
						
					}
					
				}
				else
				{
					
					if(animate == true)
					{
						
						if((anamationFrame + 1) < northWestAnamation().length)
						{
							
							anamationFrame++;
							
						}
						else
						{
							
							anamationFrame = 0;
							
						}
						
						pic = northWestAnamation()[anamationFrame];
						
						animate = false;
				
					}

					
				}
				
			
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
	 * 
	 */
	public void aI()
	{
		if(attacking == false)
		{
		
			List<Object> see = WhenShitHits.sightTest(this);

			if(alerted == 0)
			{
			heard = WhenShitHits.hearingTest(this);
			}	
			
			
			if(objective.equals("searching"))
			{
			
				//*i don't see anyone nor do i hear anything moving in the distance*
				if(see.size() == 0 && heard == false)
				{
					//System.out.println("searching");
					EnemyAI.searchCL(this);
		
				}
				else if(see.size() > 0)//*i see something*
				{
					//System.out.println("I see you");
					//chase
					
					
					 
					//the main character is priority, next would be the closest actor in the list
					 for(Object o:see)
					 {
						 int dist = 0;
					 
						  if(o instanceof Player)
						  {
							  
							  target = (Actor) o;
							  System.out.println(target);
							  CoreClass.addNotification(new DialogBox(this, "There IT is!!!!"));
							  
							  objective = "hunting";
							  
							/*	int dist1 = (int) Mathariffic.distanceBetween2Coordinates(this.getActualCenter(), target.getActualCenter());
								 
							 	if(dist1 > 100)//move towards
							 	{
							 		
							 		EnemyAI.pathTo(this,target);
							 		
							 	}
							 	else if(dist1 <= 100 && dist1 > 50)//ranged stun attack
							 	{
							 		
							 		//System.out.println("range stun**********************");
							 		CoreClass.addNotification(new DialogBox(this, "Stunn attack"));
							 	}
							 	else//melee attack
							 	{
							 	
							 		//System.out.println("melee ++++++++++++++++++++++++++");
							 		CoreClass.addNotification(new DialogBox(this, "melee"));
							 	}*/
							  
							  
							  	break;
						  
						  }
						  else if(o instanceof Enemy)
						  {
						  
							 if(Mathariffic.distanceBetween2Coordinates(this.getActualCenter(), ((Enemy) o).getActualCenter()) > dist)
							 {
								 
								 target = (Actor) o;
								// CoreClass.addNotification(new DialogBox(this, "Hey Buddy!"));
								 
								 
							 }
							
						  
								int dist1 = (int) Mathariffic.distanceBetween2Coordinates(this.getActualCenter(), ((Actor) target).getActualCenter());
								 
							 	if(dist1 > 100)//move towards
							 	{
							 		
							 		EnemyAI.pathTo(this,(Actor) target);
							 		
							 	}
							 	else if(dist1 <= 100 && dist1 > 50)//ranged stun attack
							 	{
							 		
							 		//System.out.println("range stun**********************");
							 		CoreClass.addNotification(new DialogBox(this, "Hows is it going?"));
							 		
							 		EnemyAI.pathTo(this,(Actor) target);
							 	}
							 	else//melee attack
							 	{
							 	
							 		//System.out.println("melee ++++++++++++++++++++++++++");
							 		CoreClass.addNotification(new DialogBox(this, "I hate this place."));
							 		
							 		objective = "converse";
							 		
							 	}
							 
						  }
					  
					  }
					 

					
				}	
				else if(see.size() == 0 && heard == true)//hear something moving near me
				{
					//System.out.println("I hear you");
					//*look around*
					CoreClass.addNotification(new DialogBox(this, "Who's out there?"));
					
					 if(stopAndLook == false)
					 {
						 //System.out.println("stop and look = false");
					 	if(alerted == 0)
					 	{
					 		EnemyAI.randomLookAround(this);
							alerted = r.nextInt(3)+1;
							stopAndLook = true;
							turnTimer.start();
					 	
					 	}
					 	else
					 	{
					 	
					 	alerted--;
					 	EnemyAI.randomLookAround(this);
					 	turnTimer.start();
					 	
					 	}
				
					 	//System.out.println(alerted);
					}
	
					
					
				}
				
			}
			else if(objective.equals("hunting"))
			{
				
				if(see.contains(target))
				{
					
					if(true)
					{
					
						int dist1 = (int) Mathariffic.distanceBetween2Coordinates(this.getActualCenter(), ((Actor) target).getActualCenter());
						 
					 	if(dist1 > 35)//move towards
					 	{
					 		
					 		EnemyAI.pathTo(this,(Actor) target);
					 		
					 	}
					 	/*else if(dist1 <= 100 && dist1 > 50)//ranged stun attack
					 	{
					 		
					 		//System.out.println("range stun**********************");
					 		CoreClass.addNotification(new DialogBox(this, "Stunn attack"));
					 		
					 		EnemyAI.pathTo(this,(Actor) target);
					 		
					 	}*/
					 	else//melee attack
					 	{
					 	
					 		//System.out.println("melee ++++++++++++++++++++++++++");
					 		CoreClass.addNotification(new DialogBox(this, "melee"));
					 		meleeAttack();
					 		
					 	}
				
					}
					else
					{
						
						
						//pick up?
						
					}
			 	
				}
				else
				{
					


					 if(stopAndLook == false)
					 {
						 //System.out.println("stop and look = false");
					 	if(alerted == 0)
					 	{
					 		
					 		CoreClass.addNotification(new DialogBox(this, "think you can lose me "));
					 		
					 		EnemyAI.pathTo(this,(Actor) target);
					 		
					 		
							alerted = r.nextInt(4)+2;
							stopAndLook = true;
							turnTimer.start();
					 	
					 	}
					 	else
					 	{
					 	
					 	alerted--;
					 	
					 	
					 	
					 		if(alerted == 0)
					 		{
					 			CoreClass.addNotification(new DialogBox(this, "guess i lost the little shit "));
					 			objective = "searching";
					 			stopAndLook = false;
					 		}
					 		else
					 		{
					 			EnemyAI.pathTo(this,(Actor) target);
					 			turnTimer.start();
					 		}
					 	
					 	
					 	}
				
					 	//System.out.println(alerted);
					}
					
				}
				
			}
			else if(objective.equals("converse"))
			{
				CoreClass.addNotification(new DialogBox(this, "have a conversation"));
				objective = "searching";
			}
	
			
		}	
	}
		
	


	private void meleeAttack() 
	{
		if(attacking == false)
		{
			
			
		attacking = true;

				switch(this.getDirection())
				{
				
					case 1:
						
							this.setxPos(this.getxPos() + (this.idleImages(1).getWidth(null) / 2) - (this.attackImage(1).getWidth(null) / 2));
							
							this.setyPos(this.getyPos() - (this.attackImage(1).getHeight(null) / 2));
								
							this.setPic(this.attackImage(1));
							
							
								attackTimer.start();
									
								WhenShitHits.enemyMeleeAttackPlayer(this, getNorthAttackBox());

								
							
					
						break;
				
					case 2:

						this.setyPos(this.getyPos() - (this.attackImage(1).getHeight(null) / 2));
							
						this.setPic(this.attackImage(2));
						
						attackTimer.start();
								
						
						WhenShitHits.enemyMeleeAttackPlayer(this, getNorthEastAttackBox());

							
						
				
					break;
						
					case 3:
		
						this.setyPos(this.getyPos() - (this.attackImage(1).getHeight(null) / 2));
							
						this.setPic(this.attackImage(3));
						
						attackTimer.start();
								

						WhenShitHits.enemyMeleeAttackPlayer(this, getEastAttackBox());
						
				
					break;
						
					case 4:
		
						this.setPic(this.attackImage(4));
						
						attackTimer.start();
								

						WhenShitHits.enemyMeleeAttackPlayer(this, getSouthEastAttackBox());
							
						
				
					break;
						
					case 5:
						
						this.setxPos(this.getxPos() - (this.idleImages(1).getWidth(null)));
							
						this.setPic(this.attackImage(5));
						
						attackTimer.start();
								
		
						WhenShitHits.enemyMeleeAttackPlayer(this, getSouthAttackBox());
						
				
						
						break;
						
					case 6:
						
						this.setxPos(this.getxPos() - (this.attackImage(1).getWidth(null) / 2));

						this.setPic(this.attackImage(6));
						
						attackTimer.start();
								
						WhenShitHits.enemyMeleeAttackPlayer(this, getSouthWestAttackBox());
				
					break;
						
					case 7:
						
						this.setxPos(this.getxPos() - (this.attackImage(1).getWidth(null) / 2));
						
						this.setyPos(this.getyPos() - (this.attackImage(1).getHeight(null) / 2));
							
						this.setPic(this.attackImage(7));
						
						attackTimer.start();
								
						
						WhenShitHits.enemyMeleeAttackPlayer(this, getWestAttackBox());
							
						
				
					break;
						
					case 8:
						
						this.setxPos(this.getxPos() - (this.attackImage(1).getWidth(null) / 2));
						
						this.setyPos(this.getyPos() - (this.attackImage(1).getHeight(null) / 2));
							
						this.setPic(this.attackImage(8));
						
						attackTimer.start();
								
						WhenShitHits.enemyMeleeAttackPlayer(this, getNorthWestAttackBox());

							
						
				
					break;
				}
				

		}

		
	}

	/**
	 * 
	 */
	public Coordinate getActualCenter()
	{
		
		actualCenter = new Coordinate(((xPos + 22 + xPos + 3) / 2),((yPos + 24 + yPos + 15) / 2));
		
			return actualCenter;
		
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


	/**
	 * get the seeing collision box
	 * 
	 * @return Polygon
	 */
	public Polygon getseeingBox()
	{
		
		int tempX[] = null;
		int tempY[] = null;
		
		int temp = (int) (sightRange * 0.75);
		int temp2 = 10;
		int temp3 = (int) (sightRange * 0.250);
		Coordinate aC = getActualCenter();
		int aCx = aC.getX();
		int aCy = aC.getY();
		
		
		
			switch(direction)
			{
			
				case 1:
					
						tempX = new int[]{aCx, (aCx + temp), (aCx - temp)};
						tempY = new int[]{aCy, (aCy - sightRange), (aCy - sightRange)};
					
					break;
					
				case 2:
					
						tempX = new int[]{aCx, (aCx + (sightRange / temp2)), (aCx + sightRange + temp3)};
						tempY = new int[]{aCy, (aCy - sightRange - temp3), (aCy - (sightRange / temp2))};
					
					break;
					
				case 3:
					
						tempX = new int[]{aCx, (aCx + sightRange), (aCx + sightRange)};
						tempY = new int[]{aCy, (aCy + temp), (aCy - temp)};
					
					break;
					
				case 4:
					
						tempX = new int[]{aCx, (aCx + (sightRange / temp2)), (aCx + sightRange + temp3)};
						tempY = new int[]{aCy, (aCy + sightRange + temp3), (aCy + (sightRange / temp2))};
					
					break;
					
				case 5:
					
						tempX = new int[]{aCx, (aCx + temp), (aCx - temp)};
						tempY = new int[]{aCy, (aCy + sightRange), (aCy + sightRange)};
					
					break;
					
				case 6:
					
						tempX = new int[]{aCx, (aCx - (sightRange / temp2)), (aCx - sightRange - temp3)};
						tempY = new int[]{aCy, (aCy + sightRange + temp3), (aCy + (sightRange / temp2))};
					
					break;
					
				case 7:
					
						tempX = new int[]{aCx, (aCx - sightRange), (aCx - sightRange)};
						tempY = new int[]{aCy, (aCy + temp), (aCy - temp)};
					
					break;
					
				case 8:
					
						tempX = new int[]{aCx, (aCx - (sightRange / temp2)), (aCx - sightRange - temp3)};
						tempY = new int[]{aCy, (aCy - sightRange - temp3), (aCy - (sightRange / temp2))};
					
					break;
					
				default:
					
						System.out.println(direction+" we have not directi0n");
					
					break;
			
			}

				seeingBox = new Polygon(tempX, tempY, 3);
					
					return seeingBox;
		
	}

	/**
	 * set the seeing collision box
	 * 
	 * @param Polygon
	 */
	public void setseeingBox(Polygon seeingBox)
	{
		
		this.seeingBox = seeingBox;
	
	}

	/**
	 * get how far the character can see
	 */
	public int getSightRange()
	{
		
		return sightRange;
		
	}

	/**
	 * set how far the character can see
	 */
	public void setSightRange(int sightRange)
	{
		
		this.sightRange = sightRange;
		
	}

	/**
	 * 
	 * @return
	 */
	public int getAlerted() {
		return alerted;
	}

	/**
	 * 
	 * @param alerted
	 */
	public void setAlerted(int alerted) {
		this.alerted = alerted;
	}
	
	
	@Override
	public void setImageEffect(String s) {
		imageEffect = s;
		
	}


	@Override
	public String getImageEffect() {
		
		return imageEffect;
	}

	@Override
	public void attacked(int a, boolean[] hits)
	{
		if((a - defense) > 0)
		{
			if(hitpoints - (a - defense) > 0)
			{
				
				hitpoints = hitpoints - (a - defense);
			
			}
			else
			{
				
				CoreClass.removeEnemy(this);
				
			}
		}
		
		
		System.out.println(hitpoints + " hp - ENEMY");
		
	}

	@Override
	public Font getFont() {
		// TODO Auto-generated method stub
		return font;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return pic.getWidth(null);
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return pic.getHeight(null);
	}
	

}