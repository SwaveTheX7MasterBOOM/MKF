package control;

import java.awt.Image;

import physicalGameObjects.MeleeEnemy;

import logicalGameObjects.Enemy;



/**
 * Collection of Enemy AI methods 
 * 
 * @author Thomas Capach
 *
 */
public class EnemyAI
{	

	/**
	 * creeping line search pattern
	 * 	   ___     ___
	 * 	  |   |   |
	 * ___|   |___|
	 * 
	 * 
	 * FIRST movement
	 * 	   
	 * 	  |
	 * ___|
	 * 
	 * SECOND movement
	 * ___
	 *    |
	 *    |
	 *    
	 * The movements are akin to the way a knight moves on a chess board.
	 * Patterns continue until the objects have hit the opposite side of 
	 * the board at which point the enemy object will reverse the direction.
	 * 
	 * @param e - enemy object
	 */
	public static void searchCL(Enemy e)
	{
		
		switch(e.getIntendedDirection())
		{
		
			case 1://search left to right
				
				if(e.getCLX() < e.getMaxCLX() && e.getCLY() == 0)//first movement
				{
					//move in x away from border
					if(e.getCLX() <= e.getMaxCLX() && e.getCLY() == 0)
					{
						//out of bounds check
						if((e.getxPos() + e.getSpeed()) < CoreClass.getCurrentLevel().getMaxBound())
						{
							//move in x axis, increment cl counter
							e.setCLX(e.getCLX() + 1);
							e.moveEast();
							
						}
						else
						{
							//reached other side of the board, turn around
							e.setIntendedDirection(2);
							
						}
			
					}
				}
				else if(e.getCLY() < e.getMaxCLY() && e.getCLX() >= e.getMaxCLX())//move in y+ parallel to starting edge
				{

							//move in the y axis, increment cl counter
							e.setCLY(e.getCLY() + 1);
							e.moveNorth();
			
				}
				else//second movement
				{
					
					if(e.getCLX() > 0 && e.getCLY() == e.getMaxCLY())
					{
	
						//out of bounds check
						if((e.getxPos() + e.getSpeed()) < CoreClass.getCurrentLevel().getMaxBound())
						{
							//move in x axis, increment cl counter
							e.setCLX(e.getCLX() - 1);
							e.moveEast();
							
						}
						else
						{
							//reached other side of the board, turn around
							e.setIntendedDirection(2);
							
						}
						
					}
					else
					{
						//move opposite -y direction, increment counter
						e.setCLY(e.getCLY() - 1);
						e.moveSouth();	
						
					}
					
					
				}
			
			break;
			
		case 2://right to left
			
			if(e.getCLX() < e.getMaxCLX() && e.getCLY() == 0)//first movement
			{
				//move in x away from border
				if(e.getCLX() <= e.getMaxCLX() && e.getCLY() == 0)
				{
					//out of bounds check
					if((e.getxPos() - e.getSpeed()) > 0)
					{
						//move in x axis, increment cl counter
						e.setCLX(e.getCLX() + 1);
						e.moveWest();
						
					}
					else
					{
						//reached other side of the board, turn around
						e.setIntendedDirection(1);
						
					}
		
				}
				
			}
			else if(e.getCLY() < e.getMaxCLY() && e.getCLX() >= e.getMaxCLX())//move in y+ parallel to starting edge
			{

						//move in the y axis, increment cl counter
						e.setCLY(e.getCLY() + 1);
						e.moveNorth();
		
			}
			else//second movement
			{
				
				if(e.getCLX() > 0 && e.getCLY() == e.getMaxCLY())
				{

					//out of bounds check
					if((e.getxPos() - e.getSpeed()) > 0)
					{
						//move in x axis, increment cl counter
						e.setCLX(e.getCLX() - 1);
						e.moveWest();
						
					}
					else
					{
						//reached other side of the board, turn around
						e.setIntendedDirection(1);
						
					}
					
				}
				else
				{
					//move opposite -y direction, increment counter
					e.setCLY(e.getCLY() - 1);
					e.moveSouth();	
					
				}
				
				
			}
			
			break;
			
		case 3://north to south
			
			if(e.getCLY() < e.getMaxCLY() && e.getCLX() == 0)//first movement
			{
				//move in y away from border
				if(e.getCLY() <= e.getMaxCLY() && e.getCLX() == 0)
				{
					//out of bounds check
					if((e.getyPos() + e.getSpeed()) <  CoreClass.getCurrentLevel().getMaxBound())
					{
						//move in y axis, increment cl counter
						e.setCLY(e.getCLY() + 1);
						e.moveSouth();
						//System.out.println("move y- "+ e.getCLY());
					}
					else
					{
						//reached other side of the board, turn around
						e.setIntendedDirection(4);
						
					}
		
				}
				
			}
			else if(e.getCLX() < e.getMaxCLX() && e.getCLY() >= e.getMaxCLY())//move in x axis, increment cl counter
			{

						//move in the x axis, increment cl counter
						e.setCLX(e.getCLX() + 1);
						e.moveEast();

			}
			else//second movement
			{
				
				if(e.getCLY() > 0 && e.getCLX() == e.getMaxCLX())
				{

					//out of bounds check
					if((e.getyPos() - e.getSpeed())  <  CoreClass.getCurrentLevel().getMaxBound())
					{
						//move in y axis, increment cl counter
						e.setCLY(e.getCLY() - 1);
						e.moveSouth();
						
					}
					else
					{
						//reached other side of the board, turn around
						e.setIntendedDirection(4);
						
					}
					
				}
				else
				{
					//move opposite -y direction, increment counter
					e.setCLX(e.getCLX() - 1);
					e.moveWest();	
					
				}
				
			}
			
			break;
			
		case 4://south to north
			
			if(e.getCLY() < e.getMaxCLY() && e.getCLX() == 0)//first movement
			{
				//move in y away from border
				if(e.getCLY() <= e.getMaxCLY() && e.getCLX() == 0)
				{
					//out of bounds check
					if((e.getyPos() - e.getSpeed()) > 0)
					{
						//move in y axis, increment cl counter
						e.setCLY(e.getCLY() + 1);
						e.moveNorth();
						
					}
					else
					{
						//reached other side of the board, turn around
						e.setIntendedDirection(3);
						
					}
		
				}
			}
			else if(e.getCLX() < e.getMaxCLX() && e.getCLY() >= e.getMaxCLY())//move in x axis, increment cl counter
			{

						//move in the x axis, increment cl counter
						e.setCLX(e.getCLX() + 1);
						e.moveEast();
		
			}
			else//second movement
			{
				
				if(e.getCLY() > 0 && e.getCLX() == e.getMaxCLX())
				{

					//out of bounds check
					if((e.getyPos() - e.getSpeed())  > 0)
					{
						//move in y axis, increment cl counter
						e.setCLY(e.getCLY() - 1);
						e.moveNorth();
						
					}
					else
					{
						//reached other side of the board, turn around
						e.setIntendedDirection(4);
						
					}
					
				}
				else
				{
					//move opposite -x direction, increment counter
					e.setCLX(e.getCLX() - 1);
					e.moveWest();	
					
				}
				
				
			}
			
			break;
			
		}

	}
	
	
	

	
	
	
	/** Basic AI for melee type enemies.  The enemy will approach the Player till the player is within 
	 * the enemies hit box.  The attack animation will start and at the damage dealing frame the 
	 * collision method is called and damage is determined there.
	 *  
	 * @param me - Melee Enemy object.  The current enemy being manipulated  **/
	public static void meleeAI(final MeleeEnemy me)
	{	
//		final int attackFrame = me.getAttackFrame();
//		final Image [] att = me.getAttackAnimation();
		final int attackFrame = 0;
		final Image [] att = null;
		final int attSpeed = me.getSpeed()/2;//need to test this might need to foul around with this or add another variable to control speed 
				
		if(!me.getHitbox().intersects(CoreClass.mainCharacter.getHitbox()))
		{	
	
			double run = me.getxPos() - CoreClass.mainCharacter.getxPos();
			double rise = me.getyPos()- CoreClass.mainCharacter.getyPos();
			
			run = Math.abs(run);
			rise = Math.abs(rise);

			//get greatest common factor
			int gcf = Mathariffic.GCF((int)rise, (int)run);		

			//gets the rise and run
			run = run / gcf;
			rise = rise / gcf;
			run = Math.abs(run);
			rise = Math.abs(rise);
		
			//gets speed based on rise run
			double chX = me.getSpeed() * run;
			double chY = me.getSpeed() * rise;

			//Divides speed in half till it reaches the max enemy speed in the x or y
			while(chX > me.getSpeed() && chX > 0 || chY > me.getSpeed() && chY > 0)
			{				
				chX = chX / 2;
				chY = chY / 2;				
			}
			
			chX = Math.abs(chX);
			chY = Math.abs(chY);			
			
			//add or subtracts based on the found change
			if(CoreClass.mainCharacter.getxPos() < run)
			{				
				run = run - chX;				
			}
			else
			{				
				run = run + chX;				
			}
			
			if(CoreClass.mainCharacter.getyPos() < rise)
			{				
				rise = rise- chY;				
			}
			else
			{				
				rise = rise + chY;				
			}				
		}
		else
		{			
			//attack animation start
			
			/*Thread temp = new Thread(){			
				public void run()
				{				
					//int attackFrame = me.getAttackFrame();
					//int [] att = me.getAttackAnimation();					
				
					for(int x = 0; x < att.length; ++x)
					{						
						if(x == attackFrame)
						{						
							WhenShitHits.MeleeEnemyAttackPlayer(me);					
						}	
						
						me.setPic(att[x]);							
						try
						{							
							Thread.sleep(attSpeed);							
						} catch (InterruptedException e)
						{
							
						}					
					}				
				}
			};
			temp.start();*/
		}		
	}
	
}