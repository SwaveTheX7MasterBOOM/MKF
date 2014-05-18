package logicalGameObjects;

import java.awt.Point;






/**
 * 
 * This class creates objects to hold (x,y) coordinate data with the int data type
 * 
 * @author Thomas "Cobra Commander" Capach
 *
 */
public class Coordinate
{

	//x an y axis positions
	private int x = 0;
	private int y = 0;
	
	/**
	 * The coordinate constructor takes the x and y axis coordinates
	 * 
	 * @param x - x axis coordinate
	 * @param y - y axis coordinate
	 */
	public Coordinate(int x, int y)
	{
		
		this.x = x;
		this.y = y;
		
	}
	
	public Coordinate(Point p)
	{
		
		this.x = p.x;
		this.y = p.y;
		
			p = null;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Point getPoint()
	{
		return new Point(x, y);
		
	}

	/**
	 * get the x axis coordinate
	 * 
	 * @return - int
	 */
	public int getX() {
		return x;
	}

	/**
	 * set the x axis coordinate
	 * 
	 * @param x - int
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * get the y axis coordinate
	 * 
	 * @return - int
	 */
	public int getY() {
		return y;
	}

	/**
	 *  set the y axis coordinate
	 * 
	 * @param y - int
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * compare coordinate objects
	 */
	public boolean equals(Object other)
	{
		
		Coordinate c = (Coordinate) other;
		
		
		if(x == c.getX() && y == c.getY())
		{
			
			return true;
			
		}
		else
		{
			
			return false;
		
		}
		
	}
	
	/**
	 * graphical representation of a coordinate object
	 */
	public String toString()
	{
		
		return "Coordinate at: X = " + getX() + " Y = " + getY();
		
	}	
	
}
