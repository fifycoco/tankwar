import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameClient extends JComponent {

    private int screenwidth;
    private int screenheight;

    private Tank playerTank;

    GameClient() {
        //this.setPreferredSize(new Dimension(800,600));
        this(800, 600);
    }

    GameClient(int screenwidth, int screenheight) {
        this.screenwidth = screenwidth;
        this.screenheight = screenheight;
        this.setPreferredSize(new Dimension(screenwidth, screenheight));
        // initial
        init();
    }

    public void init() {
        // 指定坦克圖形 by Direction
        playerTank = new Tank(getCenterPosX(50), getCenterPosY(50), Direction.DOWN);

    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        // 指定圖形, 指定XY
        //g.drawImage(new ImageIcon("assets/images/itankD.png").getImage(),400,100,null);

        // 指定圖形, 螢幕中央點
        //g.drawImage(new ImageIcon("assets/images/itankD.png").getImage(),getCenterPosX(100),getCenterPosY(100),null);

        // payerTank.getImage 取坦克圖形
        g.drawImage(playerTank.getImage(), playerTank.getX(), playerTank.getY(), null);


    }

    // X bar 中點
    private int getCenterPosX(int width) {
        return (screenwidth - width) / 2;
    }

    // Y bar 中點
    private int getCenterPosY(int height) {
        return (screenheight - height) / 2;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                playerTank.setDirection(Direction.UP);
                playerTank.setY(playerTank.getY() - 5);
                break;
            case KeyEvent.VK_DOWN:
                playerTank.setDirection(Direction.DOWN);
                playerTank.setY(playerTank.getY() + 5);
                break;
            case KeyEvent.VK_LEFT:
                playerTank.setDirection(Direction.LEFT);
                playerTank.setX(playerTank.getX() - 5);
                break;
            case KeyEvent.VK_RIGHT:
                playerTank.setDirection(Direction.RIGHT);
                playerTank.setX(playerTank.getX() + 5);
                break;
            default:;
        }
        // call paintComponent method
        this.repaint();
    }
}
