import java.awt.*;
import javax.swing.*;
public class Platform {
	private int x, y, l, h;
	Platform(int xPos,int yPos,int length){
		x = xPos;
		y = yPos;
		l = length;
		h = 10;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getL(){
		return l;
	}
	public int getH(){
		return h;
	}
}