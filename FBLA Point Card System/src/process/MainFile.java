package process;

import gui.frames_panels.MainFrame;

/**
 * Created by Sudarshan on 5/19/2016.
 */
public class MainFile {
    public static MainFrame MAIN_FRAME;
    public static final DataManager DM = new DataManager();

    public static void main(String[] args){
        MAIN_FRAME = new MainFrame();
        new Thread(new BackupProcess()).start();
    }
}
