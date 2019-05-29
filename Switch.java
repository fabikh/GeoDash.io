import java.awt.*;

/**
 * @(#)Switch.java
 *
 *
 * @author 
 * @version 1.00 2019/5/24
 */


public class Switch
{
	private boolean state;
	private static int size, range;
	private Color colour;
	private int x,y;
    public Switch(int x, int y)
    {
    	state = true;
    	size = 10;
    	colour = Color.red;
    	this.x = x -50;
    	this.y = y+15;
		range = 50;
    }
    public boolean getState ()
    {
    	return state;
    }
    public void setState (boolean st)
    {
    	state = st;
    	colour = Color.green;
    }
    public int getSize ()
    {
    	return size;
    }
    public Color getCol ()
    {
    	return colour;
    }
    public int getX (){return x;}
    public int getY () {return y;}
	public int getRange () {return range;}
}