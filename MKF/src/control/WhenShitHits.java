package control;


import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import physicalGameObjects.MeleeEnemy;

import logicalGameObjects.Actor;
import logicalGameObjects.Coordinate;
import logicalGameObjects.Enemy;
import logicalGameObjects.PlatformObjects;
import logicalGameObjects.Setpiece;


/**
 * All things collision
 * 
 * @author Thomas Capach
 *
 */
public class WhenShitHits
{
	/**

	 */
	public static void enemyMeleeAttackPlayer(Polygon p)
	{		

		boolean[] hits = {false, false, false, false};
		boolean contact = false;
		
		if(p == null)
		{
			System.out.println("fuck");
		}
		
		if(intersectionOf2Shapes(p, CoreClass.mainCharacter.getUpBox()))
		{
			
			hits[0] = true;
			
			contact = true;
			
		}
		
		if(intersectionOf2Shapes(p, CoreClass.mainCharacter.getDownBox()))
		{
			
			hits[1] = true;
			
			contact = true;
			
		}
		
		if(intersectionOf2Shapes(p, CoreClass.mainCharacter.getRightBox()))
		{
			
			hits[2] = true;
			
			contact = true;
			
		}
		
		if(intersectionOf2Shapes(p, CoreClass.mainCharacter.getLeftBox()))
		{
			
			hits[3] = true;
			
			contact = true;
			
		}
		
		if(contact == true)
		{
			System.out.println("enemy hit");
		}
		
	}	
	
	/**
	 * Collision detection for when the player attempts to jump onto a a platform object. If the player has 
	 * jumped, is falling and falls within a certain a certain range around the object then the player will
	 * stop falling and land on top of the object
	 */
	public static void PlayerToPlatformObject()
	{
		List<PlatformObjects> pl = CoreClass.getCurrentPlat();
			
		if(CoreClass.mainCharacter.isFalling == true)
		{
			for(int x = 0; x < pl.size(); x++)
			{				
				int charYPos = CoreClass.mainCharacter.getyPos();
				int jumpTopRange = (( pl.get(x)).getyPos() - 25);
				int jumpBotRange = (pl.get(x).getyPos() + pl.get(x).getImage().getHeight(null) + 25);
								
				if(charYPos <= jumpTopRange && charYPos >= jumpBotRange )
				{					
					int revisedY = (int) (CoreClass.mainCharacter.getyPos() + (CoreClass.mainCharacter.getPic().getHeight(null) - 20));
					if(new Rectangle(CoreClass.mainCharacter.getxPos(), revisedY, 20, (int)CoreClass.mainCharacter.getPic().getWidth(null)).intersects(new Rectangle(pl.get(x).getxPos(), pl.get(x).getyPos(), pl.get(x).getImage().getHeight(null), pl.get(x).getImage().getWidth(null))))
					{						
						CoreClass.setLevelMinY(pl.get(x).getyPos());
					}
				}
			}			
		}		
	}	
	

