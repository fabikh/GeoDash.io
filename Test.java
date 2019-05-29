/**
 * @(#)Test.java
 *
 *
 * @author 
 * @version 1.00 2019/5/24
 */
import javax.swing.*;
import java.awt.*;

public class Test
{
    public static TheGame g;
    public static void main (String[] args)
    {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        g = new TheGame( f );

        f.add(g);
        f.setSize(800, 500);

        f.setTitle("Door Test");
        f.setVisible(true);

    }
}