package physicalGameObjects;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import logicalGameObjects.Coordinate;
import logicalGameObjects.Enemy;
import logicalGameObjects.Levels;
import logicalGameObjects.PlatformObjects;
import logicalGameObjects.Setpiece;


import control.CoreClass;
import control.FirestormArtificialisIntelligentia;
import control.MapMaker;

/**@author Thomas "Cobra Commander" Capach **/
public class JungleLevel implements Levels
{
	//repeating background image for the level
	private Image backGround;	
	
	//Maximum Y axis coordinate for the player
	private int maxY;	 
	
	//Minimum Y axis coordinate for the player
	private int minY;
	
	//maximum boundary of the level
	private int maxBound;
	
	//all the tiles for the tile map
	private Image[] tiles = {new ImageIcon("src/images/rsz_jungle_11_tile.png").getImage(), new ImageIcon("src/images/rsz_jungle_03_tile.png").getImage(),
							new ImageIcon("src/images/jungle_00_tile.png").getImage(), new ImageIcon("src/images/rsz_jungle_01_tile.png").getImage(),
							new ImageIcon("src/images/rsz_jungle_02_tile.png").getImage(), new ImageIcon("src/images/rsz_jungle_10_tile.png").getImage(),
							new ImageIcon("src/images/rsz_jungle_12_tile.png").getImage(), new ImageIcon("src/images/rsz_jungle_20_tile.png").getImage(),
							new ImageIcon("src/images/rsz_jungle_21_tile.png").getImage(), new ImageIcon("src/images/rsz_jungle_22_tile.png").getImage(),
							new ImageIcon("src/images/rsz_jungle_1_tile.png").getImage(), new ImageIcon("src/images/rsz_jungle_2_tile.png").getImage(),
							new ImageIcon("src/images/rsz_jungle_3_tile.png").getImage(), new ImageIcon("src/images/rsz_jungle_4_tile.png").getImage()};
	
	//platforms in the level
	private PlatformObjects [] ledges;
	
	//platform object x axis positions on the board
	private int [] platformPositionsX;	
	
	//platform object y axis positions on the board
	private int [] platformPositionsY;	
	
	//enemies in the level
	private Enemy [] badGuys;	
	
	//enemy spawn points x axis
	private  int [] badGuyPositionsX;
	
	//enemy spawn points y axis
	private int [] badGuyPositionsY;	 
	
	//list of all objects
	private Set<Setpiece> allObjects; 
	
	//instance of the fire propagation class
	private FirestormArtificialisIntelligentia smartFire;
	
	//coordinates for all the objects
	private Map<Coordinate, Set<Setpiece>> aPlaceForEverything;

	//Instance of the map making class
	MapMaker m; 
	
	//abstract view of the tile map
	int[][] map;
	
	//starting x position
	int startX = 0;
	
	//starting y position
	int startY = 0;

	
	/** constructor **/
	public JungleLevel()
	{
		
		CoreClass.mainCharacter.setMapX(1040);
		CoreClass.mainCharacter.setMapY(1020);
		
		CoreClass.getCam().setMapX(1040);
		CoreClass.getCam().setMapY(1040);
		
		allObjects = new HashSet<Setpiece>();
		
			createObjects();

		
		m = new MapMaker(20, startX, startY, 20);
		
			m.MakeMeALevel();
			m.addSetpiece(allObjects);
		
				map = m.getTheWorld();
				aPlaceForEverything = m.getTheObjects();
				
				//m.seeTheWorld();
				
				
				
				smartFire = new FirestormArtificialisIntelligentia(20, map, aPlaceForEverything);
				smartFire.printGrid();

				
				maxBound = map.length * tiles[0].getHeight(null);
				
		
		//
		
				 
		maxY = 600;		 
		minY = 0;
		
		
		ledges = null;		 
		platformPositionsX = null;		 
		platformPositionsY = null;		
		badGuys = null;		
		badGuyPositionsX = null;		 
		badGuyPositionsY = null;		 
	 
	} 
	
	/**
	 * create and add all necessary setpiece for the level to the appropriate list
	 */
	public void createObjects()
	{
		//create trees
		for(int x = 0; x < 100; x++)
		{
			
			allObjects.add(new GumTree(x + 1));
			
		}
		
		//create bushes
		for(int x = 0; x < 10; x++)
		{
			
			allObjects.add(new Bush(x + 1));
			
		}
		
	}
	
