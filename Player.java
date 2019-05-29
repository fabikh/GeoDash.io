import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Player {
	private int x = 200;
    private int y = 400;
    private int vx = 0;
    private int vy = 0;
    private static int diameter = 20;
    boolean dirRight;
    Gun gun;
	public Player(){
	    if(vx >= 0){
	        dirRight = true;
        }
        gun = new Gun(20, 100,5);
	}
	public int getX(){
	    return x;
    }
    public int getY(){
	    return y;
    }
    public int getVx(){
        return vx;
    }
    public int getVy(){
        return vy;
    }
	public int getDiameter(){return diameter;}
    public void setX(int xPos){
        x = xPos;
    }
    public void setY(int yPos){
        y = yPos;
    }
    public void setVx(int xVel){
        vx = xVel;
    }
    public void setVy(int yVel){
        vy = yVel;
    }
	public void move(){
		//for x
        if(x < 20 && vx < 0){
            vx *= -1;
        }
        if (x > 780 && vx > 0){
            vx *= -1;
        }
        x += vx;

        if(vx > 0){
            if(Test.g.onPlatform()){
                vx -= Game.ax;
            }
            dirRight = true;
        } else if (vx < 0){
            if(Test.g.onPlatform()){
                vx += Game.ax;
            }
            dirRight = false;
        }
		//for y
        if(Test.g.onPlatform() && vy > 0){
            vy = 0;
        }
        y += vy;
        vy += Game.ay;
        moveGun();
	}
    public void moveGun(){
        if(dirRight){
            gun.x = x + diameter;
        } else {
            gun.x = x - gun.l + 1;
        }
        gun.y = y + diameter/2;
    }
}