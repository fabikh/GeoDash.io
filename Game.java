import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Game extends JPanel implements ActionListener, KeyListener{
    Timer t = new Timer(70,this);
    Player p;
	static Platform [] plats;
    JLabel l;
    static int ax = 1;
    static int ay = 2;
    public Game() {
		p = new Player();
		plats = new Platform[3];
		plats[0] = new Platform(0,460,800);
		plats[1] = new Platform(50,420,200);
        plats[2] = new Platform(130,340,350);
        setFocusable(true);
		addKeyListener(this);
		l = new JLabel("");
		add(l);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillOval(p.getX(),p.getY(),p.getDiameter(),p.getDiameter());
		for(int i = 0; i < plats.length; i++){
			g.fillRect(plats[i].getX(),plats[i].getY(),plats[i].getL(),plats[i].getH());
		}
		g.fillRect(p.gun.x,p.gun.y,p.gun.l,p.gun.w);
        t.start();
    }
	public boolean onPlatform(){
		boolean onPlatform = false;
		for(int i = 0; i < plats.length; i++){
			int yPos = p.getY() + p.getDiameter();
			int xPos = p.getX();
            boolean xMatch = false;
            boolean yMatch = false;
			if(yPos <= plats[i].getY() + 10 && yPos >= plats[i].getY() - 10){
				yMatch = true;
			}
            if(xPos >= plats[i].getX() && xPos <= plats[i].getX() + plats[i].getL()){
			    xMatch = true;
            }
            if(xMatch && yMatch){
                onPlatform = true;
                p.setY(plats[i].getY() - p.getDiameter());
            }
		}
		return onPlatform;
	}
    public void actionPerformed(ActionEvent e){
		if(e.getSource() == t) {
            p.move();    
			repaint();
			l.setText("x: " + p.getX() + " vx: " + p.getVx() + " y: " + p.getY() + " vy: " + p.getVy());
        }
    }
    public void keyReleased(KeyEvent k) {
    }
    public void keyPressed (KeyEvent k){
        int key = k.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            p.setVx(-10);
        } else if (key == KeyEvent.VK_RIGHT) {
            p.setVx(10);
        } else if (key == KeyEvent.VK_UP) {
			if(onPlatform()){
				p.setVy(-17);
			}			
        }
    }
    public void keyTyped (KeyEvent k){
    }
}
