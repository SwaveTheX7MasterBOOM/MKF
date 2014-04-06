package control;

import physicalGameObjects.MeleeEnemy;
import logicalGameObjects.Enemy;


/**
 * The Enemy management class is responsible for all enemy objects.  Creation, AI and Managing the list of current enemies 
 * 
 * @author Thomas Capach
 *
 */
public class EnemyManagment extends Thread
{
	//CONSTRUCTOR
	public EnemyManagment()
	{

		//no spawning set up, test enemies
		CoreClass.getAllEn().add(new MeleeEnemy(300, 0, 3, 100, 200));
		CoreClass.getAllEn().add(new MeleeEnemy(2000, 1800, 4, 150, 300));
		CoreClass.getAllEn().add(new MeleeEnemy(10,1820, 1, 60, 140));
		CoreClass.getAllEn().add(new MeleeEnemy(1900, 300, 2, 300, 600));
		
	}

	
	public void run()
	{
		
		while(CoreClass.gameON)
		{
				
			update();
	
			
				try
				{
					
					sleep(21);
					
				} catch (InterruptedException e)
				{
					
					e.printStackTrace();
					
				}
		
		}
		
	}
	
	/**
	 * loops through enemy objects calling their AI methods
	 * 
	 * @return
	 */
	public boolean update()
	{
		
		for(Enemy e:CoreClass.getAllEn())
		{
			
			e.aI();
			
		}
		
			return true;
	}
	
}