	/**
	 * get the map of all objects coordinates
	 */
	public Map<Coordinate, Set<Setpiece>> getPlaces()
	{
		
		return aPlaceForEverything;
		
	}
	
	/**
	 * get list of all the levels objects
	 */
	public Set<Setpiece> getAllObjects()
	{
		
		return allObjects;
		
	}
	
	/**
	 * set the list of all objects
	 * 
	 * @param allObjects - Set<Setpiece> 
	 */
	public void setAllObjects(Set<Setpiece> allObjects)
	{
		
		this.allObjects = allObjects;
		
	}
	
	/**
	 * get the starting x position
	 */
	public int getStartX()
	{
		
		return startX;
		
	}
	
	/**
	 * set the starting x axis position
	 */
	public void setStartX(int startX)
	{
		
		this.startX = startX;
		
	}

	/**
	 * get the starting y axis position
	 */
	public int getStartY()
	{
		
		return startY;
		
	}
	
	/**
	 * set the starting y axis position
	 */
	public void setStartY(int startY)
	{
		
		this.startY = startY;
		
	}

	/**
	 * get the multi-dimensional array representation of the tile map
	 */
	public int[][] getMap()
	{
		
		return map;
		
	}

	/**
	 * get the maximum x/y axis boundry of the level
	 */
	public int getMaxBound()
	{
		
		return maxBound;
		
	}

	/**
	 * get the size of the tiles in the tile map
	 */
	public int getTileSize()
	{
		
		int go = 102;
		
			return go;
		
	}	

	/**
	 *  get repeating background of the level 
	 *  
	 * @return background Image
	 */
	public Image getBackground() 
	{		
		
		return backGround;
		
	}
	
	/**
	 * get the levels max Y axis position
	 * 
	 * @return int maxY the lower boundry of the level
	 */
	public int getMaxYPosition()
	{		
		
		return maxY;
		
	}
	
	
	/**
	 * get the levels min Y axis position
	 * 
	 * @return int maxY the upper boundry of the level
	 */
	public int getMinYPosition()
	{		
		
		return minY;	
		
	}

	
	/**
	 *  get the platform objects need to be drawn on to the screen 
	 *  
	 * @param p -  integer list containing the indexes of the necessary platform objects
	 * @return platformObject [] containing specified platform objects
	 */
	public PlatformObjects[] getPlatObjects(List <Integer> p)
	{
		PlatformObjects[] temp = new PlatformObjects[p.size()];		
		for(int z = 0; z < p.size(); z++)
		{			
			temp[z] = ledges[p.get(z)];			
		}	
		return temp;		
	}
	
	
	/**
	 *  get the x coordinate positions of the platform objects
	 *  
	 * @return platformObject [] containing all x axis coordinites of the 
	 * levels platform objects 
	 */
	public int [] getPlatPosX()
	{		
		return platformPositionsX;		
	}
	
	
	/**
	 *  get the y coordinate positions of the platform objects
	 *  
	 * @return platformObject [] containing all y axis coordinites of the 
	 * levels platform objects *
	 */
	public int [] getPlatPosY()
	{		
		return platformPositionsY;		
	}
	
	
	/** 
	 * get the enemies that are needed to be drawn on the screen 
	 * 
	 * @param x -  integer List containing array indexes of the needed enemy objects
	 * @return Enemy [] containing specified enemy objects*
	 */
	public Enemy [] getEnemy(List <Integer> x)
	{
		Enemy[] temp = new Enemy[x.size()];	
		
		for(int z = 0; z < x.size(); z++)
		{			
			temp[z] = badGuys[x.get(z)];			
		}		
		return temp;		
	}
	
	
	/** get the x coordinates of the levels enemies 
	 * 
	 * @return int [] containing the x coordinate of enemy spawn locations
	 */
	public int [] getEnemySpawnPointX() 
	{		
		return badGuyPositionsX;		
	}
	
	
	/** get the x coordinates of the levels enemies
	 * @return int [] containing the y coordinate of enemy spawn locations 
	 */
	public int [] getEnemySpawnPointY() 
	{		
		return badGuyPositionsY;		
	}

	/**
	 * get specified tile map tile
	 */
	public Image getTile(int i)
	{
		
		return tiles[i];
		
	}


}