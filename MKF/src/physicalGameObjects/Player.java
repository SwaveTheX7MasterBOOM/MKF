package physicalGameObjects;

import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import logicalGameObjects.Actor;

import control.CoreClass;



/**@author Andy Rodriguez **/
public class Player implements Actor {

	//image of the player
	private Image pic;
	
	//player's hitpoints/life
	private int hitpoints;
	
	//player's attack strength
	private int attack;
	
	//player's defense level
	private int defense;
	
	//player's speed
	private int speed;
	
	//player's "on screen" x position
	private int xPos;
	
	//player's "on screen" y position
	private int yPos;
	
	//player's logical map position
	private int mapX;
	private int mapY;
	
	//player's hit boxes
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
	
	//walking animations
	private Image[] north;
	private Image[] northEast;
	private Image[] east;
	private Image[] southEast;
	private Image[] south;
	private Image[] southWest;
	private Image[] west;
	private Image[] northWest;
	
	//player's hitbox
	private Rectangle hitbox;
	
	//direction player is moving: -1 for left, 0 for not moving, 1 for right
	private int direction; 
	
	//not used atm
	private int orientation = 0;
	
	//not used atm, for platform mode
	public static boolean isFalling = false;	

	
	//CONSTRUCTOR new player
	public Player(int x, int y){
		
		this.pic = new ImageIcon("src/images/idle_south.png").getImage();
		
		attack = 20;
		defense = 20;
		speed = 1;
		xPos = x;
		yPos = y;
		direction = 0;
		hitbox = new Rectangle(xPos - (pic.getWidth(null)/3), yPos, pic.getWidth(null)+((2*pic.getWidth(null))/3), pic.getHeight(null));	
		
		
		mapX = x;
		mapY = y;
		
		//set to 100 for now -- testing
		hitpoints = 100;
	}
	
	
	//CONSTRUCTOR saved player
	public Player(Image pic, int hp, int att, int def, int spd, int x, int y){
		this.pic = pic;
		attack = att;
		defense = def;
		speed = spd;
		xPos = x;
		yPos = y;
		direction = 0;
		hitbox = new Rectangle(xPos - (pic.getWidth(null)/3), yPos, pic.getWidth(null)+((2*pic.getWidth(null))/3), pic.getHeight(null));	
	
		mapX = x;
		mapY = y;
		
		//set to 100 for now -- testing
		hitpoints = 100;
	}

	/**
	 * get the x axis position of the character on the tile map
	 * 
	 * @return int 
	 */
	public int getMapX() {
		return mapX;
	}
	
	/**
	 * set the x axis position of the character on the tile map
	 * 
	 * @param mapX - int
	 */
	public void setMapX(int mapX) {
		this.mapX = mapX;
	}

	/**
	 * get the y axis position of the character on the tile map
	 * 
	 * @return int
	 */
	public int getMapY() {
		return mapY;
	}

	/**
	 * set the y axis position of the character on the tile map
	 * 
	 * @param mapY - int
	 */
	public void setMapY(int mapY) {
		this.mapY = mapY;
	}

	/**
	 * get the image of the the character
	 * 
	 * @return Image
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



	/*public Rectangle getBox() {
		
		box = new Rectangle(xPos, yPos + pic.getHeight(null) - 5, 5, pic.getWidth(null));
		
		return box;
	}



	public void setBox(Rectangle box) {
		this.box = box;
	}	
*/

	// |0 -1 \2 /3  ATM NOT USED
	public int getOrientation()
	{
		
		return orientation;
		
	}
	
	//ATM NOT USED	
	public void setOrientation(int i)
	{
		
		orientation = i;
		
	}
	
	/**
	 * get the northern hit box of characters melee attack
	 * 
	 * @return Polygon
	 */
	public Polygon getNorthAttackBox()
	{

		int startX = mapX - (idleImages(1).getWidth(null));
		
			int tempX[] = {startX, startX + attackImage(1).getWidth(null), startX + attackImage(1).getWidth(null), startX};
			int tempY[] = {mapY, mapY, mapY + idleImages(1).getHeight(null), mapY + idleImages(1).getHeight(null)};
		
				northAttackBox = new Polygon(tempX, tempY, tempX.length);
		
					return northAttackBox;
		
	}
	
	/**
	 * get the northeastern hit box of characters melee attack
	 * 
	 * @return Polygon
	 */
	public Polygon getNorthEastAttackBox()
	{

		int [] tempX = {mapX,  mapX + 10, mapX + 77,  mapX + 60};
		int [] tempY = {mapY + 15, mapY - 10, mapY +20, mapY + 45};
		
			northEastAttackBox = new Polygon(tempX, tempY, tempX.length);
		
				return northEastAttackBox;
		
	}
	
