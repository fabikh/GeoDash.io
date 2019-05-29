import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Cards extends JFrame implements ActionListener{
    JPanel cards;
    JPanel menu;
    static Game game;
    JButton play, settings, credits;
    CardLayout cardLayout;
    public Cards(){
        setSize(800, 500);
        setTitle("card layout, wow!");
        setResizable(false);

        play = new JButton("play!");
        settings = new JButton("settings");
        credits = new JButton("credits");

        play.addActionListener(this);
        settings.addActionListener(this);
        credits.addActionListener(this);

        menu = new JPanel();
        menu.add(play);
        menu.add(settings);
        menu.add(credits);

        game = new Game();

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.add(menu, "menu");
        cards.add(game,"game");

        getContentPane().add(cards);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == play){
            cardLayout.show(cards, "game");
        }
        menu.setFocusable(false);
    }
    public static void main(String[] args){
        Cards c = new Cards();
        c.setVisible(true);
    }
}
