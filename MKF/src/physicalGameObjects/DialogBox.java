package physicalGameObjects;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import control.CoreClass;

import logicalGameObjects.Actor;
import logicalGameObjects.CursorCompanion;
import logicalGameObjects.DrawableObjects;
import logicalGameObjects.PopUps;

public class DialogBox implements PopUps
{
	
	private Image pic;
	
	private List<String> sl = new ArrayList<String>();
	
	private String s;
	
	private int x;
	
	private int y;
	
	private boolean alive = true;
	
	Object obj;
	
	Font font;
	
	
	private ActionListener upTimeAL = new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{

			kill();	
			
		}
		
	};
	
	private Timer t;

	private DrawableObjects parent;

	
	/*public DialogBox(String s, int x, int y)
	{
		
		pic = new ImageIcon("src/images/dialog_box_1.png").getImage();

			this.s = s;
			this.x = x;
			this.y = y;
			
				t = new Timer(s.length() /5 * 300, upTimeAL);
				
					t.start();
		
		
	}*/
	public DialogBox(DrawableObjects obj, String s)
	{
		
		pic = new ImageIcon("src/images/dialog_box_1.png").getImage();

		font = obj.getFont();
		
		setParent(obj);
		
		this.s = s;
		this.obj = obj;
		
			t = new Timer(s.length() /5 * 300, upTimeAL);
			
				t.start();
		
	}
	
	
	public List<String> getSl() {
		return sl;
	}



	public void setSl(List<String> sl) {
		this.sl = sl;
	}



	public boolean isAlive()
	{
		
		return alive;
		
	}
	
	public Image getPic() {
		return pic;
	}

	public void setPic(Image pic) {
		this.pic = pic;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public int getX() {
		
		if(obj instanceof Actor)
		{
			return ((Actor) obj).getxPos() + -(CoreClass.mapUpperLeftOffset.getX()) - (CoreClass.onScreenTile[0].getX() * 102);
		}
		else if(obj instanceof CursorCompanion)
		{
			return CoreClass.mightyMouse.getMouseCoordinate().getX();
		}
		return 0;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		
		if(obj instanceof Actor)
		{
			return ((Actor) obj).getyPos() + -(CoreClass.mapUpperLeftOffset.getY()) - (CoreClass.onScreenTile[0].getY() * 102);
		}
		else if(obj instanceof CursorCompanion)
		{
			return CoreClass.mightyMouse.getMouseCoordinate().getY();
		}
		return 0;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	/**
	 * 
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;


		result = prime * result + ((s == null) ? 0 : s.hashCode());


		return result;
	}

	/**
	 * 
	 */
	public boolean equals(Object obj) {

		DialogBox other = (DialogBox) obj;
		
		
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;

		return true;
		
	}
	
	/**
	 * 
	 */
	public void kill()
	{
	
		CoreClass.removeNotification(this);

			t.stop();
		
				alive = false;
		
	}


	@Override
	public Font getFont() {
		// TODO Auto-generated method stub
		return font;
	}


	@Override
	public void setFont(Font f) {
		// TODO Auto-generated method stub
		
	}


	public DrawableObjects getParent() {
		return parent;
	}


	public void setParent(DrawableObjects parent) {
		this.parent = parent;
	}
	
}
