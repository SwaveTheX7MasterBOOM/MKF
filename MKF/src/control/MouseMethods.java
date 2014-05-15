package control;


import java.awt.MouseInfo;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import logicalGameObjects.Coordinate;

/**
 * 
 * @author Cobrah ComMANder
 *
 */
public class MouseMethods extends MouseInputAdapter
{
	
	//the only instantiated object
	private static MouseMethods instance = new MouseMethods();
	
	/**
	 * Singleton accessor the only means of getting the instantiated object.
	 * @return One and only InputManager object.
	 */
	public static MouseMethods getInstance()
	{
		
		return instance;
		
	}
	
	/**
	 * 
	 */
	public void mouseMoved(MouseEvent arg0)
	{
		
		System.out.println("moved");
		
	}
	
	/**
	 * 
	 */
	public void mouseDragged(MouseEvent arg0)
	{
		
		System.out.println("dragged");
		
	}

	/**
	 * 
	 */
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("click at - (" + getMouseCoordinate().getX() + "," + getMouseCoordinate().getY() + ")");
		
	}

	/**
	 * 
	 */
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("entered");
		
	}

	/**
	 * 
	 */
	public void mouseExited(MouseEvent arg0) {
		System.out.println("exited");
		
	}

	/**
	 * 
	 */
	public void mousePressed(MouseEvent arg0) {
		System.out.println("pressed - (" + getMouseCoordinate().getX() + "," + getMouseCoordinate().getY() + ")");
	}

	/**
	 * 
	 */
	public void mouseReleased(MouseEvent arg0) {
		System.out.println("released - (" + getMouseCoordinate().getX() + "," + getMouseCoordinate().getY() + ")");
		
	}
	
	/**
	 * get where the mouse cursor is on screen
	 * 
	 * @return Coordinate
	 */
	public Coordinate getMouseCoordinate()
	{

		return new Coordinate(MouseInfo.getPointerInfo().getLocation());
		
	}
	

}
