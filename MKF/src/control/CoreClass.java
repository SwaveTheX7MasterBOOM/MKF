package control;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import physicalGameObjects.JungleLevel;
import physicalGameObjects.MeleeEnemy;
import physicalGameObjects.Player;

import logicalGameObjects.Camera;
import logicalGameObjects.Coordinate;
import logicalGameObjects.Enemy;
import logicalGameObjects.HealthBar;
import logicalGameObjects.Levels;
import logicalGameObjects.PlatformObjects;




/**
 * 
 * This class is called by main to start up the game by initializing shared variables used by multiple classes and starting up the necessary threads
 * 
 * @author Thomas "Fucking" Capach
 *
 */
public class CoreClass 
{
	//the player
	public static Player mainCharacter;
	
	//all enemies
	private static List <Enemy> allEnemies = new ArrayList<Enemy>();
	
	//all visible enemies
	private static List <Enemy> onScreenEnemies = new ArrayList<Enemy>();
	
	//all platforms being drawn on screen
	private static List <PlatformObjects> onScreenPlatforms = new ArrayList<PlatformObjects>();
	
	//The current level
	private static Levels currentLevel;		
	
	//the maximum y axis the player can move to in platforming mode
	private static int currentMaxY;
	
	//the minimum y axis the player can move to in platforming mode
	private static int currentMinY;
	
	//if the game loop is running
	public static boolean gameON = true;
	
	//The JFrame that contains the Renderer class JPanel
	public static TheFrame fame;
	
	//the field of view
	private static Camera cam;
	
	//calculates the needed tiles by finding the upper-left and bottom-right tile need to be drawn given where the camera is positioned
	public static Coordinate[] onScreenTile = {new Coordinate(0,0), new Coordinate(0,0)};
	
	//offset for the camera
	public static Coordinate mapUpperLeftOffset = new Coordinate(0,0);
	
	//catches input from the keyboard
	public static InputCatcher InCa = new InputCatcher();
	
	//health bar
	public static HealthBar healthBar = new HealthBar();
	
	//for thread safety
	private ExecutorService es;

	
	/**
	 * New game constructor
	 */
	public CoreClass()
	{		
		
		fame = new TheFrame();
		
		mainCharacter = new Player((fame.getWidth() / 2), fame.getHeight() / 2);
		
		//testing healthbar setting max hp to 100
		healthBar = new HealthBar(mainCharacter.getHitpoints(), 100);
		
		cam = new Camera();
		
		currentLevel = new JungleLevel();	
		
		es = Executors.newCachedThreadPool();

				es.execute(new Thread(new MovementManager()));
				es.execute(new Thread(new EnemyManagment()));
				
					es.shutdown();
			

		//	Thread tt2 = new Thread(new FirestormArtificialisIntelligentia(10,  CoreClass.getCurrentLevel().getMap() , CoreClass.getCurrentLevel().get));
			//tt2.start();
			
	}
		
	
	/**
	 * Saved game constructor
	 * 
	 * @param f - the save game file
	 */
	public CoreClass(File f)
	{
		
		//fill later
		
	}
		
	
	/**
	 * Get the current level object that is being used
	 * 
	 * @return
	 */
	public static Levels getCurrentLevel()
	{
		
		return currentLevel;
		
	}

	
	/**
	 * Set the current level object to be used
	 * 
	 * @param currentLevel
	 */
	public static void setCurrentLevel(Levels currentLevel)
	{
		
		CoreClass.currentLevel = currentLevel;
		
	}

	
	/**
	 * 
	 * Get the Camera object
	 * 
	 * @return
	 */
	public static Camera getCam()
	{
		
		return cam;
		
	}

	
	/**
	 * Set the camera object 
	 * 
	 * @param cam
	 */
	public static void setCam(Camera cam)
	{
		
		CoreClass.cam = cam;
		
	}
	
	
	/**
	 * 
	 * Finds the tiles needed (by finding the upper-right and bottom-left tile) to be drawn based on the position of the camera object.
	 * Also updates the camera offset (mapUpperLeftOffset) 
	 * 
	 */
	public static void updateNeededTiles()
	{
		
		onScreenTile[0] = new Coordinate((getCam().getMapX() - (fame.getWidth() / 2)) / getCurrentLevel().getTileSize(), (getCam().getMapY() - (fame.getHeight() / 2)) / getCurrentLevel().getTileSize()); 
		onScreenTile[1] = new Coordinate((getCam().getMapX() + (fame.getWidth() / 2)) / getCurrentLevel().getTileSize(), (getCam().getMapY() + (fame.getHeight() / 2)) / getCurrentLevel().getTileSize()); 
		
			mapUpperLeftOffset = new Coordinate((getCam().getMapX() - (fame.getWidth() / 2)) % getCurrentLevel().getTileSize(), (getCam().getMapY() - (fame.getHeight() / 2)) % getCurrentLevel().getTileSize());
		
	}
	
	
	/**
	 * Manages the list of what platform objects are being drawn on screen based on
	 *  the player position in the level 
	 */
	public synchronized void platformManagement()//
	{		
		Thread t = new Thread(){			
			public void run()
			{				
				while(true)
				{					
					if(gameON == true)
					{							
						int [] temp = currentLevel.getPlatPosX();							
						List <Integer> found = new ArrayList<Integer>();							
						onScreenPlatforms.clear();
						
						for(int i = 0; i < temp.length; i++)
						{								
							if(temp[i] <= mainCharacter.getxPos() + 500)//need to figure out exact numbers
							{									
								found.add(temp[i]);									
							}								
						}						
						onScreenPlatforms = Arrays.asList(currentLevel.getPlatObjects(found));									
					}										
				}				
			}			
		};		
	}
	

/**
 * 	get the list of current  on screen platform objects
 * @return List<PlatformObjects> - current platforms
 */
	public static List<PlatformObjects> getCurrentPlat()
	{		
		return onScreenPlatforms;		
	}

	
	/**
	 * get the list of current enemy objects on the screen
	 * 
	 * @return list<Enemy> of the current enemy objects
	 */
	public static List<Enemy> getCurrentEn()
	{	
		
		onScreenEnemies.clear();
		
		
		for(Enemy e: allEnemies)
		{
			
			int tempX = e.getxPos() / 102;
			int tempY = e.getyPos() / 102;
			
			
			
				if((tempX >= onScreenTile[0].getX() && tempX <= onScreenTile[1].getX()) && (tempY >= onScreenTile[0].getY() && tempY <= onScreenTile[1].getY()))
				{
					
					onScreenEnemies.add(e);
					
				}
			
		}
		
		return onScreenEnemies;		
	}


	/**
	 * set a new maximum y axis coordinate
	 * 
	 * @param x - int new y  axis max
	 */
	public static void setLevelMaxY(int x)
	{		
		currentMaxY = x;		
	}


	/**
	 * set a new minimum y axis coordinate
	 * 
	 * @param x - x new y axis min
	 */
	public static void setLevelMinY(int x)
	{		
		currentMinY = x;		
	}

	
	/**
	 * sets the current level
	 * 
	 * @param x level object
	 */
	public void setLevel(Levels x)
	{		
		currentLevel = x;		
	}
	
	/**
	 * get all enemy objects
	 * 
	 * @return - List<Enemy>
	 */
	public static List<Enemy> getAllEn()
	{		
		return allEnemies;		
	}



}