	/**
	 *  Collision detection for the player's melee attack between the melee attack hit box and a set pieces hitbox 
	 * 
	 * @param i - the int value for the cardinal direction (see chart in movement manager) the player is facing
	 */
	public static void playerMeleeAttSetpiece(int i) 
	{		
	
		Polygon temp = null;
		
		List<Setpiece> everything = new ArrayList<Setpiece>();
		
		int x = CoreClass.mainCharacter.getMapX() / 102;
		int y = CoreClass.mainCharacter.getMapY() / 102;
		
		
			switch(i)
			{
			
				case 1:
					
						temp = CoreClass.mainCharacter.getNorthAttackBox();
					
					break;
			
				
				case 2:
					
						temp = CoreClass.mainCharacter.getNorthEastAttackBox();
					
					break;
				
					
				case 3:
					
						temp = CoreClass.mainCharacter.getEastAttackBox();
					
					break;
				
					
				case 4:
					
						temp = CoreClass.mainCharacter.getSouthEastAttackBox();
					
					break;
				
					
				case 5:
					
						temp = CoreClass.mainCharacter.getSouthAttackBox();
					
					break;
				
					
				case 6:
					
						temp = CoreClass.mainCharacter.getSouthWestAttackBox();
					
					break;
				
					
				case 7:
					
						temp = CoreClass.mainCharacter.getWestAttackBox();
					
					break;
				
					
				case 8:
					
						temp = CoreClass.mainCharacter.getNorthWestAttackBox();
					
					break;
				
			}
		
		
		
				for (int yy = y - 1; yy <= y + 1; yy++)
				{
					
					for (int xx = x - 1; xx <= x + 1; xx++)
					{
						
						ArrayList<Coordinate> al = new ArrayList<Coordinate> (CoreClass.getCurrentLevel().getPlaces().keySet());
		
							for(Coordinate c:al)
							{
								
								if(c.equals(new Coordinate(xx, yy)))
								{
		
									everything.addAll(CoreClass.getCurrentLevel().getPlaces().get(c));
		
								}
								
							}
		
					}
					
				}
		
		
		
					for(Setpiece s:everything)
					{
						
						if(s.isDestructive())
						{
								
							if(temp.intersects(s.getBox()))
							{
								
								s.attacked();
								
								
							}
							
						}
					
					}
					
	}
	
	
	/**
	 * Detects collision between the player's upper hitbox and a setpiece object
	 * 
	 * @return
	 */
	public static boolean upHitSetpiece()
	{
		//get character object
		Polygon temp = CoreClass.mainCharacter.getUpBox();
		
		//get characters location
		int x = CoreClass.mainCharacter.getMapX() / 102;
		int y = CoreClass.mainCharacter.getMapY() / 102;
		
		//all objects around
		List<Setpiece> everything = new ArrayList<Setpiece>();
		
        	//get all setpiece objects located around the character
			for (int yy = y - 1; yy <= y + 1; yy++)
			{
				
				for (int xx = x - 1; xx <= x + 1; xx++)
				{
					
					ArrayList<Coordinate> al = new ArrayList<Coordinate> (CoreClass.getCurrentLevel().getPlaces().keySet());
	
						for(Coordinate c:al)
						{
							
							if(c.equals(new Coordinate(xx, yy)))
							{
	
								everything.addAll(CoreClass.getCurrentLevel().getPlaces().get(c));
	
							}
							
						}
	
				}
				
			}
		
				//check all setpiece objects for intersection
				for(Setpiece s:everything)
				{
					//if collision has happened
					if(temp.intersects(s.getBox()))
					{
						
						//if you can not move through the object
						if(((Setpiece) s).isTangible())
						{
								
							return false;
							
						}
						else//if it can move through the object
						{	
							//if one of the directional keys is being pressed
							if(CoreClass.KeyIn.isKeyDown(KeyEvent.VK_S) || CoreClass.KeyIn.isKeyDown(KeyEvent.VK_W) || CoreClass.KeyIn.isKeyDown(KeyEvent.VK_D) || CoreClass.KeyIn.isKeyDown(KeyEvent.VK_A))
							{
								
								if(s.isExplosive()){
									((Setpiece) s).explode();
								} else {
									((Setpiece) s).collision();
								}
								
							}
								
							return true;
								
						}
						
					}
					
				}
		
		return true;
		
	}
	
	
	/**
	 *  Detects collision between the player's lower hitbox and a setpiece object
	 * 
	 * @return
	 */
	public static boolean downHitSetpiece()
	{//see upHitSetpiece()

		Polygon temp = CoreClass.mainCharacter.getDownBox();

		int x = CoreClass.mainCharacter.getMapX() / 102;
		int y = CoreClass.mainCharacter.getMapY() / 102;
		
		List<Setpiece> everything = new ArrayList<Setpiece>();

		
			for (int yy = y - 1; yy <= y + 1; yy++)
			{
				
				for (int xx = x - 1; xx <= x + 1; xx++)
				{
					
					ArrayList<Coordinate> al = new ArrayList<Coordinate> (CoreClass.getCurrentLevel().getPlaces().keySet());
	
						for(Coordinate c:al)
						{
							
							if(c.equals(new Coordinate(xx, yy)))
							{
	
								everything.addAll(CoreClass.getCurrentLevel().getPlaces().get(c));
	
							}
							
						}
	
				}
				
			}

			
				for(Setpiece s:everything)
				{
					
					if(temp.intersects(s.getBox()))
					{
							
						if(((Setpiece) s).isTangible())
						{
								
								return false;
							
						}
						else
						{
								
							if(CoreClass.KeyIn.isKeyDown(KeyEvent.VK_S) || CoreClass.KeyIn.isKeyDown(KeyEvent.VK_W) || CoreClass.KeyIn.isKeyDown(KeyEvent.VK_D) || CoreClass.KeyIn.isKeyDown(KeyEvent.VK_A))
							{
									
								if(s.isExplosive()){
									((Setpiece) s).explode();
								} else {
									((Setpiece) s).collision();
								}
								
							}
								
								return true;
								
						}
						
					}
					
				}
		
		return true;
		
	}
	
	
	/**
	 *  Detects collision between the player's left hitbox and a setpiece object
	 * 
	 * @return
	 */
	public static boolean leftHitSetpiece()
	{//see upHitSetpiece()

		Polygon temp = CoreClass.mainCharacter.getLeftBox();
		
		int x = CoreClass.mainCharacter.getMapX() / 102;
		int y = CoreClass.mainCharacter.getMapY() / 102;
		
		List<Setpiece> everything = new ArrayList<Setpiece>();

        
			for (int yy = y - 1; yy <= y + 1; yy++)
			{
				
				for (int xx = x - 1; xx <= x + 1; xx++)
				{
					
					ArrayList<Coordinate> al = new ArrayList<Coordinate> (CoreClass.getCurrentLevel().getPlaces().keySet());
	
						for(Coordinate c:al)
						{
							
							if(c.equals(new Coordinate(xx, yy)))
							{
	
								everything.addAll(CoreClass.getCurrentLevel().getPlaces().get(c));
	
							}
							
						}
	
				}
				
			}
		

				for(Setpiece s:everything)
				{
					
					if(temp.intersects(s.getBox()))
					{

						if(((Setpiece) s).isTangible())
						{
								
							return false;
							
						}
						else
						{
							
							if(CoreClass.KeyIn.isKeyDown(KeyEvent.VK_S) || CoreClass.KeyIn.isKeyDown(KeyEvent.VK_W) || CoreClass.KeyIn.isKeyDown(KeyEvent.VK_D) || CoreClass.KeyIn.isKeyDown(KeyEvent.VK_A))
							{
									
								if(s.isExplosive()){
									((Setpiece) s).explode();
								} else {
									((Setpiece) s).collision();
								}
								
							}
							
								return true;
								
						}
	
					}
					
				}
		
		return true;
		
	}
	
	
	/**
	 *  Detects collision between the player's right hitbox and a setpiece object
	 * 
	 * @return
	 */
	public static boolean rightHitSetpiece()
	{//see upHitSetpiece()

		Polygon temp = CoreClass.mainCharacter.getRightBox();		
		
		int x = CoreClass.mainCharacter.getMapX() / 102;
		int y = CoreClass.mainCharacter.getMapY() / 102;
		
		List<Setpiece> everything = new ArrayList<Setpiece>();
  

				for (int yy = y - 1; yy <= y + 1; yy++)
				{
					
					for (int xx = x - 1; xx <= x + 1; xx++)
					{
						
						ArrayList<Coordinate> al = new ArrayList<Coordinate> (CoreClass.getCurrentLevel().getPlaces().keySet());
		
							for(Coordinate c:al)
							{
								
								if(c.equals(new Coordinate(xx, yy)))
								{
		
									everything.addAll(CoreClass.getCurrentLevel().getPlaces().get(c));
		
								}
								
							}
		
					}
					
				}
		
		
					for(Setpiece s:everything)
					{
							
						if(temp.intersects(s.getBox()))
						{
							
							if(((Setpiece) s).isTangible())
							{
								
								return false;
									
							}
							else
							{
								
								if(CoreClass.KeyIn.isKeyDown(KeyEvent.VK_S) || CoreClass.KeyIn.isKeyDown(KeyEvent.VK_W) || CoreClass.KeyIn.isKeyDown(KeyEvent.VK_D) || CoreClass.KeyIn.isKeyDown(KeyEvent.VK_A))
								{
										
									if(s.isExplosive()){
										((Setpiece) s).explode();
									} else {
										((Setpiece) s).collision();
									}
									
								}
									
									return true;
								
							}
							
						}
					
					}
				
		return true;
				
	}
	

