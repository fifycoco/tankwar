import java.awt.*;

public abstract class MoveObject extends GameObject {

    protected Direction direction;

    // 設定移動速度
    protected int speed;

    // 判斷我方或敵方坦克
    protected boolean enemy;

    public MoveObject(int x, int y, Direction direction, Image[] image) {
//        this.x = x;
//        this.y = y;
//        this.direction = direction;
//        speed = 5;
        //改為call 自己的建構式
        this(x, y, direction, false, image);
    }

    public MoveObject(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, image);
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.enemy = enemy;
        speed = 5;
    }

    public void draw(Graphics g) {
        if (!alive) {
            return;
        }
        move();
        collision();                //負責偵測坦克碰撞
        g.drawImage(image[direction.ordinal()], x, y, null);
    }



    // P.22 新增坦克move method
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

    // 負責偵測坦克碰撞
    public abstract boolean collision();

    public boolean collisionBound() {
        if (x < 0) {
            x = 0;
            return true;                // bound
        } else if (x > TankGame.gameClient.getScreenwidth() - width) {
            x = TankGame.gameClient.getScreenwidth() - width;
            return true;                // bound
        }
        if (y < 0) {
            y = 0;
            return true;                // bound
        } else if (y > TankGame.gameClient.getScreenheight() - height) {
            y = TankGame.gameClient.getScreenheight() - height;
            return true;                // bound
        }
        return false;
    }



    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
