import javax.swing.*;
import java.awt.*;

public class Tank extends GameObject {

    private Direction direction;

    // 設定移動速度
    private int speed;

    // 上下左右四個方向 & create getdirs()
    private boolean[] dirs = new boolean[4];

    // 判斷我方或敵方坦克
    private boolean enemy;

    public Tank(int x, int y, Direction direction, Image[] image) {
//        this.x = x;
//        this.y = y;
//        this.direction = direction;
//        speed = 5;
        //改為call 自己的建構式
        this(x,y,direction,false,image);
    }

    public Tank(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x,y,image);
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.enemy = enemy;
        speed = 5;
    }

    public void draw(Graphics g){
        if (!isStop()) {
            determineDirection();
            move();
        }
        // 取代 getImage()
        g.drawImage(image[direction.ordinal()],x,y,null);
    }

    public boolean isStop(){
        for (int i =0; i < dirs.length; i++){
            // 只要有一個按鍵方向為true, 就不停
            if (dirs[i]) {
                return false;
            }
        }
        return true;
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

    private void determineDirection(){
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

//    public Image getImage(){
//        String name = enemy ? "etank" : "itank";
//        if (direction==Direction.UP){
//            return new ImageIcon("assets/images/"+name+"U.png").getImage();
//        }if (direction==Direction.DOWN){
//            return new ImageIcon("assets/images/"+name+"D.png").getImage();
//        }if (direction==Direction.LEFT){
//            return new ImageIcon("assets/images/"+name+"L.png").getImage();
//        }if (direction==Direction.RIGHT){
//            return new ImageIcon("assets/images/"+name+"R.png").getImage();
//        }if (direction==Direction.UP_RIGHT){
//            return new ImageIcon("assets/images/"+name+"RU.png").getImage();
//        }if (direction==Direction.UP_LEFT){
//            return new ImageIcon("assets/images/"+name+"LU.png").getImage();
//        }if (direction==Direction.DOWN_RIGHT){
//            return new ImageIcon("assets/images/"+name+"RD.png").getImage();
//        }if (direction==Direction.DOWN_LEFT){
//            return new ImageIcon("assets/images/"+name+"LD.png").getImage();
//        }
//        return null;
//    }

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
