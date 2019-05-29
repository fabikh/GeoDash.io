import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Game extends JPanel implements ActionListener, KeyListener{
    private Timer t = new Timer(70,this); //animation timer
    private Player p;
    private int numPlats = 3;
    private Platform [] plats; //all platforms in game
    private JLabel l;
    private final static int ax = 1; //horizontal acceleration due to friction (scalar)
    private final static int ay = 2; //vertical acceleration due to gravity (scalar)
    public Game() {
        t = new Timer(16,this); //redraws every 70ms
        p = new Player(200,400);
        plats = new Platform[numPlats];
        plats[0] = new Platform(0,460,800);
        plats[1] = new Platform(50,420,200);
        plats[2] = new Platform(130,380,350);
        addKeyListener(this); //keyListener is used to get input for moving and shooting
        l = new JLabel(""); //currently used for testing purposes
        add(l);
    }

    //getters
    public static int getAx(){
        return ax;
    }
    public static int getAy(){
        return ay;
    }

    public void paintComponent(Graphics g){ //override
        super.paintComponent(g);
        //drawing player
        g.fillOval(p.getX(),p.getY(),p.getDiameter(),p.getDiameter());
        //drawing platforms
        for(int i = 0; i < plats.length; i++){
            //draws a rectangle at (x,y) of length l and height h
            g.fillRect(plats[i].getX(),plats[i].getY(),plats[i].getL(),plats[i].getH());
        }
        Gun tempGun = p.getGun(); //temporary gun
        //drawing bullets
        for(int i = 0; i < p.getGun().getNumBullets(); i++){
            if(p.getGun().bullets[i].getInRange()){
                Bullet bullet = tempGun.getBullet(i);
                g.fillOval(bullet.getX(),bullet.getY(),Bullet.diameter,Bullet.diameter);
            }
        }
        //drawing gun
        g.fillRect(tempGun.getX(),tempGun.getY(),tempGun.getL(),tempGun.getW());
        //animation timer started
        t.start();
    }

    public boolean onPlatform(){ //this methods checks if player is on platform (within 10px)
        boolean onPlatform = false;
        int range = 10; //vertical range within which player is considered to be on the platform
        for(int i = 0; i < plats.length; i++){ //for each platform
            //booleans for if player x and y are on platform or not
            boolean xMatch = false;
            boolean yMatch = false;
            //getting positions of player
            int playerY = p.getY() + p.getDiameter();
            int playerX = p.getX();
            //getting positions of platform
            int platX = plats[i].getX();
            int platY = plats[i].getY();
            //handling exception where playerX is past left edge but the player is not fully off platform
            if(playerX < platX && (playerX + p.getDiameter()) > platX) {
                playerX += p.getDiameter();
            }
            //if playerY == platY (+- 10)
            if(playerY <= (platY + range) && playerY >= (platY - range)){
                yMatch = true;
            }
            //if playerX is between the left and right ends of the platform
            if(playerX >= platX && playerX <= (platX + plats[i].getL())){
                xMatch = true;
            }
            //player is on platform for both x and y
            if(xMatch && yMatch){
                onPlatform = true;
                //adjusting (in case y fell within the +- 10)
                p.setY(platY - p.getDiameter());
            }
        }
        return onPlatform;
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == t) { //if the animation timer was the trigger
            p.move(); //makes player move based on vx, vy, ax and ay
            //each of the fired bullets is moved
            for(int i = 0; i < p.getGun().getNumBullets(); i++){
                p.getGun().bullets[i].move();
            }
            //graphics are updated
            repaint();
            l.setText("x: " + p.getX() + " vx: " + p.getVx() + " y: " + p.getY() + " vy: " + p.getVy());
        }
    }

    public void keyReleased(KeyEvent k) {
    }
    public void keyPressed (KeyEvent k){
        int key = k.getKeyCode();
        //set vx to 10 left
        if (key == KeyEvent.VK_LEFT) {
            p.setVx(-10);
        } //set vx to 10 right
        else if (key == KeyEvent.VK_RIGHT) {
            p.setVx(10);
        } //set vx to 11 up IF player is on a platform
        else if (key == KeyEvent.VK_UP) {
            if(onPlatform()){
                p.setVy(-11);
            }
        } //creates new bullet at current location if space is pressed
        else if(key == KeyEvent.VK_SPACE){
            p.getGun().fire();
        }
    }
    public void keyTyped (KeyEvent k){
    }
}
