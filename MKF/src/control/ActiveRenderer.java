package control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import physicalGameObjects.Player;

import logicalGameObjects.Coordinate;
import logicalGameObjects.Enemy;
import logicalGameObjects.Setpiece;

/**
 * 
 * @author Thomas Capach
 *
 */
public class ActiveRenderer extends JPanel implements Runnable
{
	
	BufferStrategy bs;
	Graphics2D g;
	
	
	public ActiveRenderer()
	{

		
	}
	
	
	

	public void run()
	{
	
		try
		{
			//wait for assets to load
			Thread.sleep(500);
			
		bs = CoreClass.fame.getBufferStrategy();
				
		this.setIgnoreRepaint(true);
		
		this.setFocusable(true);
		this.addMouseListener(CoreClass.mightyMouse);
		this.addMouseMotionListener(CoreClass.mightyMouse);
		
			while(true)
			{
				g = (Graphics2D)bs.getDrawGraphics();
				
				
				drawBoard();
				
				drawObjects();
				
				drawUI();

				
				
                bs.show();
                
                CoreClass.coolkit.sync();
                
                g.dispose();
                
                
                Thread.sleep(20);
				
			}
		
		} 
		catch (Exception e)
		{
			
			e.printStackTrace();
		}
	}
	
	/**
	 * draw the tile map
	 * 
	 */
	public void drawBoard()
	{
		
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
		
	}
	
	/**
	 * draw all the objects
	 */
	public void drawObjects()
	{
		
		List<Object> everything = CoreClass.getSortedObjectList();
		
		//
	//	int xOff = 0;
	//	int yOff = 0;
		
		int cameraOffX = -(CoreClass.mapUpperLeftOffset.getX());
		int cameraOffY = -(CoreClass.mapUpperLeftOffset.getY());
		
		Coordinate tempUL = CoreClass.onScreenTile[0];
		Coordinate tempBR = CoreClass.onScreenTile[1];
		
		
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
		        
		        //Rectangle r = CoreClass.mainCharacter.getHitbox();
		       // g.drawRect(r.x, r.y, r.width, r.height);
		        
		    /*     g.setColor(Color.BLACK);
		       g.fillPolygon(CoreClass.mainCharacter.getSoundObject());
		        
		       g.setColor(Color.BLACK);
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
		
				g.drawImage(((Enemy) s).getPic(), ((Enemy) s).getxPos()  + cameraOffX - (tempUL.getX() * 102), ((Enemy) s).getyPos() + cameraOffY - (tempUL.getY() * 102), null);
				
				
				//g.setColor(Color.BLUE);
				//g.fillPolygon(((Enemy) s).getseeingBox());
				
				
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
		
	}
	
	/**
	 * draw the user interface
	 */
	public void drawUI()
	{
		
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
	
}
