package control;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;



/**
 * DAT JFrame
 * 
 * @author Thomas "Fucking" Capach
 *
 */
public class TheFrame extends JFrame
{
	
	/*
	 * eclipse said to add this, idk
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Renderer r;
	

	/**
	 * Constructor
	 */
	public TheFrame()
	{
		

	    
		
		this.setCursor(CoreClass.coolkit.createCustomCursor(new ImageIcon("src/images/CC8.png").getImage(), new Point(0,0), "Cursor"));
		
		
		
  
		this.setVisible(true);

		this.createBufferStrategy(2);
		
		r = new Renderer();
		
		this.getContentPane().add(r);
		this.addKeyListener(CoreClass.KeyIn);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}


	
}
