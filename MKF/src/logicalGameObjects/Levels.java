package logicalGameObjects;

import java.awt.Image;
import java.util.List;
import java.util.Map;
import java.util.Set;



/**@author Thomas Capach **/
public interface Levels
{
	/** get background image for level  **/
	public Image getBackground();
	
	/** get characters x axis starting position on the map **/
	public int getStartX();
	
	/** get characters y axis starting position on the map **/
	public int getStartY();
	
	/** set characters x axis starting position on the map **/
	public void setStartX(int startX);
	
	/** set characters y axis starting position on the map **/
	public void setStartY(int startY);
	
	/** get the abstract view of the levels tile map **/
	public int[][] getMap();
	
	/**get the maximum y axis coordinate that player can go to on the level**/
	public int getMaxYPosition();
	
	/**get the minimum y axis coordinate that the player can go to on the level **/
	public int getMinYPosition();
	
	/** get specified platform objects in the level **/
	public  PlatformObjects[] getPlatObjects(List <Integer> p);
	
	/** get the X position of all the platform objects **/
	public int [] getPlatPosX();
	
	/** get the Y position of all the platform objects **/
	public int [] getPlatPosY();	
	
	/** gets specified enemy  **/
	public  Enemy[] getEnemy(List <Integer> x);
	
	/** get x coordinates of the levels enemy object **/
	public  int[] getEnemySpawnPointX();
	
	/** get y coordinates of the levels enemy object **/
	public  int[] getEnemySpawnPointY();

	/** get the maximum length/width of a level **/
	public  int getMaxBound();
	
	/** get specified tile **/
	public Image getTile(int i);
	
	/** get general tile size **/
	public int getTileSize();

	/** get list of all objects on the level **/
	public Set<Setpiece> getAllObjects();

	/** get a map structure with the coordinates of all the levels objects **/
	public Map<Coordinate, Set<Setpiece>> getPlaces();
	
	//add food object methods	
}