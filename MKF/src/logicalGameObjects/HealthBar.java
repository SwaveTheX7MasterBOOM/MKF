package logicalGameObjects;
 

import control.CoreClass;
import java.awt.*;

/**
 * The health bar
 * 
 * @author Andy Rodriguez 
 */

public class HealthBar {
	//coordinate positions for health bar
	private int xPos;
	private int yPos;
	
	//size for the health bar
	private int width;
	private int height;
	
	//full width of health bar background
	private int fullWidth;
	
	//the player's hitpoints and max health
	private int hitpoints;
	private int maxHealth;
	
	//the ratios
	private float healthRatio;
	private float blendRatio;
	
	//health bar colors
	private Color fullHealth = Color.GREEN;
	private Color midHealth = Color.YELLOW;
	private Color lowHealth = Color.RED;
	private float opacity = 1;
	

	/**
	 * Default Constructor 
	 */
	public HealthBar(){
		this.xPos = 48;
		this.yPos = 48;
		
		this.width = 200;
		this.height = 40;
		
		this.hitpoints = 100;
		this.maxHealth = 100;
		
		this.healthRatio = 1;
		this.blendRatio = 1;
	}
	
	/**
	 * Overloaded Constructor with the player's hitpoints and max health
	 */
	public HealthBar(int hitpoints, int maxHealth){
		this.xPos = 50;
		this.yPos = 50;
		
		this.width = 200;
		this.height = 40;
		
		this.fullWidth = 200;
		
		this.hitpoints = hitpoints;
		this.maxHealth = maxHealth;
		
		this.healthRatio = (float)(hitpoints/maxHealth);
		if(healthRatio >= .5){
			blendRatio = 1-((1-healthRatio)*2);
		} else {
			blendRatio = (float) (1-((.5-healthRatio)*2));
		}
	}
	
	/**
	 * updates health/ratios
	 */
	public void update(){
		this.hitpoints = CoreClass.mainCharacter.getHitpoints();
		
		this.healthRatio = (float)hitpoints/maxHealth;
		if(healthRatio >= .5){
			blendRatio = 1-((1-healthRatio)*2);
		} else {
			blendRatio = (float) (1-((.5-healthRatio)*2));
		}
		
		this.width = (int)(healthRatio*fullWidth);
		//System.out.println(hitpoints);
	}
	
	/**
	 * returns the x position
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * sets the x position
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * returns the y position
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * sets the y position
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * returns the health bar width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * sets the health bar width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * returns the health bar height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * sets the health bar height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * returns the opacity of the health bar
	 */
	public float getOpacity() {
		return opacity;
	}

	/**
	 * sets the opacity of the health bar
	 */
	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}
	
	/**
	 * return the full width of the health bar background
	 */
	public int getFullWidth() {
		return fullWidth;
	}

	/**
	 * set the full width of the health bar background
	 */
	public void setFullWidth(int fullWidth) {
		this.fullWidth = fullWidth;
	}

	/**
	 * returns a color created by blending two colors at a certain ratio
	 * used to have the health bar gradually change color based on remaining hitpoints
	 */
	public Color getHealthColor(){
		//find the ratio for the other color
		float r = (float) 1.0 - blendRatio;
		
		//prepare rgb array for each color
		float rgb1[] = new float[3];
		float rgb2[] = new float[3];

		Color c1, c2 = null;
		if(healthRatio >= .5){
			c1 = fullHealth;
			c2 = midHealth;
		} else {
			c1 = midHealth;
			c2 = lowHealth;
		}
			  
		//fill rgb array for each color
		c1.getColorComponents(rgb1);
		c2.getColorComponents(rgb2);
		
		//blend the 2 colors by rgb values and return the blended color
		Color blendedColor = new Color (rgb1[0] * blendRatio + rgb2[0] * r,
										rgb1[1] * blendRatio + rgb2[1] * r,
										rgb1[2] * blendRatio + rgb2[2] * r, opacity);
		return blendedColor;
	}
}


	
	