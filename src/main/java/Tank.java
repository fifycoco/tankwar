import javax.swing.*;
import java.awt.*;

public class Tank extends MoveObject {

     // 上下左右四個方向 & create getdirs()
    protected boolean[] dirs = new boolean[4];

    public Tank(int x, int y, Direction direction, Image[] image) {
//        this.x = x;
//        this.y = y;
//        this.direction = direction;
//        speed = 5;
        //改為call 自己的建構式
        this(x, y, direction, false, image);
    }

    public Tank(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y,direction,enemy, image);
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.enemy = enemy;
        speed = 5;
    }

    public void draw(Graphics g) {
        if (!isStop()) {
            determineDirection();
            move();
            collision();                //負責偵測坦克碰撞
        }
        // 取代 getImage()
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    public boolean isStop() {
        for (int i = 0; i < dirs.length; i++) {
            // 只要有一個按鍵方向為true, 就不停
            if (dirs[i]) {
                return false;
            }
        }
        return true;
    }

    //
    public void fire() {
        Bullet bullet = new Bullet(x + width / 2 - GameClient.bulletImage[0].getWidth(null) / 2,
                y + height / 2 - GameClient.bulletImage[0].getHeight(null) / 2,
                direction, enemy, GameClient.bulletImage);
        TankGame.gameClient.addGameObject(bullet);
    }

    public void superFire(){
        // 取八個方向發射子彈
        for (Direction direction: Direction.values()){
            Bullet bullet = new Bullet(x + width / 2 - GameClient.bulletImage[0].getWidth(null) / 2,
                    y + height / 2 - GameClient.bulletImage[0].getHeight(null) / 2,
                    direction, enemy, GameClient.bulletImage);
            bullet.setSpeed(15);
            TankGame.gameClient.addGameObject(bullet);
        }
    }

    // 負責偵測坦克碰撞
    public boolean collision() {

        // 2020.08.06 邊界測試
//        if (x < 0) {
//            x = 0;
//        } else if (x > TankGame.gameClient.getScreenwidth() - width) {
//            x = TankGame.gameClient.getScreenwidth() - width;
//        }
//
//        if (y < 0) {
//            y = 0;
//        } else if (y > TankGame.gameClient.getScreenheight() - height) {
//            y = TankGame.gameClient.getScreenheight() - height;
//        }
        // 撞 wall : true
        if (collisionBound()){
            return true;
        }

        // 撞 object : true
        for (GameObject object : TankGame.gameClient.getGameObjects()) {
            // 非本身物件
            if (object != this) {
                // 有碰撞到
                if (object.getRectangle().intersects(this.getRectangle())) {
                    x = oldx;
                    y = oldy;
                    return true;
                }
            }
        }
        return false;
    }

    private void determineDirection() {
        //0.上 1.下 2.左 3.右
        if (dirs[0] && dirs[2] && !dirs[1] && !dirs[3]) direction = Direction.UP_LEFT;
        else if (dirs[0] && dirs[3] && !dirs[2] && !dirs[1]) direction = Direction.UP_RIGHT;
        else if (dirs[1] && dirs[2] && !dirs[0] && !dirs[3]) direction = Direction.DOWN_LEFT;
        else if (dirs[1] && dirs[3] && !dirs[0] && !dirs[2]) direction = Direction.DOWN_RIGHT;
        else if (dirs[0] && !dirs[3] && !dirs[1] && !dirs[2]) direction = Direction.UP;
        else if (dirs[1] && !dirs[3] && !dirs[0] && !dirs[2]) direction = Direction.DOWN;
        else if (dirs[2] && !dirs[3] && !dirs[0] && !dirs[1]) direction = Direction.LEFT;
        else if (dirs[3] && !dirs[1] && !dirs[0] && !dirs[2]) direction = Direction.RIGHT;
    }


    public boolean[] getDirs() {
        return dirs;
    }

    public void setDirs(boolean[] dirs) {
        this.dirs = dirs;
    }

}
