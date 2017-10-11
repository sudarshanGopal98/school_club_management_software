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
public class HeaderPanelForSubpanel extends JPanel implements Serializable{
    private JLabel topBar;
    private JButton close, min;

    public HeaderPanelForSubpanel(int frameWidth){
        setLayout(null);
        setSize(frameWidth, 25);
        setPreferredSize(new Dimension(frameWidth, 25));

        Font f1 = new Font("Cambria", Font.BOLD, 15);
        Font f2 = new Font("Cambria",Font.BOLD,7);


        topBar = new JLabel("Mu Alpha Theta Point Card System | Software by Sudarshan Gopalakrishnan");
        topBar.setSize(frameWidth, 25);
        topBar.setFont(f1);
        topBar.setHorizontalAlignment(SwingConstants.CENTER);
        topBar.setForeground(Color.WHITE);
        topBar.setBackground(Colors.purple1);
        topBar.setOpaque(true);


        min = new JButton("_");
        min.setBounds(frameWidth-25,0,25,25);
        min.setFont(f2);
        min.setIcon(new ImageIcon("images/minimize.png"));
        min.setMargin(new Insets(0, 0, 0, 0));
        min.setBackground(Color.BLACK);
        min.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {
                MainFile.MAIN_FRAME.setState(Frame.ICONIFIED);

            }

        });


        add(min);
        add(topBar);

        repaint();

    }

    public void modifyTopBarText(String s){
        topBar.setText(s);
    }

}
