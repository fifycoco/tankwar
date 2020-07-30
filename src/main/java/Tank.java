import javax.swing.*;
import java.awt.*;

public class Tank {
    private int x;
    private int y;
    private Direction direction;

    // 設定移動速度
    private int speed;

    // 上下左右四個方向
    private boolean[] dirs = new boolean[4];

    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        speed = 5;
    }


    // P.22 新增坦克move method
    public void move(){
        switch (direction){
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

    public Image getImage(){
        if (direction==Direction.UP){
            return new ImageIcon("assets/images/itankU.png").getImage();
        }if (direction==Direction.DOWN){
            return new ImageIcon("assets/images/itankD.png").getImage();
        }if (direction==Direction.LEFT){
            return new ImageIcon("assets/images/itankL.png").getImage();
        }if (direction==Direction.RIGHT){
            return new ImageIcon("assets/images/itankR.png").getImage();
        }if (direction==Direction.UP_RIGHT){
            return new ImageIcon("assets/images/itankRU.png").getImage();
        }if (direction==Direction.UP_LEFT){
            return new ImageIcon("assets/images/itankLU.png").getImage();
        }if (direction==Direction.DOWN_RIGHT){
            return new ImageIcon("assets/images/itankRD.png").getImage();
        }if (direction==Direction.DOWN_LEFT){
            return new ImageIcon("assets/images/itankLD.png").getImage();
        }
        return null;
    }

    public boolean[] getDirs() {
        return dirs;
    }

    public void setDirs(boolean[] dirs) {
        this.dirs = dirs;
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
