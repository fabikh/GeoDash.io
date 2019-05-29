import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//this class contains all the pages necessary (game, menu, settings)
public class Cards extends JFrame implements ActionListener{
    //cards displays pages using CardLayout
	JPanel cards;
	//starting menu
    JPanel menu;
	//the game (extends JPanel)
    static Game game;
	//JButtons for menu
    JButton play, settings, credits;
	//new CardLayout
    CardLayout cardLayout;

    public Cards(){
        //formatting the JFrame
        setSize(800, 500);
        setTitle("card layout, wow!");
        setResizable(false);

        //creating JPanels
        menu = new JPanel();
        game = new Game();

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
		
        //setting up cardLayout
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.add(menu, "menu");
        cards.add(game,"game");

        //adding cards
        getContentPane().add(cards);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        //pressing play takes the player to the game JPanel
        if(e.getSource() == play){
            cardLayout.show(cards, "game");
        }
        //limiting focus to only game
        menu.setFocusable(false);
		play.setFocusable(false);
		settings.setFocusable(false);
		play.setFocusable(false);
		game.setFocusable(true);
    }

    public static void main(String[] args){
        //run
        Cards c = new Cards();
    }
}
