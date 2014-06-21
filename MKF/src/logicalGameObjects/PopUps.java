package logicalGameObjects;

import java.awt.Font;
import java.awt.Image;
import java.util.List;




public interface PopUps {

	
	public List<String> getSl();



	public void setSl(List<String> sl);



	public boolean isAlive();
	
	public Image getPic();

	public void setPic(Image pic);

	public String getS();

	public void setS(String s);

	public void setX(int x);

	public void setY(int y);

	public void setAlive(boolean alive);

	
	/**
	 * 
	 */
	public void kill();



	public int getX();



	public int getY();



	public Font getFont();
	public void setFont(Font f);

}