	/**
	 * Detect the collision of a setpiece object upon insertion into a level with other setpiece objects already safly added
	 * 
	 * @param temp - set piece to be added
	 * @param list - list of setpiece already added
	 * @param a - position of the setpiece being added in the list of setpieces
	 * 
	 * @return - 
	 */
	public static boolean setpiece2setpiece(Setpiece temp, List<Setpiece> list, int a)
	{
		
		try
		{
			//find any existing object that would collied with setpiece being added
			for(int x = 0; x < list.size(); x++)
			{
			
				if(x != a)
				{
					//if an existing object is in the way
					if(temp.getBox().intersects(((Setpiece) list.get(x)).getBox()))
					{
					
						return true;
					
					}
				
				}
		
			}
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			
		}
		
			return false;
		
	}
	
	
	/**
	 * Detect a collision between an enemy object and a setpiece object as the enemy object is moving in one of the 8 possible directions.
	 * 
	 * @param e - enemy object
	 * @param i - int
	 * @return  - a boolean [] containing which paths are blocked
	 */
	public static boolean[] Enemy2Setpiece(Enemy e, int i)
	{
		
		Polygon temp;
		
		//set all path as possible
		boolean [] paths = {false, false, false, false};
		
		//enemy object coordinates
		int x = e.getxPos() / 102;
		int y = e.getyPos() / 102;
		
		//list of setpiece objects
		List<Setpiece> everything = new ArrayList<Setpiece>();
  
				//get all setpiece objects and add them to the list
				for (int yy = y - 1; yy <= y + 1; yy++)
				{
					
					for (int xx = x - 1; xx <= x + 1; xx++)
					{
						
						ArrayList<Coordinate> al = new ArrayList<Coordinate> (CoreClass.getCurrentLevel().getPlaces().keySet());
		
							for(Coordinate c:al)
							{
								
								if(c.equals(new Coordinate(xx, yy)))
								{
		
									everything.addAll(CoreClass.getCurrentLevel().getPlaces().get(c));
		
								}
								
							}
		
					}
					
				}
		
					//check if any of the setpiece objects intersect with the specified enemy object
					for(Setpiece s:everything)
					{
						//if setpiece touches one of the path blocks.
						boolean isIntangible = false;
						
						switch(i)
						{
						
							case 1://moving north
								
									//FULL EXPLINATION
									temp = e.getUpPathBox();
									
										//intersection occurred
										if(temp.intersects(s.getBox()))
										{
											
											//if the object is solid and can not be passed through
											if(((Setpiece) s).isTangible())
											{
												
												paths[0] = true;
											
											}
											else//enemy can pass through setpiece object
											{
												
												temp = e.getUpBox();
												
													//if enemies north hitbox intersects with setpiece object
													if(temp.intersects(s.getBox()))
													{
														//indicate collision
														isIntangible = true;
														
													}
												
											}
											
										}
										
										//Refer to full explanation at the beginning of case:1
									temp = e.getRighPathtBox();
										
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[1] = true;
											
											}
											else
											{
												
												temp = e.getRightBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
										
										//Refer to full explanation at the beginning of case:1
									temp = e.getLeftPathBox();
										
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[3] = true;
											
											}
											else
											{
												
												temp = e.getLeftBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
								
								break;
								
							case 2://moving northeast
								
									temp = e.getUpPathBox();
									
									//Refer to full explanation at the beginning of case:1
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[0] = true;
											
											}
											else
											{
												
												temp = e.getUpBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
										
										//Refer to full explanation at the beginning of case:1
									temp = e.getRighPathtBox();
									
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[1] = true;
											
											}
											else
											{
												
												temp = e.getRightBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
								
								break;
								
							case 3://moving east
								
									temp = e.getRighPathtBox();
									
									//Refer to full explanation at the beginning of case:1
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[1] = true;
											
											}
											else
											{
												
												temp = e.getRightBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
										
										//Refer to full explanation at the beginning of case:1
									temp = e.getUpPathBox();
										
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[0] = true;
											
											}
											else
											{
												
												temp = e.getUpBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
										
										//Refer to full explanation at the beginning of case:1
									temp = e.getDownPathBox();
										
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[2] = true;
											
											}
											else
											{
												
												temp = e.getDownBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
								
								break;
								
							case 4://moving southeast
								
									temp = e.getRighPathtBox();
									
									//Refer to full explanation at the beginning of case:1
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[1] = true;
											
											}
											else
											{
												
												temp = e.getRightBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
									
										//Refer to full explanation at the beginning of case:1
									temp = e.getDownPathBox();
									
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[2] = true;
											
											}
											else
											{
												
												temp = e.getDownBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
								
								break;
								
							case 5://moving south
								
									temp = e.getDownPathBox();
									
									//Refer to full explanation at the beginning of case:1
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[2] = true;
											
											}
											else
											{
												
												temp = e.getDownBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
										
										//Refer to full explanation at the beginning of case:1
									temp = e.getRighPathtBox();
										
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[1] = true;
											
											}
											else
											{
												
												temp = e.getRightBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
										
										//Refer to full explanation at the beginning of case:1
									temp = e.getLeftPathBox();
										
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[3] = true;
											
											}
											else
											{
												
												temp = e.getLeftBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
								
								break;
								
							case 6://moving southwest
								
									temp = e.getDownPathBox();
									
									//Refer to full explanation at the beginning of case:1
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[2] = true;
											
											}
											else
											{
												
												temp = e.getDownBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
									
									temp = e.getLeftPathBox();
									
									//Refer to full explanation at the beginning of case:1
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[3] = true;
											
											}
											else
											{
												
												temp = e.getLeftBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
								
								break;
								
							case 7://moving west
								
									temp = e.getLeftPathBox();
									
									//Refer to full explanation at the beginning of case:1
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[3] = true;
											
											}
											else
											{
												
												temp = e.getLeftBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
										
										//Refer to full explanation at the beginning of case:1
									temp = e.getUpPathBox();
										
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[0] = true;
											
											}
											else
											{
												
												temp = e.getUpBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
									
										//Refer to full explanation at the beginning of case:1
									temp = e.getDownPathBox();
										
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[2] = true;
											
											}
											else
											{
												
												temp = e.getDownBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
								
								break;
								
							case 8://moving northwest
								
									temp = e.getUpPathBox();
									
									//Refer to full explanation at the beginning of case:1
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[0] = true;
											
											}
											else
											{
												
												temp = e.getUpBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
									
										//Refer to full explanation at the beginning of case:1
									temp = e.getLeftPathBox();
									
										if(temp.intersects(s.getBox()))
										{
											
											if(((Setpiece) s).isTangible())
											{
												
												paths[3] = true;
											
											}
											else
											{
												
												temp = e.getLeftBox();
												
												if(temp.intersects(s.getBox()))
												{
													
													isIntangible = true;
													
												}
												
											}
											
										}
								
								break;
							

						}

							//if an intangible object has made a collision with a hitbox then start the setpieces  interaction animation
							if(isIntangible == true)
							{
												
								((Setpiece) s).collision();

							}
					
					}
				
		return paths;
		
	}
	
