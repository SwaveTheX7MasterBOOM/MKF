package logicalGameObjects;

import java.awt.Image;
import java.awt.Rectangle;


/**
 * 
 * @author Thomas "Fucking" Capach
 *
 */
public interface Setpiece
{
	
	//GETTERS AND SETTERS
	public int getX();

	public void setX(int x);

	public int getY();

	public void setY(int y);

	public int getHeight();
	
	public int getWidth();
	
	public Image getThing();
	
	public void setThing(Image thing);
	
	public void setHeight(int height);
	
	public void setWidth(int width);
	
	public Rectangle getBox();
	
	public void setBox(Rectangle box);
	
	public boolean isTangible();

	public boolean isFlammable();

	public boolean isInteractive();

	public boolean isDestructive();

	public int getId();

	public void setId(int id);

	public int getDamageIndex();

	public void setDamageIndex(int damageIndex);
	
	public int getToughness();

	public void setToughness(int toughness);

	public boolean isExplosive();

	public void setExplosive(boolean explosive);

	public void setTangible(boolean tangible);

	public void setFlammable(boolean flammable);

	public void setInteractive(boolean interactive);

	public void setDestructive(boolean destructive);
	
	
	// what happens when something passes through the object, IF and object can pass through the object
	public void collision();
	
	// what happens when an attack hits the object, IF anything
	public void attacked();
	
	// what happens IF  the object explodes. IF it can.
	public void explode();
	

}
