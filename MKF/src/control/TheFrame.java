package control;


import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
	
	
	//private ActiveRenderer r;
	

	/**
	 * Constructor
	 */
	public TheFrame()
	{
				
		//this.setUndecorated(true);
		
		this.setIgnoreRepaint(true);
		
		this.setVisible(true);
		
		this.setTitle("Monkey Knife Fight");
		


		
	//	this.setResizable(false);

		this.createBufferStrategy(2);
		
	//	r = new ActiveRenderer();
		
	//	this.getContentPane().add(r);
		
		this.addKeyListener(CoreClass.KeyIn);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);
		
		this.setCursor(CoreClass.coolkit.createCustomCursor(new ImageIcon("src/images/CC8.png").getImage(), new Point(0,0), "Cursor"));
		
	}


	
}