	/**
	 * get the eastern hit box of characters melee attack
	 * 
	 * @return Polygon
	 */
	public Polygon getEastAttackBox()
	{
		
		int startX = mapX + idleImages(3).getWidth(null) + 3;
		int startY = mapY;
		
			int [] tempX = {startX, startX + idleImages(3).getWidth(null), startX + idleImages(3).getWidth(null), startX};
			int [] tempY = {startY, startY, startY + attackImage(3).getHeight(null) - 4, startY + attackImage(3).getHeight(null) - 4};
		
				eastAttackBox = new Polygon(tempX, tempY, tempX.length);
		
					return eastAttackBox;
		
	}
	
	/**
	 * get the southeastern hit box of characters melee attack
	 * 
	 * @return Polygon
	 */
	public Polygon getSouthEastAttackBox()
	{

		int [] tempX = {(mapX - 52) + 40, (mapX - 32) + 40, (mapX + attackImage(8).getWidth(null) - 40) + 40, (mapX + attackImage(8).getWidth(null) - 60) + 40};
		int [] tempY = {(mapY + attackImage(8).getHeight(null) / 2 - 5) + 40, (mapY + attackImage(8).getHeight(null) - 15) + 40, (mapY + 15) + 40, (mapY - 5) + 40};
		
			southEastAttackBox = new Polygon(tempX, tempY, tempX.length);
		
				return southEastAttackBox;
		
	}
	
	/**
	 * get the southern hit box of characters melee attack
	 * 
	 * @return Polygon
	 */
	public Polygon getSouthAttackBox()
	{
		
		int startX = mapX - (idleImages(5).getWidth(null));
		int startY = mapY + attackImage(5).getHeight(null) - 5;
		
			int [] tempX = {startX, startX + attackImage(5).getWidth(null), startX + attackImage(5).getWidth(null), startX};;
			int [] tempY = {startY, startY, startY + attackImage(5).getHeight(null) / 2, startY + attackImage(5).getHeight(null) / 2};
		
				southAttackBox = new Polygon(tempX, tempY, tempX.length);
		
					return southAttackBox;
		
	}
	
	/**
	 * get the southwestern hit box of characters melee attack
	 * 
	 * @return Polygon
	 */
	public Polygon getSouthWestAttackBox()
	{
		
		int [] tempX = {(mapX) - 40,  (mapX + 10) - 40, (mapX + 77) - 40,  (mapX + 60) - 40};
		int [] tempY = {(mapY + 15) + 48, (mapY - 10) + 48, (mapY +20) + 48, (mapY + 45) + 48};
		
			southWestAttackBox = new Polygon(tempX, tempY, tempX.length);
		
				return southWestAttackBox;
		
	}
	
	/**
	 * get the western hit box of characters melee attack
	 * 
	 * @return Polygon
	 */
	public Polygon getWestAttackBox()
	{
		
		int startX = mapX - attackImage(7).getWidth(null)/2;
		int startY = mapY;
		
			int [] tempX = {startX, startX + idleImages(7).getWidth(null), startX + idleImages(7).getWidth(null), startX};
			int [] tempY = {startY, startY, startY + attackImage(7).getHeight(null) - 4, startY + attackImage(7).getHeight(null) - 4};
		
				westAttackBox = new Polygon(tempX, tempY, tempX.length);
		
					return westAttackBox;
		
	}
	
	/**
	 * get the northwestern hit box of characters melee attack
	 * 
	 * @return Polygon
	 */
	public Polygon getNorthWestAttackBox()
	{

		int [] tempX = {(mapX) - 52, (mapX + 20) - 52, (mapX + attackImage(8).getWidth(null)) - 40, (mapX + attackImage(8).getWidth(null) - 20) - 40};
		int [] tempY = {(mapY + attackImage(8).getHeight(null) / 2) - 5, (mapY + attackImage(8).getHeight(null)) - 15, (mapY + 20) - 5, (mapY) - 5};
		
			northWestAttackBox = new Polygon(tempX, tempY, tempX.length);
		
				return northWestAttackBox;
		
	}
	
	/**
	 * get the northern collision detection hitbox
	 * 
	 * @return Polygon
	 */
	public Polygon getUpBox()
	{		
		
		int [] tempX;
		int [] tempY;
		
			tempX = new int[] {mapX, mapX + 25, mapX + 25, mapX};
			tempY = new int[] {mapY + 30, mapY + 30, mapY + 35, mapY + 35,};
		
				upBox = new Polygon(tempX, tempY, tempX.length);
		
					return upBox;
		
	}

	/**
	 * set the northern collision detection hitbox
	 * 
	 * @param Polygon
	 */
	public void setUpBox(Polygon upBox) {
		this.upBox = upBox;
	}

	/**
	 * get the southern collision detection hitbox
	 * 
	 * @return Polygon
	 */
	public Polygon getDownBox()
	{
		
		int [] tempX;
		int [] tempY;
		
			tempX = new int[] {mapX, mapX + 25, mapX + 25, mapX};
			tempY = new int[] {mapY + 44, mapY + 44, mapY + 49, mapY + 49};
		
				downBox = new Polygon(tempX, tempY, tempX.length);
		
					return downBox;
	}

