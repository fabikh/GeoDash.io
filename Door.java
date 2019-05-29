import java.awt.*;

public class Door
{
	private static int length, width;
	private Color c;
    private Switch s;
    private int x,y;
	public Door(int x, int y)
	{
		length = 25;
		width = 5;
		c = Color.cyan;
		s = new Switch(x, y);
		this.x = x;
		this.y = y;
	}
	public int getLen ()
	{
		return length;
	}
	public int getWid ()
	{
		return width;
	}
	public Color getCol ()
	{
		return c;
	}
	public Switch getSwitch()
	{
		return s;
	}
	public int getX () {return x; }
	public int getY () { return y;}
}