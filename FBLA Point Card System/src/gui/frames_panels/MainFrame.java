package gui.frames_panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by othscs001 on 5/20/2016.
 */
public class MainFrame extends JFrame{

    JPanel panelToDisplay;

    public MainFrame(){
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setUndecorated(true);
        setBackground(new Color(0, 255, 0, 0));

        setContentPane(new ContentPane());
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);



        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        /*setAlwaysOnTop(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ALT){
                    e.consume();
                    setState(Frame.NORMAL);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ALT){
                    e.consume();
                    setState(Frame.NORMAL);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ALT){
                    e.consume();
                    setState(Frame.NORMAL);
                }
            }
        });*/

        initPanelToDisplay(new HomePanel());
        setVisible(true);
    }

    public void initPanelToDisplay(JPanel newPanel){
        if(panelToDisplay != null) {
            panelToDisplay.removeAll();
            remove(panelToDisplay);
        }

        panelToDisplay = newPanel;
        panelToDisplay.setSize(newPanel.getWidth(), newPanel.getHeight());
        panelToDisplay.setLocation(getWidth()/2 - panelToDisplay.getWidth()/2, getHeight()/2 - panelToDisplay.getHeight()/2);
        add(panelToDisplay);
        repaint();
    }

    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
    }
}

class ContentPane extends JPanel {

    private static final AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC, 0.7f);

    public ContentPane() {

        setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {

        // Allow super to paint
        super.paintComponent(g);

        // Apply our own painting effect
        Graphics2D g2d = (Graphics2D) g.create();
        // 50% transparent Alpha
        g2d.setComposite(alphaComposite);

        g2d.setColor(getBackground());
        g2d.fill(getBounds());

        g2d.dispose();

    }

}