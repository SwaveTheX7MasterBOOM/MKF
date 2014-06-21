package control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import physicalGameObjects.DialogBox;
import physicalGameObjects.Player;

import logicalGameObjects.Coordinate;
import logicalGameObjects.DrawableObjects;
import logicalGameObjects.PopUps;

/**
 * Handles the rendering of Objects on screen. 
 * 
 * @author Thomas Capach
 */
public class ActiveRenderer implements Runnable
{
	
	private BufferStrategy bs;
	
	private Graphics2D g;

	
	// draw all the objects
	public void run()
	{
	
		try
		{
			//wait for assets to load
			Thread.sleep(300);
			
				bs = CoreClass.fame.getBufferStrategy();

			while(true)
			{
				
				g = (Graphics2D)bs.getDrawGraphics();
				
				//fps//long start = System.nanoTime();

					drawBoard();
				
					drawObjects();
				
					drawUI();

					drawPopUps();
				
				g.fillOval(CoreClass.mightyMouse.getMouseCoordinate().getX(), CoreClass.mightyMouse.getMouseCoordinate().getY(), 10, 10);
				
                		bs.show();
                
                			CoreClass.coolkit.sync();
                
                				g.dispose();
                
                //fps
               // System.out.println(1/((System.nanoTime() - start) * 0.00000001 * 1.0));                
              
				
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
		
		//offsets
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
				
				if(x == 20 || y == 20)
				{
					return;
				}
				
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
					
					e.printStackTrace();
					
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
		
		List<DrawableObjects> everything = CoreClass.getSortedObjectList();
		
		int cameraOffX = -(CoreClass.mapUpperLeftOffset.getX());
		int cameraOffY = -(CoreClass.mapUpperLeftOffset.getY());
		
		Coordinate tempUL = CoreClass.onScreenTile[0];

		
		// draws objects, the enemies and the player
		for(DrawableObjects d:everything)
		{

			if(d instanceof Player)
			{
				
		        //draw the character
				switch(((Player) d).getImageEffect())
				{
					
					case "emboss":
						
						g.drawImage(  ImageProcessing.emboss(CoreClass.mainCharacter.getPic()), CoreClass.mainCharacter.getxPos(), CoreClass.mainCharacter.getyPos(), null);				
						
							break;
						
					default:
						
							g.drawImage(CoreClass.mainCharacter.getPic(), CoreClass.mainCharacter.getxPos(), CoreClass.mainCharacter.getyPos(), null);
								        
								break;
					
				}

			}
			else
			{
				//draw setpieces and enemies
				switch(d.getImageEffect())
				{
					
					case "emboss":
						
						g.drawImage(  ImageProcessing.emboss(d.getPic()), d.getxPos()  + cameraOffX - (tempUL.getX() * 102), d.getyPos() + cameraOffY - (tempUL.getY() * 102), null);					
						
							break;
						
					default:
						
						g.drawImage(d.getPic(), d.getxPos()  + cameraOffX - (tempUL.getX() * 102), d.getyPos() + cameraOffY - (tempUL.getY() * 102), null);
									        
							break;
					
				}
				
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
	
	/**
	 * Draw all pop up objects
	 */
	public synchronized void drawPopUps()
	{
		
		try
		{
			
			CoreClass.dialogSemaphore.acquire();
			
		} 
		catch (InterruptedException e)
		{
			
			e.printStackTrace();
			
		}
		
		Set<PopUps> temp = Collections.unmodifiableSet(CoreClass.notifications);
		
			g.setColor(Color.BLACK);
		
			for(PopUps p: temp)
			{
				
				if(p instanceof DialogBox)
				{

					g.setFont(p.getFont());
					
					if(g.getFontMetrics().stringWidth(p.getS()) < 500)
					{
					
						g.drawImage(p.getPic(), p.getX(), p.getY()  - p.getPic().getHeight(null), null);
	
						
						ArrayList<String> sa = new ArrayList<String>(Arrays.asList( p.getS().split(" ")));
						
						ArrayList<String> lines = new ArrayList<String>();
						
						
						int total = 0;
						
						String s = "";
					
							//break down sentence into words and organize it to fit in the word bubble
							while(sa.size() > 0)
							{
								
								int part = g.getFontMetrics().stringWidth(sa.get(0));
								
									if(total + part <= 160)
									{
										
										total = total + part;
										
										s = s + " " + sa.get(0);
										
										sa.remove(0);
										
											if(sa.size() == 0)
											{
												lines.add(s);
											}
										
									}	
									else
									{
										
										lines.add(s);
										
											s = "";
											total = 0;
										
									}
			
							}
					
					
								int line = 30;
								for(String st:lines)
								{
									
									g.drawString(st, p.getX() + 10, p.getY() - p.getPic().getHeight(null) + line);
									
										line = line + 30;
									
								}

				}
					
			}
					
		}
		
		CoreClass.dialogSemaphore.release();
	}
	    
}
