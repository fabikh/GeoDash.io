import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TheGame extends JPanel implements ActionListener
{
    private Door[] d;
	private Timer t = new Timer(70,this);
	private Player p;
	private Platform[] pf;
	private int nd = 2, npf = 5;
	private JLabel l;
	private int place = 0;
    public TheGame ( JFrame frame )
    {
		//sets JLabel
		l = new JLabel("");
		add(l);
		//sets array of doors
		d = new Door[nd];
		int x = 300;
		for (int i = 0; i < nd; i++ )
		{
			d[i] = new Door (x, 395);
			x += 150;
		}
		//sets player
		p = new Player(200,400);
		//sets array of platforms
		pf = new Platform[npf];
		x = 0;
		int y = 420;
		int length = 600;
		for (int i = 0; i<npf; i++)
		{
			pf[i]= new Platform(x, y, length);
			x += 100;
			y -= 75;
			length -= 75;
		}
		frame.setFocusable(true);
        frame.addKeyListener(new KeyListener()
        {
            public void keyTyped(KeyEvent e)
            {
				char key = e.getKeyChar();
				int close = findDoor();
                if (key == 'f')
                {
					l.setText("x: " + p.getX() + " vx: " + p.getVx() + " y: " + p.getY() + " vy: " + p.getVy());
					if (close <= d[place].getSwitch().getRange())
					{
						d[place] = open(d[place]);
						repaint();
					}
                }
				else if (key == 'a')
				{
					p.setVx(-10);
				} 
				else if (key == 'd') 
				{
					p.setVx(10);
				} 
				else if (key == 'w')
				{
					if(onPlatform())
					{
						p.setVy(-17);
					}			
				}
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });
    }
	
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
		//paint player
		g.setColor(Color.PINK);
		g.fillOval(p.getX(),p.getY(),p.getDiameter(),p.getDiameter());
		//paint platforms
		g.setColor(Color.BLACK);
		for (int i = 0; i < npf; i++)
		{
			g.fillRect(pf[i].getX(),pf[i].getY(),pf[i].getL(),pf[i].getH());
		}
		//paint doors
		for (int i = 0; i< nd; i++)
		{
			if (d[i].getSwitch().getState())
			{
				g.setColor(d[i].getCol());
				g.fillRect(d[i].getX(), d[i].getY(), d[i].getWid(), d[i].getLen());
			}
			g.setColor(d[i].getSwitch().getCol());
			g.fillOval(d[i].getSwitch().getX(), d[i].getSwitch().getY(), d[i].getSwitch().getSize(), d[i].getSwitch().getSize());
        }
		t.start();
    }
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == t)
		{
			int dis = findDoor();
			if (dis > p.getDiameter() || !d[place].getSwitch().getState())
			{
				p.move();
				repaint();
			}
        }
    }
	
    private Door open(Door dn)
    {
        dn.getSwitch().setState(false);
        return dn;
    }
	
	public boolean onPlatform()//this method checks if player is on platform (within 10px)
	{ 
        boolean onPlatform = false;
        int range = 10; //vertical range within which player is considered to be on the platform
        for(int i = 0; i < pf.length; i++)
		{ //for each platform
            //booleans for if player x and y are on platform or not
            boolean xMatch = false;
            boolean yMatch = false;
            //getting positions of player
            int playerY = p.getY() + p.getDiameter();
            int playerX = p.getX();
            //getting positions of platform
            int platX = pf[i].getX();
            int platY = pf[i].getY();
            //handling exception where playerX is past left edge but the player is not fully off platform
            if(playerX < platX && (playerX + p.getDiameter()) > platX) 
			{
                playerX += p.getDiameter();
            }
            //if playerY == platY (+- 10)
            if(playerY <= (platY + range) && playerY >= (platY - range))
			{
                yMatch = true;
            }
            //if playerX is between the left and right ends of the platform
            if(playerX >= platX && playerX <= (platX + pf[i].getL()))
			{
                xMatch = true;
            }
            //player is on platform for both x and y
            if(xMatch && yMatch)
			{
                onPlatform = true;
                //adjusting (in case y fell within the +- 10)
                p.setY(platY - p.getDiameter());
            }
        }
        return onPlatform;
    }
	
	private int findDoor ()
	{
		int px = p.getX();
		int closest = 800, dis;
		place = 0;
		for (int i = 0; i < nd; i ++)
		{
			dis = Math.abs(d[i].getX()-px);
			if (dis < closest)
			{
				closest = dis;
				place = i;
			}
		}
		return closest;
	}
}
