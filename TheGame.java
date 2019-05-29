import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TheGame extends JPanel implements ActionListener
{
    private Door[] d;
	private Timer t = new Timer(70,this);
	private Player p;
	private Platform pf;
	private int nd = 2;
	private JLabel l;
	private int place = 0;
    public TheGame ( JFrame frame )
    {
		l = new JLabel("");
		add(l);
		d = new Door[nd];
		int x = 300;
		for (int i = 0; i < nd; i++ )
		{
			d[i] = new Door (x, 395);
			x += 150;
		}
		p = new Player();
		pf = new Platform (100, 421, 400);
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
		g.fillRect(pf.getX(),pf.getY(),pf.getL(),pf.getH());
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
	
	public boolean onPlatform()
	{
		boolean onPlatform = false;
		int yPos = p.getY() + p.getDiameter();
		int xPos = p.getX();
        boolean xMatch = false;
        boolean yMatch = false;
	    if(yPos <= pf.getY() + 10 && yPos >= pf.getY() - 10)
		{
			yMatch = true;
		}
        if(xPos >= pf.getX() && xPos <= pf.getX() + pf.getL())
		{
	        xMatch = true;
        }
        return (xMatch && yMatch);
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