	/**
	 * return all the objects the character currently sees
	 * 
	 * @return Object[]
	 */
	public static List<Object> sightTest(Enemy JohnBelushi)
	{//get sight, get actor objects, test collition, return objects
	
		//all the objects I see
		List<Object> foundYou = new ArrayList<Object>();
		
		//if you see the main character
		if(JohnBelushi.getseeingBox().intersects(CoreClass.mainCharacter.getHitbox()))
		{
			
			foundYou.add(CoreClass.mainCharacter);
			
		}
		else
		{
			
			List<Enemy> watchingYou = new ArrayList<Enemy>();
			
				watchingYou.addAll(CoreClass.getAllEn());
				watchingYou.remove(JohnBelushi);
				
				
				for(Enemy i:watchingYou)
				{
					
					if( JohnBelushi.getseeingBox().intersects(i.getHitbox()))
					{
						
						foundYou.add(i);
						
					}
					
				}
		}

		
		 return foundYou;
		 
	}
	
	/**
	 * check if the character hears anything
	 * 
	 * @return
	 */
	public static boolean hearingTest(Enemy seanConnery)
	{
		//what objects do i grab to test, actors obviously, but it's possible that setpieces will produce sound when i play make contact with them. possible: boolean, test all on screen objects
		
		if(CoreClass.mainCharacter.getSoundObject().intersects(seanConnery.getHitbox()))
		{
			return true;
		}
		
			return false;
		 
	}
	
