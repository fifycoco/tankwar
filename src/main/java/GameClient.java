import javax.swing.*;
import java.awt.*;

public class GameClient extends JComponent {

    private int swidth;
    private int sheight;

    GameClient(){
        //this.setPreferredSize(new Dimension(800,600));
        this(800,600);
    }

    GameClient(int swidth, int sheight){
        this.swidth = swidth;
        this.sheight = sheight;
        this.setPreferredSize(new Dimension(swidth, sheight));
    }
}
