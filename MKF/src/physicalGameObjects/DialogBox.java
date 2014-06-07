package physicalGameObjects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class DialogBox
{
	
	private Image pic;
	
	private String s;
	
	private int x;
	private int y;

	public DialogBox(String s, int x, int y)
	{
		
		pic = new ImageIcon("src/images/dialog_box_1.png").getImage();
		
		this.s = s;
		this.x = x;
		this.y = y;
		
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
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