	/**
	 * check the intersection of two polygon objects
	 * 
	 * @param a - Shape object to be compared
	 * @param b - Shape object to be compared
	 * 
	 * @return boolean - if intersection has occurred
	 */
	public static boolean intersectionOf2Shapes(Shape a, Shape b)
	{
		
		   Area areaA = new Area(a);
		   
		   		areaA.intersect(new Area(b));
		   
		   			return !areaA.isEmpty();
	
	}

	/**
	 * 
	 * right now checks all enemies on the board, improve efficiency later
	 * 
	 * @param i
	 */
	public static void playerMeleeAttEnemy(int i)
	{
		try {
		Polygon temp = null;
		List<Enemy> everything = CoreClass.getCurrentEn();
		
			CoreClass.enemySemaphore.acquire();
			

		
		
		
		//int x = CoreClass.mainCharacter.getMapX() / 102;
		//int y = CoreClass.mainCharacter.getMapY() / 102;
		
		
			switch(i)
			{
			
				case 1:
					
						temp = CoreClass.mainCharacter.getNorthAttackBox();
					
					break;
			
				
				case 2:
					
						temp = CoreClass.mainCharacter.getNorthEastAttackBox();
					
					break;
				
					
				case 3:
					
						temp = CoreClass.mainCharacter.getEastAttackBox();
					
					break;
				
					
				case 4:
					
						temp = CoreClass.mainCharacter.getSouthEastAttackBox();
					
					break;
				
					
				case 5:
					
						temp = CoreClass.mainCharacter.getSouthAttackBox();
					
					break;
				
					
				case 6:
					
						temp = CoreClass.mainCharacter.getSouthWestAttackBox();
					
					break;
				
					
				case 7:
					
						temp = CoreClass.mainCharacter.getWestAttackBox();
					
					break;
				
					
				case 8:
					
						temp = CoreClass.mainCharacter.getNorthWestAttackBox();
					
					break;
				
			}
		
		
		

		
		
		
					for(Enemy s:everything)
					{
						
						boolean[] hits = {false, false, false, false};
						boolean contact = false;
						
						if(intersectionOf2Shapes(temp, s.getUpBox()))
						{
							
							hits[0] = true;
							
							contact = true;
							
						}
						
						if(intersectionOf2Shapes(temp, s.getDownBox()))
						{
							
							hits[1] = true;
							
							contact = true;
							
						}
						
						if(intersectionOf2Shapes(temp, s.getRightBox()))
						{
							
							hits[2] = true;
							
							contact = true;
							
						}
						
						if(intersectionOf2Shapes(temp, s.getLeftBox()))
						{
							
							hits[3] = true;
							
							contact = true;
							
						}
						
						
							if(contact == true)
							{
								
					CoreClass.enemySemaphore.release();
					
					
							s.attacked(CoreClass.mainCharacter.getAttack(), hits);
							
							return;
							
							}
							
					}
		
					
		CoreClass.enemySemaphore.release();
		
		
		return;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
