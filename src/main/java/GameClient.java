import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameClient extends JComponent {

    private int screenwidth;
    private int screenheight;
    private boolean stop;

    // 我方坦克
    private Tank playerTank;

    //敵方坦克
    //private List<Tank> enemyTanks = new ArrayList<>();

    //Walls
    //private List<Wall> walls = new ArrayList<>();

    //取代 敵方坦克 & Walls
    private List<GameObject> gameObjects = new ArrayList<>();

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

        //Thread 一進程式就call repaint()
        new Thread(() -> {
            while (!stop) {
                // 一直更新坦克圖形
                repaint();
                try {
                    // sleep 50 milesecone (每50毫秒更新一次)
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void init() {
        //Image icon;
        // 指定坦克圖形 by Direction
        // 中心點
        //playerTank = new Tank(getCenterPosX(50), getCenterPosY(50), Direction.DOWN);
        //icon = Tools.getImage("itankD.png");
        //playerTank = new Tank(500, 100, Direction.DOWN,icon );

        //
        Image[] brickImage = {Tools.getImage("brick.png")};
        Image[] iTankImage = new Image[8];
        Image[] eTankImage = new Image[8];

        String[] sub = {"U.png","D.png","L.png","R.png","LU.png","RU.png","LD.png","RD.png"};
        for (int i=0; i< iTankImage.length; i++){
            iTankImage[i] = Tools.getImage("iTank"+sub[i]);
            eTankImage[i] = Tools.getImage("eTank"+sub[i]);
        }

        // 我方坦克
        playerTank = new Tank(500, 100, Direction.DOWN,iTankImage );
        gameObjects.add(playerTank);

        // 敵方坦克數及圖形
        //icon = Tools.getImage("etankU.png");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                gameObjects.add(new Tank(350 + j * 80, 500 + i * 80, Direction.UP, true,eTankImage));
            }
        }

        // wall

        //icon = Tools.getImage("brick.png");
//        Wall[] walls = {
//                new Wall(250, 150, true, 15, icon),
//                new Wall(150, 200, false, 15, icon),
//                new Wall(800, 200, false, 15, icon),
//        };
//        this.walls.addAll(Arrays.asList(walls));
        gameObjects.add(new Wall(250, 150, true, 15, brickImage));
        gameObjects.add(new Wall(150, 200, false, 15, brickImage));
        gameObjects.add(new Wall(800, 200, false, 15, brickImage));

    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        //1. 指定圖形, 指定XY
        //g.drawImage(new ImageIcon("assets/images/itankD.png").getImage(),400,100,null);

        //2. 指定圖形, 螢幕中央點
        //g.drawImage(new ImageIcon("assets/images/itankD.png").getImage(),getCenterPosX(100),getCenterPosY(100),null);

        //3. payerTank.getImage 取坦克圖形
        //g.drawImage(playerTank.getImage(), playerTank.getX(), playerTank.getY(), null);

        //4. 使用 Tank 新增 draw 方法
        //playerTank.draw(g);

//        // 敵方坦克
//        for (Tank tank : enemyTanks) {
//            tank.draw(g);
//        }
//
//        // Walls
//        for (Wall wall : walls){
//            wall.draw(g);
//        }

        //取代 敵方坦克 & Walls
        for (GameObject object : gameObjects){
            object.draw(g);
        }
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
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = true;

                // 改用 dirs[]
                //playerTank.setDirection(Direction.UP);

                //playerTank.setY(playerTank.getY() - 5);

                // 改用 Tank.move method
                //playerTank.setY(playerTank.getY() - playerTank.getSpeed());
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = true;
                //playerTank.setDirection(Direction.DOWN);
                //playerTank.setY(playerTank.getY() + playerTank.getSpeed());
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = true;
                //playerTank.setDirection(Direction.LEFT);
                //playerTank.setX(playerTank.getX() - playerTank.getSpeed());
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = true;
                //playerTank.setDirection(Direction.RIGHT);
                //playerTank.setX(playerTank.getX() + playerTank.getSpeed());
                break;
            default:
                ;
        }

        //playerTank.move();

        // call paintComponent method
        //this.repaint();       --> 改用 Thread
    }

    public void keyRelease(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = false;

                // 改用 dirs[]
                //playerTank.setDirection(Direction.UP);

                //playerTank.setY(playerTank.getY() - 5);

                // 改用 Tank.move method
                //playerTank.setY(playerTank.getY() - playerTank.getSpeed());
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = false;
                //playerTank.setDirection(Direction.DOWN);
                //playerTank.setY(playerTank.getY() + playerTank.getSpeed());
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = false;
                //playerTank.setDirection(Direction.LEFT);
                //playerTank.setX(playerTank.getX() - playerTank.getSpeed());
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = false;
                //playerTank.setDirection(Direction.RIGHT);
                //playerTank.setX(playerTank.getX() + playerTank.getSpeed());
                break;
            default:
                ;
        }

        //playerTank.move();

        // call paintComponent method
        //this.repaint();       --> 改用 Thread
    }
}
