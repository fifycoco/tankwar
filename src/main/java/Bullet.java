import java.awt.*;
import java.util.List;

public class Bullet extends MoveObject {

    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
        speed = 10;
    }

//    @Override
//    public void draw(Graphics g) {
//        if (!alive) {
//            return;
//        }
//        move();
//        collision();                //負責偵測坦克碰撞
//        g.drawImage(image[direction.ordinal()], x, y, null);
//    }

    // 負責偵測坦克碰撞
    public void collision() {
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
