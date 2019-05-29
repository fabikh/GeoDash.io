public class Gun{
    private int x; //x position
    private int y; //y position
    private int dmg; //damage
    private int r; //range
    private int l = 12; //length
    private int w = 5; //width
	private boolean dir = true; //true if player is travelling right
	private int numBullets = 0; //number of bullets fired so far
	private int mag; //magazine size
    Bullet [] bullets; //contains bullets (bullets are assigned when fire() is called)

    Gun(int damage, int range, int magSize){
        dmg = damage;
        r = range;
		mag = magSize;
        bullets = new Bullet[mag];
    }

    //getters
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getL(){
        return l;
    }
    public int getW(){
        return w;
    }
    public int getNumBullets(){
        return numBullets;
    }
    public Bullet getBullet(int i){
        return bullets[i];
    }
    public boolean getDir(){
        return dir;
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

	public void fire(){
        //if not out of bullets
		if(numBullets < mag){
		    //new bullet created
			bullets[numBullets] = new Bullet(dir,r,x+l,y,dmg);
			//numBullets updated
			numBullets++;
		}	
	}
}
