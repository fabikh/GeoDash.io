public class Player {
	private int x; //x position
    private int y; //y position
    private int vx = 0; //x velocity
    private int vy = 0; //y velocity
    private static int diameter = 20;
    boolean dirRight = true; //is true if player is going left
    private Gun gun;

	Player(int xPos,int yPos){
	    x = xPos;
	    y = yPos;
        gun = new Gun(20, 200,10);
	}

	//getters
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
    public int getDiameter(){
        return diameter;
    }
    public Gun getGun(){
        return gun;
    }

    //setters
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
		//FOR X
        //x direction is reversed if player is moving towards a wall
        if(x < 20 && vx < 0){
            vx *= -1;
        }
        if (x > 780 && vx > 0){
            vx *= -1;
        }
        //x is moved by vx
        x += vx;
        //if player is travelling left
        if(vx > 0){
            //if player is on a platform, friction takes effect
            if(Cards.game.onPlatform()){
                vx -= Game.getAx(); //slowed down
            }
            //updating direction booleans
            dirRight = true;
			gun.setDir(true);
        }//if player is travelling right
        else if (vx < 0){
            //if player is on a platform, friction takes effect
            if(Cards.game.onPlatform()){
                vx += Game.getAx(); //slowed down
            }
            //updating direction booleans
            dirRight = false;
            gun.setDir(false);
        }

		//FOR Y
        //if player is on platform and would move down, do not move down
        if(Cards.game.onPlatform() && vy > 0){
            vy = 0;
        }
        //y is moved by vy
        y += vy;
        //vy is always incremented by vertical acceleration
        vy += Game.getAy();

        //gun is moved along with player
        moveGun();
	}
    public void moveGun(){
	    //setting x (direction of gun affects drawing coordinates)
        if(dirRight){
            gun.setX(x + diameter);
        } else {
            gun.setX(x - gun.getL() + 1);
        }
        //setting y
        gun.setY(y + diameter/2);
    }
}