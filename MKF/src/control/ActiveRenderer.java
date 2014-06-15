package control;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import physicalGameObjects.DialogBox;
import physicalGameObjects.Player;

import logicalGameObjects.Coordinate;
import logicalGameObjects.Enemy;
import logicalGameObjects.PopUps;
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
	 GraphicsEnvironment ge;
	 Font font;
	 
	 public static int xOff = 0;
	 public static int yOff = 0;
	
	public ActiveRenderer()
	{

		
	}
	
	
	

	public void run()
	{
	
		try
		{
			//wait for assets to load
			Thread.sleep(250);
			
		bs = CoreClass.fame.getBufferStrategy();
				
		this.setIgnoreRepaint(true);
		
		this.setFocusable(true);
		this.addMouseListener(CoreClass.mightyMouse);
		this.addMouseMotionListener(CoreClass.mightyMouse);
		
		InputStream myStream = new BufferedInputStream(new FileInputStream("Assets/cad.ttf"));
		
		font = Font.createFont(Font.TRUETYPE_FONT , myStream);
		
		font = font.deriveFont(Font.BOLD, 18);
		
	
		
		   ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		       ge.registerFont(font);
		   

		   
			while(true)
			{
				g = (Graphics2D)bs.getDrawGraphics();
				

				drawBoard();
				
				drawObjects();
				
				drawUI();

				drawPopUps();
				
				g.fillOval(CoreClass.mightyMouse.getMouseCoordinate().getX(), CoreClass.mightyMouse.getMouseCoordinate().getY(), 10, 10);
				
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
		
		
		xOff = cameraOffX - (tempUL.getX() * 102);
		yOff = cameraOffY - (tempUL.getY() * 102);
		
		// draws objects, the enemies and the player
		for(Object s:everything)
		{
		
			
			if(s instanceof Setpiece)
			{

			
				switch(((Setpiece) s).getImageEffect())
				{
					
				case "emboss":
			
					g.drawImage(  emboss(new ImageIcon(((Setpiece) s).getThing()).getImage()),  ( (Setpiece) s ).getX() + cameraOffX - (tempUL.getX() * 102), ((Setpiece) s).getY() + cameraOffY - (tempUL.getY() * 102),null);
					
					
					
					break;
					
					default:
					{
						
							g.drawImage(  new ImageIcon(((Setpiece) s).getThing()).getImage(),  ( (Setpiece) s ).getX() + cameraOffX - (tempUL.getX() * 102), ((Setpiece) s).getY() + cameraOffY - (tempUL.getY() * 102),null);
				
						
					}
				
				}

				
				
				//just for the visualization of the hit box for debugging
				//g.setColor(Color.RED);
				//g.fillRect((int)((Setpiece) s).getBox().getX()+ cameraOffX - (tempUL.getX() * 102), ((int)((Setpiece) s).getBox().getY()) + cameraOffY - (tempUL.getY() * 102), (int)((Setpiece) s).getBox().getWidth(), (int)((Setpiece) s).getBox().getHeight());
			
				/* g.draw(new Rectangle( (int)( ( (Setpiece) s ).getX() + (-(CoreClass.mapUpperLeftOffset.getX())) - (CoreClass.onScreenTile[0].getX() * 102)) ,
							(int)( ( (Setpiece) s ).getY() + (-(CoreClass.mapUpperLeftOffset.getY())) - (CoreClass.onScreenTile[0].getY() * 102)) , 
							(int)( (Setpiece) s ).getWidth() , 
							(int)( (Setpiece) s ).getHeight() ));
				 g.setColor(Color.RED);
				 g.fillOval((int)CoreClass.mightyMouse.getMouseCoordinate().getPoint().getX(), (int)CoreClass.mightyMouse.getMouseCoordinate().getPoint().getY(), 20, 20);*/
			}
			else if(s instanceof Player)
			{
				
		        //draw the character

		        
				switch(((Player) s).getImageEffect())
				{
					
				case "emboss":
					
					g.drawImage(  emboss(CoreClass.mainCharacter.getPic()), CoreClass.mainCharacter.getxPos(), CoreClass.mainCharacter.getyPos(), null);
					
					
					break;
					
				default:
					
						g.drawImage(CoreClass.mainCharacter.getPic(), CoreClass.mainCharacter.getxPos(), CoreClass.mainCharacter.getyPos(), null);
							        
					break;
					
				}
		        
		        
		        
		        
		        
		        
		        //Rectangle r = CoreClass.mainCharacter.getHitbox();
		       
		        
		    /*     g.setColor(Color.BLACK);
		       g.fillPolygon(CoreClass.mainCharacter.getSoundObject());
		     */   
		 /*      g.setColor(Color.BLACK);
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
		

				
				switch(((Enemy) s).getImageEffect())
				{
					
				case "emboss":
					
					g.drawImage(  emboss(((Enemy) s).getPic()), ((Enemy) s).getxPos()  + cameraOffX - (tempUL.getX() * 102), ((Enemy) s).getyPos() + cameraOffY - (tempUL.getY() * 102), null);
					
					
					break;
					
				default:
					
					g.drawImage(((Enemy) s).getPic(), ((Enemy) s).getxPos()  + cameraOffX - (tempUL.getX() * 102), ((Enemy) s).getyPos() + cameraOffY - (tempUL.getY() * 102), null);
					
							        
					break;
					
				}
				

				
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
	
	/**
	 * 
	 */
	public synchronized void drawPopUps()
	{
		try {
			CoreClass.dialogSemaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<PopUps> temp = Collections.unmodifiableSet(CoreClass.notifications);

		
			g.setColor(Color.BLACK);

		
			for(PopUps p: temp)
			{
				
				if(p instanceof DialogBox)
				{
					g.setFont(font);
				//	System.out.println(g.getFontMetrics().stringWidth(p.getS()));
					
					if(g.getFontMetrics().stringWidth(p.getS()) < 500)
					{
					
						g.drawImage(p.getPic(), p.getX(), p.getY()  - p.getPic().getHeight(null), null);
	
						
						ArrayList<String> sa = new ArrayList<String>(Arrays.asList( p.getS().split(" ")));
						
						ArrayList<String> lines = new ArrayList<String>();
						
						
						int total = 0;
						
						String s = "";
					
					
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
										
										//System.out.println(s);
										
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
	
	
	
	
	
	
	   /**
     * Converts a given Image into a BufferedImage
     * 
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img){
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        // Return the buffered image
        return bimage;
    }
	
	
	
	
	
	
	
	
    /**
     * Converts a given BufferedImage into an Image
     * 
     * @param bimage The BufferedImage to be converted
     * @return The converted Image
     */
    public static Image toImage(BufferedImage bimage){
        // Casting is enough to convert from BufferedImage to Image
        Image img = (Image) bimage;
        return img;
    }
	
	
	   /**
     * Creates an empty image with transparency
     * 
     * @param width The width of required image
     * @param height The height of required image
     * @return The created image
     */
    public static Image getEmptyImage(int width, int height){
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        return toImage(img);
    }
	  

	   /**
	     * Makes a color in an Image transparent.
	     */
	    public static Image mask(Image img, Color color){
	        BufferedImage bimg = toBufferedImage(getEmptyImage(img.getWidth(null), img.getHeight(null)));
	        Graphics2D g = bimg.createGraphics();
	        g.drawImage(img, 0, 0, null);
	        g.dispose();
	        for (int y=0; y<bimg.getHeight(); y++){
	            for (int x=0; x<bimg.getWidth(); x++){
	                int col = bimg.getRGB(x, y);
	                if (col==color.getRGB()){
	                    bimg.setRGB(x, y, col & 0x00ffffff);
	                }
	            }
	        }
	        return toImage(bimg);
	    }
	    
	    
	    
	    
	    
	    public Image emboss(Image i)
	    {
			float[] embossMatrix = {
					-1.0f, -1.0f,  0.0f,
					-1.0f,  1.0f,  1.0f,
					 0.0f,  1.0f,  1.0f
				};
			
			Kernel k = new Kernel(3, 3, embossMatrix);

			BufferedImageOp op =  new ConvolveOp(k);
			 
			BufferedImage bi= toBufferedImage(i);
			
			
			 BufferedImage temper = op.filter(bi, null);
return toImage(temper);
	    }
	    
	    
	    
	    
	    
	    
	    
}
