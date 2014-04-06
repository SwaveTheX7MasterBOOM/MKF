package control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import physicalGameObjects.MeleeEnemy;
import physicalGameObjects.Player;

import logicalGameObjects.Coordinate;
import logicalGameObjects.Enemy;
import logicalGameObjects.HealthBar;
import logicalGameObjects.Setpiece;


/**
 * The JPanel where all the magic happens as well as the actionlistener game loop
 * 
 * 
 * @author Thomas "Fucking" Capach
 *
 */
public class Renderer extends JPanel implements ActionListener
{
	//eclipse said to add this, idk
	private static final long serialVersionUID = 1L;
	
	//game loop timer
	private static Timer tempus;

	/**
	 * Constructor
	 */
	public Renderer()
	{
		
		this.setFocusable(true);
		
			tempus = new Timer(1, this);

				this.addKeyListener(CoreClass.InCa);
			
					tempus.start();	

	}

	
	/**
	 * the game loop, calls the repainting
	 */
	public void actionPerformed(ActionEvent arg0)
	{	
		
		repaint();
		
	}
	

	/**
	 * Draws the object
	 */
    public void paint(Graphics g)
    { 
    	
 try
 {
    	
    	super.paint(g);
        
		//graphics object needs to cast as a 2d object so the edges of the object are smooth
		if (g instanceof Graphics2D)
		{
			//casting graphics object as a 2d object
			Graphics2D d = (Graphics2D) g;
			
				
				d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
		}
		
		//
		int xOff = 0;
		int yOff = 0;
		
		int cameraOffX = -(CoreClass.mapUpperLeftOffset.getX());
		int cameraOffY = -(CoreClass.mapUpperLeftOffset.getY());
		
		Coordinate tempUL = CoreClass.onScreenTile[0];
		Coordinate tempBR = CoreClass.onScreenTile[1];

		
		//draw the tile map
		for (int y = tempUL.getY(); y <= tempBR.getY(); y++)
		{
			
			xOff = 0;
			
			for (int x = tempUL.getX(); x <= tempBR.getX(); x++)
			{
				
				try
				{
					
					switch(CoreClass.getCurrentLevel().getMap()[y][x])
					{
					
						case 0:
						
								g.drawImage(CoreClass.getCurrentLevel().getTile(0), xOff + cameraOffX, yOff + cameraOffY, null);
							
							break;
								
						case 1:
						
								g.drawImage(CoreClass.getCurrentLevel().getTile(1), xOff + cameraOffX, yOff + cameraOffY, null);
							
							break;
								
						case 2:
						
								g.drawImage(CoreClass.getCurrentLevel().getTile(2), xOff + cameraOffX, yOff + cameraOffY, null);
							
							break;
							
						case 3:
						
								g.drawImage(CoreClass.getCurrentLevel().getTile(3), xOff + cameraOffX, yOff + cameraOffY, null);
								
							break;
								
						case 4:
						
								g.drawImage(CoreClass.getCurrentLevel().getTile(4), xOff + cameraOffX, yOff + cameraOffY, null);
							
							break;
							
						case 5:
						
								g.drawImage(CoreClass.getCurrentLevel().getTile(5), xOff + cameraOffX, yOff + cameraOffY, null);
							
							break;
							
						case 6:
						
							g.drawImage(CoreClass.getCurrentLevel().getTile(6), xOff + cameraOffX, yOff + cameraOffY, null);
								
							break;
							
							
						case 7:
						
								g.drawImage(CoreClass.getCurrentLevel().getTile(7), xOff + cameraOffX, yOff + cameraOffY, null);
							
							break;
							
						case 8:
						
								g.drawImage(CoreClass.getCurrentLevel().getTile(8), xOff + cameraOffX, yOff + cameraOffY, null);
							
							break;
							
							
						case 9:
						
								g.drawImage(CoreClass.getCurrentLevel().getTile(9), xOff + cameraOffX, yOff + cameraOffY, null);
							
							break;
							
							
						case 10:
						
								g.drawImage(CoreClass.getCurrentLevel().getTile(10), xOff + cameraOffX, yOff + cameraOffY, null);
							
							break;
							
							
						case 11:
						
								g.drawImage(CoreClass.getCurrentLevel().getTile(11), xOff + cameraOffX, yOff + cameraOffY, null);
							
							break;
							
							
						case 12:
						
								g.drawImage(CoreClass.getCurrentLevel().getTile(12), xOff + cameraOffX, yOff + cameraOffY, null);
							
							break;
							
							
						case 13:
						
								g.drawImage(CoreClass.getCurrentLevel().getTile(13), xOff + cameraOffX, yOff + cameraOffY, null);
							
							break;
							
							
						case 14:
						
								g.drawImage(CoreClass.getCurrentLevel().getTile(14), xOff + cameraOffX, yOff + cameraOffY, null);
							
							break;
							
							
							
							default:
								

								break;
					
					}

				}
				catch(Exception e)
				{
					
					//e.printStackTrace();
					
				}
				
				xOff = xOff + 102;
				
			}
			
			yOff = yOff + 102;
			
		}
        
		 //CONSIDER MOVINE OPERATION LIKE THIS TO CORECLASS AS IT IS JUST PICKING WHAT NEEDS RENDERING AND NOT EXPLICTLY RENDERING IT
		List<Object> everything = new ArrayList<Object>();
        
		everything.add(CoreClass.mainCharacter);
        
		
        //add all NEEDED objects from a level to the above list everything that will be sorted based on the Y axis
		for (int yy = tempUL.getY() - 1; yy <= tempBR.getY() + 1; yy++)
		{
			
			for (int xx = tempUL.getX() - 1; xx <= tempBR.getX() + 1; xx++)
			{
				
				ArrayList<Coordinate> al = new ArrayList<Coordinate> (CoreClass.getCurrentLevel().getPlaces().keySet());

					for(Coordinate c:al)
					{
						
						if(c.equals(new Coordinate(xx, yy)))
						{

							everything.addAll(CoreClass.getCurrentLevel().getPlaces().get(c));

						}
						
					}

			}
			
		}
		
		everything.addAll(CoreClass.getCurrentEn());
		
		//Sort the list to prepare the object to be drawn on screen based on the Y axis position
		Collections.sort(everything, new YAxisSort());
		
		// draws objects, the enemies and the player
		for(Object s:everything)
		{
		
			
			if(s instanceof Setpiece)
			{
	
				g.drawImage(new ImageIcon(((Setpiece) s).getThing()).getImage(), ((Setpiece) s).getX() + cameraOffX - (tempUL.getX() * 102), ((Setpiece) s).getY() + cameraOffY - (tempUL.getY() * 102), null);
				
				//just for the visualization of the hit box for debugging
				g.setColor(Color.RED);
				g.fillRect((int)((Setpiece) s).getBox().getX()+ cameraOffX - (tempUL.getX() * 102), ((int)((Setpiece) s).getBox().getY()) + cameraOffY - (tempUL.getY() * 102), (int)((Setpiece) s).getBox().getWidth(), (int)((Setpiece) s).getBox().getHeight());
			
				
			}
			else if(s instanceof Player)
			{
				
		        //draw the character
		        g.drawImage(CoreClass.mainCharacter.getPic(), CoreClass.mainCharacter.getxPos(), CoreClass.mainCharacter.getyPos(), null);
		        
		   /*     g.setColor(Color.BLACK);
		        g.fillPolygon(CoreClass.mainCharacter.getEastAttackBox());
		       g.fillPolygon(CoreClass.mainCharacter.getNorthAttackBox());
		        g.fillPolygon(CoreClass.mainCharacter.getSouthAttackBox());
		        g.fillPolygon(CoreClass.mainCharacter.getWestAttackBox());
		        g.fillPolygon(CoreClass.mainCharacter.getNorthEastAttackBox());
		        g.fillPolygon(CoreClass.mainCharacter.getSouthWestAttackBox());
		        g.fillPolygon(CoreClass.mainCharacter.getNorthWestAttackBox());
		        g.fillPolygon(CoreClass.mainCharacter.getSouthEastAttackBox());
		        
		        */
		        /*
		        just for the visualization of the hit box for debugging 
		        "up box"
				g.setColor(Color.BLUE);
				g.fillPolygon(CoreClass.mainCharacter.getUpBox());
				
				g.setColor(Color.GREEN);
				g.fillPolygon(CoreClass.mainCharacter.getDownBox());
				
				g.setColor(Color.ORANGE);
				g.fillPolygon(CoreClass.mainCharacter.getLeftBox());
				
				g.setColor(Color.YELLOW);
				g.fillPolygon(CoreClass.mainCharacter.getRightBox());
				
				g.fillRect(CoreClass.mainCharacter.getUpBox().x+ cameraOffX - (tempUL.getX() * 102), CoreClass.mainCharacter.getUpBox().y + cameraOffY - (tempUL.getY() * 102), (int)CoreClass.mainCharacter.getUpBox().getWidth(), (int)CoreClass.mainCharacter.getUpBox().getHeight());
				
				//"down box"
				g.setColor(Color.GREEN);
				g.fillRect(CoreClass.mainCharacter.getDownBox().x+ cameraOffX - (tempUL.getX() * 102), CoreClass.mainCharacter.getDownBox().y+ cameraOffY - (tempUL.getY() * 102), (int)CoreClass.mainCharacter.getDownBox().getWidth(), (int)CoreClass.mainCharacter.getDownBox().getHeight());
					
				//"left box"
				g.setColor(Color.ORANGE);
				g.fillRect(CoreClass.mainCharacter.getLeftBox().x+ cameraOffX - (tempUL.getX() * 102), CoreClass.mainCharacter.getLeftBox().y+ cameraOffY - (tempUL.getY() * 102), (int)CoreClass.mainCharacter.getLeftBox().getWidth(), (int)CoreClass.mainCharacter.getLeftBox().getHeight());
				
				//"right box"
				g.setColor(Color.YELLOW);
				g.fillRect(CoreClass.mainCharacter.getRightBox().x+ cameraOffX - (tempUL.getX() * 102), CoreClass.mainCharacter.getRightBox().y+ cameraOffY - (tempUL.getY() * 102), (int)CoreClass.mainCharacter.getRightBox().getWidth(), (int)CoreClass.mainCharacter.getRightBox().getHeight());
				*/
			}
			else if(s instanceof Enemy)
			{
				//System.out.println("painting enimy");
				g.drawImage(((Enemy) s).getPic(), ((Enemy) s).getxPos()  + cameraOffX - (tempUL.getX() * 102), ((Enemy) s).getyPos() + cameraOffY - (tempUL.getY() * 102), null);
				
			/*	g.setColor(Color.BLUE);
				g.fillPolygon(((Enemy) s).getUpBox());
				
				g.setColor(Color.GREEN);
				g.fillPolygon(((Enemy) s).getDownBox());
				
				g.setColor(Color.ORANGE);
				g.fillPolygon(((Enemy) s).getLeftBox());
				
				g.setColor(Color.YELLOW);
				g.fillPolygon(((Enemy) s).getRightBox());*/
				
			/*	
				g.setColor(Color.BLUE);
				g.fillPolygon(((Enemy) s).getUpPathBox());
				
				g.setColor(Color.GREEN);
				g.fillPolygon(((Enemy) s).getDownPathBox());
				
				g.setColor(Color.ORANGE);
				g.fillPolygon(((Enemy) s).getLeftPathBox());
				
				g.setColor(Color.YELLOW);
				g.fillPolygon(((Enemy) s).getRighPathtBox());
				*/
				
			}
					
		}
		
		//draw Health bar
		
		float black = 0;
		float opacity = 1;
		
		//if the character is drawn under the health bar, make the health bar transparent
		if(CoreClass.mainCharacter.getxPos() <= CoreClass.healthBar.getxPos() + CoreClass.healthBar.getFullWidth()+5 && CoreClass.mainCharacter.getyPos() <= CoreClass.healthBar.getyPos() + CoreClass.healthBar.getHeight() + 5 ){
			opacity = (float).35;
		}
		CoreClass.healthBar.setOpacity(opacity);
		
		//draw the black background
		g.setColor(new Color(black, black, black, opacity));
		g.fillRect(CoreClass.healthBar.getxPos()-5, CoreClass.healthBar.getyPos()-5, CoreClass.healthBar.getFullWidth()+10, CoreClass.healthBar.getHeight()+10);
		
		//draw the actual health bar
		g.setColor(CoreClass.healthBar.getHealthColor());
	
		g.fillRect(CoreClass.healthBar.getxPos(), CoreClass.healthBar.getyPos(), CoreClass.healthBar.getWidth(), CoreClass.healthBar.getHeight());
		
 }
catch(Exception e)
{
	
	
	
}

	}
    
	
	/**
	 *starts the game loop
	 */
	public void startTimer()
	{		
		
		tempus.start();	
		
			CoreClass.gameON = true;
		
	}
	
	
	/**
	 * stops the game loop
	 */
	public void StopTimer()
	{		
		
		tempus.stop();
		
			CoreClass.gameON = false;
		
	}
	
}