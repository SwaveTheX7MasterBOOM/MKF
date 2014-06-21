package logicalGameObjects;

import java.awt.Font;
import java.awt.Image;

import java.awt.Rectangle;

public interface DrawableObjects
{

	Font getFont();

String getImageEffect();

Image getPic();

int getxPos();

int getyPos();

void setImageEffect(String string);

Rectangle getHitbox();

int getWidth();

int getHeight();




	
	
}