	/**
	 * set the southern collision detection hitbox
	 * 
	 * @param Polygon
	 */
	public void setDownBox(Polygon downBox) {
		this.downBox = downBox;
	}

	/**
	 * get the eastern collision detection hitbox
	 * 
	 * @return Polygon
	 */
	public Polygon getLeftBox()
	{

		int [] tempX;
		int [] tempY;	

			tempX = new int[] {mapX - 2, mapX + 3, mapX + 3, mapX - 2};
			tempY = new int[] {mapY + 32, mapY + 32, mapY + 47, mapY + 47,};
		
				leftBox = new Polygon(tempX, tempY, tempX.length);
		
					return leftBox;
					
	}

	/**
	 * set the eastern collision detection hitbox
	 * 
	 * @param Polygon
	 */
	public void setLeftBox(Polygon leftBox) {
		this.leftBox = leftBox;
	}

	/**
	 * get the eastern collision detection hitbox
	 * 
	 * @return Polygon
	 */
	public Polygon getRightBox()
	{

		int [] tempX;
		int [] tempY;
		
			tempX = new int[] {mapX + 22, mapX + 27, mapX + 27, mapX + 22};
			tempY = new int[] {mapY + 32, mapY + 32, mapY + 47, mapY + 47};
		
				rightBox = new Polygon(tempX, tempY, tempX.length);
		
					return rightBox;
		
	}

	/**
	 * set the western collision detection hitbox
	 * 
	 * @param Polygon
	 */
	public void setRightBox(Polygon rightBox) {
		this.rightBox = rightBox;
	}

	/**
	 * get the image array containing the northern movement animation
	 * 
	 * @return Image array
	 */
	public Image[] northAnamation()
	{
		
		north = new Image[]{new ImageIcon("src/images/north_one.png").getImage(), new ImageIcon("src/images/north_two.png").getImage(), 
							new ImageIcon("src/images/idle_north.png").getImage()};
		
			return north;
		
	}

	/**
	 * get the image array containing the northeastern movement animation
	 * 
	 * @return Image array
	 */
	public Image[] northEastAnamation()
	{
		
		northEast = new Image[]{new ImageIcon("src/images/north_east_one.png").getImage(), new ImageIcon("src/images/north_east_two.png").getImage(),
								new ImageIcon("src/images/idle_north_east.png").getImage()};
		
			return northEast;
		
	}

	/**
	 * get the image array containing the eastern movement animation
	 * 
	 * @return Image array
	 */
	public Image[] eastAnamation()
	{
		
		east = new Image[]{new ImageIcon("src/images/east_one.png").getImage(), new ImageIcon("src/images/east_two.png").getImage(),
						   new ImageIcon("src/images/idle_east.png").getImage()};
		
			return east;
		
	}

	/**
	 * get the image array containing the south-eastern movement animation
	 * 
	 * @return Image array
	 */
	public Image[] southEastAnamation()
	{
		
		southEast = new Image[]{new ImageIcon("src/images/south_east_one.png").getImage(), new ImageIcon("src/images/south_east_two.png").getImage(),
								new ImageIcon("src/images/idle_south_east.png").getImage()};
		
			return southEast;
		
	}

	/**
	 * get the image array containing the southern movement animation
	 * 
	 * @return Image array
	 */
	public Image[] southAnamation()
	{

		south = new Image[]{new ImageIcon("src/images/south_one.png").getImage(), new ImageIcon("src/images/south_two.png").getImage(),
							new ImageIcon("src/images/idle_south.png").getImage()};

		return south;
		
	}

	/**
	 * get the image array containing the south-western movement animation
	 * 
	 * @return Image array
	 */
	public Image[] southWestAnamation()
	{
		
		southWest= new Image[]{new ImageIcon("src/images/south_west_one.png").getImage(), new ImageIcon("src/images/south_west_two.png").getImage(),
							   new ImageIcon("src/images/idle_south_west.png").getImage()};
		
		return southWest;
		
	}

	/**
	 * get the image array containing the western movement animation
	 * 
	 * @return Image array
	 */
	public Image[] westAnamation()
	{
		
		west = new Image[]{new ImageIcon("src/images/west_one.png").getImage(), new ImageIcon("src/images/west_two.png").getImage(),
						   new ImageIcon("src/images/idle_west.png").getImage()};
		
		return west;
		
	}

	/**
	 * get the image array containing the north-western movement animation
	 * 
	 * @return Image array
	 */
	public Image[] northWestAnamation()
	{
		
		northWest = new Image[]{new ImageIcon("src/images/north_west_one.png").getImage(), new ImageIcon("src/images/north_west_two.png").getImage(),
								new ImageIcon("src/images/idle_north_west.png").getImage()};
		
		return northWest;
		
	}

	/**
	 * get the idle Image of the character
	 * 
	 * @param int - the direction the charter is facing
	 * @return Image 
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
	 * get the melee attack Image of the character
	 * 
	 * @param int - the direction the charter is facing
	 * @return Image
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

}

