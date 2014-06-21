package physicalGameObjects;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

import logicalGameObjects.Setpiece;

import control.CoreClass;


/**
 * 
 * @author Thomas "Fucking" Capach
 *
 */
public class Bush implements Setpiece
{

	//the objects default image
	private Image thing = new ImageIcon("src/images/plant_undamaged.png").getImage();
	
	//images of the object damges
	private Image[] damage = {new ImageIcon("src/images/plant_cut.png").getImage()};

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
	private boolean tangible = false;
	private boolean flammable = true;
	private boolean interactive = true;
	private boolean destructive = true;
	private boolean explosive = false;

	String imageEffect = "";
	
	//collision box
	private Rectangle box; 	


	/**
	 * CONSTRUCTOR
	 * 
	 * @param i - objects id
	 */
	public Bush(int i)
	{

		x = 0;
	     y = 0;
		
		id = i;

	}

	
	/**
	 * get the objects x coordinate
	 * 
	 */
	public int getxPos() {
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
	public int getyPos() {
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
	public Image getPic() {
		return thing;
	}

	
	/**
	 * set the objects image
	 * 
	 */
	public void setPic(Image thing) {
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
	 * get the images of the object in its various damaged states
	 * 
	 * @return
	 */
	public Image[] getDamage() {
		return damage;
	}

	/**
	 * set the images of the object in its various damaged states
	 * 
	 * @param damage
	 */
	public void setDamage(Image[] damage) {
		this.damage = damage;
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
	 * the bush shakes when another object moves through its hitbox
	 * 
	 */
	public synchronized void collision()
	{

		Thread t = new Thread(){			
			public void run()
			{				
					
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
					thing = new ImageIcon("src/images/plant_shake_L.png").getImage();
					
					try {
						sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					thing = new ImageIcon("src/images/plant_shake_R.png").getImage();
					
					try {
						sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					thing = new ImageIcon("src/images/plant_undamaged.png").getImage();
					
								
			}			
		};	
		
		
		if(interactive == true)
		{
		t.start();
		}
		
		
		t = null;
		
		return;
		
	}
		
	
	/**
	 * when attacked by a melee attack the bush is sliced
	 * 
	 */
	public void attacked()
	{
		
		damageIndex = damageIndex + (CoreClass.mainCharacter.getAttack() - toughness);
		
		interactive = false;
		
			if(damageIndex < damage.length)
			{
				
				thing = damage[damageIndex];
				
			}
			else
			{
				
				thing = damage[damage.length - 1];
				
			}
		
	}
	
	/**
	 * bushes do not explode, or do they?
	 */
	public void explode()
	{
		
		
		
	}


	@Override
	public void setImageEffect(String s) {
		imageEffect = s;
		
	}


	@Override
	public String getImageEffect() {
		// TODO Auto-generated method stub
		return imageEffect;
	}


	@Override
	public Font getFont() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Rectangle getHitbox() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, width, height);
	}

}
