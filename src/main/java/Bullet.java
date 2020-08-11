import java.awt.*;
import java.util.List;

public class Bullet extends Tank {
    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
        speed = 10;
    }

    @Override
    public void draw(Graphics g) {
        if (!alive) {
            return;
        }
        move();
        collision();                //負責偵測坦克碰撞
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    // 負責偵測坦克碰撞
    public void collision() {

        // 2020.08.06 邊界測試
//        if (x < 0) {
//            x = 0;
//        } else if (x > TankGame.gameClient.getScreenwidth() - width) {
//            x = TankGame.gameClient.getScreenwidth() - width;
//        }
//        if (y < 0) {
//            y = 0;
//        } else if (y > TankGame.gameClient.getScreenheight() - height) {
//            y = TankGame.gameClient.getScreenheight() - height;
//        }
        // collsionBound = true 有撞到wall
        if (collisionBound()) {
            alive = false;          //子彈消失
            return;
        }

        List<GameObject> objects = TankGame.gameClient.getGameObjects();
        for (GameObject object : objects) {
            if (object == this) {
                continue;
            }
            // 偵測我方坦克
            if (object instanceof Tank) {
                if (((Tank) object).enemy == enemy) {
                    continue;           // 我方坦克ignore
                }
            }
            // 有碰撞到
            if (object.getRectangle().intersects(this.getRectangle())) {
                alive = false;          //子彈消失
                // 碰撞到敵方坦克消失
                if (object instanceof Tank) {
                    object.alive = false;
                }
                return;
            }
        }


    }
}
