import javax.swing.*;

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

        gameClient.repaint();

    }
}
