package gui.misc;

import datatypes.Colors;
import process.MainFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * Created by Sudarshan on 5/19/2016.
 */
public class HeaderPanel extends JPanel implements Serializable{
    private JLabel topBar;
    private JButton close, min;

    public HeaderPanel(int frameWidth){
        setLayout(null);

        Font f1 = new Font("Cambria", Font.BOLD, 15);
        Font f2 = new Font("Cambria",Font.BOLD,7);


        topBar = new JLabel("Tompkins FBLA | Point Card System | Software by Sudarshan Gopalakrishnan");
        topBar.setSize(frameWidth, 25);
        topBar.setFont(f1);
        topBar.setHorizontalAlignment(SwingConstants.CENTER);
        topBar.setForeground(Color.WHITE);
        topBar.setBackground(Colors.purple1);
        topBar.setOpaque(true);

        min = new JButton("_");
        min.setBounds(frameWidth-60,0,25,25);
        min.setFont(f2);
        min.setIcon(new ImageIcon("images/minimize.png"));
        min.setMargin(new Insets(0, 0, 0, 0));
        min.setBackground(Color.BLACK);
        min.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {
                MainFile.MAIN_FRAME.setState(Frame.ICONIFIED);
            }

        });

        close = new JButton("X");
        close.setBounds(frameWidth-25,0,25,25);
        close.setFont(f2);
        close.setIcon(new ImageIcon("images/close.png"));
        close.setMargin(new Insets(0, 0, 0, 0));
        close.setBackground(Color.BLACK);
        close.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        add(close);
        add(min);
        add(topBar);
    }

    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
    }

}
