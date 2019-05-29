public class Bullet{
    private boolean dir; //true if bullet is moving right
    private int x; //x position
    private int y; //y position
	private int vel; //x velocity (bullets travel at point blank so no y velocity)
    private int dmg; //damage dealt by bullet
	private int distance = 0; //distance bullet has travelled
	private int r = 120; //max range bullet can travel
	static int diameter = 5;
	private boolean inRange = true; //drawing does not occur if bullet out of range

    Bullet(boolean direction, int range, int xPos, int yPos, int damage){
        dir = direction;
        x = xPos;
        y = yPos;
        r = range;
        dmg = damage;
		vel = direction ? 15 : -15;
    }

    //getters
	public boolean getDir(){
		return dir;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getVel(){
		return vel;
	}
	public boolean getInRange(){return inRange;}
	public int getDmg(){
		return dmg;
	}

	//setters
	public void setDir(boolean direction){
		dir = direction;
	}
	public void setX(int xPos){
		x = xPos;
	}
	public void setY(int yPos){
		y = yPos;
	}

	public void move(){
		//if bullet has not travelled past its range, x is incremented and distance is updated
		if(distance < r){
			x += vel;
			distance += Math.abs(vel);
		} else {
			inRange = false; //used for drawing
		}
	}
}
