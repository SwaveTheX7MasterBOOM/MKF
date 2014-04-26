package control;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * manages the movement of the player as they move throughout the level
 * 
 * @author Thomas "Fucking" Capach
 */
public class MovementManager extends Thread
{

	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;	
	private boolean onPath = false;
	
	private int attack = 0;
	//private int facing = 5;
	
	private final int baseSpeed = CoreClass.mainCharacter.getSpeed();
	
	private ExecutorService es = Executors.newSingleThreadExecutor();
	
	//consider threaD SAFTY!
	private Thread attacking = new Thread() {
		
		
		public void run()
		{
			System.out.println(CoreClass.mainCharacter.getDirection()+"+++++++++++++++++++++++++++++++++++++++++");
			switch(CoreClass.mainCharacter.getDirection())
			{
			
				case 1:
					
						CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() + (CoreClass.mainCharacter.idleImages(1).getWidth(null) / 2) - (CoreClass.mainCharacter.attackImage(1).getWidth(null) / 2));
						
						CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() - (CoreClass.mainCharacter.attackImage(1).getHeight(null) / 2));
							
						CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.attackImage(1));
						
						
							WhenShitHits.playerMeleeAttSetpiece(1);
						
							
							try
							{
								
								Thread.sleep(200);
								
							} catch (InterruptedException e)
							{
								
								e.printStackTrace();
								
							}
								
						
						CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() - (CoreClass.mainCharacter.idleImages(1).getWidth(null) / 2) + (CoreClass.mainCharacter.attackImage(1).getWidth(null) / 2));
						
						CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() + (CoreClass.mainCharacter.attackImage(1).getHeight(null) / 2));
							
						CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.idleImages(1));
							
						attack = 0;
				
					break;
			
				case 2:
					
					//CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() + (CoreClass.mainCharacter.idleImages(1).getWidth(null) / 2) - (CoreClass.mainCharacter.attackImage(1).getWidth(null) / 2));
					
					CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() - (CoreClass.mainCharacter.attackImage(1).getHeight(null) / 2));
						
					CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.attackImage(2));
					
					WhenShitHits.playerMeleeAttSetpiece(2);
						
						try
						{
							
							Thread.sleep(200);
							
						} catch (InterruptedException e)
						{
							
							e.printStackTrace();
							
						}
							
					
					//CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() - (CoreClass.mainCharacter.idleImages(1).getWidth(null) / 2) + (CoreClass.mainCharacter.attackImage(1).getWidth(null) / 2));
					
					CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() + (CoreClass.mainCharacter.attackImage(1).getHeight(null) / 2));
						
					CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.idleImages(2));
						
					attack = 0;
			
				break;
					
				case 3:
					
					//CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() + (CoreClass.mainCharacter.idleImages(1).getWidth(null) / 2) - (CoreClass.mainCharacter.attackImage(1).getWidth(null) / 2));
					
					CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() - (CoreClass.mainCharacter.attackImage(1).getHeight(null) / 2));
						
					CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.attackImage(3));
					
					WhenShitHits.playerMeleeAttSetpiece(3);	
					
						try
						{
							
							Thread.sleep(200);
							
						} catch (InterruptedException e)
						{
							
							e.printStackTrace();
							
						}
							
					
					//CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() - (CoreClass.mainCharacter.idleImages(1).getWidth(null) / 2) + (CoreClass.mainCharacter.attackImage(1).getWidth(null) / 2));
					
					CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() + (CoreClass.mainCharacter.attackImage(1).getHeight(null) / 2));
						
					CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.idleImages(3));
						
					attack = 0;
			
				break;
					
				case 4:
					
					//CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() + (CoreClass.mainCharacter.idleImages(1).getWidth(null) / 2) - (CoreClass.mainCharacter.attackImage(1).getWidth(null) / 2));
					
					//CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() - (CoreClass.mainCharacter.attackImage(1).getHeight(null) / 2));
						
					CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.attackImage(4));
					
					WhenShitHits.playerMeleeAttSetpiece(4);
						
						try
						{
							
							Thread.sleep(200);
							
						} catch (InterruptedException e)
						{
							
							e.printStackTrace();
							
						}
							
					
					//CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() - (CoreClass.mainCharacter.idleImages(1).getWidth(null) / 2) + (CoreClass.mainCharacter.attackImage(1).getWidth(null) / 2));
					
					//CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() + (CoreClass.mainCharacter.attackImage(1).getHeight(null) / 2));
						
					CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.idleImages(4));
						
					attack = 0;
			
				break;
					
				case 5:
					
					CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() - (CoreClass.mainCharacter.idleImages(1).getWidth(null)));
						
					CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.attackImage(5));
					
					WhenShitHits.playerMeleeAttSetpiece(5);
						
						try
						{
							
							Thread.sleep(200);
							
						} catch (InterruptedException e)
						{
							
							e.printStackTrace();
							
						}
							
					
					CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() + (CoreClass.mainCharacter.idleImages(1).getWidth(null)));
					CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.idleImages(5));
						
					attack = 0;
			
					
					break;
					
				case 6:
					
					CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() - (CoreClass.mainCharacter.attackImage(1).getWidth(null) / 2));
					
					//CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() - (CoreClass.mainCharacter.attackImage(1).getHeight(null) / 2));
						
					CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.attackImage(6));
					
					WhenShitHits.playerMeleeAttSetpiece(6);
						
						try
						{
							
							Thread.sleep(200);
							
						} catch (InterruptedException e)
						{
							
							e.printStackTrace();
							
						}
							
					
					CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() + (CoreClass.mainCharacter.attackImage(1).getWidth(null) / 2));
					
					//CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() + (CoreClass.mainCharacter.attackImage(1).getHeight(null) / 2));
						
					CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.idleImages(6));
						
					attack = 0;
			
				break;
					
				case 7:
					
					CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() - (CoreClass.mainCharacter.attackImage(1).getWidth(null) / 2));
					
					CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() - (CoreClass.mainCharacter.attackImage(1).getHeight(null) / 2));
						
					CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.attackImage(7));
					
					WhenShitHits.playerMeleeAttSetpiece(7);
						
						try
						{
							
							Thread.sleep(200);
							
						} catch (InterruptedException e)
						{
							
							e.printStackTrace();
							
						}
							
					
					CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() + (CoreClass.mainCharacter.attackImage(1).getWidth(null) / 2));
					
					CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() + (CoreClass.mainCharacter.attackImage(1).getHeight(null) / 2));
						
					CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.idleImages(7));
						
					attack = 0;
			
				break;
					
				case 8:
					
					CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() - (CoreClass.mainCharacter.attackImage(1).getWidth(null) / 2));
					
					CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() - (CoreClass.mainCharacter.attackImage(1).getHeight(null) / 2));
						
					CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.attackImage(8));
					
					WhenShitHits.playerMeleeAttSetpiece(8);
						
						try
						{
							
							Thread.sleep(200);
							
						} catch (InterruptedException e)
						{
							
							e.printStackTrace();
							
						}
							
					
					CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() + (CoreClass.mainCharacter.attackImage(1).getWidth(null) / 2));
					
					CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() + (CoreClass.mainCharacter.attackImage(1).getHeight(null) / 2));
						
					CoreClass.mainCharacter.setPic(CoreClass.mainCharacter.idleImages(8));
						
					attack = 0;
			
				break;
			}
			

			
		}
		
	};
	private Thread northMove = new Thread() {
		
		Image[] temp = CoreClass.mainCharacter.northAnamation();
		
		public void run()
		{
			
			CoreClass.mainCharacter.setDirection(1);
			
			
			try
			{
				
				while(up == true && (right == false && left == false && down == false) && (attack == 0))
				{
					
					CoreClass.mainCharacter.setPic(temp[0]);
				
						if(onPath == true)
						{
							
							Thread.sleep(200);
						
						}
						else
						{
							
							Thread.sleep(400);
							
						}
						
					if(up == true && (right == false && left == false && down == false) && (attack == 0))
					{
						
						CoreClass.mainCharacter.setPic(temp[1]);
						
							if(onPath == true)
							{
								
								Thread.sleep(200);
							
							}
							else
							{
								
								Thread.sleep(400);
								
							}
							
					}
	
				}
				
					CoreClass.mainCharacter.setPic(temp[2]);
			
			}
			catch(InterruptedException e)
			{
				
				CoreClass.mainCharacter.setPic(temp[2]);
				
			}
			
		}
				
	};
	
	
	private Thread northEastMove = new Thread() {
		
		Image[] temp = CoreClass.mainCharacter.northEastAnamation();
		
		public void run()
		{
			
			CoreClass.mainCharacter.setDirection(2);
			
			try
			{
				
				while((up == true && right == true) && (left == false && down == false) && (attack == 0))
				{
					
					CoreClass.mainCharacter.setPic(temp[0]);
				
						if(onPath == true)
						{
							
							Thread.sleep(200);
						
						}
						else
						{
							
							Thread.sleep(400);
							
						}
						
					if((up == true && right == true) && (left == false && down == false) && (attack == 0))
					{
						
						CoreClass.mainCharacter.setPic(temp[1]);
						
							if(onPath == true)
							{
								
								Thread.sleep(200);
							
							}
							else
							{
								
								Thread.sleep(400);
								
							}
							
					}
	
				}
				
					CoreClass.mainCharacter.setPic(temp[2]);
			
			}
			catch(InterruptedException e)
			{
				
				CoreClass.mainCharacter.setPic(temp[2]);
				
			}
			
		}
		
	};
	
	private Thread eastMove = new Thread() {
		
		Image[] temp = CoreClass.mainCharacter.eastAnamation();
		
		public void run()
		{
			
			CoreClass.mainCharacter.setDirection(3);
			
			try
			{
				
				while(right == true && (up == false && left == false && down == false) && (attack == 0))
				{
					
					CoreClass.mainCharacter.setPic(temp[0]);
				
						if(onPath == true)
						{
							
							Thread.sleep(200);
						
						}
						else
						{
							
							Thread.sleep(400);
							
						}
						
					if(right == true && (up == false && left == false && down == false) && (attack == 0))
					{
						
						CoreClass.mainCharacter.setPic(temp[1]);
						
							if(onPath == true)
							{
								
								Thread.sleep(200);
							
							}
							else
							{
								
								Thread.sleep(400);
								
							}
							
					}
	
				}
				
					CoreClass.mainCharacter.setPic(temp[2]);
			
			}
			catch(InterruptedException e)
			{
				
				CoreClass.mainCharacter.setPic(temp[2]);
				
			}
			
		}
		
	};
	
	private Thread southEastMove = new Thread() {
		
		Image[] temp = CoreClass.mainCharacter.southEastAnamation();
		
		public void run()
		{
			
			CoreClass.mainCharacter.setDirection(4);
			
			try
			{
				
				while((down == true && right == true) && (up == false && left == false) && (attack == 0))
				{
					
					CoreClass.mainCharacter.setPic(temp[0]);
				
						if(onPath == true)
						{
							
							Thread.sleep(200);
						
						}
						else
						{
							
							Thread.sleep(400);
							
						}
						
					if((down == true && right == true) && (up == false && left == false) && (attack == 0))
					{
						
						CoreClass.mainCharacter.setPic(temp[1]);
						
							if(onPath == true)
							{
								
								Thread.sleep(200);
							
							}
							else
							{
								
								Thread.sleep(400);
								
							}
							
					}
	
				}
				
					CoreClass.mainCharacter.setPic(temp[2]);
			
			}
			catch(InterruptedException e)
			{
				
				CoreClass.mainCharacter.setPic(temp[2]);
				
			}
			
		}
		
	};
	
	private Thread southMove = new Thread() {
		
		Image[] temp = CoreClass.mainCharacter.southAnamation();
		
		public void run()
		{
			
			CoreClass.mainCharacter.setDirection(5);
			
			
			try
			{
				
				while(down == true && (up == false && right == false && left == false) && (attack == 0))
				{
					
					CoreClass.mainCharacter.setPic(temp[0]);
				
						if(onPath == true)
						{
							
							Thread.sleep(200);
						
						}
						else
						{
							
							Thread.sleep(400);
							
					}
						
					if(down == true && (up == false && right == false && left == false) && (attack == 0))
					{
						
						CoreClass.mainCharacter.setPic(temp[1]);
						
							if(onPath == true)
							{
								
								Thread.sleep(200);
							
							}
							else
							{
								
								Thread.sleep(400);
								
							}
							
					}
	
				}
				
					CoreClass.mainCharacter.setPic(temp[2]);
			
			}
			catch(InterruptedException e)
			{
				
				CoreClass.mainCharacter.setPic(temp[2]);
				
			}
			
		}
		
	};
	
	private Thread southWestMove = new Thread() {
		
		Image[] temp = CoreClass.mainCharacter.southWestAnamation();
		
		public void run()
		{
			
			CoreClass.mainCharacter.setDirection(6);
			
			try
			{
				
				while((down == true && left == true) && (up == false && right == false) && (attack == 0))
				{
					
					CoreClass.mainCharacter.setPic(temp[0]);
				
						if(onPath == true)
						{
							
							Thread.sleep(200);
						
						}
						else
						{
							
							Thread.sleep(400);
							
						}
						
					if((down == true && left == true) && (up == false && right == false) && (attack == 0))
					{
						
						CoreClass.mainCharacter.setPic(temp[1]);
						
							if(onPath == true)
							{
								
								Thread.sleep(200);
							
							}
							else
							{
								
								Thread.sleep(400);
								
							}
								
					}
	
				}
				
					CoreClass.mainCharacter.setPic(temp[2]);
			
			}
			catch(InterruptedException e)
			{
				
				CoreClass.mainCharacter.setPic(temp[2]);
				
			}
			
		}
		
	};
	
	private Thread westMove = new Thread() {
		
		Image[] temp = CoreClass.mainCharacter.westAnamation();
		
		public void run()
		{
			
			CoreClass.mainCharacter.setDirection(7);
			
			try
			{
				
				while(left == true && (right == false && up == false && down == false) && (attack == 0))
				{
					
					CoreClass.mainCharacter.setPic(temp[0]);
				
						if(onPath == true)
						{
							
							Thread.sleep(200);
						
						}
						else
						{
							
							Thread.sleep(400);
							
						}
						
					if(left == true && (right == false && up == false && down == false) && (attack == 0))
					{
						
						CoreClass.mainCharacter.setPic(temp[1]);
						
							if(onPath == true)
							{
								
								Thread.sleep(200);
							
							}
							else
							{
								
								Thread.sleep(400);
								
							}
							
					}
	
				}
				
					CoreClass.mainCharacter.setPic(temp[2]);
			
			}
			catch(InterruptedException e)
			{
				
				CoreClass.mainCharacter.setPic(temp[2]);
				
			}
			
		}
		
	};
	
	private Thread northWestMove = new Thread() {
		
		Image[] temp = CoreClass.mainCharacter.northWestAnamation();
		
		public void run()
		{
			
			CoreClass.mainCharacter.setDirection(8);
			
			try
			{
				
				while((left == true && up == true) && (right == false && down == false) && (attack == 0))
				{
					
					CoreClass.mainCharacter.setPic(temp[0]);
				
						if(onPath == true)
						{
							
							Thread.sleep(200);
						
						}
						else
						{
							
							Thread.sleep(400);
							
						}
						
					if((left == true && up == true) && (right == false && down == false) && (attack == 0))
					{
						
						CoreClass.mainCharacter.setPic(temp[1]);
						
							if(onPath == true)
							{
								
								Thread.sleep(200);
							
							}
							else
							{
								
								Thread.sleep(400);
								
							}
							
					}
	
				}
				
					CoreClass.mainCharacter.setPic(temp[2]);
			
			}
			catch(InterruptedException e)
			{
				
				CoreClass.mainCharacter.setPic(temp[2]);
				
			}
			
		}
		
	};
	
	
	public void run()
	{
		
		while(true)
		{
			//CONSIDER CHNAGEING MULTIPLE OCCURENCES OF GETTING X AND Y VALUES TO COORDINATE OBJECTS AND WORKING WITH THAT ONE RETREIVED OBJECT	 
			
			//update keyboard
			CoreClass.InCa.update();	
			
			//find the tile code for the tile the character currently occupies
			int tile = CoreClass.getCurrentLevel().getMap()[CoreClass.mainCharacter.getActualCenter().getY()/102][CoreClass.mainCharacter.getActualCenter().getX()/102];
		
			//check logic later to ensure accuracy
				if((tile != 1) && CoreClass.mainCharacter.getSpeed() == baseSpeed)
				{
					
					onPath = true;
					
						CoreClass.mainCharacter.setSpeed(CoreClass.mainCharacter.getSpeed() * 2);
					
				}
				else if((tile == 1) && CoreClass.mainCharacter.getSpeed() != baseSpeed)
				{
					
					onPath = false;
					
						CoreClass.mainCharacter.setSpeed(CoreClass.mainCharacter.getSpeed() / 2);
					
				}
			
				//testing for health bar
				if(CoreClass.InCa.isKeyDown(KeyEvent.VK_R))
				{
					Random r = new Random();
					
					//testing the player getting hit/losing hp
					int hp = CoreClass.mainCharacter.getHitpoints();
					hp -= r.nextInt(10);
					
					if(hp < 0){
						hp = 0;
					}
					
					CoreClass.mainCharacter.setHitpoints(hp);
					CoreClass.healthBar.update();
				}
				//testing for health bar
				if(CoreClass.InCa.isKeyDown(KeyEvent.VK_T))
				{
					//testing the player getting healed
					int hp = CoreClass.mainCharacter.getHitpoints();
					hp += 10;
					
					if(hp > 100){
						hp = 100;
					}
					
					CoreClass.mainCharacter.setHitpoints(hp);
					CoreClass.healthBar.update();
				}
				
				
				if(CoreClass.InCa.isKeyDown(KeyEvent.VK_P))
				{
					
					//pause the game
	
				}
				
				
				if(CoreClass.InCa.isKeyDown(KeyEvent.VK_SPACE) && attack == 0)
				{
					
					System.out.println(attack+"Attack"+CoreClass.mainCharacter.getDirection());
					attack = 1;
					es.execute(attacking);
					
				}

	
		
				if(WhenShitHits.rightHitSetpiece())
				{
					
					if(CoreClass.InCa.isKeyDown(KeyEvent.VK_RIGHT))
					{
						//System.out.println("p");
						
						right = true;
						
						
						//move player map position as long as it does not go outside of the map boundries **** fix max x boundry
						if(CoreClass.mainCharacter.getMapX() + (CoreClass.mainCharacter.getSpeed() * 0.5) <= CoreClass.getCurrentLevel().getMaxBound()- CoreClass.mainCharacter.getPic().getWidth(null))
						{
							
							CoreClass.mainCharacter.setMapX(CoreClass.mainCharacter.getMapX() + CoreClass.mainCharacter.getSpeed());
							
						}
		
						//move camera map postion as long as the view port will not go outside of the map boundires
						if((CoreClass.getCam().getMapX() - CoreClass.mainCharacter.getMapX()) < 0 && (CoreClass.getCam().getMapX() + (CoreClass.fame.getWidth() / 2)) + CoreClass.mainCharacter.getSpeed() <= CoreClass.getCurrentLevel().getMaxBound())
						{
		
							CoreClass.getCam().setMapX(CoreClass.getCam().getMapX() + CoreClass.mainCharacter.getSpeed());
							
						}
						else
						{
							//remove -50 later
							if((CoreClass.mainCharacter.getxPos() + CoreClass.mainCharacter.getSpeed()) <= (CoreClass.fame.getWidth()-CoreClass.mainCharacter.getPic().getWidth(null)))
							{//handles updating player position on screen *** fix where its drawn so the center of player is center of screen
		
									CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() + CoreClass.mainCharacter.getSpeed());							
		
							}
							
						}
						
					}
					else
					{
						
						
						right = false;
						
					}
					
				}
				
				
				
				if(WhenShitHits.leftHitSetpiece())
				{
				
					if(CoreClass.InCa.isKeyDown(KeyEvent.VK_LEFT))
					{
						
						
						left = true;
						//System.out.println(isMoving +" "+ left);
						
						//move player map position as long as it does not go outside of the map boundries 
						if(CoreClass.mainCharacter.getMapX() - (CoreClass.mainCharacter.getSpeed() * 0.5) >= 0)
						{
							
							CoreClass.mainCharacter.setMapX(CoreClass.mainCharacter.getMapX() - CoreClass.mainCharacter.getSpeed());
							
						}
		
						//move camera map postion as long as the view port will not go outside of the map boundires
						if((CoreClass.getCam().getMapX() - CoreClass.mainCharacter.getMapX()) > 0 && (CoreClass.getCam().getMapX() - (CoreClass.fame.getWidth() / 2)) - CoreClass.mainCharacter.getSpeed() >= 0)
						{
							
							CoreClass.getCam().setMapX(CoreClass.getCam().getMapX() - CoreClass.mainCharacter.getSpeed());
							
						}
						else
						{
							if((CoreClass.mainCharacter.getxPos() - CoreClass.mainCharacter.getSpeed()) >= 0)
							{//handles updating player position on screen *** fix where its drawn so the center of player is center of screen
								
									CoreClass.mainCharacter.setxPos(CoreClass.mainCharacter.getxPos() - CoreClass.mainCharacter.getSpeed());
									
							}
							
						}
		
					}
					else
					{
						
						
						left = false;
						//System.out.println(isMoving +" "+ left);
					}
					
				}		
					
					
				if(WhenShitHits.upHitSetpiece())	
				{	
					if(CoreClass.InCa.isKeyDown(KeyEvent.VK_UP))
					{
						//System.out.println("h");
							
							up = true;
						 
						//move player map position as long as it does not go outside of the map boundries 
						if(CoreClass.mainCharacter.getMapY() - (CoreClass.mainCharacter.getSpeed() * 0.5) > -20)
						{
		
							CoreClass.mainCharacter.setMapY(CoreClass.mainCharacter.getMapY() - CoreClass.mainCharacter.getSpeed());
								
						}
		
						//move camera map postion as long as the view port will not go outside of the map boundires
						if((CoreClass.getCam().getMapY() - CoreClass.mainCharacter.getMapY()) > 0 && (CoreClass.getCam().getMapY() - (CoreClass.fame.getHeight() / 2)) - CoreClass.mainCharacter.getSpeed() >= 0)
						{
		
							CoreClass.getCam().setMapY(CoreClass.getCam().getMapY() - CoreClass.mainCharacter.getSpeed());
								
						}
						else
						{
								
							if((CoreClass.mainCharacter.getyPos() - CoreClass.mainCharacter.getSpeed()) > 0)
							{//handles updating player position on screen *** fix where its drawn so the center of player is center of screen
		
									CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() - CoreClass.mainCharacter.getSpeed());
		
							}
								
						}
							
					}
					else
					{
						
						
						up = false;
						
					}
					 
				}
				
				
				
				if(WhenShitHits.downHitSetpiece())
				{
					
					if(CoreClass.InCa.isKeyDown(KeyEvent.VK_DOWN))
					{
						//System.out.println("ha");
						
						down = true;
						
		
						//move player map position as long as it does not go outside of the map boundries **** fix max x boundry                                 //check this right here later
						if(CoreClass.mainCharacter.getMapY() + (CoreClass.mainCharacter.getSpeed() * 0.5) <= CoreClass.getCurrentLevel().getMaxBound()-CoreClass.mainCharacter.getPic().getHeight(null)*2-20)
						{
		
							CoreClass.mainCharacter.setMapY(CoreClass.mainCharacter.getMapY() + CoreClass.mainCharacter.getSpeed());
		
						}
							
						//move camera map postion as long as the view port will not go outside of the map boundires
						if((CoreClass.getCam().getMapY() - CoreClass.mainCharacter.getMapY()) < 0 && (CoreClass.getCam().getMapY() + (CoreClass.fame.getHeight() / 2)) + CoreClass.mainCharacter.getSpeed() <= CoreClass.getCurrentLevel().getMaxBound())
						{
		
								CoreClass.getCam().setMapY(CoreClass.getCam().getMapY() + CoreClass.mainCharacter.getSpeed());
		
						}
						else
						{	
							//remove -50 later
							if((CoreClass.mainCharacter.getyPos() + CoreClass.mainCharacter.getSpeed()) <= (CoreClass.fame.getHeight()-CoreClass.mainCharacter.getPic().getHeight(null)*2))
							{//handles updating player position on screen *** fix where its drawn so the center of player is center of screen
		
										CoreClass.mainCharacter.setyPos(CoreClass.mainCharacter.getyPos() + CoreClass.mainCharacter.getSpeed());
										
							}
								
						}
							
					}
					else
					{
						
						
						down = false;
						
					}
				
				}
				
				
					//System.out.println(up);
					//System.out.println(down);
					//System.out.println(right);
					//System.out.println(left);
					if((up == true && right == true))
					{
						
						es.execute(northEastMove);
						
							CoreClass.mainCharacter.setDirection(2);
						
					}
					else if((up == true && left == true))
					{

						es.execute(northWestMove);
						CoreClass.mainCharacter.setDirection(8);
							//facing = 8;
							
					}
					else if((down == true && right == true))
					{
					
						es.execute(southEastMove);
						CoreClass.mainCharacter.setDirection(4);
							//facing = 4;
						
					}
					else if((down == true && left == true))
					{

						es.execute(southWestMove);
						CoreClass.mainCharacter.setDirection(6);
							//facing = 6;
						
					}
					else if(right == true)
					{
						
						es.execute(eastMove);
						CoreClass.mainCharacter.setDirection(3);
							//facing = 3;
								
					}
					else if(left == true)
					{
					
						es.execute(westMove);
						CoreClass.mainCharacter.setDirection(7);
							//facing = 7;

					}
					else if(up == true)
					{
					
						es.execute(northMove);
						CoreClass.mainCharacter.setDirection(1);
							//facing = 1;
						
					}
					else if(down == true)
					{
						
						es.execute(southMove);
						CoreClass.mainCharacter.setDirection(5);
							//facing = 5;
	
					}

								

				 CoreClass.updateNeededTiles(); 
				 
					try
					{
						
						Thread.sleep(20);
						
					} 
					catch (InterruptedException e)
					{
		
						//e.printStackTrace();
						
					}
				
		}
		
	}
	
}
