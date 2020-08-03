import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        //frame.add(new GameClient(1024,768));
        GameClient gameClient = new GameClient(1024,768);
        frame.add(gameClient);

        frame.setTitle("TankWar");
        frame.setVisible(true);

        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //gameClient.repaint();  //改用 Thread

        // 按鍵偵測
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //super.keyPressed(e);
                //System.out.println((char)e.getKeyCode());

                // 移動坦克
                gameClient.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //super.keyReleased(e);
                gameClient.keyRelease(e);
            }
        });




    }
}
