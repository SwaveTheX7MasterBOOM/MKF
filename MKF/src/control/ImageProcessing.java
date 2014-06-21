package control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class ImageProcessing
{

	
	
	
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
	    
	    
	    
	    
	    
	    public static Image emboss(Image i)
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
