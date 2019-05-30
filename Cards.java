import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//this class contains all the pages necessary (game, menu, settings)
public class Cards extends JFrame implements ActionListener{
    //cards displays pages using CardLayout
	JPanel cards, menu, gOver;
	//the game (extends JPanel)
    static TheGame game;
	//JButtons for menu
    JButton play, settings, credits;
	//new CardLayout
    CardLayout cardLayout;
	private ImageIcon ic;
    public Cards(){
        //formatting the JFrame
        setSize(800, 500);
        setTitle("card layout, wow!");
        setResizable(false);

        //creating JPanels
        menu = new JPanel();
        game = new TheGame(this);
		gOver = new JPanel();
        //creating buttons for menu
        play = new JButton("play!");
        settings = new JButton("settings");
        credits = new JButton("credits");
        //adding ActionListener
        play.addActionListener(this);
        settings.addActionListener(this);
        credits.addActionListener(this);
        //adding buttons
        menu.add(play);
        menu.add(settings);
        menu.add(credits);
		//sets up game over screen
		ic = new ImageIcon("GameOver.png");
		//gOver.add(ic); add ic to label
        //setting up cardLayout
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.add(menu, "menu");
        cards.add(game,"game");
		cards.add(gOver, "gOver");
        //adding cards
        getContentPane().add(cards);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        //pressing play takes the player to the game JPanel
        if(e.getSource() == play){
			 //limiting focus to only game
            cardLayout.show(cards, "game");
			unfocus();
			game.setFocusable(true);
        }
		//else if (e.getSource() == //idk)
		//{
			//cardLayout.show(cards, "gOver");
			//unfocus();
			//gOver.setFocusable(true);
		//}
    
    }
	private void unfocus ()
	{
		menu.setFocusable(false);
		play.setFocusable(false);
		settings.setFocusable(false);
		gOver.setFocusable(false);
		game.setFocusable(false);
	}
    public static void main(String[] args){
        //run
        Cards c = new Cards();
    }
}
