package physicalGameObjects;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

import logicalGameObjects.Setpiece;

/**
 * 
 * @author Thomas "Fucking" Capach
 *
 */
public class GumTree implements Setpiece
{
	//the objects default image
	private Image thing = new ImageIcon("src/images/GumTree.png").getImage();

	//level's numerical identification
	private int id;
	
	// x/y  position of object
	private int x;
	private int y;
	
	//Dimensions of the image
	private int height = thing.getHeight(null);
	private int width = thing.getWidth(null);
	
	//what stage of damage the object is in
	private int damageIndex = 0;
	
	//Resistance to damage
	private int toughness = 0;

	//object characteristics
	private boolean tangible = true;
	private boolean flammable = true;
	private boolean interactive = false;
	private boolean destructive = false;
	private boolean explosive = false;

	//collision box
	private Rectangle box; 	

/**
 * CONSTRUCTOR
 * 
 * @param i - int
 */
	public GumTree(int i)
	{

		x = 0;
		y = 0;
		
		id = i;

	}

	
	/**
	 * get the objects x coordinate
	 * 
	 */
	public int getX() {
		return x;
	}

	
	/**
	 *  set the objects x coordinate
	 * 
	 */
	public void setX(int x) {
		this.x = x - (width/2);
	}

	
	/**
	 *  get the objects y coordinate
	 * 
	 */
	public int getY() {
		return y;
	}

	
	/**
	 *  set the objects y coordinate
	 * 
	 */
	public void setY(int y) {
		this.y = y - (height);
	}

	
	/**
	 *  get the objects height
	 * 
	 */
	public int getHeight() {
		return height;
	}

	
	/**
	 * get the objects width
	 * 
	 */
	public int getWidth() {
		return width;
	}

	
	/**
	 * get the objects image
	 * 
	 */
	public Image getThing() {
		return thing;
	}

	
	/**
	 * set the objects image
	 * 
	 */
	public void setThing(Image thing) {
		this.thing = thing;
	}

	
	/**
	 * set the objects height
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	
	/**
	 * set the objects width
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	
	/**
	 * get the objects hitbox
	 * 
	 */
	public Rectangle getBox() {

		box = new Rectangle(x + width/2 - 12, y + height - 10, 10, 10);

		return box;
	}

	
	/**
	 * set the objects hitbox
	 * 
	 */
	public void setBox(Rectangle box) {
		this.box = box;
	}


	/**
	 * does not have damaged states
	 * 
	 * @return
	 */
	public Image[] getDamage() {
		return null;
	}

	/**
	 * does not have damaged states
	 * 
	 * @param damage
	 */
	public void setDamage(Image[] damage) {
		
	}

	
	/**
	 * get the objects id
	 * 
	 */
	public int getId() {
		return id;
	}

	
	/**
	 * set the objects id
	 * 
	 */
	public void setId(int id) {
		this.id = id;
	}
	

	/**
	 * get the objects damage index
	 * 
	 */
	public int getDamageIndex() {
		return damageIndex;
	}

	
	/**
	 * set the objects damage index
	 * 
	 */
	public void setDamageIndex(int damageIndex) {
		this.damageIndex = damageIndex;
	}

	
	/**
	 * get the objects toughness
	 * 
	 */
	public int getToughness() {
		return toughness;
	}

	
	/**
	 * set the objects toughness
	 * 
	 */
	public void setToughness(int toughness) {
		this.toughness = toughness;
	}

	
	/**
	 * get the objects tangibility
	 * 
	 */
	public boolean isTangible() {
		return tangible;
	}

	
	/**
	 * set the objects tangibility
	 * 
	 */
	public void setTangible(boolean tangible) {
		this.tangible = tangible;
	}

	
	/**
	 * get the objects flammablity
	 * 
	 */
	public boolean isFlammable() {
		return flammable;
	}

	
	/**
	 * set the objects flammablity
	 * 
	 */
	public void setFlammable(boolean flammable) {
		this.flammable = flammable;
	}

	
	/**
	 * get whether the object is interactive
	 * 
	 */
	public boolean isInteractive() {
		return interactive;
	}
	

	/**
	 * set whether the object is interactive
	 * 
	 */
	public void setInteractive(boolean interactive) {
		this.interactive = interactive;
	}

	
	/**
	 * get whether the object is destructible
	 * 
	 */
	public boolean isDestructive() {
		return destructive;
	}
	

	/**
	 * set whether the object is destructible
	 * 
	 */
	public void setDestructive(boolean destructive) {
		this.destructive = destructive;
	}

	
	/**
	 * get whether the object explodes when destroyed
	 * 
	 */
	public boolean isExplosive() {
		return explosive;
	}
	

	/**
	 * set whether the object explodes when destroyed
	 * 
	 */
	public void setExplosive(boolean explosive) {
		this.explosive = explosive;
	}

	
	/**
	 *not used
	 */
	public void collision()
	{
		
		
	}
	
	
	/**
	 * not used
	 */
	public void attacked()
	{
		
		
		
	}
	
	
	/**
	 * not used
	 */
	public void explode()
	{
		
		
		
	}
	
	

}
