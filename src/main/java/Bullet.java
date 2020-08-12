import java.awt.*;
import java.util.List;

public class Bullet extends Tank {

    // ??
    //private Direction direction;

    // ?? 設定移動速度
    //private int speed;

    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
        speed = 15;

    }


    @Override
    public void draw(Graphics g) {
        move();
        collision();
        g.drawImage(image[direction.ordinal()], x, y, null);
    }


    // 新增子彈move method
    public void move() {
        oldx = x;
        oldy = y;

        switch (direction) {
            case UP:
                y = y - speed;
                break;
            case DOWN:
                y = y + speed;
                break;
            case LEFT:
                x = x - speed;
                break;
            case RIGHT:
                x = x + speed;
                break;
            case UP_LEFT:
                y = y - speed;
                x = x - speed;
                break;
            case UP_RIGHT:
                y = y - speed;
                x = x + speed;
                break;
            case DOWN_LEFT:
                y = y + speed;
                x = x - speed;
                break;
            case DOWN_RIGHT:
                y = y + speed;
                x = x + speed;
                break;
            default:
        }
    }

    // 負責偵測子彈碰撞
    public void collision() {

        // 2020.08.06 邊界測試
        if (x < 0) {
            x = 0;
        } else if (x > TankGame.gameClient.getScreenwidth() - width) {
            x = TankGame.gameClient.getScreenwidth() - width;
        }

        if (y < 0) {
            y = 0;
        } else if (y > TankGame.gameClient.getScreenheight() - height) {
            y = TankGame.gameClient.getScreenheight() - height;
        }


//        for (GameObject object : TankGame.gameClient.getGameObjects()) {
//            // 非本身物件
//            if (object != this) {
//                // 有碰撞到
//                if (object.getRectangle().intersects(this.getRectangle())) {
//                    x = oldx;
//                    y = oldy;
//                    return;
//                }
//            }
//        }

        List<GameObject> objects  = TankGame.gameClient.getGameObjects();
        for (GameObject object : objects) {
            // 碰到自己物件
            if (object == this) {
                continue;
            }
            // 碰到坦克
            // object 向下轉型Tank, 取得需要的enemy屬性
            if (object instanceof Tank){
                if (((Tank) object).enemy == enemy) {
                    continue;
                }
            }
            if (getRectangle().intersects(object.getRectangle())){
                x=oldx;
                y= oldy;
                return;
            }
        }


    }


//    public boolean[] getDirs() {
//        return dirs;
//    }
//
//    public void setDirs(boolean[] dirs) {
//        this.dirs = dirs;
//    }
//
//    public int getSpeed() {
//        return speed;
//    }
//
//    public void setSpeed(int speed) {
//        this.speed = speed;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public void setX(int x) {
//        this.x = x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }
//
//    public Direction getDirection() {
//        return direction;
//    }
//
//    public void setDirection(Direction direction) {
//        this.direction = direction;
//    }
}
